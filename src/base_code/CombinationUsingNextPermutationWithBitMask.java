package base_code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// next permutation + <bitmasking> 을 이용해 combination(조합)을 구하는 base code
// #next permutation #combination # bitmasking
public class CombinationUsingNextPermutationWithBitMask {
	static int n;
	static int c;
	static int flag;

	static boolean np() {
		int i = 0;

		while ((flag & 1 << i + 1) >= (flag & 1 << i))
			++i;

		if (i == n - 1)
			return false;

		int j = 0;
		while ((flag & 1 << j) == 0)
			j++;
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
		// System.setIn(new FileInputStream("./static/input5.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		long startTime = System.nanoTime();
		n = Integer.parseInt(br.readLine());
		c = Integer.parseInt(br.readLine());

		// next permutation을 위한 비트 셋팅
		for (int i = 0; i < c; i++) {
			flag |= 1 << i;
		}

		// next permutation 으로 조합 구하기
		do {
			System.out.println(Integer.toBinaryString(flag));
		} while (np());

		long endTime = System.nanoTime();
		System.out.println(endTime - startTime);
	}
}