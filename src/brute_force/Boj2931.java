package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드3) boj 2931 가스관 - 성공
public class Boj2931 {
	static int R, C;

	static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
	static char[][] arr;
	static char[] signals = {'|', '1', '2', '3', '4', '-', '+'};
	static int[][] directions = {{1, 3}, {0, 1}, {0, 3}, {2, 3}, {1, 2}, {0, 2}, {0, 1, 2, 3}};

	static Queue<int[]> q = new ArrayDeque<>();
	static boolean[][] visited;
	static List<Integer> list = new ArrayList<>();

	static void bfs() {
		while (!q.isEmpty()) {
			int[] now = q.poll();
			int x = now[0], y = now[1];

			if (visited[x][y])
				continue;
			visited[x][y] = true;

			int dir = -1;
			for (int i = 0; i < 7; i++) {
				if (signals[i] == arr[x][y]) {
					dir = i;
					break;
				}
			}

			for (int d : directions[dir]) {
				int nx = x + dx[d];
				int ny = y + dy[d];

				if (isNotValidRange(nx, ny))
					continue;
				if (arr[nx][ny] == '.') {
					list.add((d + 2) % 4);
					visited[nx][ny] = true;
				}
				q.offer(new int[] {nx, ny});
			}
		}
	}

	static boolean isNotValidRange(int nx, int ny) {
		return nx < 0 || nx >= R || ny < 0 || ny >= C;
	}

	public static void main(String[] args) throws IOException {
		// 입력을 받기 위한 bufferedReader 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer 선언
		StringTokenizer st;
		// 정답을 담을 StringBuilder 선언
		StringBuilder sb = new StringBuilder();

		// 정답의 row 위치를 담을 변수를 선언한다
		int ansRow = -1;
		// 정답의 column 위치를 담을 변수를 선언한다.
		int ansCol = -1;
		// 정답의 도로를 담을 변수를 선언한다.
		char ansRoad = ' ';

		// R, C를 입력 받는다.
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		visited = new boolean[R][C];

		// 배열을 입력받는다.
		arr = new char[R][C];
		for (int i = 0; i < R; i++) {
			String row = br.readLine();
			for (int j = 0; j < C; j++) {
				arr[i][j] = row.charAt(j);
				// 입력 받은 배열에서 출발지, 도착지를 방문 처리한다.
				if (arr[i][j] == 'M' || arr[i][j] == 'Z')
					visited[i][j] = true;
					// 도로들을 bfs q에 넣는다.
				else if (arr[i][j] != '.') {
					// for debugging
					/*System.out.print("i = " + i);
					System.out.print(", j = " + j);
					System.out.println(", arr[i][j] = " + arr[i][j]);*/
					q.offer(new int[] {i, j});
				}
			}
		}

		bfs();

		// 끊긴 도로 찾기
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (arr[i][j] == '.' && visited[i][j]) {
					ansRow = i;
					ansCol = j;
					break;
				}
			}
		}

		/*// for debugging
		for (int i = 0; i < R; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}

		// 끊긴 도로 4방 탐색
		for (int i = 0; i < 4; i++) {
			int nx = ansRow + dx[i];
			int ny = ansCol + dy[i];

			if (isNotValidRange(nx, ny) || !visited[nx][ny])
				continue;
		// System.out.println("list = " + list);
			list.add(i);
		}*/
		list.sort(Comparator.naturalOrder());

		if (list.size() == 4) {
			ansRoad = '+';
		} else {
			for (int i = 0; i < 6; i++) {
				boolean isAnswer = true;
				for (int j = 0; j < 2; j++) {
					if (list.get(j) != directions[i][j]) {
						isAnswer = false;
						break;
					}
				}

				if (isAnswer) {
					ansRoad = signals[i];
					break;
				}
			}
		}

		// 출력에 이 테스트에 대해 기록한다.
		sb.append(ansRow + 1).append(' ')
				.append(ansCol + 1).append(' ')
				.append(ansRoad)
		;

		// 정답 출력
		System.out.println(sb);
	}
}


/*
 - '-' : ny - y > 0, ny - y < 0 < >
 - '+' : > v < ^
 - '1' : v >
 - '2' : ^ >
 - '3' : < ^
 - '4' : < v
 */