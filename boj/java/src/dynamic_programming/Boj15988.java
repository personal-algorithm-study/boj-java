package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj15988 {
    static StringBuilder sb = new StringBuilder();
    // static int[] dp = new int[1_000_1001];

    public static void solution(int n) {
        if (n <= 2) {
            sb.append(n).append("\n");
            return;
        } else if (n == 3) {
            sb.append(4).append("\n");
            return; // 이 부분 누락 실수 ㅠㅠ
        }
        long[] dp = new long[n + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < n + 1; i++) {
            dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % 1000000009;
        }

        sb.append(dp[n]).append("\n");

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            solution(n);
        }

        System.out.println(sb.toString());

        return;
    }
}