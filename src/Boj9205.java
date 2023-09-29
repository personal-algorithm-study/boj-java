import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드5) boj 9205 맥주 매시면서 걸어가기 - 실패
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
			q.offer(new Position(positions[0].x, positions[1].y));

			while (!q.isEmpty()) {
				Position now = q.poll();

				for (int i = 0; i < N + 1; i++) {
					if (visited[i] || now.calculateDistance(positions[i]) > 1000) continue;
					visited[i] = true;
					q.offer(new Position(positions[i].x, positions[i].y));
				}
			}
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


/*

 - 한 박스 맥주 20명 이하
 - 50미터에 한 병씩
 - 편의점 0 ~ 100
 - 편의점에서 맥주 충전 가능

 */