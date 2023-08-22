package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (골드5) boj 13023 ABCDE
public class Boj13023 {
	static List<Integer>[] graph;
	static boolean[] visited;
	static char answer = '0';

	static void dfs(int now, int depth) {
		if (depth == 4) {
			answer = '1';
			return;
		}

		for (Integer next : graph[now]) {
			if (visited[next]) continue;
			visited[next] = true;
			dfs(next, depth + 1);
			visited[next] = false;

			if (answer == '1') return;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		// 그래프 초기화
		graph = new ArrayList[n + 1];

		for (int i = 0; i < n + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		visited = new boolean[n + 1];

		// 그래프 구성
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph[a].add(b);
			graph[b].add(a);
		}

		for (int i = 1; i < n + 1; i++) {
			visited[i] = true;
			dfs(i, 0);
			visited[i] = false;
		}

		System.out.println(answer);
	}
}
