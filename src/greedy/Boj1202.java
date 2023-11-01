package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드2) boj1202 보석 도둑 - 성공
// 그리디 알고리즘, 정렬, 우선순위 큐
public class Boj1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		long answer = 0;

		// 보석 입력
		int[][] arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			arr[i][0] = m;
			arr[i][1] = v;
		}

		Arrays.sort(arr,
				(o1, o2) -> o1[0] != o2[0] ?
						o1[0] - o2[0] : o2[1] - o1[1]
		);

		// 가방 입력
		int[] bags = new int[K];
		for (int i = 0; i < K; i++) {
			int c = Integer.parseInt(br.readLine());
			bags[i] = c;
		}

		Arrays.sort(bags);
		PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
		int j = 0;

		for (int i = 0; i < K; i++) {
			while (j < N && arr[j][0] <= bags[i]) {
				pq.offer(arr[j++][1]);
			}

			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);
	}
}
