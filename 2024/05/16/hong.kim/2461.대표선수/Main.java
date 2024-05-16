package 투포인터.대표_선수_2461;
/*
 * 24/05/10
 *
 * 백준(투포인터) - 대표선수
 *
 * https://www.acmicpc.net/problem/2461
 */

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int answer = Integer.MAX_VALUE;

		Scanner scanner = new Scanner(System.in);

		int row = scanner.nextInt();
		int col = scanner.nextInt();

		int[][] map = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = scanner.nextInt();
			}
		}

		int[] nowPointer = new int[row];  // 각 학급의 현재 포인터 저장
		for (int i = 0; i < row; i++) {
			Arrays.sort(map[i]);  // 오름차순 정렬
			nowPointer[i] = 0;
		}

		while (true) {
			int minScore = Integer.MAX_VALUE;
			int maxScore = Integer.MIN_VALUE;

			int minScoreTeam = -1;  // 가장 낮은 능력치를 가진 학급

			for (int i = 0; i < row; i++) {  // 각 학급 비교
				if (minScore > map[i][nowPointer[i]]) {  // 능력치가 가장 낮은 학급 추출
					minScore = map[i][nowPointer[i]];
					minScoreTeam = i;
				}

				if (maxScore < map[i][nowPointer[i]]) {  // 능력치가 가장 높은 학급 추출
					maxScore = map[i][nowPointer[i]];
				}
			}

			int nowDiff = maxScore - minScore;
			answer = Math.min(answer, nowDiff);  // 능력치 차이의 최솟값 갱신

			if (nowPointer[minScoreTeam] >= col - 1) {
				System.out.println(answer);
				return;
			}

			nowPointer[minScoreTeam]++;  // 현재 가장 낮은 능력치를 가진 학급의 포인터 증가
			// 최댓값과 최솟값의 차이의 최소를 구해야하는데, 오름차순으로 정렬되어 있는 배열에서 최댓값을 가지는
			// 학급의 포인터를 증가시키면 계속해서 최솟값과 최대값의 차이가 커지기 때문에 최솟값을 가지는 학급의
			// 포인터를 증가시켜야한다.
		}
	}
}
