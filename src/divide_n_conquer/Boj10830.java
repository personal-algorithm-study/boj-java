package divide_n_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


// (골드4) boj10830 행렬 제곱
public class Boj10830 {
	static int N;

	static int[][] matrix;

	static int[][] multiply(int[][] a1, int[][] a2) {
		int[][] result = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N;j++) {
				for (int k = 0; k < N; k++) {
					result[i][j] += (a1[i][k] * a2[k][j]) % 1000;
				}
				result[i][j] %= 1000;
			}
		}
		return result;
	}

	static int[][] dfs(int[][] matrix, long cnt) {
		if (cnt <= 1) {
			return matrix;
		}

		int[][] result = dfs(matrix, cnt / 2);
		result = multiply(result, result);

		if (cnt % 2 != 0) result = multiply(result, matrix);
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		long B = Long.parseLong(st.nextToken());

		matrix = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}

		StringBuilder sb = new StringBuilder();
		int[][] answer = dfs(matrix, B);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(answer[i][j]).append(' ');
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
}
