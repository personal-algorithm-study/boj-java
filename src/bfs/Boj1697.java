package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

// (실버1) boj1697 숨바꼭질 - 성공
public class Boj1697 {
	static int[] dx = {-1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] input = br.readLine().split(" ");

		final int MAX = 100_000;
		int[] dp = new int[MAX + 1];

		int start = Integer.parseInt(input[0]);
		int end = Integer.parseInt(input[1]);

		int cnt = 0;
		for (int i = start; i < MAX + 1; i++) {
			dp[i] = cnt++;
		}

		cnt = 0;
		for (int i = start; i >= 0; i--) {
			dp[i] = cnt++;
		}

		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] {start, 0});

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int nx;

			if (now[0] == end) {
				break;
			}

			for (int i = 0; i < 2; i++) {
				nx = now[0] + dx[i];
				if (nx >= 0 && nx <= MAX && dp[nx] >= now[1] + 1) {
					dp[nx] = now[1] + 1;
					q.offer(new int[] {nx, now[1] + 1});
				}
			}
			nx = now[0] * 2;
			if (nx <= MAX) {
				if (dp[nx] >= now[1] + 1) {
					dp[nx] = now[1] + 1;
					q.offer(new int[] {nx, now[1] + 1});
				}
			}
		}
		System.out.println(dp[end]);
	}
}