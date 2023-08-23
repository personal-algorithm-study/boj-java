package bitmasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// (골드4) boj17371 게리맨더링 - 성공
// #비트 마스킹 #그래프 탐색
public class Boj17471 {
	static int N;
	static int total;
	static int answer = Integer.MAX_VALUE;

	static int[] arr;
	static boolean[][] graph;
	static Queue<Integer> q = new ArrayDeque<>();

	static void dfs(int flag, int idx, int sum) {
		if (idx == N) {
			// 반대 그룹에 속한 애들
			int reversedFlag = reverse(flag);
			// 한 그룹이 된 경우 거르기
			if (reversedFlag == 0)
				return;

			// 두 그룹 각각이 모두 연결 되었는지 확인
			if (isConnected(flag) && isConnected(reversedFlag)) {
				answer = Math.min(answer, Math.abs(2 * sum - total));
			}
			return;
		}

		// 재귀를 통해 부분집합 구하기
		dfs(flag | 1 << idx, idx + 1, sum + arr[idx]);
		dfs(flag, idx + 1, sum);
	}

	// 반대편에 속한 그룹 구하기
	static int reverse(int flag) {
		int masking = (int)Math.pow(2, N) - 1;
		/* for 문 이용
		for (int i = 0; i < N; i++) {
			masking |= 1 << i;
		}*/
		return ~flag & masking;
	}

	// 모든 그룹이 연결되었는지 확인하는 함수
	static boolean isConnected(int flag) {
		// 진입 노드 구하기
		int start = 0;
		for (int i = 0; i < N; i++) {
			if ((flag & 1 << i) != 0) {
				start = i;
				break;
			}
		}

		// 진입 노드 방문 표시
		int visitedFlag = 1 << start;
		q = new ArrayDeque<>();
		q.offer(start);

		// bfs를 통해 한 그룹이 연결되었는지 확인
		while (!q.isEmpty()) {
			Integer now = q.poll();

			for (int i = 0; i < N; i++) {
				if ((visitedFlag & 1 << i) != 0)
					continue; // 방문 되었으면 무시
				if (graph[now][i] && (flag & 1 << i) != 0) { // 연결과 그룹에 속하는지 확인
					q.offer(i);
					visitedFlag |= 1 << i;
				}
			}
		}
		return flag == visitedFlag; // 그룹에 속한 애들로만 방문해서 그 그룹 원소 모두에 닿았는지 확인
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		// stream 이용
		arr = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();
		total = Arrays.stream(arr).sum();

	/* stream 이용 x
		st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
	*/

		// 인접 행렬 초기화
		graph = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());

			for (int j = 0; j < M; j++) {
				int next = Integer.parseInt(st.nextToken()) - 1;
				graph[i][next] = true;
				graph[next][i] = true;
			}
		}

		// 재귀 진입
		dfs(1, 1, arr[0]);
		// 정답 출력
		System.out.println(answer != Integer.MAX_VALUE ? answer : -1);
	}
}