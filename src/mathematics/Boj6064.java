package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버 1) boj 6064 카잉달력
public class Boj6064 {

	static int getGCD(int a, int b) {
		if (a % b == 0)
			return b;
		return getGCD(b, a % b);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int length = Integer.parseInt(br.readLine());
		for (int i = 0; i < length; i++) {
			st = new StringTokenizer(br.readLine());

			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			int gcd = getGCD(m, n);
			int mcm = m * n / gcd;

			int s = -1;
			for (int j = y; j <= mcm; j += n) {
				if ((j - x) % m == 0) {
					s = (j - x) / m;
					break;
				}
			}
			sb.append(s != -1 ? m * s + x : -1).append('\n');
		}
		System.out.println(sb);
	}
}
