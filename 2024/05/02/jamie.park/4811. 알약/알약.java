import java.util.*;
import java.io.*;

/*
풀이과정
    1. 미리 배열에 다 저장해놓고 테스트케이스 들어오면 바로 출력
    2. 2차원 배열로 W, H의 개수에 따른 경우의 수를 모두 적어놓는다.
    3. W의 개수 >= H의 개수 : W < H일 때 dp[W][H] = 0
    4. 값이 매우 커질 수 있으므로 dp 배열은 long[][]로 선언
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long[][] dp = new long[31][31]; // 남은 W, H로 만들 수 있는 문자열의 개수

        for (int i = 1; i < 31; i++) { // W
            for (int j = 0; j < 31; j++) { // H
                if (j == 0) {
                    dp[i][j] = 1; // W가 i개, H가 0개일 때는 WWW... 1개밖에 없음
                } else if (i < j) { // W >= H 이어야 함
                    continue;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if (num == 0) {
                return;
            } else {
                System.out.println(dp[num][num]);
            }
        }
    }
}