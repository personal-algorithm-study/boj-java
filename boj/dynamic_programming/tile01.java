import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {
    static int[] dp;
    static StringBuilder sb = new StringBuilder();

    public static void solution(int n) {
        if (n <= 2) {
            sb.append(n % 15746).append('\n');
            return;
        }
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; // 오버플로우 지점
        }

        sb.append(dp[n] % 15746).append('\n');
        return;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        dp = new int[n + 1];
        solution(n);

        System.out.println(sb.toString());
    }
}