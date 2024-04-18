package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class DepthFirstSearch$11724 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 리스트 배열로 그래프 구현
        List<Integer>[] graph = new List[N + 1];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            // 무방향 그래프이기 때문에 양쪽 모두 저장
            graph[u].add(v);
            graph[v].add(u);
        }
        // 인덱스를 편하기 쓰기 위해 N + 1로 배열 초기화
        boolean[] visited = new boolean[N + 1];
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (visited[i]) continue;
            // 아직 방문하지 않은 노드가 있다면 개수를 추가
            cnt++;
            // 깊이 탐색으로 연결되어 있는 모든 노드 방문
            Stack<Integer> stk = new Stack<>();
            stk.push(i);
            visited[i] = true;
            while (!stk.isEmpty()) {
                Integer curr = stk.pop();
                for (Integer node : graph[curr]) {
                    if (!visited[node]) {
                        stk.push(node);
                        visited[node] = true;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
