package greedyalgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon$1202 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<int[]> jewels = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.add(new int[]{weight, value});
        }
        // 무게가 작은 순으로 보석 정렬
        jewels.sort(Comparator.comparingInt(a -> a[0]));

        int[] backs = new int[k];
        for (int i = 0; i < k; i++) {
            backs[i] = Integer.parseInt(br.readLine());
        }
        // 무게 제한이 작은 순으로 가방 정렬
        Arrays.sort(backs);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long result = 0;
        int idx = 0;

        for (int i = 0; i < k; i++) {
            // 현재 가방에 담을 수 있는 보석들을 모두 PriorityQueue에 넣음
            while (idx < n && jewels.get(idx)[0] <= backs[i]) {
                pq.add(jewels.get(idx)[1]);
                idx++;
            }

            // 가방에 담을 수 있는 보석들 중 가치가 가장 큰 보석을 넣음
            if (!pq.isEmpty()) {
                result += pq.poll();
            }
        }
        System.out.println(result);
    }
}
/*  시간 초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        // 가치 순으로 내림차순 정렬, 만약 가치가 같으면 낮은 무게부터
        PriorityQueue<int[]> jewels = new PriorityQueue<>(n, (a, b) -> {
            if (a[1] == b[1])
                return a[0] - b[0];
            return b[1] - a[1];
        });
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());
            jewels.add(new int[]{weight, value});
        }

        // 무게가 낮은 가방부터 채워나가는 게 효율적
        PriorityQueue<Integer> bags = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            bags.add(Integer.parseInt(br.readLine()));
        }

        int money = 0;
        // 남은 보석과 가방이 둘 중 하나라도 없으면 종료
        while (!jewels.isEmpty() && !bags.isEmpty()) {
            LinkedList<Integer> temp = new LinkedList<>(); // 안 맞는 가방을 임시로 담아둠

            int[] jewel = jewels.poll(); // 보석은 담지 못하면 버려야함
            int weight = jewel[0];
            int value = jewel[1];

            // 맞는 가방이 나올 때까지 탐색
            while (!bags.isEmpty()) {
                Integer currentBag = bags.poll();
                if (currentBag >= weight) {
                    money += value;
                    break;
                }
                temp.add(currentBag);
            }
            // 다시 담을 수 있는 가방 목록에 넣음
            bags.addAll(temp);
        }
        System.out.println(money);
    }
*/
