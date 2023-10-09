package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드5) boj 27172 수 나누기 게임 - 성공
public class Boj27172 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		final int MAX = 1000_000;
		int N = Integer.parseInt(br.readLine());

		int[] arr = new int[N];
		boolean[] card = new boolean[MAX + 1];
		int[] score = new int[MAX + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			card[arr[i]] = true;
		}

		for (int i : arr) {
			for (int j = 2 * i; j < MAX + 1; j+= i) {
				if (card[j]) {
					score[i]++;
					score[j]--;
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i : arr) {
			sb.append(score[i]).append(' ');
		}

		System.out.println(sb);
	}
}
