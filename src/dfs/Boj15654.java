package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// (실버3) boj 15654 N과 M(5)
public class Boj15654 {
	static int N;
	static int M;

	static StringBuilder sb = new StringBuilder();
	static int[] arr;
	static int[] perm;
	static boolean[] visited;

	static void dfs(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M; i++) {
				sb.append(perm[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			if (visited[i])
				continue;
			visited[i] = true;
			perm[cnt] = arr[i];
			dfs(cnt + 1);
			visited[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		perm = new int[M];
		visited = new boolean[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		dfs(0);
		System.out.println(sb);
	}
}

