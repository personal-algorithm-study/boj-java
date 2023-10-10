package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj17144 미세먼지 안녕!
public class Boj17144 {
	static int R, C;
	static int robotR, robotC;

	static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0};
	static int[][] oldArr, newArr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int T = Integer.parseInt(st.nextToken());

		oldArr = new int[R][C];
		newArr = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				oldArr[i][j] = Integer.parseInt(st.nextToken());
				if (oldArr[i][j] == -1 && robotR == 0) {
					robotR = i;
				}
			}
		}

		// test
		for (int t = 0; t < T; t++) {
			// 확산
			diffuse();

			// 순환
			circulate();
		}

		int answer = 0;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (oldArr[r][c] > 0) {
					answer += oldArr[r][c];
				}
			}
		}

		System.out.println(answer);
	}

	static void diffuse() {
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				int cnt = 0;
				int now = oldArr[r][c], diffused = oldArr[r][c] / 5;

				if (now <= 0)
					continue;
				if (diffused <= 0) {
					newArr[r][c] += now;
					continue;
				}

				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if (nr < 0 || nr >= R || nc < 0 || nc >= C
							|| oldArr[nr][nc] <= -1)
						continue;
					newArr[nr][nc] += diffused;
					cnt++;
				}
				newArr[r][c] += now - diffused * cnt;
			}
		}

		newArr[robotR][robotC] = -1;
		newArr[robotR + 1][robotC] = -1;

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				oldArr[r][c] = newArr[r][c];
			}
		}
	}

	static void circulate() {
		// 위쪽 반시계 방향
		for (int i = robotR - 1; i > 0; i--) {
			newArr[i][0] = oldArr[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			newArr[0][i] = oldArr[0][i + 1];
		}

		for (int i = 0; i < robotR; i++) {
			newArr[i][C - 1] = oldArr[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			newArr[robotR][i] = oldArr[robotR][i - 1];
		}
		newArr[robotR][1] = 0;

		// 시계 방향
		for (int i = robotR + 2; i < R - 1; i++) {
			newArr[i][0] = oldArr[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			newArr[R - 1][i] = oldArr[R - 1][i + 1];
		}

		for (int i = R - 1; i > robotR + 1; i--) {
			newArr[i][C - 1] = oldArr[i - 1][C - 1];
		}

		for (int i = C - 1; i > 0; i--) {
			newArr[robotR + 1][i] = oldArr[robotR + 1][i - 1];
		}
		newArr[robotR + 1][1] = 0;

		int[][] tmp = oldArr;
		oldArr = newArr;
		newArr = tmp;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				newArr[i][j] = 0;
			}
		}
	}

	// for debugging
	/*static void print(int[][] arr) {
		for (int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(arr[r]));
		}
		System.out.println();
	}*/
}
