package prefix_sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//(실버3) boj 11659 구간 합 구하기 4
public class Boj11659 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = br.readLine().split(" ");
		String[] arrStr = br.readLine().split(" ");

		int n = Integer.parseInt(inputs[0]);
		int m = Integer.parseInt(inputs[1]);

		int[] arr = new int[n + 1];
		for (int i = 0; i < n; i++) {
			arr[i + 1] = Integer.parseInt(arrStr[i]);
		}

		for (int i = 1; i < n + 1; i++) {
			arr[i] += arr[i - 1];
		}

		for (int i = 0; i < m; i++) {
			String[] orders = br.readLine().split(" ");

			int start = Integer.parseInt(orders[0]);
			int end = Integer.parseInt(orders[1]);
			System.out.println(arr[end] - arr[start - 1]);
		}
	}
}
