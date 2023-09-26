package backtracking;

import java.io.*;
import java.util.*;


// (골드4) boj 2239 스도쿠 - 성공
public class Boj2239 {
	static final int LENGTH = 9;
	static int zeroCnt;
	static boolean isAnswer;

	static int[][] board;
	static List<int[]> zeros = new ArrayList<>();
	static StringBuilder output = new StringBuilder();


	static void dfs(int cnt) {
		if (cnt == zeros.size()) {
			isAnswer = true;

			for (int i = 0; i < LENGTH; i++) {
				for (int j = 0; j < LENGTH; j++) {
					output.append(board[i][j]);
				}
				output.append('\n');
			}
			return;
		}
		int[] now = zeros.get(cnt);
		int x = now[0], y = now[1];

		for (int i = 1; i < LENGTH + 1; i++) {
			if (isAnswer) return;
			board[x][y] = i;
			if (isValid(x, y, board[x][y])) dfs(cnt + 1);
			board[x][y] = 0;
		}
	}

	static boolean isValid(int row, int col, int value) {
		for (int i = 0; i < LENGTH; i++) {
				if (i == col) continue;
				int r = board[row][i];
				if (r == value) return false;
		}

		for (int i = 0; i < LENGTH; i++) {
				if (i == row) continue;
				int c = board[i][col];
				if (c == value) return false;
		}

		int sr = row / 3 * 3;
		int sc = col / 3 * 3;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (sr + i == row && sc + j == col) continue;
				if (board[sr + i][sc + j] == value) return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		board = new int[LENGTH][LENGTH];
		zeros = new ArrayList<>();

		for (int i = 0; i < LENGTH; i++) {
			String row = br.readLine();
			for (int j = 0; j < LENGTH; j++) {
				board[i][j] = row.charAt(j) - '0';
				if (board[i][j] == 0) {
					zeros.add(new int[]{i, j});
				}
			}
		}

		dfs(0);
		System.out.println(output);
	}
}