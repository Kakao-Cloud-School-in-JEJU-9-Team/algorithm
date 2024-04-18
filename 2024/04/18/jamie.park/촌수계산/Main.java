import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static ArrayList<Integer>[] graph;
    static int answer = -1; // 촌수 계산 안될 때 -1 출력

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); // node

        StringTokenizer st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine()); // edge

        graph = new ArrayList[n + 1]; // 1~n 인덱스 쓰기 위해 n+1로 초기화
        visited = new boolean[n + 1];

        for (int i = 1; i < n + 1; i++) { // 각 노드별 리스트 초기화
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());

            graph[first].add(second);
            graph[second].add(first);
        }

        dfs(x, y, 0);
        System.out.println(answer);
    }
    
    static void dfs(int start, int end, int count) {
        if (start == end) {
            answer = count;
            return;
        }

        visited[start] = true;

        for (int i : graph[start]) {
            if (!visited[i]) {
                dfs(i, end, count + 1);
                if (answer != -1)
                    return;
            }
        }
            
    }
}