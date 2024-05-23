package 이진탐색.기타_레슨_2343;
/*
 * 24/05/21
 *
 * 백준(이진탐색) - 기타 레슨
 *
 * https://www.acmicpc.net/problem/2343
 */

import java.util.Scanner;

public class Main {

	private static int lectureNum;
	private static int bluRay;
	private static int[] lectureTimeMap;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		lectureNum = scanner.nextInt();
		bluRay = scanner.nextInt();

		lectureTimeMap = new int[lectureNum];

		int minTotalTime = Integer.MAX_VALUE;  // 최소 강의 시간 (이진 탐색 시작)
		int maxTotalTime = 0;  // 최대 강의 시간 (이진 탐색 종료)

		for (int i = 0; i < lectureNum; i++) {
			int value = scanner.nextInt();

			minTotalTime = Math.min(minTotalTime, value);
			maxTotalTime += value;
			lectureTimeMap[i] = value;
		}

		while (minTotalTime <= maxTotalTime) {
			int middleTime = (minTotalTime + maxTotalTime) / 2;  // 현재의 중앙 시간 값

			int nowTotalLectureTime = 0;
			int bluRayUseCount = 0;

			for (int i = 0; i < lectureNum; i++) {
				if (nowTotalLectureTime + lectureTimeMap[i] > middleTime) {  // 다음 블루레이로 이동 (블루레이의 최대치 가득참)
					bluRayUseCount++;
					nowTotalLectureTime = 0;
				}

				nowTotalLectureTime = nowTotalLectureTime + lectureTimeMap[i];
			}

			if (nowTotalLectureTime != 0)  // 마지막 블루레이 추가 -> 즉, nowTotalLectureTime 이 0 인 경우 깔끔하게 나눠진 경우이다.
				bluRayUseCount++;

			if (bluRayUseCount > bluRay) {  // 현재의 middleTime 으로는 주어진 블루레이 개수에 넣을 수 없을 경우
				minTotalTime = middleTime + 1;
				continue;
			}
			maxTotalTime = middleTime - 1;
		}

		System.out.println(minTotalTime);
	}
}
