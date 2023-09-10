package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (골드5) boj 9251 LCS
public class Boj9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String a = br.readLine();
		String b = br.readLine();

		int aLength = a.length();
		int bLength = b.length();

		int[][] dp = new int[aLength + 1][bLength + 1];

		for (int i = 1; i < aLength + 1; i++) {
			for (int j = 1; j < bLength + 1; j++) {
				if (a.charAt(i - 1) != b.charAt(j - 1)) {
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
				} else {
					dp[i][j] = dp[i - 1][j - 1] + 1;
				}
			}
		}
		System.out.println(dp[aLength][bLength]);
	}
}
