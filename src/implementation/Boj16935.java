package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드5) boj 16935 - 배열 돌리기3 (성공)
public class Boj16935 {
	static int n;
	static int m;
	static int[][] arr;

	static void rotate(int orderNum) {
		// 상하 반전
		if (orderNum == 1) {
			int[] temp;

			for (int i = 0; i < n / 2; i++) {
				temp = arr[i];
				arr[i] = arr[n - i - 1];
				arr[n - i - 1] = temp;
			}
			// 좌우 반전
		} else if (orderNum == 2) {
			int temp;

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m / 2; j++) {
					temp = arr[i][j];
					arr[i][j] = arr[i][m - j - 1];
					arr[i][m - j - 1] = temp;
				}
			}
			// 오른쪽으로 90도 회전
		} else if (orderNum == 3) {
			int[][] result = new int[m][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					result[j][n - i - 1] = arr[i][j];
				}
			}

			int temp = n;
			n = m;
			m = temp;

			arr = result;
			// 왼쪽으로 90도 회전
		} else if (orderNum == 4) {
			int[][] result = new int[m][n];

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					result[m - j - 1][i] = arr[i][j];
				}
			}

			int temp = n;
			n = m;
			m = temp;

			arr = result;
			// 4분 구간 시계 방향 회전
		} else if (orderNum == 5) {
			int[][] result = new int[n][m];

			int xLength = n / 2;
			int yLength = m / 2;

			int[] ex = {0, xLength, 0, -xLength};
			int[] ey = {yLength, 0, -yLength, 0};

			int sx = 0;
			int sy = 0;

			int heightLength = n / 2;
			int widthLength = m / 2;

			for (int i = 0; i < 4; i++) {
				for (int j = sx; j < sx + heightLength; j++) {
					for (int k = sy; k < sy + widthLength; k++) {
						result[j + ex[i]][k + ey[i]] = arr[j][k];
					}
				}
				sx += ex[i];
				sy += ey[i];
			}
			arr = result;
			// 사분 구간 반시계 방향 회전
		} else if (orderNum == 6) {
			int[][] result = new int[n][m];

			int xLength = n / 2;
			int yLength = m / 2;

			int[] ex = {xLength, 0, -xLength, 0};
			int[] ey = {0, +yLength, 0, -yLength};

			int sx = 0;
			int sy = 0;
			for (int i = 0; i < 4; i++) {
				for (int j = sx; j < sx + xLength; j++) {
					for (int k = sy; k < sy + yLength; k++) {
						result[j + ex[i]][k + ey[i]] = arr[j][k];
					}
				}
				sx += ex[i];
				sy += ey[i];
			}
			arr = result;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		// 입력 받기
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		int order;

		// 입력
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		// 회전 시행
		for (int i = 0; i < r; i++) {
			order = Integer.parseInt(st.nextToken());
			rotate(order);
		}

		// 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(arr[i][j]).append(' ');
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}

/*
 * 1 : 상하 반전, 2 : 좌우 반전,
 * 3 : 오른쪽으로 90도 회전, 4 : 왼쪽으로 90도 회전,
 * 5 : 1 -> 2 -> 3 -> 4 -> 1
 * 6 : 1 -> 4 -> 3 -> 2 -> 1
 */

/*
	[0 ~ n / 2), [0 ~ m / 2)
	[0 ~ n / 2], [m / 2 ~ m)
	[n / 2 ~ n), [0 ~ m / 2)
	[n / 2 ~ n), [m / 2 ~ m)
*/