package 다익스트라.최단경로_1753;
/*
 * 24/05/10
 *
 * 백준(다익스트라) - 최단경로
 *
 * https://www.acmicpc.net/problem/1753
 *
 * Think
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

	private static int totNodeNum;
	private static int totEdgeNum;

	private static int[] dist;  // 최단 거리 비용
	private static List<Node>[] map;
	private static boolean[] visit;

	private static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		totNodeNum = scanner.nextInt();
		totEdgeNum = scanner.nextInt();

		dist = new int[totNodeNum + 1];
		map = new List[totNodeNum + 1];
		visit = new boolean[totNodeNum + 1];

		int firstNode = scanner.nextInt();

		// 초기 설정
		for (int i = 1; i <= totNodeNum; i++) {
			dist[i] = INF;
			map[i] = new ArrayList<>();
		}

		for (int i = 0; i < totEdgeNum; i++) {
			int startNode = scanner.nextInt();
			int endNode = scanner.nextInt();
			int weight = scanner.nextInt();

			map[startNode].add(new Node(endNode, weight));  // start -> end 의 비용(weight)
		}

		다익스트라(firstNode);
	}

	private static void 다익스트라(int firstNode) {
		PriorityQueue<Node> queue = new PriorityQueue<>(
			Comparator.comparingInt(o -> o.weight)
		);

		// 시작 노드까지의 거리는 0
		dist[firstNode] = 0;
		queue.offer(new Node(firstNode, 0));

		while (!queue.isEmpty()) {
			Node nowNode = queue.poll();  // 현재 정점

			visit[nowNode.number] = true;

			for (int i = 0; i < map[nowNode.number].size(); i++) {
				Node linkNode = map[nowNode.number].get(i);  // 현재 정점에서 이어진 정점

				if (!visit[linkNode.number]) {

					// 현재 거리가 더 적은 값일 경우
					if (dist[linkNode.number] > nowNode.weight + linkNode.weight) {
						dist[linkNode.number] = nowNode.weight + linkNode.weight;

						queue.offer(new Node(linkNode.number, dist[linkNode.number]));
					}
				}
			}
		}

		for (int i = 1; i <= totNodeNum; i++) {
			if (dist[i] == INF) {
				System.out.println("INF");
				continue;
			}
			System.out.println(dist[i]);
		}
	}
}

class Node {
	int number;  // 노드 번호
	int weight;

	public Node(int number, int weight) {
		this.number = number;
		this.weight = weight;
	}
}

// 메모리 초과....
// 최대 노드 수가 20_000 라서 20_000 * 20_000 가 필요한 문제
// => map 2차원 배열 -> 인접 리스트

// public class Main {
//
// 	private static int totNodeNum;
// 	private static int totEdgeNum;
//
// 	private static int[] dist;  // 최단 거리 비용
// 	private static int[][] map;
// 	private static boolean[] visit;
//
// 	private static final int INF = Integer.MAX_VALUE;
//
// 	public static void main(String[] args) throws Exception {
// 		Scanner scanner = new Scanner(System.in);
//
// 		totNodeNum = scanner.nextInt();
// 		totEdgeNum = scanner.nextInt();
//
// 		dist = new int[totNodeNum + 1];
// 		map = new int[totNodeNum + 1][totNodeNum + 1];
// 		visit = new boolean[totNodeNum + 1];
//
// 		int firstNode = scanner.nextInt();
//
// 		// 초기 설정
// 		for (int i = 1; i <= totNodeNum; i++) {
// 			dist[i] = INF;  // 모든 노드간 거리 무한대로 설정
// 			Arrays.fill(map[i], INF);  // 모든 간선을 초기화 (무한대)
// 		}
//
// 		for (int i = 0; i < totEdgeNum; i++) {
// 			int startNode = scanner.nextInt();
// 			int endNode = scanner.nextInt();
// 			int weight = scanner.nextInt();
//
// 			map[startNode][endNode] = weight; // start -> end 가중치
// 		}
//
// 		다익스트라(firstNode);
// 	}
//
// 	private static void 다익스트라(int firstNode) {
// 		PriorityQueue<Node> queue = new PriorityQueue<>(
// 			Comparator.comparingInt(o -> o.weight)
// 		);
//
// 		// 시작 노드까지의 거리는 0
// 		dist[firstNode] = 0;
// 		queue.offer(new Node(firstNode, 0));
//
// 		while (!queue.isEmpty()) {
// 			Node nowNode = queue.poll();  // 현재 정점
//
// 			visit[nowNode.number] = true;
//
// 			// 현재 정점과 연결된 간선들
// 			for (int i = 1; i <= totNodeNum; i++) {
//
// 				// 아직 방문x & 현재 정점에서 i 정점으로 가는 간선이 존재한 경우
// 				if (!visit[i] && map[nowNode.number][i] != INF) {
//
// 					// 현재 거리가 더 적은 거리일 경우
// 					if (dist[i] > map[nowNode.number][i] + nowNode.weight) {
// 						dist[i] = map[nowNode.number][i] + nowNode.weight;
//
// 						queue.offer(new Node(i, dist[i]));
// 					}
// 				}
// 			}
// 		}
//
// 		for (int i = 1; i <= totNodeNum; i++) {
// 			if (dist[i] == INF) {
// 				System.out.println("INF");
// 				continue;
// 			}
// 			System.out.println(dist[i]);
// 		}
// 	}
// }
//
// class Node {
// 	int number;  // 노드 번호
// 	int weight;
//
// 	public Node(int number, int weight) {
// 		this.number = number;
// 		this.weight = weight;
// 	}
// }
