package greedyalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon$1339 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        String[] words = new String[n]; // 입력 받은 문자열을 저장하는 배열

        int alphabetSize = 26;
        int[] numbers = new int[alphabetSize]; // 알파벳에서 치환된 수를 저장하는 배열
        int[] priority = new int[alphabetSize]; // 알파벳의 우선순위를 저장하는 배열
        for (int i = 0; i < n; i++) {
            words[i] = br.readLine();

            // 자릿수가 높은 곳에 나왔을수록 높은 점수
            int priorityScore = 1;
            for (int j = words[i].length() - 1; j >= 0; j--) {
                char c = words[i].charAt(j);
                priority[c - 'A'] += priorityScore; // 알파벳 인덱스에 점수 추가 (ex. A=0, B=1, ..., Z=25)
                priorityScore *= 10;
            }
        }

        int currentNum = 9;
        while (true) {
            int maxIndex = -1;
            int maxScore = 0;
            // 점수가 제일 높은 알파벳부터 순차적으로 숫자 부여
            for (int i = 0; i < alphabetSize; i++) {
                if (priority[i] > maxScore) {
                    maxIndex = i;
                    maxScore = priority[i];
                }
            }
            // 인덱스가 -1이면 모든 수가 0이라는 의미임
            if (maxIndex == -1) {
                break;
            } else {
                numbers[maxIndex] = currentNum--;
                priority[maxIndex] = 0;
            }
        }

        int result = 0;
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                // 알파벳의 숫자 * 자릿수
                result += numbers[word.charAt(i) - 'A'] * (int) (Math.pow(10, word.length() - i - 1));
            }
        }
        System.out.println(result);
    }
}
