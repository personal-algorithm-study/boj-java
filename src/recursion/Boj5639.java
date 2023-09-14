package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

// #재귀, #그래프 탐색
// (골드5) boj 5639 이진 검색 트리
public class Boj5639 {
	static StringBuilder sb = new StringBuilder();
	static List<Integer> tree = new ArrayList<>();

	static void dfs(int start, int end) {
		if (start >= end) {
			return;
		}

		int now = tree.get(start);
		int left = end;
		int right = end;

		for (int i = start + 1; i < end; i++) {
			if (tree.get(i) > now) {
				right = i;
				break;
			} else if (i < left) {
				left = i;
			}
		}

		dfs(left, right);
		dfs(right, end);
		sb.append(tree.get(start)).append('\n');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		tree = new ArrayList<>();
		String input = "";

		while ((input = br.readLine()) != null) {
			tree.add(Integer.parseInt(input));
		}

		int length = tree.size();

		dfs(0, length);
		System.out.println(sb);
	}
}
