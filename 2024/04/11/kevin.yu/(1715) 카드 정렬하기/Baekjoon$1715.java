package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class StackAndQueue$1715 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 새로운 카드 묶음도 정렬 상태를 유지해야 하기 때문에 PriorityQueue 사용
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            queue.offer(Integer.parseInt(br.readLine()));
        }

        int result = 0; // 카드 묶음의 누적합
        while (queue.size() > 1) { // 더 이상 합칠 카드 묶음이 없다면 종료
            int sum = queue.poll() + queue.poll(); // 두 카드 묶음의 합
            result += sum;
            queue.offer(sum); // 하나로 합친 카드 묶음을 다시 넣음
        }
        System.out.println(result);
    }

/*  1. 실패 -> 카드 묶음을 합쳐서 새롭게 생성된 카드 묶음도 정렬해야 했음
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] decks = new int[n];
        for (int i = 0; i < n; i++) {
            decks[i] = Integer.parseInt(br.readLine());
        }

        if (n <= 2) {
            int result = 0;
            for (int deck : decks) {
                result += deck;
            }
            System.out.println(result);
            return;
        }

        Arrays.sort(decks);

        int accumulate = decks[0] + decks[1]; // 모든 묶음에 대한 누적합
        int sum = accumulate; // 현재 묶음의 개수
        for (int i = 2; i < decks.length; i++) {
            sum += decks[i];
            accumulate += sum;
        }
        System.out.println(accumulate);
    }
*/
}
