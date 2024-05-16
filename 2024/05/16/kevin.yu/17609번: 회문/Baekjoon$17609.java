package twopointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Baekjoon$17609 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            char[] arr = br.readLine().toCharArray();
            int left = 0;
            int right = arr.length - 1;

            int count = 0;
            while (left <= right) {
                // 두 문자가 같으면 계속 진행
                if (arr[left] == arr[right]) {
                    left++;
                    right--;
                    continue;
                }

                // 만약 한 번이라도 두 문자가 다르다면, 왼쪽으로 한 번, 오른쪽으로 한 번 제거한 후 다시 탐색
                boolean leftFirst = search(arr, left + 1, right);
                boolean rightFirst = search(arr, left, right - 1);

                // 만약 남은 문자열이 둘 다 회문이 아니면 틀린 부분이 2개 이상이므로 2를 더하고, 하나라도 회문이면 1을 더함
                count += leftFirst || rightFirst ? 1 : 2;
                break;
            }
            System.out.println(count);
        }
    }

    private static boolean search(char[] arr, int left, int right) {
        while (left <= right) {
            if (arr[left] == arr[right]) {
                left++;
                right--;
            } else {
                return false; // 회문이 아님
            }
        }
        return true; // 회문임
    }
}