package divide_n_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// (골드4) boj 2448 별찍기 - 성공
public class Boj2448 {
	static int n;

	static char[][] arr;

	static char STAR = '*';

	static void dfs(int length, int row, int col) {
		if (length == 3) {
			checkStar(row, col);
			return;
		}

		int half = length / 2;

		dfs(half, row, col);
		dfs(half, row + half, col - half);
		dfs(half, row + half, col + half);
	}

	static void checkStar(int row, int col) {
		arr[row][col] = STAR;

		arr[row + 1][col - 1] = STAR;
		arr[row + 1][col + 1] = STAR;

		for (int i = 0; i < 5; i++) {
			arr[row + 2][col - 2 + i] = STAR;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		arr = new char[n][2 * n];

		for (int i = 0; i < n; i++) {
			Arrays.fill(arr[i], ' ');
		}
		dfs(n, 0, n - 1);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < 2 * n - 1; j++) {
				sb.append(arr[i][j]);
			}
			sb.append('\n');
		}
		System.out.println(sb);
	}
}