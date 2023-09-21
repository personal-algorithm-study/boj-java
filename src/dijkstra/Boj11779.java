package dijkstra;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드4) boj 11779 최소비용 구하기 2 - 성공
public class Boj11779 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());

		List<int[]>[] graph = new List[N + 1];
		for (int i = 1; i < N + 1; i++) {
			graph[i] = new ArrayList<>();
		}

		int[][] dist = new int[N + 1][2];

		for (int i = 1; i < N + 1; i++) {
			dist[i][0] = i;
			dist[i][1] = 1000_000_000;
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			graph[s].add(new int[] {e, c});
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());

		PriorityQueue<int[]> pq = new PriorityQueue<>(comparingInt(e -> e[1]));
		pq.offer(new int[] {start, 0});

		dist[start][0] = 0;
		dist[start][1] = 0;

		while (!pq.isEmpty()) {
			int[] now = pq.poll();

			if(now[0] == end) {
				break;
			}

			if (dist[now[0]][1] > now[1])
				continue;

			for (int[] edge : graph[now[0]]) {
				if (dist[edge[0]][1] > dist[now[0]][1] + edge[1]) {
					dist[edge[0]][1] = dist[now[0]][1] + edge[1];
					dist[edge[0]][0] = now[0];
					pq.offer(new int[] {edge[0], dist[edge[0]][1]});
				}
			}
		}

		List<Integer> path = new ArrayList<>();
		StringBuilder output = new StringBuilder();
		output.append(dist[end][1]).append('\n');

		while (end != 0) {
			path.add(end);
			end = dist[end][0];
		}

		int size = path.size();
		output.append(size).append('\n');

		for (int i = size - 1; i >= 0; i--) {
			output.append(path.get(i)).append(' ');
		}

		System.out.println(output);
	}
}