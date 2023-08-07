package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

// (골드5) boj2493 탑 - 성공
public class Boj2493 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		String[] inputs = br.readLine().split(" ");

		int[] buildings = new int[n];
		int[] ans = new int[n];

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			buildings[i] = Integer.parseInt(inputs[i]);
		}

		Deque<Integer> stack = new LinkedList<>();

		for (int i = n - 1; i >= 0; i--) {
			while (!stack.isEmpty() && buildings[stack.peekLast()] < buildings[i]) {
				int idx = stack.pollLast();
				ans[idx] = i + 1;
			}
			stack.offerLast(i);
		}

		for (int i = 0; i < n; i++) {
			sb.append(ans[i]).append(" ");
		}
		System.out.println(sb);
	}
}
