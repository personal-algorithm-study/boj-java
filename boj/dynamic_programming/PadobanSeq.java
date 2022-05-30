import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.IOException;

public class PadobanSeq {
    static long[] dp = new long[101];
    static StringBuilder sb = new StringBuilder();

    public static void solution(int n) {
        if (dp[n] != 0) {
            sb.append(dp[n]).append('\n');
            return;
        }

        int j = 1;

        for (int i = 6; i < n + 1; i++) {
            dp[i] = dp[i - 1] + dp[j++];
        }

        // System.out.println(Arrays.toString(dp));
        sb.append(dp[n]).append('\n');

        return;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        dp[4] = 2;
        dp[5] = 2;
        dp[6] = 3;

        for (int i = 0; i < t; i++) {
            int n = Integer.parseInt(br.readLine());
            solution(n);
        }

        System.out.println(sb.toString());
    }
}