package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

// (실버2) boj 1260 DFS와 BFS
public class Boj1260 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		StringBuilder dfsResult = new StringBuilder();
		StringBuilder bfsResult = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int v = Integer.parseInt(st.nextToken());

		List<List<Integer>> graph = new ArrayList<>();

		for (int i = 0; i < n + 1; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());

			int w = Integer.parseInt(st.nextToken());
			int u = Integer.parseInt(st.nextToken());

			graph.get(w).add(u);
			graph.get(u).add(w);
		}

		Deque<Integer> deque = new ArrayDeque<>();
		boolean[] visited = new boolean[n + 1];
		deque.offerLast(v);

		// dfs 탐색
		while (!deque.isEmpty()) {
			Integer now = deque.pollLast();
			if(visited[now]) continue;
			visited[now] = true;
			dfsResult.append(now).append(' ');

			graph.get(now).sort(Collections.reverseOrder());
			for (Integer next : graph.get(now)) {
				deque.offerLast(next);
			}
		}

		// 그래프 표현 방버 : 인저행렬, 인접리스트
		// 그ㅐ프 트리 정의
		// dfs, bfs 탐색

		visited = new boolean[n + 1];
		deque.offerLast(v);
		visited[v] = true;

		// bfs 탐색
		while (!deque.isEmpty()) {
			Integer now = deque.pollFirst();
			bfsResult.append(now).append(' ');

			graph.get(now).sort(Comparator.naturalOrder());
			for (Integer next : graph.get(now)) {
				if(visited[next]) continue;
				visited[next] = true;
				deque.offerLast(next);
			}
		}

		System.out.println(dfsResult);
		System.out.println(bfsResult);
	}
}
