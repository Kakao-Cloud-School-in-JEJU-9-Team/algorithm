package dp.나누기_21757;
/*
 * 24/04/30
 *
 * 백준(DP) - 사탕
 *
 * https://www.acmicpc.net/problem/1415
 *
 * 문제 조건 - 4부분으로 나누기
 *         - 값이 클 수도 있으니 long 타입 사용하기
 * Think
 * - 먼저 연속적으로 4부분으로 나눠야하기 때문에, 첫번째 부분에서의 합의 조합을 정의
 * - 첫번째 부분에서 정의 한 값을 토대로 2, 3, 4 부분을 순서대로 정의한 합이 될 때까지 sum
 * - 중요한 것은, 처음에 첫번째 부분의 합을 정의하고 2~4 부분을 나눌 때 이전에 가능했던 경우의 수를 더해가는 방법을 사용
 *   => 중복되는 경우의 수를 제외할 수 있음
 *
 * 풀면서 주의할 점
 * - Out of index 조심..
 */

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int totalNum = scanner.nextInt();

		long sum = 0;
		long[] sumMap = new long[totalNum + 1];
		for (int i = 1; i < totalNum + 1; i++) {
			int num = scanner.nextInt();
			sumMap[i] = sumMap[i - 1] + num;
			sum += num;
		}

		if (sum % 4 != 0) {  // 4로 나눠지지 않으면 동일한 합을 가지는 4부분으로 나눌 수 없음
			System.out.println(0);
			return;
		}

		long divNum = sum / 4;  // 각 부분에서 얻어야하는 합
		long[][] dpMap = new long[totalNum + 1][4];  // [현재 인덱스 + 1][나눈 부분]

		// 0 번째 인덱스부터 시작하지 않은 이유
		// 이전 인덱스 값을 가져와야 하는데 인덱스 값이 0 일 경우, -1 이 되기 때문에 1 인덱스 부터 시작

		dpMap[0][0] = 1;  // 시작 인덱스의 경우의 수 (0 이 아닌 1 부터 시작 하므로 필요하다)

		for (int i = 1; i < totalNum + 1; i++) {
			dpMap[i][0] = 1;  // 시작 부분의 경우의 수

			for (int j = 1; j < 4; j++) {
				dpMap[i][j] = dpMap[i - 1][j];  // 이전 조합으로 가능했던 경우의 수

				// 지금 인덱스까지의 합이 나눈 부분의 누적합과 같으면 이전 인덱스 까지로 부분을 나눌 수 있는 경우의 수
				if (sumMap[i] == divNum * j) {
					dpMap[i][j] += dpMap[i - 1][j - 1];
				}
			}
		}

		long answer = dpMap[totalNum - 1][3];
		System.out.println(answer);
	}
}
