package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// (브론즈3) boj3029 경고 - 성공
public class Boj3029 {
	public static int[] calculate(int[] start, int[] target) {
		int[] answer = new int[3];
		answer[2] = target[2] - start[2];
		if (answer[2] < 0) {
			target[1]--;
			answer[2] += 60;
		}

		answer[1] = target[1] - start[1];
		if (answer[1] < 0) {
			target[0]--;
			answer[1] += 60;
		}

		answer[0] = target[0] - start[0];
		if (answer[0] < 0) {
			answer[0] += 24;
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] start = Arrays.stream(br.readLine().split(":"))
				.mapToInt(Integer::parseInt)
				.toArray();

		int[] target = Arrays.stream(br.readLine().split(":"))
				.mapToInt(Integer::parseInt)
				.toArray();

		int[] answer = {0, 0, 0};

		if (Arrays.equals(start, target)) { // 시간이 같은 경우 하루 걸리는 것!!
			answer[0] = 24;
		} else {
			answer = calculate(start, target);
		}

		System.out.println(
				String.format("%02d", answer[0])
						+ ":" + String.format("%02d", answer[1])
						+ ":" + String.format("%02d", answer[2])
		);
	}
}
