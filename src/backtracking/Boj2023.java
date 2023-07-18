package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Boj2023 {
	int[] candidates = new int[] {1, 2, 3, 5, 7, 9};
	static int n;

	static List<Integer> answer = new ArrayList();

	void dfs(int now) {
		if (getLength(now) == n) {
			answer.add(now);
			return;
		}

		for (int candidate : candidates) {
			int next = now * 10 + candidate;
			if (checkPrime(next)) {
				dfs(next);
			}
		}
	}

	private boolean checkPrime(int num) {
		if (num == 1) {
			return false;
		}

		if (num == 2) {
			return true;
		}

		for (int i = 2; i <= Math.ceil(Math.sqrt(num)); i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}

	private int getLength(int num) {
		int result = 0;
		while (num != 0) {
			num /= 10;
			result += 1;
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		new Boj2023().dfs(0);

		for (Integer ans : answer) {
			System.out.println(ans);
		}
	}
}
