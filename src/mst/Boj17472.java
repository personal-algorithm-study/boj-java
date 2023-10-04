package mst;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드1) 다리 만들기2
// 그래프 탐색 + MST(최소신장트리) - 크루스칼 이용
public class Boj17472 {
	static int R;
	static int C;
	static int nodeCnt = 0;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static char[][] map;
	static int[][] graph;

	static Queue<int[]> q = new ArrayDeque<>();

	static class Edge {
		int u, v, cost;

		public Edge(int u, int v) {
			if (u < v) {
				this.u = u;
				this.v = v;
			} else {
				this.u = v;
				this.v = u;
			}
		}

		public Edge(int u, int v, int cost) {
			this(u, v);
			this.cost = cost;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			Edge node = (Edge)o;
			return this.u == node.u && this.v == node.v;
		}

		@Override
		public int hashCode() {
			return Objects.hash(u, v);
		}

		@Override
		public String toString() {
			return "Edge{" +
					"u=" + u +
					", v=" + v +
					", cost=" + cost +
					'}';
		}
	}

	static int find(int[] parent, int a) {
		if (parent[a] != a)
			parent[a] = find(parent, parent[a]);
		return parent[a];
	}

	static void union(int[] parent, int a, int b) {
		a = find(parent, a);
		b = find(parent, b);

		if (a < b)
			parent[b] = a;
		else
			parent[a] = b;
	}

	static void checkIsland(int[] start) {
		q.offer(start);
		graph[start[0]][start[1]] = ++nodeCnt;

		while (!q.isEmpty()) {
			int[] now = q.poll();

			for (int i = 0; i < 4; i++) {
				int nx = now[0] + dx[i];
				int ny = now[1] + dy[i];

				if (nx < 0 || nx >= R || ny < 0 || ny >= C
						|| map[nx][ny] == '0' || graph[nx][ny] != 0)
					continue;

				graph[nx][ny] = nodeCnt;
				q.offer(new int[] {nx, ny});
			}
		}
	}

	static List<Edge> getShortestPath(int sx, int sy) {
		List<Edge> result = new ArrayList<>();

		for (int i = 0; i < 4; i++) {
			int nx = sx + dx[i];
			int ny = sy + dy[i];

			int cnt = 0;
			while (nx >= 0 && nx < R && ny >= 0 && ny < C
					&& graph[nx][ny] != graph[sx][sy]) {
				if (graph[nx][ny] != 0) {
					if (cnt >= 2) {
						result.add(new Edge(graph[sx][sy], graph[nx][ny], cnt));
					}
					break;
				}
				nx += dx[i];
				ny += dy[i];
				cnt++;
			}
		}
		return result;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기 --- 1
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new char[R][C];
		graph = new int[R][C];

		// 입력 받기 --- 2
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				map[r][c] = st.nextToken().charAt(0);
			}
		}

		// 섬 표시 & 섬 갯수 세기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (map[r][c] != '1' || graph[r][c] != 0)
					continue;
				checkIsland(new int[] {r, c});
			}
		}

		Map<Edge, Integer> map = new HashMap<>();
		// 섬 사이의 최소 경로 구하기
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (graph[r][c] == 0)
					continue;
				List<Edge> nodes = getShortestPath(r, c);

				for (Edge node : nodes) {
					if (!map.containsKey(node)) {
						map.put(node, node.cost);
					} else {
						Integer currentValue = map.get(node);
						if (currentValue > node.cost) {
							map.put(node, node.cost);
						}
					}
				}
			}
		}

		// 크루스칼 알고리즘
		PriorityQueue<Edge> pq = new PriorityQueue<>(comparingInt(n -> n.cost));

		// value 최솟값이 아님
		/*for (Edge edge : map.keySet()) {
			pq.offer(edge);
		}*/

		// 최솟값으로 수정
		for (Map.Entry<Edge, Integer> edgeEntry : map.entrySet()) {
			Edge edge = edgeEntry.getKey();
			edge.cost = edgeEntry.getValue();
			pq.offer(edge);
		}

		int[] p = new int[nodeCnt + 1];
		for (int i = 0; i < nodeCnt + 1; i++) {
			p[i] = i;
		}

		int answer = 0;
		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (find(p, now.u) != find(p, now.v)) {
				answer += now.cost;
				union(p, now.u, now.v);
			}
		}

		// 다 연결되었는지 확인
		int allParent = find(p, 1);
		for (int i = 1; i < nodeCnt + 1; i++) {
			if (find(p, i) != allParent) {
				answer = 0;
				break;
			}
		}

		System.out.println(answer == 0 ? -1 : answer);
	}
}

/*

4 8
0 0 0 0 0 0 1 1
1 0 0 1 1 0 1 1
1 1 1 1 0 0 0 0
1 1 0 0 0 1 1 0

8 8
1 1 1 1 1 1 1 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 0 0 1 1 0 0 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 0 0 0 0 0 0 1
1 1 1 1 1 1 1 1

5 6
1 1 0 0 0 1
1 1 0 0 0 1
0 0 0 0 0 1
0 0 0 0 0 1
1 1 1 1 1 1


6 5
1 1 0 0 1
1 1 0 0 1
0 0 0 0 1
0 0 0 0 1
0 0 0 0 1
1 1 1 1 1

 */
