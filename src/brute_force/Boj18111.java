package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj18111 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int n = Integer.parseInt(input[0]);
		int m = Integer.parseInt(input[1]);
		int b = Integer.parseInt(input[2]);

		int total = 0;
		int[][] arr = new int[n][m];

		for (int i = 0; i < n; i++) {
			String[] row = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(row[j]);
				total += arr[i][j];
			}
		}
		total += b;

		int minTime = Integer.MAX_VALUE;
		int ansHeight = 0;
		int time;
		int diff;

		final int MAX_HEIGHT = 256;
		for (int height = 0; height < MAX_HEIGHT + 1; height++) {
			if (height * n * m > total)
				break;
			time = 0;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					diff = height - arr[i][j];
					if (diff > 0) {
						time += diff;
					} else {
						time += diff * (-2);
					}
				}
			}

			if (time <= minTime) {
				minTime = time;
				ansHeight = height;
			}

		}
		System.out.println(minTime + " " + ansHeight);
	}
}