import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// (골드3) boj 2342 - Dance Dance Revolution - 실패
// 1. 그리디로 접근
class Boj2342 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N;

		List<Integer> list = new ArrayList<>();
		while (true) {
			int now = Integer.parseInt(st.nextToken());
			if (now == 0)
				break;
			list.add(now);
		}
		N = list.size();

		int[] arr = new int[N + 1];
		for (int i = 0; i < N; i++) {
			arr[i + 1] = list.get(i);
		}

		int[] left = new int[N + 1];
		int[] right = new int[N + 1];
		int[] score = new int[N + 1];
		// x -> x, 0 -> x, 1 -> 4, 3

		for (int i = 1; i < N + 1; i++) {
			left[i] = left[i - 1];
			right[i] = right[i - 1];
			int leftDiff = Math.abs(left[i - 1] - arr[i]);
			int rightDiff = Math.abs(right[i - 1] - arr[i]);

			if (leftDiff == 0 || rightDiff == 0) {
				score[i] = score[i - 1] + 1;
			} else if (left[i - 1] == 0) {
				left[i] = arr[i];
				score[i] = score[i - 1] + 2;
			} else if (right[i - 1] == 0) {
				right[i] = arr[i];
				score[i] = score[i - 1] + 2;
			} else if (leftDiff == 2) {
				right[i] = arr[i];
				score[i] = score[i - 1] + 3;
			} else if (rightDiff == 2) {
				left[i] = arr[i];
				score[i] = score[i - 1] + 3;
			}
		}

		// System.out.println("Arrays.toString(arr) = " + Arrays.toString(arr));
		// System.out.println(Arrays.toString(left));
		// System.out.println(Arrays.toString(right));
		// System.out.println(Arrays.toString(score));

		System.out.println(score[N]);
	}
}