import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] array = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;

        int min = Integer.MAX_VALUE;

        int count = array[0] == 1 ? 1 : 0;

        while (right < N) {
            if (count == K) {
                min = Math.min(min, right - left + 1);

                if (array[left] == 1) {
                    count--;
                }

                left++;
            } else { // count < K
                right++;

                if (right < N && array[right] == 1) {
                    count++;
                }
            }
        }

        bw.write(Integer.toString(min == Integer.MAX_VALUE ? -1 : min));
        bw.flush();

        br.close();
        bw.close();
    }
}
