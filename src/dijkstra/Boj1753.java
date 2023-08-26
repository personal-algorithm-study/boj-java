package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드4) boj 1753 최단 경로
public class Boj1753 {
	static final int INF = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int V = Integer.parseInt(st.nextToken()); // 정점 수
		int E = Integer.parseInt(st.nextToken()); // 간선 수
		int K = Integer.parseInt(br.readLine()) - 1; // 시작점

		List<Edge>[] graph = new List[V]; // 그래프 구성 - 인접 리스트

		for (int i = 0; i < V; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken()) - 1; // 시작점
			int v = Integer.parseInt(st.nextToken()) - 1; // 도착점
			int w = Integer.parseInt(st.nextToken()); // 가중치

			graph[u].add(new Edge(v, w));
		}

		int[] dist = new int[V];

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		dist[K] = 0;
		// PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.weight - e2.weight);
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
		pq.offer(new Edge(K, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			for (Edge edge : graph[now.end]) {
				int acc = dist[now.end] + edge.weight;
				if (dist[edge.end] > acc) {
					dist[edge.end] = acc;
					pq.offer(new Edge(edge.end, dist[edge.end]));
				}
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < V; i++) {
			if (i == K) sb.append('0');
			else if (dist[i] == INF) sb.append("INF");
			else sb.append(dist[i]);
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static class Edge {
		int end;
		int weight;

		public Edge(int end, int weight) {
			this.end = end;
			this.weight = weight;
		}
	}
}
