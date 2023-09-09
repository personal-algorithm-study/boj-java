package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

// (실버2) boj 15663  N과 M(9)
public class Boj15663 {
	static int N;
	static int M;

	static boolean[] visited;
	static int[] arr;
	static int[] perm;

	// static StringBuilder sb = new StringBuilder();
	static Set<String> answer = new LinkedHashSet<>();

	static void dfs(int cnt) {
		if (cnt == M) {
			StringBuilder output = new StringBuilder();

			for (int i = 0; i < M; i++) {
				output.append(perm[i]).append(' ');
			}

			if (answer.contains(output.toString()))
				return;
			answer.add(output.toString());
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			perm[cnt] = arr[i];
			visited[i] = true;
			dfs(cnt + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N];
		arr = new int[N];
		perm = new int[M];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0);

		for (String output : answer) {
			sb.append(output).append('\n');
		}

		System.out.println(sb);
	}
}
