import java.io.*;
import java.util.*;

public class Main {

    private static final int[][] dyx = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int[][] map = new int[N + 1][N + 1]; // -1 = 뱀, 0 = 빈 공간, 1 = 사과

        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int column = Integer.parseInt(st.nextToken());

            map[row][column] = 1;
        }

        int L = Integer.parseInt(br.readLine());

        Map<Integer, Character> turns = new HashMap<>();

        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            turns.put(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0));
        }

        Queue<Snake> queue = new LinkedList<>();

        queue.offer(new Snake(1, 1));

        int snakeHeadY = 1;
        int snakeHeadX = 1;
        int turn = 3; // 시작 턴

        int second = 0;

        while (true) {
            second++;

            int nextY = snakeHeadY + dyx[turn][0];
            int nextX = snakeHeadX
            
            // 범위 벗어남
            if (nextY < 1 || nextY > N || nextX < 1 || nextX > N) {
                break;
            }

            // 벽인 경우
            if (map[nextY][nextX] == -1) {
                break;
            }

            if (map[nextY][nextX] == 0) { // 다음 자리가 빈 공간인 경우
                Snake snakeTail = queue.poll();
                map[snakeTail.y][snakeTail.x] = 0;
            }

            map[nextY][nextX] = -1;
            queue.offer(new Snake(nextY, nextX));

            if (turns.containsKey(second)) {
                turn = turnSnakeHead(turn, turns.get(second));
            }

            snakeHeadY = nextY;
            snakeHeadX = nextX;
        }

        bw.write(Integer.toString(second));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int turnSnakeHead(int currentTurn, char direction) {
        if (currentTurn == 0) { // 상
            return direction == 'L' ? 2 : 3;
        } else if (currentTurn == 1) { // 하
            return direction == 'L' ? 3 : 2;
        } else if (currentTurn == 2) { // 좌
            return direction == 'L' ? 1 : 0;
        }

        // 우
        return direction == 'L' ? 0 : 1;
    }

    private static class Snake {

        final int y;
        final int x;

        Snake(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
