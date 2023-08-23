package hash;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버1) boj20529 가장 가까운 세 사람의 심리적 거리
// #비트 마스킹 #np() # 해시 충돌 원리
public class Boj20529 {
	static int m;

	static boolean[] comArr;

	static boolean np() {
		int i = m - 1;

		while (i > 0 && (comArr[i - 1] || (!comArr[i - 1] && !comArr[i])))
			--i;

		if (i == 0)
			return false;

		int j = m - 1;
		while (!comArr[j])
			--j;

		comArr[i - 1] ^= comArr[j];
		comArr[j] ^= comArr[i - 1];
		comArr[i - 1] ^= comArr[j];

		int k = m - 1;
		while (i < k) {
			comArr[i] ^= comArr[k];
			comArr[k] ^= comArr[i];
			comArr[i++] ^= comArr[k--];
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		String[] arr;
		String[] target = new String[3];

		int min;

		for (int i = 0; i < t; i++) {
			m = Integer.parseInt(br.readLine());
			arr = new String[m];
			min = Integer.MAX_VALUE;

			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[j] = st.nextToken();
			}
			comArr = new boolean[m];

			for (int j = m - 1; j > m - 4; j--) {
				comArr[j] = true;
			}

			do {
				int size = 0;
				for (int j = 0; j < m; j++) {
					if (comArr[j])
						target[size++] = arr[j];
					if (size > 3)
						break;
				}

				int cnt = 0;
				for (int j = 0; j < 3; j++) {
					for (int k = j + 1; k < 3; k++) {
						for (int l = 0; l < 4; l++) {
							if (target[j].charAt(l) != target[k].charAt(l))
								cnt++;
						}
					}
				}
				min = Integer.min(min, cnt);
			} while (np());

			sb.append(min).append('\n');
		}
		System.out.println(sb);
	}
}