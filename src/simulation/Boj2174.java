package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Boj2174 {
	static int rowLength;
	static int cowLength;

	static int[] dr = {0, 1, 0, -1};
	static int[] dc = {1, 0, -1, 0};

	static int[][] graph;
	static List<Robot> robots = new ArrayList<>();
	static List<Order> orders = new ArrayList<>();
	static final Map<String, Integer> map = new HashMap<>();

	String solve() {
		for (Order order : orders) {
			Robot robot = robots.get(order.idx);
			if (order.isTurnLeft()) {
				robot.direction = (robot.direction + 3 * order.cnt) % 4;
			} else if (order.isTurnRight()) {
				robot.direction = (robot.direction + order.cnt) % 4;
			} else if (order.isForward()) {
				for (int i = 0; i < order.cnt; i++) {
					int nr = robot.r + dr[robot.direction];
					int nc = robot.c + dc[robot.direction];
					if (nr < 0 || nr >= rowLength || nc < 0 || nc >= cowLength) {
						return "Robot " + (order.idx + 1) + " crashes into the wall";
					}
					if (graph[nr][nc] != -1) {
						return "Robot " + (order.idx + 1) + " crashes into robot " + (graph[nr][nc] + 1);
					}
					graph[robot.r][robot.c] = -1;
					graph[nr][nc] = order.idx;
					robot.r = nr;
					robot.c = nc;
				}
			}
		}
		return "OK";
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] lengths = br.readLine().split(" ");
		String[] inputs = br.readLine().split(" ");

		map.put("E", 0);
		map.put("S", 1);
		map.put("W", 2);
		map.put("N", 3);

		rowLength = Integer.parseInt(lengths[1]);
		cowLength = Integer.parseInt(lengths[0]);
		graph = new int[rowLength][cowLength];

		for (int i = 0; i < rowLength; i++) {
			for (int j = 0; j < cowLength; j++) {
				graph[i][j] = -1;
			}
		}

		int n = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);

		for (int i = 0; i < n; i++) {
			String[] robotInfo = br.readLine().split(" ");

			int r = rowLength - Integer.parseInt(robotInfo[1]);
			int c = Integer.parseInt(robotInfo[0]) - 1;
			int d = map.get(robotInfo[2]);

			graph[r][c] = i;
			robots.add(new Robot(r, c, d));
		}

		for (int i = 0; i < m; i++) {
			String[] orderInfo = br.readLine().split(" ");
			orders.add(new Order(orderInfo));
		}
		System.out.println(new Boj2174().solve());
	}
}

class Robot {
	int r;
	int c;
	int direction;

	public Robot(int r, int c, int direction) {
		this.r = r;
		this.c = c;
		this.direction = direction;
	}
}

class Order {
	int idx;
	String input;
	int cnt;

	public Order(String[] orderInfo) {
		this.idx = Integer.parseInt(orderInfo[0]) - 1;
		this.input = orderInfo[1];
		this.cnt = Integer.parseInt(orderInfo[2]);
	}

	public boolean isTurnLeft() {
		return this.input.equals("L");
	}

	public boolean isTurnRight() {
		return this.input.equals("R");
	}

	public boolean isForward() {
		return this.input.equals("F");
	}
}