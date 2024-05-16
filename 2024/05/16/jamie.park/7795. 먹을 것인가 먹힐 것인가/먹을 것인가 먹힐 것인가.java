import java.util.*;
import java.io.*;

public class Main {
  /*
   * A의 크기가 B의 크기보다 큰 쌍의 개수 찾기
   * : 큰 수부터 비교
   */
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int T = Integer.parseInt(st.nextToken());

    for (int i = 0; i < T; i++) {
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken()); // length of A
      int m = Integer.parseInt(st.nextToken()); // length of B

      int[] A = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < n; j++) {
        A[j] = Integer.parseInt(st.nextToken());
      }

      int[] B = new int[m];
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        B[j] = Integer.parseInt(st.nextToken());
      }

      Arrays.sort(A);
      Arrays.sort(B);

      int a = n - 1;
      int b = m - 1;
      int count = 0;

      while (a >= 0 && b >= 0) {
        if (A[a] > B[b]) {
          count += b + 1; // 인덱스 b까지의 숫자 개수 더하기
          a--;
        } else {
          b--;
        }
      }
      
      System.out.println(count);
    }
  }
}
