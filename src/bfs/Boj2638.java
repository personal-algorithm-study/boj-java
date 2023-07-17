package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2638 {

	static int n;
	static int m;
	static int[][] graph;

	static final int[] dx = {0, 1, 0, -1};
	static final int[] dy = {1, 0, -1, 0};

	public int solve(int cheese) {
		Queue<int[]> q = new LinkedList<>();
		boolean[][] visited;
		int answer = 0;

		while (cheese > 0) {
			visited = new boolean[n][m];
			q.offer(new int[] {0, 0});
			answer++;

			while (!q.isEmpty()) {
				int[] now = q.poll();
				for (int i = 0; i < 4; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m)
						continue;
					if (visited[nx][ny])
						continue;

					if (graph[nx][ny] >= 1) {
						graph[nx][ny]++;
					} else {
						visited[nx][ny] = true;
						q.offer(new int[] {nx, ny});
					}
				}
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (graph[i][j] >= 3) {
						graph[i][j] = 0;
						cheese--;
					} else if (graph[i][j] == 2) {
						graph[i][j] -= 1;
					}
				}
			}
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] lengths = br.readLine().split(" ");

		n = Integer.parseInt(lengths[0]);
		m = Integer.parseInt(lengths[1]);
		int cheese = 0;

		graph = new int[n][m];

		for (int i = 0; i < n; i++) {
			graph[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt).toArray();

			for (int v : graph[i]) {
				cheese += v;
			}
		}
		System.out.println(new Boj2638().solve(cheese));
	}
}
