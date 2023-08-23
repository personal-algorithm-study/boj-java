package topological_sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;


/*
N명의 학생들을 키 순서대로 줄을 세우려고 한다.

- 두 학생의 키를 비교
- 일부 학생들의 키만을 비교
- 일부 학생들의 키를 비교한 결과가 주어질 때, 줄을 세우는 프로그램을 작성하시오

- N : 학생 수 - 1 ~ 3.2 * 10^4, M : 키를 비교한 횟수 - 1 ~ 10^5
- 학생 번호는 1번 부터 N번 까지
- A B -> A가 B의 앞에 서야 한다.

*/

// (골드3) boj 2252 줄세우기
public class Boj2252 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] arr = new int[n + 1];
		Queue<Integer> q = new ArrayDeque<>();
		List<List<Integer>> graph = new ArrayList<>();

		// graph 초기화
		for (int i = 0; i < n + 1; ++i) {
			graph.add(new ArrayList<>());
		}

		// 방향 간선 입력 받기
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());

			++arr[B];
			graph.get(A).add(B);
		}

		// 진입 차수가 0인 간선 넣기
		for (int i = 1; i < n + 1; ++i) {
			if (arr[i] == 0)
				q.offer(i);
		}

		// 위상 정렬
		while (!q.isEmpty()) {
			Integer now = q.poll();
			sb.append(now).append(' ');

			for (Integer next : graph.get(now)) {
				arr[next] -= 1;
				if (arr[next] == 0)
					q.offer(next);
			}
		}

		System.out.println(sb);
	}
}


