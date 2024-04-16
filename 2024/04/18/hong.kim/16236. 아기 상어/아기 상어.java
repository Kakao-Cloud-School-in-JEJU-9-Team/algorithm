package bfs.아기상어;
/*
 * 24/04/15
 *
 * 백준(BFS) - 아기상어
 *
 * https://www.acmicpc.net/problem/16236
 */

// Think
// - 첫 시작 크기 2
// - 현재 크기 만큼 먹으면 "크기 + 1"
// - 현재 크기보다 크면 못지나감
// - 조건.1 가장 가까운 물고기를 먹으러감 (먹을 수 있는)
// - 조건.2 거리가 동일하면 먼저 row 기준, 그다음 col 기준으로 가까운 물고기를 먹으러감

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	private static int maxSize;
	private static int sharkSize = 2;
	private static int sharkEatCount = 0;  // 상어의 크기 만큼 물고기를 먹으면 +1
	private static int[][] map;
	private static int[] dx = {-1, 1, 0, 0};  // 상하좌우
	private static int[] dy = {0, 0, -1, 1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		maxSize = sc.nextInt();
		map = new int[maxSize][maxSize];

		// 아기 상어 시작점
		int startSharkX = 0;
		int startSharkY = 0;

		for (int i = 0; i < maxSize; i++) {
			for (int j = 0; j < maxSize; j++) {
				int num = sc.nextInt();
				if (num == 9) {  // 상어 시작 지점은 0으로 둬서 무시 하기
					startSharkX = i;
					startSharkY = j;
					map[i][j] = 0;
				} else {
					map[i][j] = num;
				}
			}
		}

		bfs(startSharkX, startSharkY);
	}

	private static void bfs(int startX, int startY) {
		int count = 0;
		int sharkX = startX;
		int sharkY = startY;

		while (true) {
			// TODO: refactor - (비효율적 같은데.. 통과함..)
			PriorityQueue<Shark> feed = new PriorityQueue<>(  // 먹을 수 있는 물고기들이 있는 우선 순위 큐
				Comparator.comparingInt((Shark shark) -> shark.count)  // 정렬 1. 최단 거리
					.thenComparingInt(shark -> shark.x)  // 정렬 2. row 기준 오름차순
					.thenComparingInt(shark -> shark.y)  // 정렬 3. col 기준 오름차순
			);

			Queue<Shark> queue = new LinkedList<>();
			queue.offer(new Shark(sharkX, sharkY, 0));

			boolean[][] visit = new boolean[maxSize][maxSize];
			visit[sharkX][sharkY] = true;  // 현재 상어의 위치는 방문 처리로 시작

			while (!queue.isEmpty()) {
				Shark shark = queue.poll();

				for (int i = 0; i < 4; i++) {  // 4방향으로 이동
					int movedX = shark.x + dx[i];
					int movedY = shark.y + dy[i];

					if (movedX < 0 || movedX >= maxSize ||  // 범위 밖인 경우 pass
						movedY < 0 || movedY >= maxSize)
						continue;

					if (visit[movedX][movedY])  // 이미 방문한 경우 pass
						continue;

					if (map[movedX][movedY] > sharkSize)  // 상어 크기보다 큰 물고기 인 경우 pass
						continue;

					// 위 3가지 만족 시 상어가 이동 가능 (물고기 먹는 것 포함)

					visit[movedX][movedY] = true;  // 방문 처리
					queue.offer(new Shark(movedX, movedY, shark.count + 1));

					if (map[movedX][movedY] > 0 &&
						map[movedX][movedY] < sharkSize) {  // 물고기를 먹을 수 있는 경우
						feed.offer(new Shark(movedX, movedY, shark.count + 1));
					}
				}
			}

			// 먹을게 없으면 종료
			if (feed.isEmpty())
				break;

			// 먹을게 있는 경우

			// 상어가 가장 가까운 물고기를 먹음
			Shark shark = feed.poll();
			count += shark.count;

			// 물고기 먹기
			sharkX = shark.x;  // 먹은 위치로 상어 이동 (즉, BFS 시작 노드 재설정)
			sharkY = shark.y;
			map[shark.x][shark.y] = 0;  // 먹은 위치는 빈칸으로

			sharkEatCount++;

			if (sharkEatCount == sharkSize) {  // 상어 크기가 커지는 조건
				sharkSize++;
				sharkEatCount = 0;
			}
		}
		System.out.println(count);
	}

	private static class Shark {
		int x;
		int y;
		int count;

		public Shark(int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
		}
	}
}
