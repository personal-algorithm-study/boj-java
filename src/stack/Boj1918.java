package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Boj1918 {

	Map<Character, Integer> priority = new HashMap<>();

	public String solve(char[] input) {
		Deque<Character> stack = new LinkedList<>();
		StringBuilder sb = new StringBuilder();
		priority.put('+', 1);
		priority.put('-', 1);
		priority.put('*', 2);
		priority.put('/', 2);

		for (char c : input) {
			if (Character.isAlphabetic(c)) {
				sb.append(c);
			} else if (c == '(') {
				stack.offerLast(c);
			} else if (c == ')') {
				while (!stack.isEmpty() && stack.peekLast() != '(') {
					sb.append(stack.pollLast());
				}
				stack.pollLast();
			} else {
				while (true) {
					if (stack.isEmpty()
							|| stack.peekLast() == '('
							|| priority.get(stack.peekLast()) < priority.get(c)
					) {
						stack.offerLast(c);
						break;
					}
					sb.append(stack.pollLast());
				}
			}
		}

		while (!stack.isEmpty()) {
			sb.append(stack.pollLast());
		}

		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] input = br.readLine().toCharArray();
		System.out.println(new Boj1918().solve(input));
	}

}
