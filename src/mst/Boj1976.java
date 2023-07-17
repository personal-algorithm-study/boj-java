package mst;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj1976 {
	static int find(int[] p, int a) {
		if (p[a] != a) p[a] = find(p, p[a]);
		return p[a];
	}

	static void union(int[] p, int a, int b) {
		int pa = find(p, a);
		int pb = find(p, b);

		if (pa < pb) p[pb] = pa;
		else p[pa] = pb;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());

		int[] p = new int[n];

		for (int i = 0; i < n; i++) p[i] = i;

		for(int i = 0; i < n; i++) {
			int[] arr = Arrays.stream(br.readLine().split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();

			for (int j = 0; j < n; j++) {
				if (arr[j] == 1) union(p, i, j);
			}
		}

		int[] path = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		int set = find(p, path[0] - 1);
		String answer = "YES";

		for (int i = 1; i < m; i++) {
			if (find(p, path[i] - 1) != set) {
				answer = "NO";
				break;
			}
		}
		System.out.println(answer);
	}
}