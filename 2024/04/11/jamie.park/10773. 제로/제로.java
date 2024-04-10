import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int K = sc.nextInt(); // 전체 수의 개수
        Stack<Integer> stack = new Stack<>(); // 수 담을 스택

        for (int i = 0; i < K; i++) {
            int n = sc.nextInt();
            if (n == 0) {
                stack.pop();
            } else {
                stack.push(n);
            }
        }

        int sum = 0;
        for (int num : stack) {
            sum += num;
        }
        System.out.println(sum);

    }
}
