package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj9019 {
	final int MAX_VALUE = 10000;
	String answer = "";

	public String solve(int before, int after) {
		Queue<Pos> q = new LinkedList<>();
		boolean[] visited = new boolean[MAX_VALUE + 1];
		q.offer(new Pos(before, ""));

		while (!q.isEmpty()) {
			Pos p = q.poll();

			if (p.now == after) {
				answer = p.command;
				break;
			}
			int next;

			next = p.now * 2 % MAX_VALUE;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new Pos(next, p.command + "D"));
			}

			next = p.now != 0 ? p.now - 1 : MAX_VALUE - 1;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new Pos(next, p.command + "S"));
			}

			next = (p.now % 1000) * 10 + p.now / 1000;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new Pos(next, p.command + "L"));
			}

			next = (p.now % 10) * 1000 + p.now / 10;
			if (!visited[next]) {
				visited[next] = true;
				q.offer(new Pos(next, p.command + "R"));
			}
		}

		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Boj9019 problem = new Boj9019();

		int t = Integer.parseInt(br.readLine());
		for (int i = 0; i < t; i++) {
			String[] inputs = br.readLine().split(" ");
			int before = Integer.parseInt(inputs[0]);
			int after = Integer.parseInt(inputs[1]);
			System.out.println(problem.solve(before, after));
		}
	}
}

class Pos {
	int now;
	String command;

	public Pos(int now, String command) {
		this.now = now;
		this.command = command;
	}
}