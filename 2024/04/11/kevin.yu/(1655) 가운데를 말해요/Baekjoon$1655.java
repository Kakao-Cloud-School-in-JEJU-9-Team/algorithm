package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Baekjoon$1655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> bigger = new PriorityQueue<>(); // 큰 값들을 저장
        PriorityQueue<Integer> smaller = new PriorityQueue<>(Comparator.reverseOrder()); // 작은 값들을 역순 저장

        boolean flag = true; // true면 smaller에, false면 bigger에 들어가야 함
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            if (flag) {
                // 큰 수 중 가장 작은 수가 현재 값보다 작으면 자리 교체
                if (smaller.isEmpty() || bigger.peek() > num) {
                    smaller.offer(num);
                } else {
                    smaller.offer(bigger.poll());
                    bigger.offer(num);
                }
                flag = false;
            } else {
//                if (bigger.isEmpty() || smaller.peek() < num) // 2번째 시도 실패 -> isEmpty()가 문제였음
                // 작은 수 중 가장 큰 값이 현재 값보다 크면 자리 교체
                if (smaller.peek() < num) {
                    bigger.offer(num);
                } else {
                    bigger.offer(smaller.poll());
                    smaller.offer(num);
                }
                flag = true;
            }
            builder.append(smaller.peek()).append("\n"); // 시간 제한이 빡빡해서 StringBuilder로 모아서 한 번만 출력
        }
        System.out.println(builder);
    }

/*  시간 초과 실패
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        LinkedList<Integer> list = new LinkedList<>();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            boolean flag = true;
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j) > num) {
                    list.add(j, num);
                    flag = false;
                    break;
                }
            }
            if (flag)
                list.add(num);

            int index = list.size() / 2;
            if (list.size() % 2 == 0)
                index--;

            builder.append(list.get(index)).append("\n");
        }
        System.out.println(builder);
    }
*/
}
