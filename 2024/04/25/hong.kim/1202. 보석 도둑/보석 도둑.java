package 그리디.보석도둑;
/*
 * 24/04/22
 *
 * 백준(그리디) - 보석도둑
 *
 * https://www.acmicpc.net/problem/1202
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long result = 0;

		Scanner scan = new Scanner(System.in);

		int maxJewelry = scan.nextInt();
		int maxBag = scan.nextInt();

		List<Jewelry> jewelryList = new ArrayList<>();
		for (int i = 0; i < maxJewelry; i++) {
			int weight = scan.nextInt();  // 보석의 무게
			int value = scan.nextInt();  // 보석의 가격
			jewelryList.add(new Jewelry(weight, value));
		}

		int[] capacity = new int[maxBag];
		for (int i = 0; i < maxBag; i++) {
			capacity[i] = scan.nextInt();
		}

		// 모름 차순 정렬
		jewelryList.sort(Comparator.comparingInt(Jewelry::getWeight));
		Arrays.sort(capacity);

		PriorityQueue<Integer> map = new PriorityQueue<>(Comparator.reverseOrder());

		int idx = 0;
		for (int i = 0; i < maxBag; i++) {
			int bagCapacity = capacity[i];

			// 현재 가방에 넣을 수 있는 보석들을 우선순위 큐에 추가
			while (idx < maxJewelry && jewelryList.get(idx).getWeight() <= bagCapacity) {
				map.offer(jewelryList.get(idx).getValue());
				idx++;
			}

			if (!map.isEmpty()) {
				result += map.poll();
			}
		}
		// 결과 출력
		System.out.println(result);
	}

	static class Jewelry {
		private int weight;
		private int value;

		public Jewelry(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public int getWeight() {
			return weight;
		}

		public int getValue() {
			return value;
		}
	}
}
