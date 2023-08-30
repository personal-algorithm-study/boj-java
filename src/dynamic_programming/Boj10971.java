package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버 2) boj 10971 외판원 순회2
public class Boj10971 {
	static int N;
	static int answer = Integer.MAX_VALUE;

	static int[] arr;
	static int[][] graph;

	static void dfs(int cnt, int flag, int total) {
		if (cnt == N) {
			int lastEdge = graph[arr[N - 1]][arr[0]];
			if (lastEdge == 0)
				return;
			answer = Math.min(answer, total + lastEdge);
			return;
		}

		if (total > answer)
			return;

		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			arr[cnt] = i;

			if (cnt == 0) {
				dfs(cnt + 1, flag | 1 << i, total);
			} else if (graph[arr[cnt - 1]][arr[cnt]] != 0) {
				dfs(cnt + 1, flag | 1 << i, total + graph[arr[cnt - 1]][arr[cnt]]);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// --- 입력 받기 ---
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		graph = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0, 0);
		System.out.println(answer);
	}
}
