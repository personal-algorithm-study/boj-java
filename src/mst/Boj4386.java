package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드3) boj4386 별자리 만들기 - 성공
// mst - 크루스칼, or 프림
public class Boj4386 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		double answer = 0;

		boolean[] visited = new boolean[N];
		double[][] nodes = new double[N][2];
		double[][] graph = new double[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			nodes[i][0] = x;
			nodes[i][1] = y;
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = calculateDistance(nodes[i], nodes[j]);
				graph[i][j] = dist;
				graph[j][i] = dist;
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.offer(new Edge(0, 0));

		while (!pq.isEmpty()) {
			Edge now = pq.poll();

			if (visited[now.no]) continue;
			visited[now.no] = true;

			answer += now.weight;

			for (int i = 0; i < N; i++) {
				if (visited[i]) continue;
				pq.offer(new Edge(i, graph[now.no][i]));
			}
		}

		System.out.printf("%.2f%n",answer);
}

	private static double calculateDistance(double[] node1, double[] node2) {
		return Math.sqrt(Math.pow(node1[0] - node2[0], 2) + Math.pow(node1[1] - node2[1], 2));
	}

static class Edge implements Comparable<Edge> {
	int no;
	double weight;

	public Edge(int no, double weight) {
		this.no = no;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Double.compare(this.weight, o.weight);
	}
}
}
