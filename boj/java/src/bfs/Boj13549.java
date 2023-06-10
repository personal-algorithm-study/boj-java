package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj13549 {
	public static void main(String[] args) throws IOException {
		final int MAX = 100_000;
		final int INF = 1000_000_000;

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		int start = Integer.parseInt(inputs[0]);
		int end = Integer.parseInt(inputs[1]);

		int[] dp = new int[MAX + 1];

		for (int i = 0; i < MAX + 1; i++) {
			dp[i] = INF;
		}
		dp[start] = 0;
		Queue<Integer> q = new LinkedList<>();
		q.offer(start);

		while (!q.isEmpty()) {
			int now = q.poll();

			int[] next = {now - 1, now + 1};
			int doubled = now * 2;

			for (int i : next) {
				if (i >= 0 && i <= MAX && dp[now] + 1 < dp[i]) {
					dp[i] = dp[now] + 1;
					q.offer(i);
				}
			}

			if (doubled >= 0 && doubled <= MAX && dp[now] < dp[doubled]) {
				dp[doubled] = dp[now];
				q.offer(doubled);
			}
		}
		System.out.println(dp[end]);
	}
}