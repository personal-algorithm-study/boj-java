package brute_force;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


// (골드5) boj1107 리모컨
public class Boj1107 {
	static int N;
	static int length;
	static int min;

	static boolean[] broken;


	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		// N : 이동하고 싶은 채널, 0 ~ 5*10^5
		N = Integer.parseInt(br.readLine());

		// M : 고장난 버튼, 0 ~ 10
		int M = Integer.parseInt(br.readLine());

		if (M > 0) {
			st = new StringTokenizer(br.readLine());
		}

		broken = new boolean[10];
		for (int i = 0; i < M; i++) {
			int btn = Integer.parseInt(st.nextToken());
			broken[btn] = true;
		}

		int tmp = N;
		while (tmp > 0) {
			++length;
			tmp /= 10;
		}
		min = Math.abs(N - 100);
		for (int i = 0; i < 10; i++) { // 될 문 안돌고, dfs(0, 0)만 했는데 0만 눌러도 될 때는 버튼 한 개 눌러야 되는데 0개로 잡힘
			if (broken[i]) continue;
			int result = Math.abs(i - N) + 1;
			min = Math.min(min, result);
			dfs(i,  1);
		}
		System.out.println(min);
	}

	static void dfs(int now, int cnt) {
		if (cnt == length + 1) {
			return;
		}

		for (int i = 0; i < 10; i++) {
			if (broken[i]) continue;
			int next = now * 10 + i;
			int result = Math.abs(next - N) + cnt + 1;
			min = Math.min(min, result);
			dfs(next, cnt + 1);
		}
	}
}
