package bfs;

import static java.lang.Character.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


// (골ㄷ1) 달이 차오른다, 가자 1194
public class Boj1194 {
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		char[][] graph = new char[N][M];
		boolean[][][] visited = new boolean[N][M][64];

		Queue<Position> q = new ArrayDeque<>();
		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < M; j++) {
				graph[i][j] = row.charAt(j);
				if (graph[i][j] == '0') {
					q.offer(new Position(i, j, 0, 0));
					visited[i][j][0] = true;
				}
			}
		}

		int answer = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Position now = q.poll();

			if (graph[now.x][now.y] == '1') {
				answer = Math.min(answer, now.dist);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				int nx = now.x + dx[i];
				int ny = now.y + dy[i];
				int nextKey = now.keys;

				if (nx < 0 || nx >= N || ny < 0 || ny >= M
						|| graph[nx][ny] == '#'
						|| visited[nx][ny][now.keys]) continue;
				if (isAlphabetic(graph[nx][ny])) {
					if (isLowerCase(graph[nx][ny])) {
						int order = graph[nx][ny] - 'a';
						nextKey = nextKey | 1 << order;
					} else {
						int order = graph[nx][ny] - 'A';
						if ((now.keys & 1 << order) == 0) continue;
					}
				}
				visited[nx][ny][nextKey] = true;
				q.offer(new Position(nx, ny, now.dist + 1, nextKey));
			}
		}

		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
	}

	static class Position {
		int x, y, dist, keys;

		public Position(int x, int y, int dist, int keys) {
			this.x = x;
			this.y = y;
			this.dist = dist;
			this.keys = keys;
		}
	}
}