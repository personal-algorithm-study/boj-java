package prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] arr = new int[n][n];
		int[][] dp = new int[n + 1][n + 1];

		for (int i = 0; i < n; i++) {
			String[] inputs = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				arr[i][j] = Integer.parseInt(inputs[j]);
			}
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				dp[i][j] = dp[i - 1][j] + dp[i][j - 1] - dp[i - 1][j - 1] + arr[i - 1][j - 1];
			}
		}

		int max = dp[1][1];
		for (int k = 0; k < n; k++) {
			for (int i = 1; i < n - k + 1; i++) {
				for (int j = 1; j < n - k + 1; j++) {
					max = Math.max(max, dp[i + k][j + k] - dp[i - 1][j + k] - dp[i + k][j - 1] + dp[i - 1][j - 1]);
				}
			}
		}

		System.out.println(max);
	}
}