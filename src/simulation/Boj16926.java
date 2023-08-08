package simulation;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버 1) boj16926 배열 돌리기1 - 성공
public class Boj16926 {

	static int r;

	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};

	static int[][] arr;
	static int[][] ans;

	static void dfs(int height, int width, int sx, int sy) {
		if (height <= 1 || width <= 1) { // 오답 height <= 0 || width <= 0
			return;
		}

		// 꼭지점 값
		int[] ex = {sx + height - 1, sx + height - 1, sx, sx};
		int[] ey = {sy, sy + width - 1, sy + width - 1, sy};

		// 네 방향에 대해서 회전
		for (int i = 0; i < 4; i++) {
			while (sx != ex[i] || sy != ey[i]) {
				int nx = sx + dx[i];
				int ny = sy + dy[i];

				sx = nx;
				sy = ny;

				int cnt = r % (2 * (height + width - 2)); // 오답 (2 * (height + width))
				int d = i;

				int rx = nx;
				int ry = ny;

				while (cnt > 0) {
					int diff = Math.max(abs(ex[d] - rx), abs(ey[d] - ry));
					if (diff >= cnt) {
						rx += cnt * dx[d];
						ry += cnt * dy[d];
						cnt = 0;
						continue;
					} else {
						rx = ex[d];
						ry = ey[d];
						cnt -= diff;
					}
					d = (d + 1) % 4;
				}
				ans[rx][ry] = arr[nx][ny];
			}
		}
		// 재귀 호출
		dfs(height - 2, width - 2, sx + 1, sy + 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		ans = new int[n][m];

		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				ans[i][j] = arr[i][j]; // 초기화 안해서 틀렸었음
			}
		}

		// dfs 호출
		dfs(n, m, 0, 0);

		//출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(ans[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}
