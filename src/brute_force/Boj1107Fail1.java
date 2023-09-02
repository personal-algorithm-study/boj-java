package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드5) 리모컨 실패
public class Boj1107Fail1 {

	static int N;
	static int M;
	// static int cnt = Integer.MAX_VALUE;
	static boolean[] brokenButtons;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		// StringBuilder output = new StringBuilder();

		// 시작 채널 100
		// 시작 채널 100
		N = Integer.parseInt(br.readLine()); // 가고 싶은 채널

		int M = Integer.parseInt(br.readLine()); // 고장난 버튼의 개수
		brokenButtons = new boolean[10 + 1];


		if (M > 0)
			st = new StringTokenizer(br.readLine());

		for (int i = 0; i < M; i++) {
			int button = Integer.parseInt(st.nextToken());
			brokenButtons[button] = true;
		}

		if (M == 10) {
			System.out.println(Math.abs(N - 100));
		} else {
			// int l = Math.abs(N - 100);
			int r = dfs(100, 0, 0);

			System.out.println(r);
		}

	}

	private static int dfs(int n, int depth, int total) {
		if (n == N) {
			return total;
		}
		int l = Math.abs(N - n);

		int cnt = N;
		int r = 0;
		int reversedResult = 0, result = 0;

		while (cnt > 0) {
			int digit = -1;
			int now = cnt % 10;
			int minDiff = Integer.MAX_VALUE;
			++r;

			for (int i = 0; i < 10; i++) {
				if (brokenButtons[i]) continue;
				if (Math.abs(now - i) < minDiff) {
					minDiff = Math.abs(now - i);
					digit = i;
				}
			}
			reversedResult += digit;
			reversedResult *= 10;
			cnt /= 10;
		}
		reversedResult /= 10;

		while (reversedResult > 0) {
			result += reversedResult % 10;
			reversedResult /= 10;
			result *= 10;
		}
		result /= 10;

		if (l < r || n == result) return l + total;
		// r = Math.min(r, dfs(result, depth + 1));

		return dfs(result, depth + 1, total + r);
	}
}
