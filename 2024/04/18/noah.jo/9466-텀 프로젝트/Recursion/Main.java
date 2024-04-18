import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static int[] graph;
    private static boolean[] visited;
    private static boolean[] cycled; // 사이클이 구성된 경우

    private static int cycleMember;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            // init
            graph = new int[n + 1]; // 단일 연결이기 때문에 배열 사용
            visited = new boolean[n + 1];
            cycled = new boolean[n + 1];

            cycleMember = 0;

            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (visited[i]) {
                    continue;
                }

                dfs(i);
            }

            sb.append(n - cycleMember).append('\n');
        }

        bw.write(sb.toString().trim());
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dfs(int currentVertex) {
        visited[currentVertex] = true;

        int nextVertex = graph[currentVertex];

        if (!visited[nextVertex]) {
            dfs(nextVertex);
        } else if (!cycled[nextVertex]) {
            for (int i = nextVertex; i != currentVertex; i = graph[i]) {
                cycleMember++;
            }

            cycleMember++;
        }

        cycled[currentVertex] = true;
    }
}
