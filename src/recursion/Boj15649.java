package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버3) boj 15649 N과 M (1)
public class Boj15649 {
	static boolean[] visited;
	static int[] result;
	static StringBuilder sb = new StringBuilder();

	public static void solution(int n, int m) {
		visited = new boolean[n];
		result = new int[m];

		permutation(0, n, m);
	}

	public static void permutation(int depth, int n, int m) {
		if (depth == m) {

			for (int i = 0; i < result.length; i++) {
				sb.append(result[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = i + 1;
				permutation(depth + 1, n, m);
				visited[i] = false;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		solution(n, m);
		System.out.println(sb.toString());
	}
}