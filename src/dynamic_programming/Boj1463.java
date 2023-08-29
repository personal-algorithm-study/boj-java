package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (실버3) boj 1463 1로 만들기 - 성공
public class Boj1463 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] dp = new int[N + 1];

		dp[0] = 1;

		if (N < 1) {
			System.out.println(0);
		} else {
			for (int i = 2; i < N + 1; i++) {
				dp[i] = dp[i - 1] + 1;

				if (i % 2 == 0)
					dp[i] = Math.min(dp[i], dp[i / 2] + 1);
				if (i % 3 == 0)
					dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			System.out.println(dp[N]);
		}
	}
}
