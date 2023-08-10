package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Boj11286 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		PriorityQueue<Integer> pq = new PriorityQueue<>((i1, i2) -> {
			int absI1 = Math.abs(i1);
			int absI2 = Math.abs(i2);

			if (absI1 != absI2) {
				return absI1 - absI2;
			} else {
				return i1 - i2;
			}
		});

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			int now = Integer.parseInt(br.readLine());

			if (now != 0) {
				pq.offer(now);
			} else {
				if (!pq.isEmpty())
					sb.append(pq.poll());
				else
					sb.append('0');
				sb.append('\n');
			}
		}

		System.out.println(sb);
	}
}