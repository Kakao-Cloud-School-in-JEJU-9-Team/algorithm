package dp.사탕_1415;
/*
 * 24/04/30
 *
 * 백준(DP) - 사탕
 *
 * https://www.acmicpc.net/problem/1415
 *
 * Think
 * 첫번째로 생각 나는 방법 : 1개 샀을 때 ~ N 개 샀을 때, 가격이 소수인지 검증
 *                     (오래 걸릴 것 같아서 기각)
 * 두번째로 생각 나는 방법 : 가격을 전부 더하면 최대 만들 수 있는 숫자의 최대가 나오면 그 숫자까지의 소수 검증?
 *                     (만약 1, 50 주어지며면 2, 3, 5,... 만들 수 없잖 기각)
 *
 * 0 1 2 3 4 5 6 7 8 9 10 11
 * 1 1 2 1 1 0 0 1 1 2 1  1
 * 2 + 1 + 1 + 1 = 5
 *
 * - 먼저 가격 오름차순으로 정렬 (가격, 개수)
 * - 작은 가격부터 dp 맵에 기입
 * - 가격이 0 인 경우? 0이 2개인 경우 {7}, {0, 7}, {0, 0, 7}
 *                  0이 3개인 경우 {7}, {0,7}, {0,0,7}, {0,0,0,7}
 *                  즉, (소수를 만들 수 있는 경우의 수) x (0의 개수 + 1)
 *                  => 따라서 애초에 0의 개수를 1개로 잡고 시작
 *                  `dpMap[0] = 1` 시작
 * - dpMap[idx] += dpMap[idx - 가격 * j(개수)]
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		int maxInput = scanner.nextInt();
		int zeroCount = 1;

		// Pair<가격, 개수>
		List<Pair<Integer, Integer>> pairMap = new ArrayList<>();

		for (int i = 0; i < maxInput; i++) {
			int nowValue = scanner.nextInt();

			if (nowValue == 0) {  // 사탕 가격이 0인 경우
				zeroCount++;
				continue;
			}

			boolean isExist = false;
			for (Pair<Integer, Integer> pair : pairMap) {
				if (pair.getValue() == nowValue) {
					pair.setCount(pair.getValue() + 1);
					isExist = true;
					break;
				}
			}

			if (!isExist) {
				pairMap.add(new Pair<>(nowValue, 1));
			}
		}

		// 미리 소수를 구하기 50(개) * 10000(가격) 입력 전까지
		boolean[] primeMap = new boolean[500001];
		Arrays.fill(primeMap, true);

		primeMap[0] = false;
		primeMap[1] = false;

		int maxNum = 10000 * maxInput;
		for (int i = 2; i <= Math.sqrt(maxNum); i++) {
			if (!primeMap[i]) {
				continue;
			}

			for (int j = i * i; j <= maxNum; j += i) {
				primeMap[j] = false;
			}
		}

		long[] dpMap = new long[500001];
		dpMap[0] = 1;

		for (Pair<Integer, Integer> pair : pairMap) {

			for (int i = maxInput * 10000; i >= 0; i--) {
				// 현재 계산할 값이 이전에 계산할 것에 영향을 받아야 하는데,
				// 오름 차순으로 하면 현재 계산할 것이 나중에 계산할 것에 영향을 준다

				for (int j = 1; j <= pair.getCount(); j++) {

					if (i - pair.getValue() * j < 0) {
						break;
					}

					dpMap[i] += dpMap[i - pair.getValue() * j];
				}
			}
		}

		long answer = 0;
		for (int i = 2; i <= maxInput * 10000; i++) {
			if (primeMap[i]) {
				answer += dpMap[i];
			}
		}

		System.out.println(answer * zeroCount);
	}

	static class Pair<V, C> {
		private final V value;
		private C count;

		public Pair(V value, C count) {
			this.value = value;
			this.count = count;
		}

		public V getValue() {
			return value;
		}

		public C getCount() {
			return count;
		}

		public void setCount(C count) {
			this.count = count;
		}
	}
}
