package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon$17835 {
    private static final long INFINITE = Long.MAX_VALUE;

    private static List<Edge>[] edges;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 도시의 수
        int m = Integer.parseInt(st.nextToken()); // 도로의 수
        int k = Integer.parseInt(st.nextToken()); // 면접장의 수

        edges = new List[n + 1]; // 인덱스를 편하기 쓰기 위해 +1
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()); // 첫 번째 도시
            int v = Integer.parseInt(st.nextToken()); // 두 번째 도시
            int c = Integer.parseInt(st.nextToken()); // 도로의 길이

            // 반대 방향의 간선을 추가
            edges[v].add(new Edge(u, c));
        }

        long[] distance = new long[n + 1]; // 각 도시별로 면접장까지의 거리를 저장, 최대 100억이기 때문에 long으로
        Arrays.fill(distance, INFINITE);

        PriorityQueue<Edge> nextNodes = new PriorityQueue<>(Comparator.comparingLong(a -> a.weight));
        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int interviewRoom = Integer.parseInt(st.nextToken());
            distance[interviewRoom] = 0; // 면접장에서 시작
            nextNodes.offer(new Edge(interviewRoom, 0));
        }

        while (!nextNodes.isEmpty()) {
            Edge curr = nextNodes.poll();
            int next = curr.to;
            long weight = curr.weight;

            // 더 짧은 경로가 이미 발견된 경우
            if (distance[next] < weight) continue;

            for (Edge edge : edges[next]) {
                if (distance[edge.to] > distance[next] + edge.weight) {
                    distance[edge.to] = distance[next] + edge.weight;
                    nextNodes.offer(new Edge(edge.to, distance[edge.to]));
                }
            }
        }

        // 가장 먼 도시를 찾아서 출력
        int city = 1;
        long maxDistance = distance[1];
        for (int i = 2; i <= n; i++) {
            if (maxDistance < distance[i]) {
                city = i;
                maxDistance = distance[i];
            }
        }
        System.out.println(city);
        System.out.println(maxDistance);
    }

    private static class Edge {
        int to;
        long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}