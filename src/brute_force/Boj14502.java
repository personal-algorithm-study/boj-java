package brute_force;

import java.util.*;
import java.io.*;


// (골드4) boj 14502 연구소 - 성공
public class Boj14502 {
	static int N;
	static int M;
	static int answer;

	static char[][] graph;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static Queue<int[]> q = new ArrayDeque<>();

	public static void dfs(int cnt) {
		if (cnt == 3) {
			answer = Math.max(answer, bfs());
			return ;
		}

		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (graph[r][c] != '0') continue;
				graph[r][c] = '1';
				dfs(cnt + 1);
				graph[r][c] = '0';
			}
		}
	}

	static int bfs() {
		int result = 0;
		char[][] simul = new char[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				simul[i][j] = graph[i][j];
				if (graph[i][j] == '2') q.offer(new int[]{i, j});
			}
		}

		while(!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if (simul[nx][ny] != '0') continue;
				simul[nx][ny] = '2';
				q.offer(new int[]{nx, ny});
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (simul[i][j] == '0') result++;
			}
		}

		q.clear();
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력받기 - 1
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];

		// 입력받기 - 2
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = st.nextToken().charAt(0);
			}
		}

		dfs(0);
		System.out.println(answer);
	}
}