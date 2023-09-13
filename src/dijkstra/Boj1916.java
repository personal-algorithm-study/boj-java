package dijkstra;

import java.util.*;
import java.io.*;

// 다익스트라
// (골드5) boj1916 최소 비용 구하기
public class Boj1916 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// 정점의 갯수, 간선의 갯수
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		int[] dists = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			dists[i] = Integer.MAX_VALUE;
		}

		List<Edge>[] graph = new List[N + 1];

		// graph 초기화
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		int start;
		int end;
		int dist;

		// graph - 정점 사이 edge 연결
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			dist = Integer.parseInt(st.nextToken());

			graph[start].add(new Edge(end, dist));
		}

		// 출발지, 도착지
		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(start, 0));
		dists[start] = 0;

		while(!pq.isEmpty()) {
			Edge now = pq.poll();
			if (dists[now.to] < now.dist) continue;

			for (Edge next : graph[now.to]) {
				int totalDist = now.dist + next.dist;
				if (dists[next.to] > totalDist) {
					dists[next.to] = totalDist;
					pq.offer(new Edge(next.to, totalDist));
				}
			}
		}

		System.out.println(dists[end]);
	}

	static class Edge implements Comparable<Edge> {
		int to;
		int dist;

		public Edge(int to, int dist) {
			this.to = to;
			this.dist = dist;
		}

		@Override
		public int compareTo(Edge o) {
			return this.dist - o.dist;
		}
	}
}