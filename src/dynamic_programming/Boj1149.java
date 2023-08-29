package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// (실버1) boj 1149 RGB거리 - 성공
public class Boj1149 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N + 1][3];

		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 3; j++) {
				dp[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < N + 1; i++) {
				dp[i][0] += Math.min(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] += Math.min(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] += Math.min(dp[i - 1][0], dp[i - 1][1]);
		}

		System.out.println(Arrays.stream(dp[N]).min().getAsInt());
	}
}
