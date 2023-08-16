package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드2) boj 3109 빵집
public class Boj3109 {
	static int r;
	static int c;
	static int ans;

	static int[][] arr;
	static final int[] dx = {-1, 0, 1};

	static boolean dfs(int x, int y) {
		if (y == c - 1) {
			ans++;
			return true;
		}

		for (int i = 0; i < 3; i++) {
			int nx = x + dx[i];

			if (nx < 0 || nx >= r || arr[nx][y + 1] != '.') continue;
			arr[nx][y + 1] = '-';
			if (dfs(nx, y + 1))
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		for (int i = 0; i < r; i++) {
			char[] tmp = br.readLine().toCharArray();
			for (int j = 0; j < c; j++) {
				arr[i][j] = tmp[j];
			}
		}

		for (int i = 0; i < r; i++) {
			if (arr[i][0] != '.') continue;
			arr[i][0] = '_';
			dfs(i, 0);
		}

		System.out.println(ans);
	}
}
