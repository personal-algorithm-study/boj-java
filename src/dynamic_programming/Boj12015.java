package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드 2) boj 12015 가장 긴 증가하는 수열2 - 성공
// dp
public class Boj12015 {
	static int[] dp;

	static int binarySearch(int now, int idx) {
		int s = 1, e = idx;
		int m;
		while (s < e) {
			m = s + (e - s) / 2;
			if (dp[m] == now)
				return m;
			else if (dp[m] > now)
				e = m;
			else
				s = m + 1;
		}
		return s;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력 받기
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int idx = 0;
		for (int i = 0; i < N; i++) {
			if (dp[idx] == arr[i])
				continue;
			else if (dp[idx] < arr[i])
				dp[++idx] = arr[i];
			int m = binarySearch(arr[i], idx);
			dp[m] = arr[i];
		}
		System.out.println(idx);
	}
}
