package dynamic_programming;

import java.io.*;
import java.util.*;

// (골드5) boj 11054 가장 긴 바이토닉 부분 수열
public class Boj11054 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int n = Integer.parseInt(br.readLine());
		int answer = 1;
		int[] arr = new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] increasingDp = new int[n];
		int[] decreasingDp = new int[n];

		for (int i = 0; i < n; i++) {
			increasingDp[i] = 1;
			decreasingDp[i] = 1;
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					increasingDp[i] = Math.max(increasingDp[i], increasingDp[j] + 1);
				}
			}
		}

		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j > i; j--) {
				if (arr[i] > arr[j]) {
					decreasingDp[i] = Math.max(decreasingDp[i], decreasingDp[j] + 1);
				}
				answer = Math.max(answer, increasingDp[i] + decreasingDp[i]);
			}
		}

		System.out.println(answer == 1 ? answer : answer - 1);
	}
}