package 그리디.저울;
/*
 * 24/04/23
 *
 * 백준(그리디) - 저울
 *
 * https://www.acmicpc.net/problem/2437
 */

// Think
// - 누적합
//    - 지금까지의 누적합 + 1 보다 클 경우 조합을 만들 수 없음.

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int maxCount = scanner.nextInt();
		int[] map = new int[maxCount];

		for (int i = 0; i < maxCount; i++) {
			map[i] = scanner.nextInt();
		}

		Arrays.sort(map);

		int cumulativeSum = 1;  // 누적합 저장 (시작 자체를 1부터 => 즉, 첫 값이 1보다 크면 "1"을 만들 수 없다.)
		for (int i = 0; i < maxCount; i++) {
			if (cumulativeSum < map[i]) {
				break;
			}
			cumulativeSum += map[i];
		}
		System.out.println(cumulativeSum);
	}
}
