import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        String string = br.readLine();

        // stack
        int popCount = 0;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < N; i++) {
            int value = string.charAt(i) - '0';

            if (popCount == M) {
                // stack.push(value)
                sb.append(value);
                continue;
            }

            // stack.isEmpty()
            if (sb.length() == 0) {
                // stack.push(value)
                sb.append(value);
                continue;
            }

            // !stack.isEmpty() && stack.peek() < value
            while (sb.length() != 0 && sb.charAt(sb.length() - 1) - '0' < value) {
                popCount++;

                // stack.pop()
                sb.deleteCharAt(sb.length() - 1);

                if (popCount == M) {
                    break;
                }
            }

            // stack.push(value)
            sb.append(value);
        }

        // 뒤에 숫자를 빼야하는 경우
        if (popCount != M) {
            sb.setLength(N - M);
        }

        bw.write(sb.toString().trim());
        bw.flush();

        br.close();
        bw.close();
    }
}
