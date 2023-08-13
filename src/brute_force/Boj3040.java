package brute_force;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (브론즈 2) boj 3040 백설 공주와 일곱 난쟁이
public class Boj3040 {
	static int flag = 0b00000011;

	static boolean np() {
		int i = 0;

		while ((flag & 1 << i + 1) >= (flag & 1 << i)) ++i;

		if (i == 9) return false;

		int j = 0;
		while ((flag & 1 << j) == 0) j++;
		bitSwap(i + 1, j);

		int k = 0;
		while (i > k) {
			bitSwap(i--, k++);
		}

		return true;
	}

	static void bitSwap(int a, int b) {
		if (((flag >> a) & 1) != ((flag >> b) & 1)) {
			int mask = (1 << a) | (1 << b);
			flag = flag ^ mask;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int total = 0;
		int[] arr = new int[9];

		for (int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			total += arr[i];
		}

		flag = 0b000_000_11;

		int now;
		do {
			now = total;
			// System.out.println(Integer.toBinaryString(flag));

			for (int i = 0; i < 9; i++) {
				if((flag & 1 << i) != 0) now -= arr[i];
			}
			if (now == 100) {
				break;
			}
		} while (np());

		for (int i = 0; i < 9; i++) {
			if ((flag & 1 << i) == 0)
				sb.append(arr[i]).append('\n');
		}

		System.out.println(sb);
	}
}
