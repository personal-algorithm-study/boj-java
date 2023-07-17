package dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Boj9095 { // 1, 2, 3 더하기 1
    static StringBuilder sb = new StringBuilder();

    public static void solution(int n) {
        int[] dp = new int[11];
        for (int i = 1; i < 4; i++) {
            dp[i] = i;
        }
        dp[3] += 1;

        for (int i = 4; i < n + 1; i++) {
            dp[i] += dp[i - 3] + dp[i - 2] + dp[i - 1];
        }
        // for (int i = 0; i < dp.length; i++) {
        // System.out.print(dp[i] + " ");
        // }
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