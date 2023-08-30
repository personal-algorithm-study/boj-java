package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드 3) boj 1600 - 말이 되고픈 원숭이
public class Boj1600 {
	static int W;
	static int H;
	static final int MAX = 987654321;

	static int[] dx = new int[]{0, 1, 0, -1};
	static int[] dy = new int[]{1, 0, -1, 0};
	static int[] horseDx = new int[]{-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] horseDy = new int[]{1, 2, 2, 1, -1, -2, -2, -1};

	static int[][] arr;
	static int[][][] dist;
	// static int debug = 0;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		// for debugging
		// System.setOut(new PrintStream(new FileOutputStream("./output1.txt")));

		// --- input
		int K = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		arr = new int[H][W];
		dist = new int[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// initialize dist
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				for (int k = 0; k < K + 1; k++) {
					dist[i][j][k] = MAX;
				}
			}
		}
		// ... ... ... ... ...

		// start (0, 0) -> (H - 1, W - 1)
		Queue<Position> q = new ArrayDeque<>();
		q.offer(new Position(0, 0, K));
		dist[0][0][K] = 0;

		while (!q.isEmpty()) {
			Position now = q.poll();
			// ++debug;
			// for debugging
			// System.out.println(now);

			if (now.x == H - 1 && now.y == W - 1) {
				break;
			}
			move(q, now, false);
			if(now.horseMove - 1 >= 0)
				move(q, now, true);
		}

		int answer = MAX;
		/* // for debugging
		for (int k = 0; k < K + 1; k++) {
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					System.out.print(dist[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}*/

		for (int i = 0; i < K + 1; i++) {
			answer = Math.min(answer, dist[H - 1][W - 1][i]);
		}

		// System.out.println(debug);
		System.out.println(answer == MAX ? -1 : answer);
	}

	private static void move(Queue<Position> q, Position now, boolean isHorseMove) {
		int directionLength;
		int[] xDirection;
		int[] yDirection;

		if (isHorseMove) {
			directionLength = 8;
			xDirection = horseDx;
			yDirection = horseDy;
		} else {
			directionLength = 4;
			xDirection = dx;
			yDirection = dy;
		}

		// System.out.println(now);
		for (int i = 0; i < directionLength; i++) {
			int nx = now.x + xDirection[i];
			int ny = now.y + yDirection[i];

			if (isNotValidMove(nx, ny)) continue;
			if(isHorseMove) {
				int next = now.horseMove - 1;
				if (dist[nx][ny][next] <= dist[now.x][now.y][now.horseMove] + 1) continue;
				dist[nx][ny][next] = dist[now.x][now.y][now.horseMove] + 1;
				q.offer(new Position(nx, ny, now.horseMove - 1));
			} else {
				if (dist[nx][ny][now.horseMove] <= dist[now.x][now.y][now.horseMove] + 1) continue;
				dist[nx][ny][now.horseMove] = dist[now.x][now.y][now.horseMove] + 1;
				q.offer(new Position(nx, ny, now.horseMove));
			}
		}
	}

	static boolean isNotValidMove(int nx, int ny) {
		return nx < 0 || nx >= H || ny < 0 || ny >= W || arr[nx][ny] == 1;
	}

	static class Position {
		int x;
		int y;
		int horseMove;
		int count;

		public Position(int x, int y, int horseMove) {
			this.x = x;
			this.y = y;
			this.horseMove = horseMove;
			this.count = 0;
		}

		/*@Override
		public String toString() {
			return "Position{" +
					"x=" + x +
					", y=" + y +
					", horseMove=" + horseMove +
					'}';
		}*/
	}
}
