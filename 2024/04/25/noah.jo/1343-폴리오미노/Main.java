import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String string = br.readLine();

        boolean flag = true;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            if (c == 'X') {
                int index = i;
                int count = 0;

                while (index < string.length() && string.charAt(index) != '.') {
                    index++;
                    count++;
                }

                if ((count & 1) == 1) {
                    flag = false;
                    break;
                }

                sb.append("AAAA".repeat(count / 4)).append("BB".repeat(count % 4 / 2));

                // '.'을 넣기 위해 -1
                i = index - 1;
            } else { // c == '.'
                sb.append('.');
            }
        }

        bw.write(flag ? sb.toString() : Integer.toString(-1));
        bw.flush();

        br.close();
        bw.close();
    }
}
