package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


// (실버2) boj 11725 트리의 부모 찾기
public class Boj11725 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		List<Integer>[] tree = new List[N + 1];
		int[] parent = new int[N + 1];

		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}


		for (int i = 0; i < N - 1; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());

			tree[u].add(v);
			tree[v].add(u);
		}

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);

		while(!q.isEmpty()) {
			Integer now = q.poll();

			for (Integer next : tree[now]) {
				if (parent[next] != 0) continue;
				parent[next] = now;
				q.offer(next);
			}
		}

		for (int i = 2; i < N + 1; i++) {
			output.append(parent[i]).append('\n');
		}
		System.out.println(output);
	}
}
