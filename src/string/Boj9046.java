package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// (브론즈2) boj9046 복호화 - 성공
public class Boj9046 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder output = new StringBuilder();

		int count = Integer.parseInt(br.readLine());

		for (int i = 0; i < count; i++) {
			int[] alphabets = new int[26]; // 초기화 안해서 오답이었음
			String row = br.readLine();
			int max = 0;
			for (char letter : row.toCharArray()) {
				if (letter == ' ')
					continue;
				int idx = letter - 'a';
				alphabets[idx]++;
			}
			char answer = ' ';

			for (int j = 0; j < 26; j++) {
				if (max < alphabets[j]) {
					max = alphabets[j];
					answer = (char)(97 + j);
				} else if (max == alphabets[j]) {
					answer = '?';
				}
			}
			output.append(answer).append("\n");
		}
		System.out.println(output);
	}
}
