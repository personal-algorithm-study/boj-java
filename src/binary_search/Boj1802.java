package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (실버1) boj 1802 종이 접기 - 성공
// 이분 탐색, 분할 정복
public class Boj1802 {

	static boolean search(char[] line) {
		int s = 0, e = line.length - 1;

		while (s < e) {
			int mid = s + (e - s) / 2;

			for (int i = 0; i < mid; i++) {
				if (line[i] == line[e - i]) return false;
			}
			e = mid - 1;
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			char[] line = br.readLine().toCharArray();
			sb.append(search(line) ? "YES" : "NO").append('\n');
		}

		System.out.println(sb);
	}
}