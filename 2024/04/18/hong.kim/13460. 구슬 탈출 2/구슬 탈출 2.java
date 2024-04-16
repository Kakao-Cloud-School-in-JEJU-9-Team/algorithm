package bfs.구슬탈출2;
/*
 * 24/04/14
 *
 * 백준(BFS) - 구슬 탈출2
 *
 * https://www.acmicpc.net/problem/13460
 */

// Think
// - 최대 10번의 기회, 초과시 -1
// - 빨간색만 구멍에 도달하면 "성공"
// - 빨간색과 파란색이 둘다 구멍에 도달하면 "실패"
// - 빨간색과 파란색이 겹치는 경우? -> 이동하기 전에 어느 위치에 있었는가?

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static char[][] map;
	private static int endX, endY;  // 구멍 탈출구
	private static Marble red, blue;
	private static int[] dx = {-1, 1, 0, 0};  // 상하좌우
	private static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);

		int row = sc.nextInt();
		int col = sc.nextInt();

		map = new char[row][col];

		for (int i = 0; i < row; i++) {
			String line = sc.next();
			for (int j = 0; j < col; j++) {
				map[i][j] = line.charAt(j);
				if (map[i][j] == 'B') {
					blue = new Marble(i, j);
				} else if (map[i][j] == 'R') {
					red = new Marble(i, j);
				} else if (map[i][j] == 'O') {
					endY = i;
					endX = j;
				}
			}
		}

		bfs();
	}

	private static void bfs() {
		Queue<MarblePair> queue = new LinkedList<>();
		queue.add(new MarblePair(blue, red, 0));

		boolean[][][][] visit = new boolean[10][10][10][10];
		visit[blue.y][blue.x][red.y][red.x] = true;

		while (!queue.isEmpty()) {
			MarblePair marbles = queue.poll();

			if (marbles.count > 10) {  // 10회가 넘어가면 종료 (-1)
				System.out.println(-1);
				return;
			}

			if (marbles.red.y == endY && marbles.red.x == endX) {  // 이미 빨간 구슬이 빠져나간 경우
				System.out.println(marbles.count);
				return;
			}

			for (int i = 0; i < 4; i++) {
				Marble movedBlue = movedMarble(marbles.blue, i);
				Marble movedRed = movedMarble(marbles.red, i);

				if (movedBlue.y == endY && movedBlue.x == endX)  // 파란색 구슬이 구멍에 빠지는 경우
					continue;

				// 빨간색/파란색 구슬이 겹치는 경우
				if (movedBlue.y == movedRed.y && movedBlue.x == movedRed.x) {
					switch (i) {
						case 0:  // 위로 기울였을 때, 기존에 파란색 구글이 빨간색 아래 있었던 경우
							if (marbles.blue.x > marbles.red.x)
								movedBlue.x++;
							else
								movedRed.x++;
							break;
						case 1:  // 아래쪽으로 기울였을 때, 기존에 파란색 구글이 빨간색 아래 있었던 경우
							if (marbles.blue.x > marbles.red.x)
								movedRed.x--;
							else
								movedBlue.x--;
							break;
						case 2:  // 왼쪽으로 기울였을 때, 기존에 파란색 구글이 빨간색 오른쪽에 있었던 경우
							if (marbles.blue.y > marbles.red.y)
								movedBlue.y++;
							else
								movedRed.y++;
							break;
						case 3:  // 오른쪽으로 기울였을 때, 기존에 파란색 구글이 빨간색 오른쪽에 있었던 경우
							if (marbles.blue.y > marbles.red.y)
								movedRed.y--;
							else
								movedBlue.y--;
							break;

					}
				}

				if (!visit[movedBlue.y][movedBlue.x][movedRed.y][movedRed.x]) {
					visit[movedBlue.y][movedBlue.x][movedRed.y][movedRed.x] = true;
					queue.add(new MarblePair(movedBlue, movedRed, marbles.count + 1));
				}
			}
		}
		System.out.println(-1);
	}

	private static Marble movedMarble(Marble marble, int k) {
		int y = marble.y;
		int x = marble.x;

		while (map[y + dy[k]][x + dx[k]] != '#') {  // 벽에 부딪히기 전까지 loop
			y += dy[k];
			x += dx[k];
			if (y == endY && x == endX)  // 구슬이 구멍에 빠지는 경우
				break;
		}
		return new Marble(y, x);
	}

	private static class Marble {
		int y;
		int x;

		public Marble(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}

	private static class MarblePair {
		Marble blue;
		Marble red;
		int count;

		public MarblePair(Marble blue, Marble red, int count) {
			this.blue = blue;
			this.red = red;
			this.count = count;
		}
	}
}
