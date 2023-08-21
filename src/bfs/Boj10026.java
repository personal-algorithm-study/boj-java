package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

// (골드5) boj10026 적록색약
public class Boj10026 {

	static int n;
	// 색맹 아닌 사람 결과
	static int result1 = 0;
	// 색맹인 사람 결과
	static int result2 = 0;

	static Queue<int[]> q = new ArrayDeque<>();
	static char[][] arr;
	static boolean[][] visited;

	static int[] dx = new int[] {0, 1, 0, -1};
	static int[] dy = new int[] {1, 0, -1, 0};

	static void bfs(int x, int y, boolean mode) {
		q.offer(new int[] {x, y});
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int d = 0; d < 4; ++d) {
				int nx = now[0] + dx[d];
				int ny = now[1] + dy[d];

				if (nx < 0 || nx >= n || ny < 0 || ny >= n)
					continue;
				if (visited[nx][ny])
					continue;
				if (isSame(x, y, nx, ny, mode)) {
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
		}

		if (mode)
			++result1;
		else
			++result2;
	}

	// mode : true -> 색맹 아닌것
	static boolean isSame(int x, int y, int nx, int ny, boolean mode) {
		if (arr[x][y] == arr[nx][ny]) return true;
		if (!mode) {
			return (arr[x][y] == 'G' && arr[nx][ny] == 'R')
					|| (arr[x][y] == 'R' && arr[nx][ny] == 'G');
		}
		return false;
	}

	// graph를 순회하면서 bfs 순회
	static void process(boolean mode) {
		visited = new boolean[n][n];
		for (int i = 0; i < n; ++i) {
			for (int j = 0; j < n; ++j) {
				if (visited[i][j])
					continue;
				bfs(i, j, mode);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 행렬의 크기 입력
		n = Integer.parseInt(br.readLine());
		arr = new char[n][n];

		// 배열 입력 받기
		for (int i = 0; i < n; i++) {
			String row = br.readLine();
			for (int j = 0; j < n; j++) {
				arr[i][j] = row.charAt(j);
			}
		}

		// 진행하기
		boolean mode = true;
		for (int i = 0; i < 2; ++i) {
			process(mode);
			mode = !mode;
		}
		System.out.println(result1 + " " + result2);
	}
}