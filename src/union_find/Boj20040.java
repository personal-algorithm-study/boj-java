package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj20040 사이클 게임
public class Boj20040 {

	static int find(int[] p, int a) {
		if (p[a] != a) p[a] = find(p, p[a]);
		return p[a];
	}

	static void union(int[] p, int a, int b) {
		a = find(p, a); b = find(p, b);
		if (a < b) p[b] = a;
		else p[a] = b;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int answer = 0;

		int[] parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		for (int i = 1; i < M + 1; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			if (find(parent, a) == find(parent, b)) {
				answer = i;
				break;
			}
			union(parent, a, b);
		}

		System.out.println(answer);
	}
}
