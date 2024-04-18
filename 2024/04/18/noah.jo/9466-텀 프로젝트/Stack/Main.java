import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[] graph;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            // init
            graph = new int[n + 1]; // 단일 연결이기 때문에 배열 사용
            visited = new int[n + 1]; // -1 = 사이클, 0 = 방문 X

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i] != 0) {
                    continue;
                }

                dfs(i);
            }

            int count = 0;

            for (int i = 1; i <= n; i++) {
                if (visited[i] != -1) {
                    count++;
                }
            }

            sb.append(count).append('\n');
        }

        bw.write(sb.toString().trim());
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dfs(int startVertex) {
        int currentVertex = startVertex;

        while (true) {
            visited[currentVertex] = startVertex;

            currentVertex = graph[currentVertex]; // next vertex

            if (visited[currentVertex] == startVertex) { // 사이클 발생
                while (visited[currentVertex] != -1) {
                    visited[currentVertex] = -1;

                    currentVertex = graph[currentVertex];
                }

                return;
            }

            if (visited[currentVertex] > 0) { // 사이클은 아니지만, 이전에 방문한 경우
                return;
            }
        }
    }
}
