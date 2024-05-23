import java.io.*;
import java.util.*;

// ! 시간초과
public class Main {

    private static final List<Bear> buffer = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 최소 도수 = 1, 최대 도수는 맥주들 중 max
        long left = 1;
        long right = 0;

        Bear[] bears = new Bear[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int preference = Integer.parseInt(st.nextToken());
            int abv = Integer.parseInt(st.nextToken());

            right = Math.max(right, abv);

            bears[i] = new Bear(preference, abv);
        }

        // 도수 기준 오름차순 정렬
        // - 선호도는 mid 도수 미만의 맥주 선택 후 재정렬하기 때문에 오름차순, 내림차순 상관 없음
        Arrays.sort(bears, Comparator.comparingInt(bear -> bear.abv));

        long minAbv = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(N, M, bears, mid)) {
                minAbv = Math.min(minAbv, mid);

                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(Long.toString(minAbv == Long.MAX_VALUE ? -1 : minAbv));
        bw.flush();

        br.close();
        bw.close();
    }

    // 맥주를 N개 이상 먹을 수 있는지, 선호도를 만족하는지 탐색
    // mid = 도수
    private static boolean check(int N, int M, Bear[] bears, long mid) {
        buffer.clear();

        // 도수에 대한 오름차순 정렬이기 때문에 bear[i].abv > mid라면 i 이후의 abv는 힝상 mid보다 크다.
        for (Bear bear : bears) {
            if (bear.abv > mid) {
                break;
            }

            buffer.add(bear);
        }

        // 최대 도수(mid)가 너무 낮기 때문에 N개 이상의 맥주를 선택할 수 없다.
        // 최대 도수(mid)를 증가시켜 선택 가능한 맥주의 양을 늘릴 필요가 있다.
        if (buffer.size() < N) {
            return false;
        }

        // 조건 1. 최소 N개의 맥주 만족

        // 최대 도수(mid) 미만의 맥주들 기준으로 선호도 내림차순
        buffer.sort((bear1, bear2) -> bear2.preference - bear1.preference);

        long totalPreference = 0;

        // 상위 선호도 N개의 도수 계산
        for (int i = 0; i < N; i++) {
            totalPreference += buffer.get(i).preference;
        }

        // 선호도 기준(M)을 만족하는가?
        // - 만족: 현재 맥주 리스트는 모든 조건(N개, M 이상)을 만족한다, 더 낮은 최대 도수(mid)도 가능한지 확인한다. (right = mid - 1)
        // - 불만: 현재의 맥주 리스트는 선호도를 만족시킬 수 없다. 도수를 높여 맥주 선택지를 늘린다. (left = mid + 1)
        return totalPreference >= M;
    }

    private static class Bear {

        final int preference;
        final int abv; // Alcohol by volume

        Bear(int preference, int abv) {
            this.preference = preference;
            this.abv = abv;
        }
    }
}
