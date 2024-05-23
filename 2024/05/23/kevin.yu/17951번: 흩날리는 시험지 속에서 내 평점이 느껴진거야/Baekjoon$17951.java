package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon$17951 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int papers = Integer.parseInt(st.nextToken()); // 시험지의 개수
        int groups = Integer.parseInt(st.nextToken()); // 나눠야 하는 개수

        int[] answers = new int[papers];
        int maxScore = 0; // 획득 가능한 최대 점수
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < papers; i++) {
            answers[i] = Integer.parseInt(st.nextToken());
            maxScore += answers[i];
        }

        int minScore = 0;
        int score = 0;
        while (minScore <= maxScore) {
            int mid = (minScore + maxScore) / 2;

            int result = divide(answers, mid); // 현재 점수를 최솟값으로 했을 때 얼마나 나눠지는지 확인
            if (result >= groups) {
                minScore = mid + 1;
                score = mid;
            } else {
                maxScore = mid - 1;
            }
        }

        System.out.println(score);
    }

    private static int divide(int[] answers, int mid) {
        int sum = 0;
        int count = 0;

        for (int answer : answers) {
            sum += answer;

            if (sum >= mid) {
                count++;
                sum = 0;
            }
        }

        return count;
    }
}
