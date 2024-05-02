import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        // [0] = max, [1] = min
        int[][] dp = new int[2][3];

        StringTokenizer st = new StringTokenizer(br.readLine());

        dp[0][0] = dp[1][0] = Integer.parseInt(st.nextToken());
        dp[0][1] = dp[1][1] = Integer.parseInt(st.nextToken());
        dp[0][2] = dp[1][2] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int v0 = Integer.parseInt(st.nextToken());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            int previousZero = dp[0][0];
            int previousTwo = dp[0][2];

            dp[0][0] = Math.max(dp[0][0], dp[0][1]) + v0;
            dp[0][2] = Math.max(dp[0][1], dp[0][2]) + v2;
            dp[0][1] = Math.max(previousZero, Math.max(dp[0][1], previousTwo)) + v1;

            previousZero = dp[1][0];
            previousTwo = dp[1][2];

            dp[1][0] = Math.min(dp[1][0], dp[1][1]) + v0;
            dp[1][2] = Math.min(dp[1][1], dp[1][2]) + v2;
            dp[1][1] = Math.min(previousZero, Math.min(dp[1][1], previousTwo)) + v1;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            max = Math.max(max, dp[0][i]);
            min = Math.min(min, dp[1][i]);
        }

        bw.write(max + " " + min);
        bw.flush();

        br.close();
        bw.close();
    }
}
