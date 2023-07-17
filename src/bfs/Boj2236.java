package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj2236 {

	int[] dx = {0, 1, 0, -1};
	int[] dy = {1, 0, -1, 0};

	int solve(int n, int m, String[] arr) {
		int[][][] visited = new int[n][m][2];
		Queue<int[]> q = new LinkedList<>();
		int answer = -1;

		visited[0][0][0] = 1;
		q.offer(new int[] {0, 0, 0});

		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0], y = now[1], broken = now[2];

			if (x == n - 1 && y == m - 1) {
				answer = visited[x][y][broken];
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
				if (visited[nx][ny][broken] != 0) continue;

				if (arr[nx].charAt(ny) == '0') {
					visited[nx][ny][broken] = visited[x][y][broken] + 1;
					q.offer(new int[] {nx, ny, broken});
				} else if (arr[nx].charAt(ny) == '1' && now[2] == 0) {
					visited[nx][ny][1] = visited[x][y][broken] + 1;
					q.offer(new int[] {nx, ny, 1});
				}
			}
		}

		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] lengths = br.readLine().split(" ");
		int n = Integer.parseInt(lengths[0]);
		int m = Integer.parseInt(lengths[1]);

		String[] arr = new String[n];
		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}

		System.out.println(new Boj2236().solve(n, m, arr));
	}
}
