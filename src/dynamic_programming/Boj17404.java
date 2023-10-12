package dynamic_programming;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4)  RGB 거리 2 - 성공
public class Boj17404 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int min = Integer.MAX_VALUE;
		int N = Integer.parseInt(br.readLine());

		int[][] arr = new int[N][3];
		int[][] dp = new int[N][3];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
			arr[i][2] = Integer.parseInt(st.nextToken());
		}

		for (int start = 0; start < 3; start++) {
			// 처음 집 초기화
			for (int i = 0; i < 3; i++) {
				if (i == start)
					dp[0][i] = arr[0][start];
				else
					dp[0][i] = 10000;
			}

			for (int i = 1; i < N; i++) {
				dp[i][0] = arr[i][0] + min(dp[i - 1][1], dp[i - 1][2]);
				dp[i][1] = arr[i][1] + min(dp[i - 1][0], dp[i - 1][2]);
				dp[i][2] = arr[i][2] + min(dp[i - 1][0], dp[i - 1][1]);
			}

			for (int i = 0; i < 3; i++) {
				if (i == start)
					continue;
				min = min(min, dp[N - 1][i]);
			}
		}

		System.out.println(min);
	}
}
