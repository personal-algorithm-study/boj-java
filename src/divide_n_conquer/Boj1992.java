package divide_n_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버 1) boj 1992 쿼드 트리
public class Boj1992 {
	static String[] arr;
	static int n;
	static StringBuilder sb;

	static void dfs(int x, int y, int length) {
		char now = arr[x].charAt(y);

		if (length <= 1) {
			sb.append(now);
			return ;
		}

		int half = length / 2;
		boolean allSame = true;

		// 모든 부분 압축 가능한지 조사
		for (int i = x; i < x + length; i++) {
			for (int j = y; j < y + length; j++) {
				if (now != arr[i].charAt(j)) {
					allSame = false;
					break;
				}
			}
		}

		// 압축 가능하ㅕㄴ 지금 비트로 압축
		if (allSame) {
			sb.append(now);
			return;
		}

		// length를 반으로 줄여서, 재귀 호출
		sb.append('(');
		dfs(x, y, half);
		dfs(x, y + half, half);
		dfs(x + half, y, half);
		dfs(x + half, y + half, half);
		sb.append(')');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		arr = new String[n];

		for (int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}

		dfs(0, 0, n);
		System.out.println(sb);
	}
}
