package prefix_sum;

import static java.lang.Integer.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11660 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs = br.readLine().split(" ");
		StringBuilder sb = new StringBuilder();

		int n = parseInt(inputs[0]);
		int m = parseInt(inputs[1]);

		int[][] arr = new int[n + 1][n + 1];
		for (int i = 1; i < n + 1; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 1; j < n + 1; j++) {
				arr[i][j] = parseInt(row[j - 1]);
			}
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				arr[i][j] += arr[i][j - 1];
			}
		}

		for (int i = 1; i < n + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				arr[j][i] += arr[j - 1][i];
			}
		}

		for (int i = 0; i < m; i++) {
			String[] orders = br.readLine().split(" ");
			int startX = parseInt(orders[0]), startY = parseInt(orders[1]);
			int endX = parseInt(orders[2]), endY = parseInt(orders[3]);

			int result = arr[endX][endY]
					- arr[startX - 1][endY]
					- arr[endX][startY - 1]
					+ arr[startX - 1][startY - 1];
			sb.append(result + "\n");
		}
		System.out.println(sb.toString());
	}
}
