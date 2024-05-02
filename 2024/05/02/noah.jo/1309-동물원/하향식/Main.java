import java.io.*;

public class Main {

    // 모듈러 연산
    private static final int MOD = 9901;

    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // 0 = 공백, 1 = 좌, 2 = 우
        dp = new int[N + 1][3];

        bw.write(Integer.toString((topDown(N, 0) + topDown(N, 1) + topDown(N, 2)) % MOD));
        bw.flush();

        br.close();
        bw.close();
    }

    private static int topDown(int height, int i) {
        if (height == 1) {
            return 1;
        }

        // Memoization
        if (dp[height][i] == 0) {
            if (i == 0) {
                dp[height][i] = (topDown(height - 1, 0) + topDown(height - 1, 1) + topDown(height - 1, 2)) % MOD;
            } else if (i == 1) {
                dp[height][i] = (topDown(height - 1, 0) + topDown(height - 1, 2)) % MOD;
            } else { // i == 2
                dp[height][i] = (topDown(height - 1, 0) + topDown(height - 1, 1)) % MOD;
            }
        }

        return dp[height][i];
    }
}
