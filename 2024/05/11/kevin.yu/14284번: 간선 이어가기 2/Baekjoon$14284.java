package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon$14284 {
    private static final int INFINITE = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // 정점의 개수
        int m = Integer.parseInt(st.nextToken()); // 간선리스트의 간선 수

        List<Edge>[] edges = new List[n + 1]; // 인덱스를 편하기 쓰기 위해 +1
        for (int i = 0; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()); // 첫 번째 정점
            int b = Integer.parseInt(st.nextToken()); // 두 번째 정점
            int c = Integer.parseInt(st.nextToken()); // 두 정점 사이의 가중치
            // 무방향 그래프이므로 양방향을 모두 세팅
            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }
        
        // 가중치가 작은 순서대로 정렬
        for (List<Edge> edge : edges) {
            edge.sort(Comparator.comparingInt(a -> a.weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()); // 출발 정점
        int end = Integer.parseInt(st.nextToken()); // 도착 정점

        int[] weights = new int[n + 1]; // 시작점부터 해당 간선까지 가는 최소 가중치
        Arrays.fill(weights, INFINITE); // 전부 최대치로 설정
        weights[start] = 0;

        PriorityQueue<Edge> nextNodes = new PriorityQueue<>(Comparator.comparingInt(a -> a.weight));
        nextNodes.add(new Edge(start, 0)); // 가상의 시작 정점

        while (!nextNodes.isEmpty()) {
            Edge curr = nextNodes.poll(); // 현재 정점
            int next = curr.to;
            int distance = curr.weight;

            // 다음 노드로 가는 가중치가 이미 해당 노드로 가는 최소 비용보다 크면 무시
            if (weights[next] < distance) continue;

            for (Edge edge : edges[next]) {
                // 새로운 경로가 기존 경로보다 가중치가 작다면 가중치를 교체
                if (weights[edge.to] > weights[next] + edge.weight) {
                    weights[edge.to] = weights[next] + edge.weight;
                    nextNodes.offer(new Edge(edge.to, weights[edge.to]));
                }
            }
        }

        System.out.println(weights[end]);
    }

    private static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
