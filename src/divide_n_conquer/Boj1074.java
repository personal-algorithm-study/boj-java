package divide_n_conquer;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버 1) boj 1074 Z - 성공
public class Boj1074 {
	static int n;
	static int r;
	static int c;

	static int dfs(int startR, int startC, int cnt, int length) {
		if (length <= 2) {
			if (startR == r && startC == c)
				return cnt;
			else if (startR == r && startC + 1 == c)
				return cnt + 1;
			else if (startR + 1 == r && startC == c)
				return cnt + 2;
			return cnt + 3;
		}

		if (r >= startR && r < startR + length / 2
				&& c >= startC && c < startC + length / 2
		) {
			return dfs(startR, startC, cnt, length / 2);
		} else if (r >= startR && r < startR + length / 2
				&& c >= startC + length / 2 && c < startC + length
		) {
			return dfs(startR, startC + length / 2, cnt + length * length / 4, length / 2);
		} else if (r >= startR + length / 2 && r < startR + length
				&& c >= startC && c < startC + length / 2
		) {
			return dfs(startR + length / 2, startC, cnt + length * length / 4 * 2, length / 2);
		}
		return dfs(startR + length / 2, startC + length / 2, cnt + length * length / 4 * 3, length / 2);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		System.out.println(dfs(0, 0, 0, (int)Math.pow(2, n)));
	}
}