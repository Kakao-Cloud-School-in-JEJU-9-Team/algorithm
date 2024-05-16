import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] array = new long[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            array[i] = Long.parseLong(st.nextToken());
        }

        bw.write(Long.toString(twoPointer(M, array)));
        bw.flush();

        br.close();
        bw.close();
    }

    private static long twoPointer(long M, long[] array) {
        long max = 0; // 부피가 0인 경우는 없음

        int left = 0;
        int right = 0;

        long sum = array[0];

        while (right < array.length) {
            if (sum == M) {
                return M;
            } else if (sum < M) {
                max = Math.max(max, sum);

                right++;

                if (right >= array.length) {
                    continue;
                }

                sum += array[right];
            } else { // sum > M
                if (left == right) {
                    left++;
                    right++;

                    if (right >= array.length) {
                        continue;
                    }

                    // init
                    sum = array[right];
                } else {
                    sum -= array[left++];
                }
            }
        }

        return max;
    }
}
