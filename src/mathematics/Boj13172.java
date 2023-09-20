package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj13172 Σ - 성공
public class Boj13172 {
	static int MOD = 1000_000_007;

	static long calculate(long n, long s) {
		long g = getGCD(n, s);

		long a = s / g;
		long b = n / g;

		long bm1 = multiply(b, MOD - 2);

		return (a * bm1) % MOD;
	}

	static long getGCD(long a, long b) {
		if (a % b == 0) return b;
		return getGCD(b, a % b);
	}

	static long multiply(long a, long cnt) {
		if (cnt == 1) return a;
		else if (cnt % 2 != 0) {
			return (a * multiply(a, cnt - 1)) % MOD;
		}

		long half = multiply(a, cnt / 2);
		return (half * half) % MOD;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int m = Integer.parseInt(br.readLine());
		long answer = 0;

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			answer += calculate(a, b);
			answer %= MOD;
		}
		System.out.println(answer);
	}
}
