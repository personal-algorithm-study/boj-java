package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj7576 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] lengths = br.readLine().split(" ");

		int m = Integer.parseInt(lengths[0]);
		int n = Integer.parseInt(lengths[1]);

		int cnt = 0;
		int day = 0;

		int[][] tomatoes = new int[n][m];
		Queue<int[]> q = new LinkedList<>();

		for (int i = 0; i < n; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				tomatoes[i][j] = Integer.parseInt(row[j]);
				if (tomatoes[i][j] == 0) {
					cnt++;
				} else if (tomatoes[i][j] == 1) {
					q.offer(new int[] {i, j});
				}
			}
		}

		while (!q.isEmpty()) {
			int size = q.size();
			if (cnt == 0) {
				break;
			}
			day++;
			for (int i = 0; i < size; i++) {
				int[] now = q.poll();

				for (int j = 0; j < 4; j++) {
					int nx = now[0] + dx[j];
					int ny = now[1] + dy[j];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m
							|| tomatoes[nx][ny] != 0)
						continue;
					tomatoes[nx][ny] = 1;
					cnt--;
					q.offer(new int[] {nx, ny});
				}
			}
		}

		if (cnt > 0) {
			day = -1;
		}
		System.out.println(day);
	}
}
