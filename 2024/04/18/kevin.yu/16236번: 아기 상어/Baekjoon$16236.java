package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BreadthFirstSearch$16236 {
    private static int n;
    private static int[] point; // 아기 상어의 위치
    private static int[][] map;

    private static int size = 2; // 아기 상어의 크기
    private static int growth = 2; // 아기 상어의 크기

    private static final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}}; // 반시계

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        // map 초기화
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // 아기 상어를 발견하면 해당 위치를 저장
                if (map[i][j] == 9) {
                    point = new int[]{i, j};
                    map[i][j] = 0;
                }
            }
        }

        System.out.println(move());
    }

    private static int move() {
        int time = 0;
        while (true) {
            int[] result = findEatableFish();
            // 만약 물고기를 찾지 못했다면 종료
            if (result == null) break;
            // 아기 상어의 위치를 변경하고 시간을 계산
            point[0] = result[0];
            point[1] = result[1];
            time += result[2];
            map[point[0]][point[1]] = 0;
            // 아기 상어 성장 로직
            growth--;
            if (growth == 0) {
                size++;
                growth = size;
            }
        }
        return time;
    }

    private static int[] findEatableFish() {
        Queue<int[]> que = new LinkedList<>();
        que.add(point);

        boolean[][] visited = new boolean[n][n];
        visited[point[0]][point[1]] = true;

        int[] candidate = null; // 후보 물고기 정보
        int[][] distance = new int[n][n];
        while (!que.isEmpty()) {
            int[] curr = que.poll();

            for (int[] dir : DIRS) {
                int r = curr[0] + dir[0];
                int c = curr[1] + dir[1];
                // 범위를 벗어나면 건너뜀
                if (r < 0 || r >= n || c < 0 || c >= n) continue;
                // 이미 방문했다면 건너뜀
                if (visited[r][c]) continue;
                // 한 칸씩 이동하며 물고기를 탐색
                if (map[r][c] <= size) {
                    distance[r][c] = distance[curr[0]][curr[1]] + 1;
                    que.add(new int[]{r, c});
                    visited[r][c] = true;

                    if (isEatableFish(r, c, distance, candidate)) {
                        candidate = new int[]{r, c, distance[r][c]};
                    }
                }
            }
        }
        return candidate;
    }

    private static boolean isEatableFish(int r, int c, int[][] distance, int[] candidate) {
        // 먹을 수 있는 물고기이면서
        boolean isEatable = map[r][c] != 0 && map[r][c] < size;
        if (candidate == null) {
            return isEatable;
        }
        // 조건에 가장 부합하는 물고기라면
        boolean isClosest = candidate[2] > distance[r][c] // 후보 물고기보다 거리가 가깝거나
                || (candidate[2] == distance[r][c] && (candidate[0] > r // 거리가 같을 때 더 위에 있거나
                || (candidate[0] == r && candidate[1] > c))); // 더 왼쪽에 있는 물고기를 새로운 후보 물고기로 선정

        return isEatable && isClosest;
    }
}
