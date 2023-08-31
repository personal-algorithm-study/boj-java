package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj 17069 파이프 옮기기 2
public class Boj17069 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// --- input
		int N = Integer.parseInt(br.readLine());

		int[][][] dp = new int[N][N][3];
		int[][] arr = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		/*
		type 0 : 가로
		type 1 : 세로
		type 2 : 대각선
		dp[i][j][k] : (i, j) 까지 type k로 도달한 방법의 수
		 */
		dp[0][1][0] = 1;

		for (int x = 0; x < N; x++) {
			for (int y = 0; y < N; y++) {
				if (arr[x][y] == 1)
					continue;

				int bx = x - 1;
				int by = y - 1;

				if (bx >= 0) {
					dp[x][y][1] += dp[bx][y][1] + dp[bx][y][2];
				}

				if (by >= 0) {
					dp[x][y][0] += dp[x][by][0] + dp[x][by][2];
				}

				if (bx >= 0 && by >= 0) {
					if (arr[bx][y] != 1 && arr[x][by] != 1) {
						dp[x][y][2] += dp[bx][by][1] + dp[bx][by][0] + dp[bx][by][2];
					}
				}
			}
		}

		// for debugging
		/*
		for (int k = 0; k < 3; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					System.out.print(dp[i][j][k] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		System.out.println("dp[N - 1][N - 1][0] = " + dp[N - 1][N - 1][0]);
		System.out.println("dp[N - 1][N - 1][1] = " + dp[N - 1][N - 1][1]);
		System.out.println("dp[N - 1][N - 1][2] = " + dp[N - 1][N - 1][2]);
		*/
		System.out.println(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2]);
	}
}
