import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String string = br.readLine();

        string = string.replaceAll("XXXX", "AAAA");
        string = string.replaceAll("XX", "BB");

        bw.write(string.contains("X") ? Integer.toString(-1) : string);
        bw.flush();

        br.close();
        bw.close();
    }
}
