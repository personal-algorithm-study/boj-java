package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2563 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		final int MAX = 100;
		int[][] arr = new int[MAX + 1][MAX + 1];

		// 배열 채우기
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			for (int j = x; j < x + 10; j++) {
				for (int k = y; k < y + 10; k++) {
					arr[j][k] = 1;
				}
			}
		}

		int area = 0;

		// 채운 1 검사
		for (int i = 1; i < MAX + 1; i++) {
			for (int j = 1; j < MAX + 1; j++) {
				if (arr[i][j] == 1) {
					++area;
				}
			}
		}

		System.out.println(area);
	}
}
