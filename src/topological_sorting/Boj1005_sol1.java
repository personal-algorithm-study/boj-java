package topological_sorting;

import static java.lang.Integer.*;
import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드3) ACM Craft - 성공
public class Boj1005_sol1 { // PriorityQueue<Edge> + Edge class 이용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Queue<Edge> pq = new PriorityQueue<>(comparingInt((Edge o) -> o.weight));

		// 테스트 케이스
		int T = parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			int N = parseInt(st.nextToken());
			int K = parseInt(st.nextToken());

			int[] time = new int[N];
			int[] inOrders = new int[N];
			int[] dp = new int[N];

			List<Integer>[] graph = new List[N];
			pq.clear();

			// 시간 입력
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				time[i] = parseInt(st.nextToken());
				// dp[i] = time[i];
			}

			// graph 입력
			for (int i = 0; i < N; i++) {
				graph[i] = new ArrayList<>();
			}

			// edge 입력
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = parseInt(st.nextToken()) - 1;
				int y = parseInt(st.nextToken()) - 1;

				graph[x].add(y);
				inOrders[y]++;
			}

			// 마지막 노드 입력
			int win = parseInt(br.readLine()) - 1;

			// 진입 노드 삽입
			for (int i = 0; i < N; i++) {
				if (inOrders[i] == 0) {
					pq.offer(new Edge(i, time[i]));
				}
			}

			// 위상 정렬
			while (!pq.isEmpty()) {
				Edge now = pq.poll();
				if (now.to == win) {
					break;
				}

				for (Integer nextEdge : graph[now.to]) {
					inOrders[nextEdge]--;
					dp[nextEdge] = Math.max(dp[nextEdge], time[now.to] + dp[now.to]);
					if (inOrders[nextEdge] == 0) {
						// dp[nextEdge] = dp[now.to];
						pq.offer(new Edge(nextEdge, time[nextEdge]));
					}
				}
			}
			sb.append(dp[win] + time[win]).append('\n');
		}
		System.out.println(sb);
	}

	static class Edge {
		int to;
		int weight;

		public Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
