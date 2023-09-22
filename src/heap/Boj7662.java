package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// (골드4) boj 7662 이중 우선 순위 큐 - 성공
public class Boj7662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		/*
		* 이중 우선 순위 큐
		* I 삽입
		* D 1 최댓값 삭제
		* D -1 최솟값 삭제
		* */
		TreeMap<Integer, Integer> map = new TreeMap<>();

		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());

			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());

				String order = st.nextToken();
				Integer number = Integer.parseInt(st.nextToken());

				if ("I".equals(order)) {
					map.put(number, map.getOrDefault(number, 0) + 1);
				} else if ("D".equals(order)) {
					int now;

					if (map.isEmpty()) continue;
					if (number == 1)
						now = map.lastKey();
					else
						now = map.firstKey();

					int cnt = map.get(now);
					if (cnt <= 1) {
						map.remove(now);
					} else {
						map.put(now, cnt - 1);
					}
				}
			}

			if(map.isEmpty()) {
				sb.append("EMPTY");
			} else {
				sb.append(map.lastKey()).append(' ').append(map.firstKey());
			}
			sb.append('\n');
			map.clear(); // map을 초기화 해주지 않아 오답이었음
		}

		System.out.println(sb);
	}
}
