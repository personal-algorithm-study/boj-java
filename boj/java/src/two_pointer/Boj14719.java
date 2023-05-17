package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj14719 {
	public int solve(int width, int[] heights) {
		int left = 0, right = width - 1;
		int answer = 0;

		int leftMax = heights[left];
		int rightMax = heights[right];

		while (left < right) {
			leftMax = Math.max(leftMax, heights[left]);
			rightMax = Math.max(rightMax, heights[right]);

			if (leftMax < rightMax)
				answer += leftMax - heights[left++];
			else
				answer += rightMax - heights[right--];
		}
		return answer;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] sizes = br.readLine().split(" ");

		int w = Integer.parseInt(sizes[1]);
		int[] heights = Arrays.stream(br.readLine().split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

		System.out.println(new Boj14719().solve(w, heights));
	}
}