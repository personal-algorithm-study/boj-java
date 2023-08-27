package brute_force;

import static java.util.Comparator.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (??) boj 2931 가스관 - 실패 - 도전중
public class Boj2931Fail { // 연결되지 않은 가스관일 경우도 걸림
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	public static void main(String[] args) throws IOException {
		// 입력을 받기 위한 bufferedReader 선언
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// StringTokenizer 선언
		StringTokenizer st;
		// 정답을 담을 StringBuilder 선언
		StringBuilder sb = new StringBuilder();

		// 테스트 케이스 수 T 입력 받는다.
		int T = Integer.parseInt(br.readLine());

		// 정답의 row 위치를 담을 변수를 선언한다
		int ansRow = -1;
		// 정답의 column 위치를 담을 변수를 선언한다.
		int ansCol = -1;
		// 정답의 도로를 담을 변수를 선언한다.
		char ansRoad = ' ';

		// 인접한 방향에 연결해주어야 할 도로가 있는지 파악하기 위한 리스트 선언
		List<Integer> roads = new ArrayList<>();

		// 테스트 케이스 수만큼 처리한다.
		for (int t = 1; t <= T; t++) {
			// R, C를 입력 받는다.
			st = new StringTokenizer(br.readLine());

			// 받은 입력을 integer로 변환하고, 변수에 저장 한다.
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			// 도면을 저장할 배열 선언
			char[][] arr = new char[r][c];

			// 배열을 입력받는다.
			for (int i = 0; i < r; i++) {
				String row = br.readLine();
				for (int j = 0; j < c; j++) {
					arr[i][j] = row.charAt(j);
				}
			}

			// row를 순회한다.
			for (int i = 0; i < r; i++) {
				// column을 순회한다.
				for (int j = 0; j < c; j++) {

					// 탐색을 위해 리스트를 비워준다.
					roads.clear();
					// 사방 탐색한다.
					for (int k = 0; k < 4; k++) {
						// 다음 방향을 담을 변수를 선언한다.
						int nx = i + dx[k];
						int ny = j + dy[k];

						// 범위를 넘어간 값에 대해서는 처리하지 않는다.
						if (nx < 0 || nx >= r || ny < 0 || ny >= c)
							continue;
						// 도로가 있으면 현재 기준으로 도로가 존재하는 방향을 담는다.
						if (arr[nx][ny] != '.')
							roads.add(k);
					}

					// 리스트의 size를 담는다
					int size = roads.size();
					// size가 0이거나 홀수이면, 처리하지 않는다.
					if (size <= 0 || size % 2 == 1)
						continue;

					// 정답에 row, column을 입력한다.
					ansRow = i + 1;
					ansCol = j + 1;

					// roads를 정렬한다.
					roads.sort(naturalOrder());

					// 연결해야 할 도로가 4개이면 +를 기록한다.
					if (size == 4) {
						ansRoad = '+';
						// 연결해야 할 도로가 2개이면 경우를 나눈다.
					} else if (size == 2) {
						// 두 방향을 순서대로 변수에 담는다.
						int first = roads.get(0);
						int second = roads.get(1);

						// if () continue;

						// 위, 아래를 연결해야 하는 경우
						if (first == 1 && second == 3) {
							ansRoad = '|';
							// 좌, 우를 연결해야 하는 경우
						} else if (first == 0 && second == 2) {
							ansRoad = '-';
							// 아래, 우를 연결해야 하는 경우
						} else if (first == 1 && second == 0) {
							ansRoad = '1';
							// 위, 우를 연결해야 하는 경우
						} else if (first == 0 && second == 3) {
							ansRoad = '2';
							// 위, 좌를 연결해야 하는 경우
						} else if (first == 2 && second == 3) {
							ansRoad = '3';
							// 아래, 우를 연결해야 하는 경우
						} else if (first == 1 && second == 2) {
							ansRoad = '4';
						}
					}
				}
			}

			// 출력에 이 테스트에 대해 기록한다.
			sb.append('#').append(t).append(' ')
					.append(ansRow).append(' ')
					.append(ansCol).append(' ')
					.append(ansRoad)
			;
		}

		// 정답 출력
		System.out.println(sb);
	}
}