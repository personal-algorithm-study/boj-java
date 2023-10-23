package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드3) boj7579 앱 - 성공
// 냅색 문제, 다이나믹 프로그래밍
public class Boj7579 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int TOTAL = 0;

		int[] memories = new int[N + 1];
		int[] costs = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memories[i + 1] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costs[i + 1] = Integer.parseInt(st.nextToken());
			TOTAL += costs[i + 1];
		}

		// dp 시작
		int answer = Integer.MAX_VALUE;
		int[] dp = new int[TOTAL + 1];

		for (int n = 1; n < N + 1; n++) {
			int memory = memories[n], cost = costs[n];
			for (int c = TOTAL; c > 0; c--) {
				if (c - cost < 0)
					break;
				dp[c] = Math.max(dp[c], dp[c - cost] + memory);
				if (dp[c] >= M)
					answer = Math.min(answer, c);
			}
		}
		System.out.println(answer);
	}
}