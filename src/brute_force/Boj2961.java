package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2961 {
	static int n;
	static int[][] cook;
	static int ans = 1000_000_000;

	static void dfs(int cnt, int sourT, int bitterT) {
		ans = Math.min(ans, Math.abs(sourT - bitterT));
		if (cnt >= n) {
			return;
		}

		dfs(cnt + 1, sourT * cook[cnt][0], bitterT + cook[cnt][1]);
		dfs(cnt + 1, sourT, bitterT);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		cook = new int[n][2];

		for (int i = 0; i < n; i++) {
			String[] inputs = br.readLine().split(" ");
			cook[i][0] = Integer.parseInt(inputs[0]);
			cook[i][1] = Integer.parseInt(inputs[1]);
		}

		for (int i = 0; i < n; i++) {
			dfs(i + 1, cook[i][0], cook[i][1]);
		}

		System.out.println(ans);
	}
}
