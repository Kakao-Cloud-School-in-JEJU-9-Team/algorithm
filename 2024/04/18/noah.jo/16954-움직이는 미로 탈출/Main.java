import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    private static final int MAX_SIZE = 8;
    // X, 시계 방향
    private static final int[][] dyx = {{0, 0}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};

    private static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // '.' == false, '#' = true
        graph = new boolean[MAX_SIZE][MAX_SIZE];

        for (int i = 0; i < MAX_SIZE; i++) {
            String string = br.readLine();

            for (int j = 0; j < MAX_SIZE; j++) {
                graph[i][j] = string.charAt(j) == '#';
            }
        }

        bw.write(Integer.toString(bfs()));
        bw.flush();

        br.close();
        bw.close();
    }

    // 8턴 동안 살아 있다면, 된거 아닌가?
    private static int bfs() {
        int cycle = 0;

        // 8초 이상부터는 벽이 없음
        boolean[][][] visited = new boolean[9][MAX_SIZE][MAX_SIZE];

        Queue<Vertex> queue = new LinkedList<>();

        queue.offer(new Vertex(7, 0, 0));

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();

            if (currentVertex.second > cycle) {
                moveWall();

                cycle++;
            }

            // 현재 위치에 벽이 있는 경우
            if (graph[currentVertex.y][currentVertex.x]) {
                continue;
            }

            if (currentVertex.second > 7) {
                return 1;
            }

            for (int i = 0; i < 9; i++) {
                int nextY = currentVertex.y + dyx[i][0];
                int nextX = currentVertex.x + dyx[i][1];

                // 범위 밖
                if (nextY < 0 || nextY >= MAX_SIZE || nextX < 0 || nextX >= MAX_SIZE) {
                    continue;
                }

                // 다음 위치에 벽이 있는 경우
                if (graph[nextY][nextX]) {
                    continue;
                }

                int nextSecond = currentVertex.second + 1;

                if (visited[nextSecond][nextY][nextX]) {
                    continue;
                }

                visited[nextSecond][nextX][nextX] = true;

                queue.offer(new Vertex(nextY, nextX, nextSecond));
            }
        }

        return 0;
    }

    // 한 사이클마다 벽 이동
    private static void moveWall() {
        boolean[][] newGraph = new boolean[MAX_SIZE][MAX_SIZE];

        for (int i = 0, nextY = 1; i < MAX_SIZE - 1; i++, nextY++) {
            for (int j = 0; j < MAX_SIZE; j++) {
                newGraph[nextY][j] = graph[i][j];
            }
        }

        graph = newGraph;
    }

    private static class Vertex {

        final int y;
        final int x;
        final int second;

        Vertex(int y, int x, int second) {
            this.y = y;
            this.x = x;
            this.second = second;
        }
    }
}
