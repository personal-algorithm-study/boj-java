package dfs;

import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드5) boj 9205 맥주 매시면서 걸어가기 - 성공
public class Boj9205 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st;
		Queue<Position> q = new ArrayDeque<>();

		int T = parseInt(br.readLine());

		// 테스트 케이스
		for (int t = 0; t < T; t++) {
			int N = parseInt(br.readLine());
			Position[] positions = new Position[N + 2];
			boolean[] visited = new boolean[N + 2];

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());

				int x = parseInt(st.nextToken()), y = parseInt(st.nextToken());
				positions[i] = new Position(x, y);
			}
			visited[0] = true;
			q.offer(new Position(positions[0].x, positions[0].y));

			while (!q.isEmpty()) {
				Position now = q.poll();

				for (int i = 0; i < N + 2; i++) {
					if (visited[i] || now.calculateDistance(positions[i]) > 1000)
						continue;
					visited[i] = true;
					q.offer(new Position(positions[i].x, positions[i].y));
				}
			}
			sb.append(visited[N + 1] ? "happy" : "sad");
			sb.append('\n');
		}

		System.out.println(sb);
	}

	static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int calculateDistance(Position p) {
			return Math.abs(this.x - p.x) + Math.abs(this.y - p.y);
		}
	}
}