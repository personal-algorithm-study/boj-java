package simulation;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1331 {

	static int LENGTH = 36;

	String solve(String[] inputs) {
		int[][] board = new int[6][6];

		int[] first = getCoordinate(inputs[0].charAt(0), inputs[0].charAt(1));
		int[] last = getCoordinate(inputs[35].charAt(0), inputs[35].charAt(1));
		if (isNotValidDiff(first, last)) {
			return "Invalid";
		}

		for (int i = 0; i < LENGTH - 1; i++) {
			int[] now = getCoordinate(inputs[i].charAt(0), inputs[i].charAt(1));
			int nowR = now[0], nowC = now[1];
			int[] next = getCoordinate(inputs[i + 1].charAt(0), inputs[i + 1].charAt(1));

			if (board[nowR][nowC] != 0) {
				return "Invalid";
			}
			board[nowR][nowC] = i + 1;

			if (isNotValidDiff(now, next)) {
				return "Invalid";
			}
		}

		if (board[last[0]][last[1]] != 0) {
			return "Invalid";
		}
		return "Valid";
	}

	int[] getCoordinate(char x, char y) {
		return new int[] {6 - (y - '0'), x - 'A'};
	}

	boolean isNotValidDiff(int[] now, int[] next) {
		if ((abs(now[0] - next[0]) == 2 && abs(now[1] - next[1]) == 1)
				|| (abs(now[0] - next[0]) == 1 && abs(now[1] - next[1]) == 2)
		) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = new String[36];
		for (int i = 0; i < LENGTH; i++) {
			inputs[i] = br.readLine();
		}

		System.out.println(new Boj1331().solve(inputs));
	}
}
