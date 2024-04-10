import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<Integer> balloons = new ArrayList<>(); // remove 필요해서 ArrayList로 생성
        for (int i = 0; i < N; i++) { // 풍선 속 숫자 저장
            balloons.add(sc.nextInt());
        }

        ArrayList<Integer> positions = new ArrayList<>(); // [1~N] 풍선 번호 리스트
        for (int i = 0; i < N; i++) {
            positions.add(i+1);
        }

        int index = 0; // 터트릴 풍선의 인덱스 -> balloons 따라 업데이트 됨
        while (!balloons.isEmpty()) {
            int currentPosition = positions.get(index); // 터트릴 풍선의 번호
            int move = balloons.get(index); // 터트릴 풍선 속 숫자 = 이동 거리
            System.out.print(currentPosition + " ");

            balloons.remove(index); // 풍선 속 숫자 제거
            positions.remove(index); // 풍선 번호 리스트에서도 제거

            if (!balloons.isEmpty()) { // 다음으로 터트릴 풍선 인덱스 계산
                if (move > 0) { 
                    index = (index + move - 1) % balloons.size(); // 터진 풍선 제외
                } else {
                    index = (index + move) % balloons.size();
                    index = index < 0 ? index + balloons.size() : index; // 음수 인덱스라면 양수로 변환
                }
            }

        }
    }
}