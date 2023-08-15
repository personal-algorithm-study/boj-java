package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (실버1) boj 5525 IOIOI - 성공
public class Boj5525 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		String s = br.readLine();

		int[] dp = new int[m];
		int ans = 0;

		for (int i = 0; i < 2; i++) {
			if (s.charAt(i) == 'I') {
				dp[i] = 1;
			}
		}

		for (int i = 2; i < m; i++) {
			char now = s.charAt(i);
			if (now == 'O') continue;
			if (s.charAt(i - 1) != 'O') {
				dp[i] = 1;
			} else {
				dp[i] = dp[i - 2] + 1;
				if (i - 2 * n >= 0 && dp[i - 2 * n] != 0 && dp[i] - dp[i - 2 * n] == n) {
					ans++;
				}
			}
		}

		System.out.println(ans);
	}
}