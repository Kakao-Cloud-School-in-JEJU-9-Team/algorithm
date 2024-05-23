import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int T = Integer.parseInt(br.readLine());

    for (int t = 0; t < T; t++) {

      int N = Integer.parseInt(br.readLine());
      int[] base = new int[N];
      String[] input = br.readLine().split(" ");
      for (int i = 0; i < N; i++) {
        base[i] = Integer.parseInt(input[i]);
      }
      Arrays.sort(base);

      int M = Integer.parseInt(br.readLine());
      int[] targetNums = new int[M];
      String[] input2 = br.readLine().split(" ");
      for (int i = 0; i < M; i++) {
        targetNums[i] = Integer.parseInt(input2[i]);
      }

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < M; i++) {
        int target = targetNums[i];

        if (BinarySearch(base, target)) {
          sb.append(1).append("\n");
        } else {
          sb.append(0).append("\n");
        }
      }
        sb.deleteCharAt(sb.length() - 1); // 마지막 공백 지우기

      System.out.println(sb.toString());
    }
  }
  
  private static boolean BinarySearch(int[] arr, int target) {
    int start = 0;
    int end = arr.length - 1;
        
    while (start <= end) {
      int mid = (start + end) / 2;

      if (arr[mid] == target) {
        return true;
      } else if (arr[mid] < target) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
    }
    return false;
  }
}