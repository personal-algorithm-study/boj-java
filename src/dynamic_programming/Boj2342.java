package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (골드3) boj 2342 - Dance Dance Revolution - 성공
// 1. 그리디로 접근 -> 2. dp
class Boj2342 {
	static final int INF = 1000_000_000;

	static int calculateScore(int before, int after) {
		if (before == after) return 1;
		else if (before == 0) return 2;
		else if (Math.abs(before - after) == 2) return 4;
		return 3;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N;

		// 입력 받기
		List<Integer> list = new ArrayList<>();
		while (true) {
			int now = Integer.parseInt(st.nextToken());
			if (now == 0) break;
			list.add(now);
		}
		N = list.size();

		int[] arr = new int[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i + 1] = list.get(i);
		}

		int[][][] dp = new int[N + 1][5][5];
		for (int i = 0; i < N + 1; i++) {
			for (int l = 0; l < 5; l++) {
				for (int r = 0; r < 5; r++) {
					dp[i][l][r] = INF;
				}
			}
		}

		dp[0][0][0] = 0;
		for (int i = 0; i < N; i++) {
			for (int l = 0; l < 5; l++) {
				for (int r = 0; r < 5; r++) {
					if (dp[i][l][r] == INF) continue;
					int next = arr[i + 1];
					// dp 갱신
					dp[i + 1][next][r] = Math.min(dp[i + 1][next][r], dp[i][l][r] + calculateScore(l, next));
					dp[i + 1][l][next] = Math.min(dp[i + 1][l][next], dp[i][l][r] + calculateScore(r, next));
				}
			}
		}

		int answer = INF;
		for (int l = 0; l < 5; l++) {
			for (int r = 0; r < 5; r++) {
				answer = Math.min(answer, dp[N][l][r]);
			}
		}

		System.out.println(answer);
	}
}