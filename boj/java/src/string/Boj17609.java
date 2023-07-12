package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17609 {
	int solve(String input) {
		int result = 2;
		int start = 0;
		int end = input.length() - 1;

		if (checkPalindrome(input, start, end)) {
			return 0;
		}

		while (start < end) {
			if (input.charAt(start) == input.charAt(end)) {
				start++;
				end--;
			} else {
				boolean left = checkPalindrome(input, start + 1, end);
				boolean right = checkPalindrome(input, start, end - 1);
				if (left || right) {
					result = 1;
				}
				break;
			}
		}

		return result;
	}

	boolean checkPalindrome(String input, int left, int right) {
		while (left < right) {
			if (input.charAt(left++) != input.charAt(right--)) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Boj17609 solution = new Boj17609();

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			System.out.println(solution.solve(s));
		}
	}
}
