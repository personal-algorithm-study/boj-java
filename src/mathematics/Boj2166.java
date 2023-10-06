package mathematics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드5) boj 2166 다각형의 면적 - 성공
// 수학
public class Boj2166 {
	static double calculate(long[] v1, long[] v2) {
		return v1[0] * v2[1] - v2[0] * v1[1];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		double answer = 0;
		int N = Integer.parseInt(br.readLine());
		long[][] coordinate = new long[N][2];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			long x = Integer.parseInt(st.nextToken());
			long y = Integer.parseInt(st.nextToken());
			coordinate[i] = new long[]{x, y};
		}

		for (int i = 1; i < N; i++){
			answer += calculate(coordinate[i - 1], coordinate[i]) / 2;
		}
		answer += calculate(coordinate[N - 1], coordinate[0]) / 2;

		System.out.printf("%.1f", Math.abs(answer));
	}
}

/*
	틀린 이유:
	- 아이디어 부족: 다각형의 면적 구하는 공식 참고함
	- 오타 : (20) new int[N][N] (메모리 초과) -> new int[N][2]
	- overflow 고려 못함 : int -> long
 */