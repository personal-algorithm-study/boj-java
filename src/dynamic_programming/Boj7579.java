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

		int N = Integer.parseInt(st.nextToken()); // 앱의 수
		int M = Integer.parseInt(st.nextToken()); // 확보해야 하는 메모리

		int[] memories = new int[N];
		int[] costs = new int[N];
		int answer = 0;

		int[] dp = new int[10001];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			memories[i] = Integer.parseInt(st.nextToken());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < N; i++) {
			for (int j = 10000; j >= 0; j--) {
				if (j - costs[i] < 0)
					continue;
				int possibleMemory = dp[j - costs[i]] + memories[i];
				if (dp[j] < possibleMemory) {
					dp[j] = possibleMemory;
				}
			}
		}

		for (int i = 0; i < 10001; i++) {
			if (dp[i] >= M) {
				answer = i;
				break;
			}
		}
		System.out.println(answer);
	}
}