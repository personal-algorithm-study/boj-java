package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드 4) boj17406 배열 돌리기 4
public class Boj17406 {
	static int n;
	static int m;
	static int k;
	static int ans = Integer.MAX_VALUE;

	static int[][] initialArr;
	static int[][] arr;

	static boolean[] visited;
	static int[][] permutation;
	static int[][] orders;

	static void permute(int cnt) {
		if (cnt == k) {
			// 초기화
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					arr[i][j] = initialArr[i][j];
				}
			}
			int r;
			int c;
			int s;

			for (int i = 0; i < k; i++) {
				r = permutation[i][0];
				c = permutation[i][1];
				s = permutation[i][2];
				for (int j = 1; j <= s; j++) {
					rotate(r - 1, c - 1, j);
				}
			}

			int sum;
			for (int i = 0; i < n; i++) {
				sum = 0;
				for (int j = 0; j < m; j++) {
					sum += arr[i][j];
				}
				ans = Integer.min(ans, sum);
			}
			return;
		}

		for (int i = 0; i < k; i++) {
			if (!visited[i]) {
				visited[i] = true;
				permutation[cnt] = orders[i];
				permute(cnt + 1);
				visited[i] = false;
			}
		}

	}

	static void rotate(int cr, int cc, int s) {
		int temp = arr[cr - s][cc - s];

		for (int i = cr - s; i < cr + s; i++) {
			arr[i][cc - s] ^= arr[i + 1][cc - s];
			arr[i + 1][cc - s] ^= arr[i][cc - s];
			arr[i][cc - s] ^= arr[i + 1][cc - s];
		}

		for (int i = cc - s; i < cc + s; i++) {
			arr[cr + s][i] ^= arr[cr + s][i + 1];
			arr[cr + s][i + 1] ^= arr[cr + s][i];
			arr[cr + s][i] ^= arr[cr + s][i + 1];
		}

		for (int i = cr + s; i > cr - s; i--) {
			arr[i][cc + s] ^= arr[i - 1][cc + s];
			arr[i - 1][cc + s] ^= arr[i][cc + s];
			arr[i][cc + s] ^= arr[i - 1][cc + s];
		}

		for (int i = cc + s; i > cc - s + 1; i--) {
			arr[cr - s][i] ^= arr[cr - s][i - 1];
			arr[cr - s][i - 1] ^= arr[cr - s][i];
			arr[cr - s][i] ^= arr[cr - s][i - 1];
		}

		arr[cr - s][cc - s + 1] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		// n, m, k
		st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new int[n][m];
		initialArr = new int[n][m];

		visited = new boolean[k];
		permutation = new int[k][3];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				initialArr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		orders = new int[k][3];

		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			orders[i][0] = r;
			orders[i][1] = c;
			orders[i][2] = s;
		}

		permute(0);
		System.out.println(ans);

		/*
		for (int j = 1; j <= s; j++) {
			rotate(r - 1, c - 1, j);
		}
		*/

		/*
		for (int a = 0; a < n; a++) {
			for (int j = 0; j < m; j++) {
				System.out.print(arr[a][j] + "");
			}
			System.out.println();
		}
		System.out.println();
		 */
	}
}


/*

- 크기가 N x M 크기인 배열 A가 있을 떄,
- 배열 A의 값은 각 행에 있는 모든 수의 합 중 최솟값을 의미한다.

- 배열은 회전 연산을 수행한다.
	- 회전 연산은 세 정수 (r, c, s)로 이루어져 있고,
	- 가장 왼쪽 윗 칸이 (r - s, c - s)
	- 가장 오른쪽 아랫 칸이 (r + s, c + s)인 정사각형을
	- 시계 방향으로 한 칸씩 돌린다는 의미이다.
- 배열의 칸 (r, c)는 r행 c열을 의미한다.


예1)
- 예를 들어, 배열 A의 크기가 6 x 6이고,
	- 회전 연산이 (3, 4, 2)인 경우에는 아래 그림과 같이 회전하게 된다.
(r, c, s) = (3, 4, 2)
(r - s, c - s) = (3 - 2, 4 - 2) -> (1, 2)
(r + s, c + s) = (3 + 2, 4 + 2) -> (5, 6)


예2)
- 회전 연산이 두 개 이상이면, 연산을 수행한 순서에 따라 최종 배열이 다르다.
- 다음은 배열 A의 크기가 5 x 6이고, 회전 연산이 (3, 4, 2), (4, 2, 1)인 경우의 예시이다.

- 배열 A에 (3, 4, 2), (4, 2, 1) 순서로 연산을 수행하면 배열 A의 값은 12, (4, 2, 1), (3, 4, 2) 순서로 연산을 수행하면 15 이다.

- 배열 A와 사용 가능한 회전 연산이 주어졌을 때, 배열 A의 값의 최솟값을 구해보자.
- 회전 연산은 모두 한 번씩 사용해야 하며, 순서는 임의로 정해도 된다.


--- 입력
- 배열 A의 크기 N, M, 회전연산의 개수 K가 주어진다.
- 둘째 줄부터 N개의 줄에 배열 A에 들어있는 수 A[i][j]가 주어지고,
- 다음 K개의 줄에 회전 연산의 정보 r, c, s가 주어진다.

 */
