package sliding_window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

// (실버1) boj15961 회전 초밥 - 성공
public class Boj15961 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 놓인 접시 수
		int d = Integer.parseInt(st.nextToken()); // 초밥의 가짓 수
		int k = Integer.parseInt(st.nextToken()); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

		int[] arr = new int[N + k];

		// 초밥 배열 생성
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		for (int i = N; i < N + k; i++) {
			arr[i] = arr[i - N]; // arr[i - N + 1] (x)
		}

		// 초밥의 종류를 카운팅할 map
		Map<Integer, Integer> map = new HashMap<>();

		// 초기 앞에 k개의 키 넣기
		for (int i = 0; i < k; i++) {
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);
		}

		int answer = map.keySet().size();
		if (!map.containsKey(c)) {
			++answer;
		}

		for (int i = k; i < N + k; i++) {
			Integer before = map.get(arr[i - k]);
			if (before != null) {
				if (before - 1 == 0)
					map.remove(arr[i - k]);
				else
					map.put(arr[i - k], before - 1);
			}
			map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

			int now = map.keySet().size();
			if (!map.containsKey(c))
				++now;

			// System.out.println(map); // for debugging
			answer = Math.max(answer, now);
		}

		System.out.println(answer);
	}
}




