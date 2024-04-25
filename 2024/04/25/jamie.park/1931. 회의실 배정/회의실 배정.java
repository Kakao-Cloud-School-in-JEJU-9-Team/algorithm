import java.io.*;
import java.util.*;

/*
 * 풀이 과정
 * 끝나는 시간을 기준으로 정렬하고, 끝나는 시간이 빠른 회의들을 고르자
 * 1. priorityQueue로 입력 받기 -> int[] 데이터타입은 안됨 ..
 * 2. -> int[][] 로 입력 받기 -> [][0]: 시작, [][1]: 끝
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] meetings = new int[n][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            meetings[i][0] = Integer.parseInt(st.nextToken());
            meetings[i][1] = Integer.parseInt(st.nextToken());
        }

        // 회의 끝나는 시간 기준으로 정렬
        // 회의 끝나는 시간이 갖다면 시작 시간으로 정렬
        Arrays.sort(meetings, (o1, o2) -> {
            return o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1];
        });

        int prev_end = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            int[] check = meetings[i];
            if (check[0] >= prev_end) {
                // 검토하려는 회의의 시작 시간이 진행 중인 회의 종료 시간 이후에 시작하는 경우
                count += 1;
                prev_end = check[1];
            }
        }
        
        System.out.println(count);

    }
}