package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2579 {
	public static void solution(int n, int[] arr) {
		if (n == 1) {
			System.out.println(arr[0]);
			return;
		}
		if (n == 2) {
			System.out.println(arr[0] + arr[1]);
			return;
		}
		if (n == 3) {
			System.out.println(Math.max(arr[0], arr[1]) + arr[2]);
			return;
		}

		int[] dp = new int[n];
		dp[0] = arr[0];
		dp[1] = arr[1] + arr[0];
		dp[2] = Math.max(arr[1] + arr[2], arr[0] + arr[2]);

		for (int i = 3; i < n; i++) {
			dp[i] = Math.max(arr[i] + arr[i - 1] + dp[i - 3], arr[i] + dp[i - 2]);
		}

		System.out.println(dp[n - 1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		solution(n, arr);

	}
}