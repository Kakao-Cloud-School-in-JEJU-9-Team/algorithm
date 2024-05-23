import java.util.*;
import java.io.*;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    String[][] conditions = new String[N][2];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      conditions[i][0] = st.nextToken(); // label
      conditions[i][1] = st.nextToken(); // number
    }

    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < M; i++) {
      int target = Integer.parseInt(br.readLine());
      
      int start = 0;
      int end = N - 1;
      
      while (start <= end) {
        int mid = (start + end) / 2;

        int currentCriteria = Integer.parseInt(conditions[mid][1]);
        if (currentCriteria < target) {
          start = mid + 1;
        } else {
          end = mid - 1;
        }
      }
      
      sb.append(conditions[end + 1][0]).append("\n");
    }
    System.out.println(sb.toString());

  }
}