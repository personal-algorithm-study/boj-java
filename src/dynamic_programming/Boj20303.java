package dynamic_programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

// (골드3) boj20303 할로윈의 양아치
// 그래프 탐색 + dp
public class Boj20303 {
	static int[] parent;

	static void union(int a, int b) {
		a = find(a);
		b = find(b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	static int find(int a) {
		if (parent[a] != a) {
			parent[a] = find(parent[a]);
		}
		return parent[a];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		// 초기화
		parent = new int[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		// 입력 받기
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Map<Integer, int[]> map = new HashMap<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}

		// 집합 구하기
		for (int i = 0; i < N; i++) {
			int p = find(i);
			int[] now = map.getOrDefault(p, new int[] {0, 0});
			map.put(p, new int[] {now[0] + 1, now[1] + arr[i]});
		}
		List<int[]> list = new ArrayList<>(map.values());

		int[] dp = new int[N + 1];
		int answer = 0;

		for (int i = 0; i < list.size(); i++) {
			int[] now = list.get(i);
			for (int j = N; j >= 0; j--) {
				if (j - now[0] < 0)
					continue;
				dp[j] = Math.max(dp[j], dp[j - now[0]] + now[1]);
				if (j < K)
					answer = Math.max(answer, dp[j]);
			}
		}
		System.out.println(answer);
	}
}
