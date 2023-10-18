import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// (골드3) boj 2373 세 용액 - 실패
public class Boj2473 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(arr);
		long[] answer = new long[4];
		answer[0] = Integer.MAX_VALUE;

		for (int l = 0; l < N - 2; l++) {
			for (int m = l + 1; m < N - 1; m++) {
				int s = m + 1, e = N - 1;
				while (s <= e && s >= m + 1 && e <= N - 1) {
					int r = s + (e - s) / 2;
					long now = arr[l] + arr[m] + arr[r];

					if (now < 0) s = r + 1;
					else e = r - 1;

					long diff = Math.abs(now);
					if (diff < answer[0]) {
						answer[0] = diff;
						answer[1] = arr[l];
						answer[2] = arr[m];
						answer[3] = arr[r];
						if (diff == 0) break;
					}
				}
			}
		}

		System.out.println(answer[1] + " " + answer[2] + " " + answer[3]);
	}
}