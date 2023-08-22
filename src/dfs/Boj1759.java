package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

// (골드5) boj 1759 암호 만들기 - 성공
public class Boj1759 {
	static int l;
	static int c;

	static final Set<Character> vowels = new HashSet<>();
	static char[] arr;
	static char[] result;
	static StringBuilder sb = new StringBuilder();

	static void dfs(int idx, int length, int vowelCount) {
		if (length == l) {
			if (vowelCount < 1) return;
			if (length - vowelCount < 2) return;

			for (int i = 0; i < l; i++) {
				sb.append(result[i]);
			}
			sb.append('\n');
			return;
		}

		for (int i = idx; i < c; ++i) {
			result[length] = arr[i];
			if (vowels.contains(result[length])) ++vowelCount;
			dfs(i + 1, length + 1, vowelCount);
			if (vowels.contains(result[length])) --vowelCount;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		l = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		arr = new char[c];
		result = new char[l];

		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));

		dfs(0,0, 0);

		System.out.println(sb);
	}
}