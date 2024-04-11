import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final int MAX_STATION = 1000001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] nextStations = new int[MAX_STATION];
        int[] previousStations = new int[MAX_STATION];

        st = new StringTokenizer(br.readLine());

        int firstStation = Integer.parseInt(st.nextToken()); // lastStation에 사용하기 위해 변수 선언

        int previousStation = firstStation;

        for (int i = 0; i < N - 2; i++) {
            int currentStation = Integer.parseInt(st.nextToken());

            nextStations[previousStation] = currentStation;
            previousStations[currentStation] = previousStation;

            previousStation = currentStation;
        }

        int lastStation = Integer.parseInt(st.nextToken());

        // N-1번째 다음 역 등록
        nextStations[previousStation] = lastStation;

        // 첫번째 역 등록
        previousStations[firstStation] = lastStation;

        // 마지막 역 등록
        nextStations[lastStation] = firstStation;
        previousStations[lastStation] = previousStation;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < M; i++) {
            int printStation;

            st = new StringTokenizer(br.readLine());

            String command = st.nextToken();

            int currentStation = Integer.parseInt(st.nextToken());

            if (command.equals("BN")) {
                int buildStation = Integer.parseInt(st.nextToken());

                int nextStation = nextStations[currentStation];

                printStation = nextStation;

                // 새로운 역 등록
                nextStations[buildStation] = nextStation;
                previousStations[buildStation] = currentStation;

                // 기존 역과 새로운 역 연결
                nextStations[currentStation] = buildStation;
                previousStations[nextStation] = buildStation;
            } else if (command.equals("BP")) {
                int buildStation = Integer.parseInt(st.nextToken());

                previousStation = previousStations[currentStation];

                printStation = previousStation;

                // 새로운 역 등록
                nextStations[buildStation] = currentStation;
                previousStations[buildStation] = previousStation;

                // 기존 역과 새로운 역 연결
                nextStations[previousStation] = buildStation;
                previousStations[currentStation] = buildStation;
            } else if (command.equals("CN")) {
                int removeStation = nextStations[currentStation];

                printStation = removeStation;

                int nextStation = nextStations[removeStation];

                // 역 폐쇄
                nextStations[currentStation] = nextStation;
                previousStations[nextStation] = currentStation;
            } else { // command.equals("CP")
                int removeStation = previousStations[currentStation];

                printStation = removeStation;

                previousStation = previousStations[removeStation];

                // 역 폐쇄
                nextStations[previousStation] = currentStation;
                previousStations[currentStation] = previousStation;
            }

            sb.append(printStation).append('\n');
        }

        bw.write(sb.toString().trim());
        bw.flush();

        br.close();
        bw.close();
    }
}
