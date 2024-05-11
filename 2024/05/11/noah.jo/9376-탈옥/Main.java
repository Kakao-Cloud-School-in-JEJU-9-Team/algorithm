import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    private static final PriorityQueue<Vertex> pq = new PriorityQueue<>(Comparator.comparingInt(vertex -> vertex.weight));
    private static final int[][] dyx = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private static int maxHeight;
    private static int maxWidth;

    private static char[][] map;
    private static int[][][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            maxHeight = h + 2;
            maxWidth = w + 2;

            map = new char[maxHeight][maxWidth];

            // 0 == 상근, 1 == 죄수1, 2 == 죄수2
            visited = new int[3][maxHeight][maxWidth];

            Prisoner prisoner1 = null;
            Prisoner prisoner2 = null;

            for (int i = 0; i < maxHeight; i++) {
                if (i == 0 || i == h + 1) {
                    for (int j = 0; j < maxWidth; j++) {
                        // visited 초기화
                        visited[0][i][j] = visited[1][i][j] = visited[2][i][j] = Integer.MAX_VALUE;

                        map[i][j] = '.';
                    }
                    continue;
                }

                map[i][0] = map[i][w + 1] = '.';
                visited[0][i][w] = visited[1][i][w] = visited[2][i][w] = Integer.MAX_VALUE;
                visited[0][i][w + 1] = visited[1][i][w + 1] = visited[2][i][w + 1] = Integer.MAX_VALUE;

                String string = br.readLine();

                for (int j = 0, k = 1; j < w; j++, k++) {
                    // visited 초기화
                    visited[0][i][j] = visited[1][i][j] = visited[2][i][j] = Integer.MAX_VALUE;

                    char c = string.charAt(j);

                    if (c == '$') {
                        if (prisoner1 == null) {
                            prisoner1 = new Prisoner(i, k);
                        } else {
                            prisoner2 = new Prisoner(i, k);
                        }
                    }

                    map[i][k] = c;
                }
            }

            dijkstra(0, 0, 0);
            dijkstra(1, prisoner1.y, prisoner1.x);
            dijkstra(2, prisoner2.y, prisoner2.x);

            int minWeight = Integer.MAX_VALUE;

            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (map[i][j] == '*') {
                        continue;
                    }

                    int weight = visited[0][i][j] + visited[1][i][j] + visited[2][i][j];

                    if (map[i][j] == '#') {
                        weight -= 2;
                    }

                    minWeight = Math.min(minWeight, weight);
                }
            }

            sb.append(minWeight).append('\n');
        }

        bw.write(sb.toString().trim());
        bw.flush();

        br.close();
        bw.close();
    }

    private static void dijkstra(int person, int startY, int startX) {
        visited[person][startY][startX] = 0;

        pq.offer(new Vertex(startY, startX, 0));

        while (!pq.isEmpty()) {
            Vertex currentVertex = pq.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = currentVertex.y + dyx[i][0];
                int nextX = currentVertex.x + dyx[i][1];

                // 범위 밖
                if (nextY < 0 || nextY >= maxHeight || nextX < 0 || nextX >= maxWidth) {
                    continue;
                }

                int nextWeight = currentVertex.weight;

                // 벽 또는 문
                if (map[nextY][nextX] == '*') {
                    continue;
                } else if (map[nextY][nextX] == '#') {
                    nextWeight++;
                }

                if (nextWeight >= visited[person][nextY][nextX]) {
                    continue;
                }

                visited[person][nextY][nextX] = nextWeight;

                pq.offer(new Vertex(nextY, nextX, nextWeight));
            }
        }
    }

    private static class Prisoner {

        final int y;
        final int x;

        Prisoner(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    private static class Vertex {

        final int y;
        final int x;
        final int weight;

        Vertex(int y, int x, int weight) {
            this.y = y;
            this.x = x;
            this.weight = weight;
        }
    }
}
