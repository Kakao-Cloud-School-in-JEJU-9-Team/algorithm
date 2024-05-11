import java.util.*;
import java.io.*;

public class Main {

    static ArrayList<Integer>[] graph;
    static int[] distance;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken()); // # of node
        int m = Integer.parseInt(st.nextToken()); // # of ech
        int k = Integer.parseInt(st.nextToken()); // target distance
        int x = Integer.parseInt(st.nextToken()); // start node

        graph = new ArrayList[n+1];
        distance = new int[n + 1];

        for (int i = 0; i < n+1; i++) {
            // 각 노드별 리스트 초기화
            graph[i] = new ArrayList<>();
            distance[i] = -1; // 방문하지 않은 노드까지의 거리 -1로 초기화해두기
        }
        

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph[start].add(end);
        }

        check(x);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n+1; i++) {
            if (distance[i] == k) {
                ans.add(i);
            }
        }
        if (ans.isEmpty()) {
            System.out.println(-1);
        } else {
            Collections.sort(ans);
            for (int i : ans) {
                System.out.println(i);
            }
        }
    }
    
    static void check(int startNode) {
        distance[startNode] = 0; // 시작노드의 거리는 0으로 다시 초기화
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startNode);
        
        while (!queue.isEmpty()) {
            int current = queue.poll(); // 현재 위치

            for (int i : graph[current]) {
                if (distance[i] != -1) { // if visited
                    continue;
                }
        
                distance[i] = distance[current] + 1; // 기존 거리 + 1
                queue.add(i);
            }
        }

    }
}