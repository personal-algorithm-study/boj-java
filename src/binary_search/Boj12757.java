package binary_search;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

// (??) boj 12757 전설의 JBNU - 실패
public class Boj12757 {
	static int N;
	static int M;
	static int K;
	static final int MAX = 1000_000_000;

	static TreeMap<Integer, Integer> map = new TreeMap<>();
	static StringBuilder sb = new StringBuilder();

	public static void init(BufferedReader br, StringTokenizer st) throws IOException {
		/*
		  - N : 초기 데이터의 개수, M : 명령 횟수, K : 거리 제한
		  - N : 1 ~ 10^5, M : 1 ~ 10^5, K : 1 ~ 10^4
		 */
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map.put(-MAX * 2, -1);
		map.put(MAX * 2, -1);

		/*
		  - key :
		  - 1 ~ 10^9, 음이 아닌 정수 key_a != key_b
		  - (key1, value1) ~ (key_n, value_n)
		*/
		int key, value;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			key = Integer.parseInt(st.nextToken());
			value = Integer.parseInt(st.nextToken());
			map.put(key, value);
		}
	}

	/* before - try - fail..
	static int binarySearch(int target) {
		int start = 0, end = MAX;

		int minDiff = MAX;
		int foundKeyIdx = -1;

		while (start <= end) {
			int mid = start + (end - start) / 2;
			int leftKey = map.ceilingKey(mid);
			int rightKey = map.floorKey(mid);

			int leftDiff = target - leftKey;
			int rightDiff = rightKey - target;

			// target < leftKey - k : 작은 것 좀 더 키워야 한다.
			if (minDiff == leftDiff && minDiff == rightDiff) {
				return -2;
			} else if (leftDiff == 0) {
				return leftDiff;
			} else if (rightDiff == 0) {
				return rightDiff;
			}

			if(leftDiff < -K || leftDiff <= K) {
				end = mid - 1;
			} else if (rightDiff > K) {
				start = mid + 1;
			} if (leftDiff < rightDiff) {
				start = mid + 1;
			} else if (rightDiff < leftDiff) {
				end = mid - 1;
			}

			if (minDiff < leftDiff) {
				foundKeyIdx = leftKey;
			} else if (minDiff < rightDiff) {
				foundKeyIdx = rightKey;
			}
			end = mid - 1;
		}
		return foundKeyIdx;
	}*/

	static void process(int orderType, int key, int value) {
		if (orderType == 1) {
			map.put(key, value);
			return;
		}
		int leftKey = map.floorKey(key);
		int rightKey = map.ceilingKey(key);

		int leftDiff = key - leftKey;
		int rightDiff = rightKey - key;

		if (leftDiff > K && rightDiff > K) {
			if (orderType == 3) sb.append(-1).append('\n');
			return;
		} else if (leftDiff == rightDiff) {
			if(orderType == 3) sb.append('?').append('\n');
			return;
		}

		int foundKey = leftDiff < rightDiff ? leftKey : rightKey;
		if (orderType == 2) {
			map.put(foundKey, value);
		} else {
			sb.append(map.get(foundKey)).append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("./static/input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		init(br, st);

		/*
		 (orderType1, key_1, value_1) ~ (orderType_m, key_m, value_m)
		 orderType_x : 1, 2, 3
		  - 1 : key value 추가
		  - 2 : key value 변경
		  - 3 : key에 해당하는 value 조회
		  	- 없으면 -1, 두 개 이상 ?
		  	- 모든 출력은 개행을 포함
		 */
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int orderType = Integer.parseInt(st.nextToken());
			int key = Integer.parseInt(st.nextToken());
			int value = -1;

			if (st.hasMoreTokens()) {
				value = Integer.parseInt(st.nextToken());
			}

			process(orderType, key, value);
			// TODO should be removed to debug
			// System.out.print(i + " " + orderType + " " + key + " " + value + " ");
			// System.out.println(map);
			// System.out.println(keyList);
			// System.out.println(sb);
		}
		System.out.println(sb);
	}
}
