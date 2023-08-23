package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (실버1) boj 155565 귀여운 라이언
public class Boj155565 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];

		int oneCnt = 0;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int ans = Integer.MAX_VALUE;
		int left = 0, right = 0;

		while(right < n) {
			if (oneCnt < k) {
				oneCnt = arr[right++] == 1 ? oneCnt + 1 : oneCnt;
			}
			while (oneCnt >= k) {
				ans = Math.min(ans, right - left);
				oneCnt = arr[left++] == 1 ? oneCnt - 1 : oneCnt;
			}
		}

		System.out.println(ans == Integer.MAX_VALUE ? -1 : ans);
	}
}