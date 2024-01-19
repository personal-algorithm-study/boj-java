package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

// (브론즈1) boj20154 이 구역의 승자는 누구야?! - 성공
public class Boj20154 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		HashMap<Character, Integer> map = new HashMap<>();

		map.put('A', 3);
		map.put('B', 2);
		map.put('C', 1);
		map.put('D', 2);
		map.put('E', 3);

		map.put('F', 3);
		map.put('G', 3);
		map.put('H', 3);
		map.put('I', 1);
		map.put('J', 1);

		map.put('K', 3);
		map.put('L', 1);
		map.put('M', 3);
		map.put('N', 3);

		map.put('O', 1);
		map.put('P', 2);
		map.put('Q', 2);
		map.put('R', 2);
		map.put('S', 1);

		map.put('T', 2);
		map.put('U', 1);
		map.put('V', 1);
		map.put('W', 2);
		map.put('X', 2);

		map.put('Y', 2);
		map.put('Z', 1);

		String row = br.readLine();
		int answer = 0;

		for (char c : row.toCharArray()) {
			answer += map.get(c);
		}

		System.out.println(answer % 10 % 2 == 1 ? "I'm a winner!" : "You're the winner?");
	}
}
