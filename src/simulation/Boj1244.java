package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// (실버 4) boj 1244 - 스위치 켜고 끄기
public class Boj1244 {
	static int n;
	static int m;

	void solve(int[] arr, int[][] orders) {
		int idx;
		for (int i = 0; i < m; i++) {
			idx = orders[i][1] - 1;
			if (orders[i][0] == 1) {
				for (int j = idx; j < n; j += orders[i][1]) {
					arr[j] = (arr[j] + 1) % 2;
				}
			} else if (orders[i][0] == 2) {
				int left = idx, right = idx;
				while (true) {
					--left;
					++right;
					if (left < 0 || left >= n || right < 0 || right >= n
							|| arr[left] != arr[right]) {
						++left;
						--right;
						break;
					}
				}
				for (int j = left; j < right + 1; j++) {
					arr[j] = (arr[j] + 1) % 2;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		int[] arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		m = Integer.parseInt(br.readLine());

		int[][] orders = new int[m][2];
		for (int i = 0; i < m; i++) {
			orders[i] = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
		}

		new Boj1244().solve(arr, orders);

		for (int i = 0; i < n; i++) {
			System.out.print(arr[i] + " ");
			if ((i + 1) % 20 == 0) {
				System.out.println();
			}
		}
	}
}