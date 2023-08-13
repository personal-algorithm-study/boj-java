package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2839 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());

		int k = 1;
		int y;

		while (true) {
			y = n - 3 * k;
			if (y < 0) {
				System.out.println(-1);
				break;
			}

			if (y % 2 == 0) {
				y /= 2;
				if (k - y >= 0) {
					System.out.println(k);
					break;
				}
			}
			k++;
		}
	}
}
