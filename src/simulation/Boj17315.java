package simulation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// (골드3) boj 17315 캐슬 디펜스
public class Boj17315 {

	static int n;
	static int m;
	static int d;
	static int ENEMY;

	static int distance;
	static int flag;

	static final int[] dx = {0, -1, 0};
	static final int[] dy = {-1, 0, 1};
	static Queue<int[]> q = new LinkedList<>();
	static int[][] initialMap;
	static Set<Position> removed = new HashSet<>();

	static boolean np() {
		int i = 0;

		while ((flag & 1 << i + 1) >= (flag & 1 << i))
			++i;

		if (i == m - 1)
			return false;

		int j = 0;
		while ((flag & 1 << j) == 0)
			j++;
		bitSwap(i + 1, j);

		int k = 0;
		while (i > k) {
			bitSwap(i--, k++);
		}
		return true;
	}

	static void bitSwap(int a, int b) {
		if (((flag >> a) & 1) != ((flag >> b) & 1)) {
			int mask = (1 << a) | (1 << b);
			flag = flag ^ mask;
		}
	}

	static void bfs(int[][] map) {
		distance = d;
		boolean[][] visited = new boolean[n][m];

		while (distance > 0) {
			int size = q.size();

			for (int i = 0; i < size; i++) {
				int[] now = q.poll();
				// System.out.println("now = " + now[0] + ", " + now[1]);

				for (int j = 0; j < 3; j++) {
					int nx = now[0] + dx[j];
					int ny = now[1] + dy[j];

					if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) {
						continue;
					}

					if (map[nx][ny] == 1) {
						// System.out.println("nx, ny = " + nx + ", " + ny);
						removed.add(new Position(nx, ny));
						q.clear();
						return;
					}
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
			--distance;
		}
		q.clear();
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./static/input5.txt"));

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		initialMap = new int[n][m];
		int[][] map = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				initialMap[i][j] = Integer.parseInt(st.nextToken());
				map[i][j] = initialMap[i][j];
				if (map[i][j] == 1)
					++ENEMY;
			}
		}

		int answer = 0;
		// next permutation을 위한 비트 셋팅
		for (int i = 0; i < 3; i++) {
			flag |= 1 << i;
		}

		// next permutation 으로 조합 구하기
		do {
			map = new int[n][m];

			for (int i = 0; i < n; i++) {
				if (m >= 0)
					System.arraycopy(initialMap[i], 0, map[i], 0, m);
			}

			int enemyCnt = ENEMY;
			int score = 0;

			while (enemyCnt > 0) {
				// 궁수가 공격할 대상 찾기
				// System.out.println(Integer.toBinaryString(flag));
				int highestBit = 0;
				int value = flag;
				while (value > 0) {
					value >>= 1;
					++highestBit;
				}
				for (int i = 0; i <= highestBit; i++) {
					if ((flag & 1 << i) != 0) {
						// System.out.println("i = " + i);
						q.offer(new int[] {n, i});
						bfs(map);
					}
				}

				//debugging
/*
				System.out.println("removed = ");
				for (Position p : removed) {
					System.out.println(p);
				}
*/

				// 적 제거시키기
				for (Position p : removed) {
					map[p.x][p.y] = 0;
					--enemyCnt;
					++score;
				}
				removed.clear();

				// 적 이동 시키기1 - 범위 넘어간 적 제외
				for (int i = 0; i < m; i++) {
					if (map[n - 1][i] == 1) {
						map[n - 1][i] = 0;
						--enemyCnt;
					}
				}

				// 적 이동 시키기2 - 한 행씩 땡기기
				for (int i = 0; i < m; i++) {
					for (int j = n - 1; j > 0; j--) {
						map[j][i] ^= map[j - 1][i];
						map[j - 1][i] ^= map[j][i];
						map[j][i] ^= map[j - 1][i];
					}
				}

				// print
/*
				System.out.println("enemyCnt = " + enemyCnt);
				for (int i = 0; i < n; i++) {
					for (int j = 0; j < m; j++) {
						System.out.print(map[i][j] + " ");
					}
					System.out.println();
				}
				System.out.println();
				System.out.println();
*/
			}
			answer = Math.max(answer, score);
		} while (np());

		System.out.println(answer);
	}

	private static class Position {
		int x;
		int y;

		public Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Position position = (Position)o;
			return x == position.x && y == position.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}