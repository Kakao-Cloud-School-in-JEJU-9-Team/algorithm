package 다익스트라.거울설치_2151;
/*
 * 24/05/10
 *
 * 백준(다익스트라) - 거울설치
 *
 * https://www.acmicpc.net/problem/2151
 *
 * Think
 * - 거울 설치시 빛이 꺾일 수 있음
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	private static int maxNum;
	private static char[][] map;

	private static int[] start = new int[2];  // 시작 좌표
	private static int[] end = new int[2];  // 종료 좌표

	private static int[] dx = new int[] {0, 0, -1, 1};
	private static int[] dy = new int[] {-1, 1, 0, 0};

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		maxNum = scanner.nextInt();
		map = new char[maxNum][maxNum];

		scanner.nextLine();

		boolean isAlreadySet = false;  // 출발지, 도착지를 지정했나?
		for (int i = 0; i < maxNum; i++) {
			String line = scanner.nextLine();

			for (int j = 0; j < maxNum; j++) {
				char nowChar = line.charAt(j);
				map[i][j] = nowChar;

				if (nowChar == '#') {
					if (isAlreadySet) {
						end[0] = i;
						end[1] = j;
						map[i][j] = 'E';
					} else {
						start[0] = i;
						start[1] = j;
						map[i][j] = 'S';
						isAlreadySet = true;
					}
				}
			}
		}

		다익스트라();
	}

	private static void 다익스트라() {
		PriorityQueue<int[]> queue = new PriorityQueue<>(
			// 제일 적게 필요한 순서 (count 값이 낮은 순서대로)
			Comparator.comparingInt(o -> o[2])
		);

		// [idxX][idxY][상,하,좌,우]
		boolean[][][] visit = new boolean[maxNum][maxNum][4];
		for (int i = 0; i < 4; i++) {
			visit[start[0]][start[1]][i] = true;
		}

		// Start 에서 갈 수 있는 위치를 qQueue 에 추가
		for (int i = 0; i < 4; i++) {
			int nx = start[0] + dx[i];
			int ny = start[1] + dy[i];

			if (nx < 0 || ny < 0 ||
				nx >= maxNum || ny >= maxNum ||
				map[nx][ny] == '*')
				continue;

			// {nx: idxX, ny: idxY, count, i: 상(0) or 하(1) or 좌(2) or 우(3)}
			queue.offer(new int[] {nx, ny, 0, i});
		}

		while (!queue.isEmpty()) {
			int[] poll = queue.poll();

			int x = poll[0];
			int y = poll[1];
			int count = poll[2];
			int position = poll[3];  // 상(0), 하(1), 좌(2), 우(3)

			visit[x][y][position] = true;

			// 종료 조건 (도착시)
			if (map[x][y] == 'E') {
				System.out.println(count);
				return;
			}

			// 거울을 설치할 수 있는 위치인 경우
			if (map[x][y] == '!') {
				if (position == 2 || position == 3) {  // 좌 or 우
					for (int i = 0; i < 2; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];

						if (condition(nx, ny, visit, i))
							continue;

						queue.offer(new int[] {nx, ny, count + 1, i});
					}
				} else {  // 상 or 하
					for (int i = 2; i < 4; i++) {
						int nx = x + dx[i];
						int ny = y + dy[i];

						if (condition(nx, ny, visit, i))
							continue;

						queue.offer(new int[] {nx, ny, count + 1, i});
					}
				}
			}

			// 빛이 꺾이지 않고 그대로 진행
			int nx = x + dx[position];
			int ny = y + dy[position];

			if (condition(nx, ny, visit, position))
				continue;

			queue.offer(new int[] {nx, ny, count, position});
		}
	}

	private static boolean condition(int nx, int ny, boolean[][][] visit, int position) {
		return nx < 0 || nx >= maxNum ||
			ny < 0 || ny >= maxNum ||
			visit[nx][ny][position] || map[nx][ny] == '*';
	}
}
