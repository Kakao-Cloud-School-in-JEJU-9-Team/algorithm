package search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon$14426 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]); // 집합 S의 개수
        int M = Integer.parseInt(inputs[1]); // 비교할 문자열의 개수

        String[] setS = new String[N];
        for (int i = 0; i < N; i++) {
            setS[i] = br.readLine();
        }
        Arrays.sort(setS); // 이진탐색을 위해 오름차순 정렬

        String[] strs = new String[M];
        for (int i = 0; i < M; i++) {
            strs[i] = br.readLine();
        }

        int count = 0;
        for (String str : strs) {
            int front = 0;
            int rear = N - 1;

            while (front <= rear) {
                int idx = (front + rear) / 2;

                if (setS[idx].startsWith(str)) {
                    count++;
                    break;
                } else if (str.compareTo(setS[idx]) < 0) {
                    rear = idx - 1;
                } else {
                    front = idx + 1;
                }
            }
        }

        System.out.println(count);
    }
}
