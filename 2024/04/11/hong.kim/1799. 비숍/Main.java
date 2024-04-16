package 백트래킹.비숍;
/*
 * 24/04/10
 *
 * 백준(백트래킹) - 비숍
 *
 * https://www.acmicpc.net/problem/1799
 */

// Think
// - 놓을 수 없는 위치: 0, 놓을 수 있는 위치: 1, 이미 놓은 위치: 2
// - DFS 구현시 2차원을 1차원으로 생각해서 해보기
// - 체스판을 흑/백으로 나눠서 하자.
// - 체스판의 크기가 2의 배수인 경우 => 흑백흑백/백흑...
// ex) 흑백흑백
//     백흑백흑
//     흑백흑백
//     백흑백흑

import java.util.Scanner;

public class Main {

	private static int maxSize;  // 체스판 크기
	private static int[][] chessMap;
	private static int blackMaxCount, whiteMaxCount;

	public static int[] dx = {1, -1, 1, -1};
	public static int[] dy = {1, 1, -1, -1};

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		maxSize = sc.nextInt();
		chessMap = new int[maxSize][maxSize];

		for (int i = 0; i < maxSize; i++)
			for (int j = 0; j < maxSize; j++)
				chessMap[i][j] = sc.nextInt();

		getMaxCount(0, 0);  // 흑색칸
		getMaxCount(1, 0);  // 백색칸

		System.out.println(blackMaxCount + whiteMaxCount);
	}

	private static void getMaxCount(int idx, int count) {
		if (idx >= maxSize * maxSize) {
			if (idx == maxSize * maxSize)  // 흑색칸인 경우
				blackMaxCount = Math.max(blackMaxCount, count);
			else  // 백색칸인 경우
				whiteMaxCount = Math.max(whiteMaxCount, count);
			return;
		}

		// chessMap 사용을 위한 [x][y] 계산
		int x = idx / maxSize;
		int y = idx % maxSize;
		// System.out.println("(x, y) = (" + x + ", " + y + ")");

		int increaseNum = 0;

		if (maxSize % 2 == 1)  // 체스판의 크기가 홀수인 경우 +2 (.../흑 백 흑 백/흑 백...)
			increaseNum = 2;
		else if (y == maxSize - 1)  // 체스판의 크기가 짝수 && y의 가장 마지막 백색인 경우 +1 (.../흑 백 흑 "백"/"백" 흑 백 흑/...)
			increaseNum = 1;
		else if (y == maxSize - 2)  // 체스판의 크기가 짝수 && y의 가장 마지막 흑색인 경우 +3 (.../흑 백 "흑" 백/백 "흑" 백 흑/...)
			increaseNum = 3;
		else
			increaseNum = 2;

		// 체스를 두는 경우
		if (chessMap[x][y] == 1 && isPossible(x, y)) {
			chessMap[x][y] = 2;  // 체스를 놓고 다음 칸(같은 색의 칸)으로 이동
			getMaxCount(idx + increaseNum, count + 1);

			chessMap[x][y] = 1;  // 원상 복구
		}

		// 체스를 두지 않는 경우
		getMaxCount(idx + increaseNum, count);
	}

	private static boolean isPossible(int x, int y) {
		for (int i = 0; i < 4; i++) {  // x,y 기준 대각선 4방향 탐색 (중복 검색)
			int weight = 1;

			while (true) {  // 한 방향 증가하면서 탐색하기 (0,2) -> (1,3) -> (2,4) -> ...
				int checkX = x + dx[i] * weight;
				int checkY = y + dy[i] * weight;

				if (checkX < 0 || checkX >= maxSize ||
					checkY < 0 || checkY >= maxSize)
					break;

				if (chessMap[checkX][checkY] == 2)  // 중복됨 => 체스를 둘 수 없다.
					return false;

				weight++;
			}
		}
		return true;
	}
}
