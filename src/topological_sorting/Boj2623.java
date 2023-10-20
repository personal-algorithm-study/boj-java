package topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드3) boj 2623 음악 프로그램 - 성공
// 위상 정렬
public class Boj2623 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder answer = new StringBuilder();

		final int N = Integer.parseInt(st.nextToken());
		final int M = Integer.parseInt(st.nextToken());
		int cnt = 0;

		int[] indegrees = new int[N + 1];

		List<Integer>[] graph = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		Queue<Integer> q = new ArrayDeque<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int length = Integer.parseInt(st.nextToken());
			int now = Integer.parseInt(st.nextToken());

			for (int l = 0; l < length - 1; l++) {
				int next = Integer.parseInt(st.nextToken());
				graph[now].add(next);
				now = next;
			}
		}

		for (int i = 1; i < N + 1; i++) {
			for (Integer next : graph[i]) {
				indegrees[next]++;
			}
		}

		for (int i = 1; i < N + 1; i++) {
			if (indegrees[i] == 0)
				q.offer(i);
		}

		loop:
		while (!q.isEmpty()) {
			Integer now = q.poll();
			answer.append(now).append('\n');
			cnt++;

			for (Integer next : graph[now]) {
				indegrees[next]--;
				if (indegrees[next] == 0)
					q.offer(next);
				else if (indegrees[next] < 0)
					break loop;
			}
		}

		System.out.println(cnt == N ? answer.toString() : "0");
	}
}