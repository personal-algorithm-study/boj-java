package topological_sorting;

import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드3) ACM Craft - 성공
public class Boj1005_sol2 { // Queue 이용
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		Queue<Integer> q = new ArrayDeque<>();

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
			q.clear();

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
					q.offer(i);
				}
			}

			// 위상 정렬
			while (!q.isEmpty()) {
				Integer now = q.poll();
				if (now == win) {
					break;
				}

				for (Integer next : graph[now]) {
					inOrders[next]--;
					dp[next] = Math.max(dp[next], time[now] + dp[now]);
					if (inOrders[next] == 0) {
						q.offer(next);
					}
				}
			}
			sb.append(dp[win] + time[win]).append('\n');
		}
		System.out.println(sb);
	}
}
