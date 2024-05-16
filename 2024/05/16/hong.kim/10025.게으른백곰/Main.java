package 투포인터.게으른_백곰_10025;
/*
 * 24/05/14
 *
 * 백준(투포인터) - 게으른 백곰
 *
 * https://www.acmicpc.net/problem/10025
 */

import java.util.Scanner;

public class Main {

	private static int maxIceCount;  // 얼음이 거리(배열)에 놓여진 개수
	private static int maxMove;  // 얼음을 가져올 수 있는 최대 거리

	private static int[] map = new int[1_000_001];  // 거리 맵

	private static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		maxIceCount = scanner.nextInt();
		maxMove = scanner.nextInt() * 2 + 1;  // 앞뒤로 K칸 -> (K * 2) + 1

		for (int i = 0; i < maxIceCount; i++) {
			int iceCount = scanner.nextInt();  // 얼음 개수
			int idx = scanner.nextInt();  // 얼음 좌표
			map[idx] = iceCount;
		}

		int sum = 0;
		for (int i = 0; i < 1_000_001; i++) {
			if (i - maxMove >= 0) {
				sum -= map[i - maxMove];
			}
			sum += map[i];
			answer = Math.max(answer, sum);
		}

		System.out.println(answer);
	}
}
