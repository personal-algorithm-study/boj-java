package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (골드4) boj 15683 감시 - 성공
public class Boj15683 {
	static int n, m;
	static int answer = Integer.MAX_VALUE;
	static int cctvSize;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	static char[][] graph;
	static int[][] watchingCnt;

	static List<int[]> cctvPositions = new ArrayList<>();

	static void dfs(int cnt) {
		if (cnt == cctvSize) {
			int result = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (graph[i][j] == '0' && watchingCnt[i][j] == 0)
						++result;
				}
			}
			answer = Math.min(answer, result);
			return;
		}

		int[] now = cctvPositions.get(cnt);
		int cctvNum = graph[now[0]][now[1]] - '0';
		int[][] cctvDirections = Direction.cctvs[cctvNum];

		// explanation
		for (int i = 0; i < cctvDirections.length; i++) {
			mark(now, 1, cctvDirections[i]);
			dfs(cnt + 1);
			mark(now, -1, cctvDirections[i]);
		}
	}

	static void mark(int[] start, int filled, int[] direction) {
		int sx = start[0], sy = start[1];

		for (int j : direction) {
			int x = sx, y = sy;
			while (true) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				if (nx < 0 || nx >= n || ny < 0 || ny >= m)
					break;
				if (graph[nx][ny] == '6')
					break;
				x = nx;
				y = ny;
				watchingCnt[nx][ny] += filled;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// 입력 받기1
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		// 입력 받기2
		graph = new char[n][m];
		watchingCnt = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				graph[i][j] = st.nextToken().charAt(0);
				if (graph[i][j] != '0' && graph[i][j] != '6') {
					cctvPositions.add(new int[] {i, j});
					++cctvSize;
				}
			}
		}

		dfs(0);
		System.out.println(answer);
	}

	static class Direction {
		static final int[][] cctv1 = new int[][] {{0}, {1}, {2}, {3}};
		static final int[][] cctv2 = new int[][] {{0, 2}, {1, 3}};
		static final int[][] cctv3 = new int[][] {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
		static final int[][] cctv4 = new int[][] {{0, 1, 2}, {1, 2, 3}, {0, 2, 3}, {0, 1, 3}};
		static final int[][] cctv5 = new int[][] {{0, 1, 2, 3}};

		static final int[][][] cctvs = new int[][][] {null, cctv1, cctv2, cctv3, cctv4, cctv5};
	}
}