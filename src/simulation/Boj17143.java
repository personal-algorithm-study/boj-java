package simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드1) boj17143 낚시왕 - 성공
public class Boj17143 {
	static int R;
	static int C;

	static int rCycle;
	static int cCycle;

	static int[] dr = {0, -1, 1, 0, 0};
	static int[] dc = {0, 0, 0, 1, -1};

	// 방향 전환
	// 1 -> 2 -> 1
	// 3 -> 4 -> 3
	static int[] changeDir = new int[] {0, 2, 1, 4, 3};

	public static void main(String[] args) throws IOException {
		// --- 입력 받기 ---
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken()); // 행 크기 : 2 ~ 100
		C = Integer.parseInt(st.nextToken()); // 열 크기 : 2 ~ 100
		int M = Integer.parseInt(st.nextToken()); // M : 0 ~ R * C = 10^4

		rCycle = 2 * (R - 1);
		cCycle = 2 * (C - 1);

		Shark[][] sharks = new Shark[R][C];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
		/*
			- (r c s d z)
		 	- (r, c): 상어의 위치, s: 속력, d : 이동방향, z: 크기
			- r: 1 ~ R, c: 1 ~ C,  s: 0 ~ 10^3,  d: 1 ~ 4
		 */
			// 하나의 칸에 둘 이상의 상어가 있는 경우는 없다.
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;

			// 두 상어가 같은 크기를 갖는 경우는 없고,
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());

			sharks[r][c] = new Shark(s, d, z);
		}
		// --- 입력 받기 끝 ---

		int answer = 0;
		/*
			1-1 낚시왕 이동
			1-2 낚시
			2-1 상어 이동, 상어 정리 - 상어 방향 전환, 겹치는 값 정리
		 */
		// 1-1 반복문을 통해 낚시왕을 이동 시킴
		// 100 * (100 * 100)
		for (int i = 0; i < C; i++) {
			if (M == 0)
				break;
			// 1-2 낚시 수행
			for (int j = 0; j < R; j++) {
				if (sharks[j][i] != null) {
					answer += sharks[j][i].size;
					sharks[j][i] = null;
					M -= 1;
					break;
				}
			}
/*
			// for debugging
			for (int j = 0; j < R; j++) {
				System.out.println(Arrays.toString(sharks[j]));
			}
*/
			//2-1, 2-2 상어 이동 수행
			sharks = moveShark(sharks);
		}
		// *** 구하는 것 ***
		// 낚시왕이 잡은 상어 크기의 합을 출력한다.
		System.out.println(answer);
	}

	static Shark[][] moveShark(Shark[][] sharks) {
		Shark[][] result = new Shark[R][C];

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				Shark now = sharks[r][c];
				if (now == null)
					continue;
				int nr = (r + dr[now.direction] * (now.speed % rCycle)) % rCycle;
				int nc = (c + dc[now.direction] * (now.speed % cCycle)) % cCycle;

				if (nr < 0) {
					nr += rCycle;
				}
				if (nc < 0) {
					nc += cCycle;
				}

				if ((R - 1) < nr) {
					nr = rCycle - nr;
					now.direction = changeDir[now.direction];
				}
				if ((C - 1) < nc) {
					nc = cCycle - nc;
					now.direction = changeDir[now.direction];
				}

/*
				// for debugging
				System.out.print("r = " + r);
				System.out.println(", c = " + c);

				System.out.print("nr = " + nr);
				System.out.println(", nc = " + nc);

				System.out.println(now);
*/
				if (result[nr][nc] != null
						&& result[nr][nc].size > now.size)
					continue;
				result[nr][nc] = now;
			}
		}
		return result;
	}

	static class Shark {
		int speed;
		int direction;
		int size;

		// s d z
		public Shark(int speed, int direction, int size) {
			this.speed = speed;
			this.direction = direction;
			this.size = size;
		}
	}
}


/*
- r, c 격자판으로 각 칸은 (r, c)로 나타낼 수 있다.
- 칸에는 상어가 최대 한 마리 들어있을 수 있다.
- 상어의 <크기>와 <속도> - <속력, 방향>를 가지고 있다.

  - 낚시왕이 오른쪽으로 한 칸 이동한다.
		- 처음에 낚시왕은 1번열의 한 칸 왼쪽에 있다.
		- 낚시왕은 가장 오른쪽 열의 오른쪽 칸에 이동하면 이동을 멈춘다 -> 반복문 종료 조건
	- 낚시 수행
		- 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다.
		- 상어를 잡으면 격자판에서 잡은 상어가 사라진다.

  - 상어가 이동한다.
  	- 상어는 입력으로 주어진 속도로 이동하고, 속도의 단위는 칸 / 초이다. /... 칸초 먹고 싶다.../
  	- 상어가 이동하려는 칸의 격자판의 경계를 넘는 경우에는 방향을 반대로 바꾸어 속력을 유지한채로 이동한다.
  	- 상어가 이동을 마친 후 한 칸에 두 마리 이상 있을 수 있다. -> 이때는 크기가 가장 큰 상어가 너미지 상어를 모두 잡아먹는다.

  - 낚시왕이 상어 낚시를 하는 격자판의 상태가 주어졌을 때, 낚시왕이 잡은 상어 크기의 합을 구해보자.
 */

/*
- nx % (r - 1) * 2
- nx > (r - 1) 방향 전환

- nx < (r - 1) -> nx
- (r - 1) < nx < 2 * (r - 1) -> 2 * (r - 1) - nx

--- memo
     0   > |   < x < | > 0
           0         x         0
 0 1 2 3 4 5 6 7 8 9 0 1 2
 0 1 2 3 4 5 4 3 2 1 0 1 2 3 4 5
 0 1 2 3 2 1 0 1 2 3
           x 1 2 3 4 5 6 7 8
           x 6 7 8 9 0 1 2 3 4 5
*/