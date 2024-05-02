import java.util.*;

/*
 * "완벽하게 게임을 한다" = 각 플레이어가 최선을 다해 게임을 진행한다
 * -> 게임을 먼저 시작한 상근이가 이기는 쪽으로 게임을 한다.
 * 
 * 한 번이라도 상근이가 이길 수 있으면 상근win, 
 * 모든 경우에서 창영이가 이기면 창영win
 * 
 * for문에서의 로직 생각
 * 돌이 i-1, i-3, i-4 개 남았을 때 SK가 무조건 지는 경우가 있다면, (=SK가 마지막으로 돌을 꺼냈다면)
 * i개 남았을 때 CY가 1 또는 3 또는 4개를 꺼내게 해서 SK가 이길 수 있다.
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        boolean[] doesSKwin = new boolean[num + 1];
        doesSKwin[1] = false; // SK가 1개
        if (num >= 2) doesSKwin[2] = true;  // SK가 1개, CY가 1개
        if (num >= 3) doesSKwin[3] = false; // SK가 3개
        if (num >= 4) doesSKwin[4] = true; // SK가 4개 가져가서 이길 수 있음
        if (num >= 5) {
            for (int i = 5; i <= num; i++) {
                if (!doesSKwin[i - 1] || !doesSKwin[i - 3] || !doesSKwin[i - 4]) {
                    doesSKwin[i] = true;
                }
            }
        }

        if (doesSKwin[num]) {
            System.out.println("SK") ;
        } else {
            System.out.println("CY");
        }

        sc.close();
    }
}
