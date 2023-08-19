package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드5) boj 2194 유닛이동시키기
public class Boj2194 {

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static int[][] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// n : row, m : column, a : row-범위, b : column-범위, k : 장애물 수
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		int k = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());

			int px = Integer.parseInt(st.nextToken()) - 1;
			int py = Integer.parseInt(st.nextToken()) - 1;
			arr[px][py] = -1;
		}

		// 시작 지점
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken()) - 1;
		int sy = Integer.parseInt(st.nextToken()) - 1;

		// 목표 지점
		st = new StringTokenizer(br.readLine());
		int ex = Integer.parseInt(st.nextToken()) - 1;
		int ey = Integer.parseInt(st.nextToken()) - 1;

		Queue<int[]> q = new ArrayDeque<>();

		// bfs
		q.offer(new int[] {sx, sy});
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0], y = now[1];

			if (x == ex && y == ey) {
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				boolean canMove = true;

				if (nx < 0 || nx >= n || ny < 0 || ny >= m
						|| arr[nx][ny] != 0)
					continue;

				// 범위, 장애물 체크
				for (int j = nx; j < nx + a; j++) {
					for (int l = ny; l < ny + b; l++) {
						if (j == nx && l == ny)
							continue;
						if (j < 0 || j >= n
								|| l < 0 || l >= m
								|| arr[j][l] == -1
						) {
							canMove = false;
							break;
						}
					}
				}

				// 다음 위치 큐에 삽입 & 기록
				if (canMove) {
					arr[nx][ny] = arr[x][y] + 1;
					q.offer(new int[] {nx, ny});
				}
			}
		}

		// for (int i = 0; i < n; i++) {
		// 	System.out.println(Arrays.toString(arr[i]));
		// }

		System.out.println(arr[ex][ey] != 0 ? arr[ex][ey] : -1);
	}
}
