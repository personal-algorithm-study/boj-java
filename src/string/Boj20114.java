package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj20114 {
	String solve(int n, int h, int w, String[] arr) {
		char[] ans = new char[n];

		for (int i = 0; i < n; i++) {
			ans[i] = '?';
		}

		for (int i = 0; i < n * w - w + 1; i += w) {
			for (int j = 0; j < h; j++) {
				for (int k = 0; k < w; k++) {
					if (arr[j].charAt(i + k) != '?') {
						ans[i / w] = arr[j].charAt(i + k);
					}
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(ans[i]);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] lengths = br.readLine().split(" ");

		int n = Integer.parseInt(lengths[0]);
		int h = Integer.parseInt(lengths[1]);
		int w = Integer.parseInt(lengths[2]);

		String[] arr = new String[h];

		for (int i = 0; i < h; i++) {
			arr[i] = br.readLine();
		}

		System.out.println(new Boj20114().solve(n, h, w, arr));
	}
}