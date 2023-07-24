package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj7511 {
	static int find(int[] parent, int a) {
		if (parent[a] != a) {
			parent[a] = find(parent, parent[a]);
		}
		return parent[a];
	}

	static void union(int[] parent, int a, int b) {
		int pa = find(parent, a);
		int pb = find(parent, b);

		if (pa < pb) {
			parent[pa] = pb;
		} else {
			parent[pb] = pa;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int t = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < t; i++) {
			sb.append("Scenario ").append((i + 1)).append(":\n");

			int n = Integer.parseInt(br.readLine());
			int k = Integer.parseInt(br.readLine());
			int[] parent = new int[n];

			for (int j = 0; j < n; j++) {
				parent[j] = j;
			}

			String[] inputs;
			for (int j = 0; j < k; j++) {
				inputs = br.readLine().split(" ");
				union(parent, Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
			}

			int m = Integer.parseInt(br.readLine());
			for (int j = 0; j < m; j++) {
				inputs = br.readLine().split(" ");
				if (find(parent, Integer.parseInt(inputs[0])) == find(parent, Integer.parseInt(inputs[1]))) {
					sb.append("1\n");
				} else {
					sb.append("0\n");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
		br.close();
	}
}
