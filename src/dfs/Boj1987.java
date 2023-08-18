package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// (골드4) boj 1987 알파벳
public class Boj1987 {
	static int r;
	static int c;
	static int answer;

	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	static Map<Character, Boolean> alphabets = new HashMap<>();
	static char[][] graph;

	static void dfs(int x, int y, int cnt) {
		answer = Math.max(answer, cnt);

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];

			if (nx < 0 || nx >= r || ny < 0 || ny >= c)
				continue;
			if (graph[nx][ny] == graph[x][y])
				continue;
			if (alphabets.get(graph[nx][ny]))
				continue;
			alphabets.put(graph[nx][ny], true);
			dfs(nx, ny, cnt + 1);
			alphabets.put(graph[nx][ny], false);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		// 알파벳 방문 관리
		for (char alpha = 'A'; alpha <= 'Z'; alpha++) {
			alphabets.put(alpha, false);
		}

		// 입력 받기
		graph = new char[r][c];
		for (int i = 0; i < r; i++) {
			String row = br.readLine();
			for (int j = 0; j < c; j++) {
				graph[i][j] = row.charAt(j);
			}
		}

		// dfs 탐색
		alphabets.put(graph[0][0], true);
		dfs(0, 0, 1);
		System.out.println(answer);
	}
}
