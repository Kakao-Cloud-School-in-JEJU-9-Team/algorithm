import java.util.*;
import java.io.*;

public class Main {
  /*
   * 1이 k개 이상 있는 집합의 크기
   * : start=0. end=0 에서 출발해서
   * 1이 k개 채워지면 끝내기
   */
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());

    int[] dolls = new int[n];
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < n; i++) {
      dolls[i] = Integer.parseInt(st.nextToken());
    }

    int start = 0;
    int end = 0;
    int count = 0; // 현재 집합의 라이언 개수
    int minSize = Integer.MAX_VALUE;

    while (start < n && end < n) {
      while (start < n && dolls[start] != 1) { // 1을 찾을 때까지
        start++;
      }

      if (dolls[end] == 1) {
        count++;
      }

      if (count == k) {
        minSize = Math.min(end - start + 1, minSize);

        start++;
        count--; // start를 옮기면서 1 하나 사라짐
      }
      
      end++;
    }
    
    if (minSize == Integer.MAX_VALUE) {
      System.out.println(-1);
    } else {
      System.out.println(minSize);
    }
  }
}