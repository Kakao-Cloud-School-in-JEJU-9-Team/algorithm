import java.io.*;

public class Main {

    // 모듈러 연산
    private static final int MOD = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 0 = 공백, 1 = 좌, 2 = 우
        int[][] dp = new int[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % MOD;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % MOD;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % MOD;
        }

        bw.write(Integer.toString((dp[N][0] + dp[N][1] + dp[N][2]) % MOD));
        bw.flush();

        br.close();
        bw.close();
    }
}
