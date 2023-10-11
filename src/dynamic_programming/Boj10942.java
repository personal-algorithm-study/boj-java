package dynamic_programming;

import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj10942 팰린드롬?
public class Boj10942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int N = parseInt(br.readLine());

		int[] arr = new int[N];
		boolean[][] dp = new boolean[N][N]; // dp[start][end] : start ~ end 팰린드롬 인지 t/f

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = parseInt(st.nextToken());
		}

		int M = parseInt(br.readLine());

		// 1자 dp 표시
		for (int i = 0; i < N; i++) {
			dp[i][i] = true;
		}

		// 2자 dp 표시
		for (int i = 0; i < N - 1; i++) {
			if (arr[i] == arr[i + 1])
				dp[i][i + 1] = true;
		}

		// start + 1이 갱신되지 않은채로 참조되어 올바른 결과가 안나온다.
		/*for (int start = 0; start < N; start++) {
			for (int end = start + 1; end < N; end++) {
				if (dp[start + 1][end - 1] && arr[start] == arr[end]) dp[start][end] = true;
			}
		}*/

		// 3자 이상 dp 표시
		for (int start = N - 1; start >= 0; start--) {
			for (int end = start + 2; end < N; end++) {
				if (dp[start + 1][end - 1] && arr[start] == arr[end])
					dp[start][end] = true;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int S = parseInt(st.nextToken()) - 1, E = parseInt(st.nextToken()) - 1;
			sb.append(dp[S][E] ? '1' : '0').append('\n');
		}

		System.out.println(sb);
	}
}
