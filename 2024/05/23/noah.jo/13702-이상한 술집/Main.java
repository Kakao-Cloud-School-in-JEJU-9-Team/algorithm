import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // right를 받기 위해 위에서 선언
        // 최소 막걸리 양은 0 가능
        // left를 0으로 설정 + mid가 0인 경우 ArithmeticException: / by zero 발생
        long left = 1;
        long right = 0;

        // 막걸리들?
        int[] makgeollis = new int[N];

        for (int i = 0; i < N; i++) {
            makgeollis[i] = Integer.parseInt(br.readLine());

            right = Math.max(right, makgeollis[i]);
        }

        while (left <= right) {
            long mid = (left + right) / 2;

            if (getMakgeolli(makgeollis, mid) < K) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(Long.toString(right));
        bw.flush();

        br.close();
        bw.close();
    }

    // mid = 막걸리 양
    private static int getMakgeolli(int[] makgeollis, long mid) {
        int count = 0;

        for (int makgeolli : makgeollis) {
            count += makgeolli / mid;
        }

        return count;
    }
}
