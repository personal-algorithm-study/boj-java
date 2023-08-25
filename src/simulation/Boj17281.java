package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드4) boj 17281 야구 - 성공
// #simulation
public class Boj17281 {
	static final int NUMBER_OF_PLAYERS = 9;
	static int N;
	static int answer = 0;

	static int[] permutation;
	static int[][] arr;

	static void dfs(int idx, int flag) {
		if (idx == NUMBER_OF_PLAYERS) {
			int cnt = 0;
			int score = 0;
			// int base = 0;

			for (int i = 0; i < N; i++) {
				int out = 0;
				int[] field = new int[] {0, 0, 0, 0};
				// 쓰리아웃이 될 때까지 게임 계속
				while (out < 3) {
					int result = arr[i][permutation[cnt]];
					cnt = (cnt + 1) % 9;

					/*
					base |= 1;
					if (result == 0) {
						++out;
					} else {
						for (int j = 0; j < result; j++) {
							base <<= 1;
							if ((base & (1 << 4)) == 0)
								continue;
							++score;
						}
					}
					*/

					if (result == 0) {
						++out;
					} else {
						for (int base = 2; base >= 0; base--) {
							if (field[base] == 0) continue;

							int next = base + result;
							if (next > 2) {
								++score;
							} else {
								field[next] = 1;
							}
							field[base] = 0;
						}

						if (result == 4)
							++score;
						else
							field[result - 1] = 1;
					}
				}
			}
			answer = Math.max(answer, score);
			return;
		}

		if (idx == 3) {
			dfs(idx + 1, flag);
			return;
		}

		for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
			if ((flag & 1 << i) != 0)
				continue;
			permutation[idx] = i;
			dfs(idx + 1, flag | 1 << i);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// N 이닝 수
		N = Integer.parseInt(br.readLine());

		// 특정 이닝에서, 어떤 선수의 기대 결과
		arr = new int[N][NUMBER_OF_PLAYERS];

		//입력 받기
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < NUMBER_OF_PLAYERS; j++) {
				arr[i][j] = st.nextToken().charAt(0) - '0';
			}
		}

		permutation = new int[NUMBER_OF_PLAYERS];
		permutation[3] = 0;
		dfs(0, 1);
		System.out.println(answer);
	}
}

/*

- N 이닝 동안 게임을 진행한다.

0 : 아웃, 1 : 안타, 2 : 2루타, 3 : 3루타, 4: 홈런,

## 예제분석
 - 타석 서순
 - 선수
  - 1 2 3 4 5 6 7 8 9
    2 3 4 1 5
    6 7 8

 */