package union_find;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

// (골드3) boj 16724 피리 부는 사나이 - 성공
// 그래프  탐색, 분리 집합
public class Boj16724 {
	static int N;
	static int M;

	static int[][] parent;
	static char[][] graph;
	static Map<Character, int[]> map;

	static int find(int[] a) {
		int r = a[0], c = a[1];
		if (parent[r][c] != r * M + c) {
			// x = r * M + c;
			int nr = parent[r][c] / M, nc = parent[r][c] % M;
			parent[r][c] = find(new int[] {nr, nc});
		}
		return parent[r][c];
	}

	static void union(int[] a, int[] b) {
		int pa = find(a);
		int pb = find(b);

		if (pa <= pb) {
			parent[pb / M][pb % M] = pa;
		} else {
			parent[pa / M][pa % M] = pb;
		}
	}

	static void dfs(int r, int c) {
		int[] direct = map.get(graph[r][c]);
		int nr = r + direct[0], nc = c + direct[1];
		if (nr < 0 || nr >= N || nc < 0 || nc >= M)
			return;

		int[] now = {r, c};
		int[] next = {nr, nc};
		if (find(now) != find(next)) {
			union(now, next);
			dfs(nr, nc);
		}
	}

	public static void main(String[] args) throws IOException {
		map = new HashMap<>();
		map.put('U', new int[] {-1, 0});
		map.put('D', new int[] {1, 0});
		map.put('L', new int[] {0, -1});
		map.put('R', new int[] {0, 1});

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		Set<Integer> set = new HashSet<>();

		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];

		for (int i = 0; i < N; i++) {
			String row = br.readLine();
			for (int j = 0; j < row.length(); j++) {
				graph[i][j] = row.charAt(j);
			}
		}

		// 집합 초기화
		parent = new int[N][M];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				parent[r][c] = r * M + c;
			}
		}

		// 그래프 탐색
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				dfs(r, c);
			}
		}

		// 집합 부모 확인
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				set.add(find(new int[] {r, c}));
			}
		}

		System.out.println(set.size());
	}
}
