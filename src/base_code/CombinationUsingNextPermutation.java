package base_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// next permutation을 이용해 combination(조합)을 구하는 base code
// #next permutation #combination
public class CombinationUsingNextPermutation {
	static int n;
	static int c;
	static int[] arr;

	static boolean np() {
		int i = n - 1;

		while (i > 0 && arr[i - 1] >= arr[i])
			--i;

		if (i == 0)
			return false;

		int j = n - 1;
		while (arr[i - 1] >= arr[j])
			--j;
		swap(i - 1, j);

		int k = n - 1;
		while (i < k) {
			swap(i++, k--);
		}
		return true;
	}

	static void swap(int a, int b) {
		int tmp = arr[a];
		arr[a] = arr[b];
		arr[b] = tmp;
	}

	public static void main(String[] args) throws IOException {
		// System.setIn(new FileInputStream("./static/input5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long startTime = System.currentTimeMillis();
		n = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());

		arr = new int[n];

		// next permutation을 위한 비트 셋팅
		for (int i = n - 1; i > n - 1 - c; i--) {
			arr[i] = 1;
		}

		// next permutation 으로 조합 구하기
		do {
			System.out.println(Arrays.toString(arr));
		} while (np());

		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);
	}
}