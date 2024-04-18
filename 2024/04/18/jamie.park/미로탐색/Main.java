import java.util.*;
import java.io.*;

public class Main {

    /*
     * BFS:
     * 트리에서 해당 깊이에서 갈 수 있는 노드 탐색을 마친 후 
     * 다음 깊이로 넘어가기 때문에
     * 목표 노드를 찾는 순간의 깊이가 최솟값이 된다.
     */

    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = { 1, -1, 0, 0 };
    static int[] dy = { 0, 0, 1, -1 };
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String line = br.readLine();

            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(line.toString().substring(j, j + 1));
            }
        }

        BFS(0, 0);
        System.out.println(graph[n-1][m-1]);

    }
    
    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>(); // [x,y] 형태로 삽입 -> <int[]>
        queue.add(new int[] { x, y });
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll(); // 현재 위치

            for (int i = 0; i < 4; i++) { // 상하좌우 탐색
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                if (nx >= n || nx < 0 || ny >= m || ny < 0) { // 범위 벗어나면 종료
                    continue;
                }

                if (graph[nx][ny] == 1 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    graph[nx][ny] = graph[current[0]][current[1]] + 1; // 깊이 +1
                    queue.add(new int[] { nx, ny });
                }
            }
        }
    }
}
