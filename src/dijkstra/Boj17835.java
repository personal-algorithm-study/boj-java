package dijkstra;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드2) boj 17835 면접보는 승범이네 - 성공
public class Boj17835 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		long[] dist = new long[N];
		for (int i = 0; i < N; i++) {
			dist[i] = 1000_000_000_0L;
		}

		List<int[]>[] graph = new List[N];

		for (int i = 0; i < N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			graph[v].add(new int[] {u, c});
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>(comparingLong(e -> e.cost));

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			int target = Integer.parseInt(st.nextToken()) - 1;
			dist[target] = 0;
			pq.offer(new Edge(target, 0));
		}

		while (!pq.isEmpty()) {
			Edge now = pq.poll();
			if (dist[now.to] < now.cost)
				continue;

			for (int[] next : graph[now.to]) {
				if (dist[next[0]] > now.cost + next[1]) {
					dist[next[0]] = now.cost + next[1];
					pq.offer(new Edge(next[0], dist[next[0]]));
				}
			}
		}

		long answer = Integer.MIN_VALUE;
		int num = -1;

		for (int i = 0; i < N; i++) {
			if (dist[i] > answer) {
				answer = dist[i];
				num = i + 1;
			}
		}

		StringBuffer sb = new StringBuffer();
		sb.append(num).append('\n');
		sb.append(answer);

		System.out.println(sb);
	}

	static class Edge {
		int to;
		long cost;

		public Edge(int to, long cost) {
			this.to = to;
			this.cost = cost;
		}
	}
}