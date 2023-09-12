package bfs;

import java.util.*;
import java.io.*;

// 굳이 bfs만 할 필요 없음, 문제는 트리 지름이지만 풀이는 그래프 탐색
// (골드2) boj1167 트리의 지름 
public class Boj1167 {
	static int N;
	static int answer;

	static Node[] tree;
	static Queue<Node> q = new ArrayDeque<>();

	static int bfs(int num) {
		boolean[] visited = new boolean[N + 1];
		q.offer(tree[num]);
		tree[num].total = 0;
		
		visited[num] = true;
		int max = Integer.MIN_VALUE;
		int end = -1;

		while (!q.isEmpty()) {
			Node now = q.poll();

			for (Edge edge : now.edges) {
				if (visited[edge.node.num]) continue;
				visited[edge.node.num] = true;

				int acc = now.total + edge.dist;
				if (max < acc) {
					end = edge.node.num;
					max = acc;
					if (answer < max) answer = max;
				}
				tree[edge.node.num].total = acc;
				q.offer(tree[edge.node.num]);
			}
		}
		return end;
	}

	public static void main (String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());

		tree = new Node[N + 1];
		for (int i = 1; i < N + 1; i++) {
			tree[i] = new Node(i);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());

			while(st.hasMoreElements()) {
				int num = Integer.parseInt(st.nextToken());
				if (num == -1) break;

				int dist = Integer.parseInt(st.nextToken());
				tree[now].edges.add(new Edge(tree[num], dist));
			}
		}

		int a = bfs(1);
		int b = bfs(a);
		System.out.println(answer);
	}

	static class Node {
		int num;
		int total;
		final List<Edge> edges = new ArrayList<>();

		public Node (int num) {
			this.num = num;
		}

		public Node (int num, int total) {
			this(num);
			this.total = total;
		}
	}

	static class Edge {
		Node node;
		int dist;

		public Edge(Node node, int dist) {
			this.node = node;
			this.dist = dist;
		}
	}
}