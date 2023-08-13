package brute_force;

import static java.lang.Math.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (골드5) boj 15686 치킨배달
public class Boj15686 {

	static int n;
	static int m;

	static int[] c;

	static int size;

	static boolean nextCombinate() {
		int i = size - 1;

		while (i > 0 && c[i - 1] >= c[i])
			--i;

		if (i == 0)
			return false;

		int j = size - 1;
		while (c[i - 1] >= c[j])
			--j;

		c[i - 1] ^= c[j];
		c[j] ^= c[i - 1];
		c[i - 1] ^= c[j];

		int k = size - 1;
		while (i < k) {
			c[i] ^= c[k];
			c[k] ^= c[i];
			c[i++] ^= c[k--];
		}

		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		char[][] arr = new char[n][n];
		int[][] combination = new int[m][2];

		List<int[]> homes = new ArrayList<>();
		List<int[]> chickens = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				arr[i][j] = st.nextToken().charAt(0);

				if (arr[i][j] == '1') {
					homes.add(new int[] {i, j});
				} else if (arr[i][j] == '2') {
					chickens.add(new int[] {i, j});
				}
			}
		}

		size = chickens.size();
		c = new int[size];

		int idx;
		int chickenDistance = Integer.MAX_VALUE;

		for (int i = size - 1; i > size - 1 - m; i--) {
			c[i] = 1;
		}

		do {
			idx = 0;
			for (int i = 0; i < size; i++) {
				if (c[i] == 1) {
					combination[idx][0] = chickens.get(i)[0];
					combination[idx][1] = chickens.get(i)[1];
					idx++;
				}
			}

			int now = 0;
			for (int[] home : homes) {
				int cd = Integer.MAX_VALUE;
				for (int i = 0; i < m; i++) {
					cd = min(cd, abs(combination[i][0] - home[0]) + abs(combination[i][1] - home[1]));
				}
				now += cd;
			}
			chickenDistance = min(chickenDistance, now);
		} while (nextCombinate());

		System.out.println(chickenDistance);
	}
}

