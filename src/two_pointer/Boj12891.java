package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj12891 {
	static boolean check(int[] cntArr, int[] condition) {
		for (int i = 0; i < 4; i++) {
			if (condition[i] > cntArr[i]) {
				return false;
			}
		}
		return true;
	}

	static void manipulateCnt(int[] arr, char x, int v) {
		if (x == 'A') {
			arr[0] += v;
		} else if (x == 'C') {
			arr[1] += v;
		} else if (x == 'G') {
			arr[2] += v;
		} else if (x == 'T') {
			arr[3] += v;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputs = br.readLine().split(" ");
		int s = Integer.parseInt(inputs[0]);
		int p = Integer.parseInt(inputs[1]);
		int ans = 0;

		String string = br.readLine();
		int[] cntArr = new int[4];
		int[] condition = new int[4];
		String[] conditionInput = br.readLine().split(" ");

		for (int i = 0; i < 4; i++) {
			condition[i] = Integer.parseInt(conditionInput[i]);
		}

		int left = 0, right = 0;
		while (right < s) {
			manipulateCnt(cntArr, string.charAt(right++), 1);
			if (right - left == p) {
				if (check(cntArr, condition))
					ans++;
				manipulateCnt(cntArr, string.charAt(left++), -1);
			}
		}

		System.out.println(ans);
	}
}