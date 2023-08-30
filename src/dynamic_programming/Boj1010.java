package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버5) boj1010 다리 놓기 - 성공
public class Boj1010 {
	static long[][] dp = new long[31][31];

	static long combinationNumber(int n, int r) {
		if (n == r || r <= 0) return 1;
		if (dp[n][r] != 0) return dp[n][r];
		return dp[n][r] = combinationNumber(n - 1, r - 1) + combinationNumber(n - 1, r);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		dp = new long[31][31];

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			sb.append(combinationNumber(n, r)).append('\n');
		}

		System.out.println(sb);
		br.close();
	}
}
