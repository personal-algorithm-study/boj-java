package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드5) boj16928 뱀과 사다리 게임
public class Boj16928 {
	static final int MAX = 100;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] board = new int[MAX + 1];
		int[] dist = new int[MAX + 1];

		// board, dist 초기화
		for (int i = 2; i < MAX + 1; i++) {
			dist[i] = 1000_000_000;
		}

		// 사다리 + 뱀
		for (int i = 0; i < N + M; i++) {
			st = new StringTokenizer(br.readLine());
			int now = Integer.parseInt(st.nextToken());
			int next = Integer.parseInt(st.nextToken());
			board[now] = next;
		}

		dist[1] = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(1);

		// bfs
		while (!q.isEmpty()) {
			Integer now = q.poll();

			for (int i = 1; i <= 6; i++) {
				int nx = now + i;

				if (nx < 0 || nx > MAX)
					continue;

				int otherPath = board[nx];
				if (otherPath != 0) { // 사다리나 뱀이 있으면, 무조건 타야 한다.
					if (dist[otherPath] > dist[now] + 1) { // 최소값이 아니면 무시한다.
						dist[otherPath] = dist[now] + 1;
						q.offer(board[nx]);
					}
					continue;
				}
				// 뱀을 탄 경우가 최소값일 수 있으므로 dp로 실패

				if (dist[nx] <= dist[now] + 1)
					continue;

				dist[nx] = dist[now] + 1;
				q.offer(nx);
			}
		}
		System.out.println(dist[MAX]);
	}
}