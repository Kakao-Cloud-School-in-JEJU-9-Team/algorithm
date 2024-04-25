import java.io.*;

/*
 * -가 있다면 그 값을 최대로
 * -가 나오면 괄호를 열고 다시 -가 나올 때 괄호를 닫는다.
 * '-'를 기준으로 split
 */
public class Main {

    static int sum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        String[] end = line.split("-"); // 마지막 수식
        for (int i = 0; i < end.length; i++) {
            int plusResult = eachSum(end[i]);
            if (i == 0) {
                sum += plusResult;
            } else {
                sum -= plusResult;
            }
        }

        System.out.println(sum);
    }
    
    static int eachSum(String each) {
        int temp = 0;

        // 각 수식 내 + 연산
        String[] plus = each.split("\\+"); // 메타문자는 '\\+' 또는 [+]로 사용
        for (String plusSum : plus) {
            temp += Integer.parseInt(plusSum);
        }
        return temp;
    }
}
