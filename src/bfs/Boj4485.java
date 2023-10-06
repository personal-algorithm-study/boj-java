package bfs;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드4) boj4485 녹색 옷 입은 애가 젤다지? - 성공
public class Boj4485 {
	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int t = 1;
		int[][] arr, dist;
		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(o -> o[2]));
		// Queue<int[]> pq = new ArrayDeque<>();

		while (true) {
			int N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			arr = new int[N][N];
			dist = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = 1000_000_000;
				}
			}

			dist[0][0] = arr[0][0];
			pq.offer(new int[] {0, 0, 0});

			while (!pq.isEmpty()) {
				int[] now = pq.poll();
				int x = now[0], y = now[1];
				if (dist[x][y] < now[2]) continue;

				for (int i = 0; i < 4; i++) {
					int nx = now[0] + dx[i];
					int ny = now[1] + dy[i];

					if (nx < 0 || nx >= N || ny < 0 || ny >= N)
						continue;
					if (dist[nx][ny] > dist[x][y] + arr[nx][ny]) {
						dist[nx][ny] = dist[x][y] + arr[nx][ny];
						pq.offer(new int[] {nx, ny, dist[nx][ny]});
					}
				}
			}

			sb.append("Problem ").append(t++).append(": ")
					.append(dist[N - 1][N - 1])
					.append('\n');
		}

		System.out.println(sb);
	}
}
