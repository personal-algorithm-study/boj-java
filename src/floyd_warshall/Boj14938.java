package floyd_warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj 14938 서강그라운드 - 성공
public class Boj14938 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// n, m, r
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int answer = 0;

		int[][] dist = new int[N][N];
		// 거리 초기화
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j)
					dist[i][j] = 0;
				else
					dist[i][j] = 1000_000_000;
			}
		}

		// item 수 받기
		int[] items = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken());

			dist[a][b] = c;
			dist[b][a] = c;
		}

		for (int k = 0; k < N; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (i == j)
						continue;
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			int result = 0;
			for (int j = 0; j < N; j++) {
				if (dist[i][j] > M)
					continue;
				result += items[j];
			}
			answer = Math.max(answer, result);
		}

		System.out.println(answer);
	}
}
