import java.io.*;
import java.util.*;

public class Main {

    private static final Map<Integer, Set<Vertex>> graph = new HashMap<>();
    private static final PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(vertex -> vertex.weight));

    private static int N;

    private static int[] shortestPath;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            graph.put(i, new HashSet<>());
        }

        shortestPath = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph.get(vertex1).add(new Vertex(vertex2, weight));
            graph.get(vertex2).add(new Vertex(vertex1, weight));
        }

        // 1. 첫 다익스트라를 수행함으로써 최단 경로를 찾음과 동시에 역방향 그래프를 만든다.
        int minWeight = firstDijkstra();

        int maxWeight = 0;

        boolean flag = false;

        int currentVertex = N;

        // 2. 최단 경로의 edge를 하나씩 끊은 후 최단 거리를 계산한다.
        //    1은 시작점이므로 currentVertex가 1인 경우, 반복문 종료
        while (currentVertex != 1) {
            int nextVertex = shortestPath[currentVertex];

            // currentVertex에서 nextVertex의 경로를 끊음
            int weight = secondDijkstra(currentVertex, nextVertex);

            // 경로를 끊었을 때, 1 -> N 이동이 불가능한 경우
            if (weight == Integer.MAX_VALUE) {
                flag = true;
                break;
            }

            // 가장 큰 가중치 저장
            maxWeight = Math.max(maxWeight, weight);

            currentVertex = nextVertex;
        }

        // 1 -> N으로 갈 수 없는 경우 -1 출력
        bw.write(Integer.toString(flag ? -1 : maxWeight - minWeight));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int firstDijkstra() {
        int[] visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        visited[1] = 0;

        pq.offer(new Vertex(1, 0));

        while (!pq.isEmpty()) {
            Vertex currentVertex = pq.poll();

            if (visited[currentVertex.number] < currentVertex.weight) {
                continue;
            }

            for (Vertex nextVertex : graph.get(currentVertex.number)) {
                int nextWeight = currentVertex.weight + nextVertex.weight;

                if (nextWeight >= visited[nextVertex.number]) {
                    continue;
                }

                shortestPath[nextVertex.number] = currentVertex.number;

                visited[nextVertex.number] = nextWeight;

                pq.offer(new Vertex(nextVertex.number, nextWeight));
            }
        }

        return visited[N];
    }

    private static int secondDijkstra(int from, int to) {
        int[] visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            visited[i] = Integer.MAX_VALUE;
        }

        visited[1] = 0;

        pq.offer(new Vertex(1, 0));

        while (!pq.isEmpty()) {
            Vertex currentVertex = pq.poll();

            if (visited[currentVertex.number] < currentVertex.weight) {
                continue;
            }

            for (Vertex nextVertex : graph.get(currentVertex.number)) {
                if ((currentVertex.number == from && nextVertex.number == to) || (currentVertex.number == to && nextVertex.number == from)) {
                    continue;
                }

                int nextWeight = currentVertex.weight + nextVertex.weight;

                if (nextWeight >= visited[nextVertex.number]) {
                    continue;
                }

                visited[nextVertex.number] = nextWeight;

                pq.offer(new Vertex(nextVertex.number, nextWeight));
            }
        }

        return visited[N];
    }

    private static class Vertex {

        final int number;
        final int weight;

        Vertex(int number, int weight) {
            this.number = number;
            this.weight = weight;
        }
    }
}
