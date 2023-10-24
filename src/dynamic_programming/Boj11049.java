package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드3) boj11049 행렬 곱셈 순서 - 성공
public class Boj11049 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		final int INF = 1000_000_000;

		int N = Integer.parseInt(br.readLine());
		int[][] matrix = new int[N][2];
		int[][] dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			matrix[i][0] = Integer.parseInt(st.nextToken());
			matrix[i][1] = Integer.parseInt(st.nextToken());
		}

		for (int length = 1; length < N; length++) {
			for (int start = 0; start + length < N; start++) {
				int end = start + length;
				dp[start][end] = INF;
				for (int k = start; k < end; k++) {
					dp[start][end] = Math.min(dp[start][end],
							dp[start][k] + dp[k + 1][end] + matrix[start][0] * matrix[k][1] * matrix[end][1]);
				}
			}
		}
		System.out.println(dp[0][N - 1]);
	}
}
