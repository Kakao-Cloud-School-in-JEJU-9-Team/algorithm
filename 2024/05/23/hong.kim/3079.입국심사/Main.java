package 이진탐색.입국심사_3079;
/*
 * 24/05/21
 *
 * 백준(이진탐색) - 기타 레슨
 *
 * https://www.acmicpc.net/problem/3079
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

	private static int station;  // 심사대
	private static long personNum;  // <- int 가 아니라 long 타입으로 해야함........
	private static int[] stationTimeMap;
	private static long maxValue;  // 가장 오래 걸리는 심사 시간

	public static void main(String[] args) throws IOException {
		long answer = Long.MAX_VALUE;

		Scanner scanner = new Scanner(System.in);

		station = scanner.nextInt();
		personNum = scanner.nextLong();

		stationTimeMap = new int[station];
		for (int i = 0; i < station; i++) {
			int value = scanner.nextInt();

			maxValue = Math.max(maxValue, value);
			stationTimeMap[i] = value;
		}

		Arrays.sort(stationTimeMap);

		long minTime = 0;  // 가능한 최소 시간
		long maxTime = personNum * maxValue;  // 가능한 최대 시간

		while (minTime <= maxTime) {
			long middleTime = (minTime + maxTime) / 2;  // 중앙값

			long processingPersonNum = 0;  // 처리 가능한 사람 수 (현재까지 처리한 사람 수)

			for (long nowStationTime : stationTimeMap) {
				long maxPerson = (middleTime / nowStationTime);  // 현재 심사대에서 밑을 수 있는 사람 수

				if (processingPersonNum >= personNum)
					break;

				processingPersonNum += maxPerson;
			}

			if (processingPersonNum < personNum) {
				minTime = middleTime + 1;
				continue;
			}

			answer = Math.min(middleTime, answer);
			maxTime = middleTime - 1;
		}

		System.out.println(answer);
	}
}
