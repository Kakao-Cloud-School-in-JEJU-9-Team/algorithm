package 백트래킹.스타트와링크;
/*
 * 24/04/10
 *
 * 백준(백트래킹) - 스타트와 링크
 *
 * https://www.acmicpc.net/problem/14889
 */

// Think
// - 팀 매칭 => (maxPerson/2 될 때까지)
// - start, link 팀을 true, false 로
// - 최소 값이 0이 되면 멈추기? => 굳이 탐색할 이유가 없다.
// - 재귀 호출시 (123), (132), (213) 등 중복되는 것 제거하기

import java.util.Scanner;

class Main {

	private static int maxPerson;
	private static int[][] scoreMap;
	private static boolean[] team;  // true: start 팀, false: link 팀
	private static int minScore = Integer.MAX_VALUE;  // result container

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		maxPerson = sc.nextInt();
		scoreMap = new int[maxPerson][maxPerson];
		team = new boolean[maxPerson];

		for (int i = 0; i < maxPerson; i++)
			for (int j = 0; j < maxPerson; j++)
				scoreMap[i][j] = sc.nextInt();

		matchTeam(0, 0);

		System.out.println(minScore);
	}

	private static void matchTeam(int idx, int trueTeamMemberNum) {
		if (trueTeamMemberNum >= maxPerson / 2) {  // 팀 완성시 두 팀의 차이 계산
			culScore();
			return;
		}

		for (int i = idx; i < maxPerson; i++) {
			if (!team[i]) {  // (123), (231), (132) 중복 해결 => 이미 방문한 것은 고려x
				team[i] = true;  // 방문시 true 팀(start 팀)으로
				matchTeam(i + 1, trueTeamMemberNum + 1);  // matchTeam::Call

				team[i] = false;  // 방문 완료 후 false 팀(link 팀)으로
			}
		}
	}

	private static void culScore() {
		// init team score
		int trueTeamTot = 0;
		int falseTeamTot = 0;

		for (int i = 0; i < maxPerson - 1; i++) {
			for (int j = i + 1; j < maxPerson; j++) {
				if (team[i] && team[j])  // i, j 가 true 팀인 경우
					trueTeamTot += scoreMap[i][j] + scoreMap[j][i];
				else if (!team[i] && !team[j])  // i, j 가 false 팀인 경우
					falseTeamTot += scoreMap[i][j] + scoreMap[j][i];
			}
		}

		int nowDifference = Math.abs(trueTeamTot - falseTeamTot);
		minScore = Math.min(nowDifference, minScore);

		if (minScore == 0) {  // 최소값이 이미 0인 경우 종료
			System.out.println(minScore);
			System.exit(0);
		}
	}
}
