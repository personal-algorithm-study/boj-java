package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드5) boj 2467 용액
// 투 포인터
public class Boj2467 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int diff = Integer.MAX_VALUE;
		int[] answer = new int[2];

		int left = 0, right = N - 1;
		while (left < right) {
			int sum = arr[left] + arr[right];
			int abs = Math.abs(sum);

			if (abs < diff) {
				answer[0] = arr[left];
				answer[1] = arr[right];
				diff = abs;
			}

			if (sum < 0) left++;
			else right--;
		}

		System.out.println(answer[0] + " " + answer[1]);
	}
}