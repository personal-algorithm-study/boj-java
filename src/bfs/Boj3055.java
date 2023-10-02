package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드4) boj 3055 탈출 - 성공
public class Boj3055 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());

		int[] water = new int[2];
		int[] start = new int[2];
		char[][] arr = new char[R][C];

		List<int[]> ws = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = row.charAt(j);

				if (arr[i][j] == 'S') {
					start[0] = i;
					start[1] = j;
				} else if (arr[i][j] == '*') {
					ws.add(new int[] {i, j});
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		boolean[][] visited = new boolean[R][C];

		Queue<Position> q = new ArrayDeque<>();

		for (int[] w : ws) {
			q.offer(new Position(w[0], w[1], 0, 'W'));
		}

		q.offer(new Position(start[0], start[1], 0, 'D'));

		while (!q.isEmpty()) {
			Position now = q.poll();

			if (arr[now.x][now.y] == 'D') {
				answer = Math.min(answer, now.cnt);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C)
					continue;
				if (arr[nx][ny] == 'X'
						|| arr[nx][ny] == 'S'
						|| arr[nx][ny] == '*'
						|| visited[nx][ny]) {
					continue;
				}

				if (now.type == 'D') {
					visited[nx][ny] = true;
				} else {
					if (arr[nx][ny] == 'D')
						continue;
					else
						arr[nx][ny] = '*';
				}
				q.offer(new Position(nx, ny, now.cnt + 1, now.type));
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? "KAKTUS" : answer);
	}

	static class Position {
		int x, y, cnt;
		char type;

		public Position(int x, int y, int cnt, char type) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
			this.type = type;
		}
	}
}
