package twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Baekjoon$15961 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int plate = Integer.parseInt(st.nextToken()); // 접시의 수
        int varieties = Integer.parseInt(st.nextToken()); // 초밥의 최대 가짓수
        int event = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
        int coupon = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        int[] conveyor = new int[plate]; // 벨트 위의 초밥 정보
        int[] varietyCounts = new int[varieties + 1]; // 현재 범위에서 각 종류의 초밥이 몇 개가 있는지
        for (int i = 0; i < plate; i++) {
            conveyor[i] = Integer.parseInt(br.readLine());
        }

        int currentTypes = 0; // 현재 범위에서 초밥의 종류가 몇 개인지
        // 첫 범위를 설정
        for (int i = 0; i < event; i++) {
            // 이전에 고르지 않은 종류라면 초밥의 종류 수 추가
            if (varietyCounts[conveyor[i]] == 0) {
                currentTypes++;
            }
            varietyCounts[conveyor[i]]++;
        }

        // 초기 최댓값에 쿠폰을 반영
        int max = currentTypes + (varietyCounts[coupon] == 0 ? 1 : 0);
        for (int i = 1; i < plate; i++) {
            int prevSushi = conveyor[i - 1];
            int newSushi = conveyor[(i + event - 1) % plate];
            
            // 맨 앞에 있는 초밥 빼기
            varietyCounts[prevSushi]--;
            if (varietyCounts[prevSushi] == 0) {
                currentTypes--;
            }

            // 바로 다음 범위의 초밥 추가
            if (varietyCounts[newSushi] == 0) {
                currentTypes++;
            }
            varietyCounts[newSushi]++;

            max = Math.max(max, currentTypes + (varietyCounts[coupon] == 0 ? 1 : 0));
        }

        System.out.println(max);
    }
}
