package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//(실버3) 15657 N과 M (8)
public class Boj15657 {
	static int N;
	static int M;

	static int[] arr = new int[N];
	static int[] perm = new int[M];

	static StringBuilder sb = new StringBuilder();

	public static void dfs(int cnt) {
		if (cnt == M) {
			for (int i = 0; i < M - 1; i++) {
				if (perm[i] > perm[i + 1]) return;
			}

			for (int i = 0; i < M; i++) {
				sb.append(perm[i]).append(' ');
			}
			sb.append('\n');
			return;
		}

		for (int i = 0; i < N; i++) {
			perm[cnt] = arr[i];
			dfs(cnt + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());

		arr = new int[N];
		perm = new int[M];

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		dfs(0);

		System.out.println(sb);
	}
}
