import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// (골드2) boj1202 보석 도둑 - 실패
public class Boj1202 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int answer = 0;

		PriorityQueue<Gem> pq = new PriorityQueue<>();

		// 입력받기
		// tmp
		Gem[] gems = new Gem[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());

			// gems[i] = new Gem(weight, value);
			pq.offer(new Gem(weight, value));
		}

		// Bag[] bags = new Bag[K];
		int[] bags = new int[K];
		for (int k = 0; k < K; k++) {
			bags[k] = Integer.parseInt(br.readLine());
		}
		Arrays.sort(bags);

		// System.out.println("gems " + pq);
		// System.out.println("Arrays.toString(bags) = " + Arrays.toString(bags));

		for (int k = 0; k < K; k++) {
			int now = bags[k];
			// int cnt = 0;

			if (!pq.isEmpty() && pq.peek().weight < now) {
				// Gem gem = pq.peek();
				// if (cnt + gem.weight > now) {
				// 	break;
				// }
				// cnt += gem.weight;
				answer += pq.poll().value;
			}
		}

		System.out.println(answer);
	}

	static class Gem implements Comparable<Gem> {
		int weight;
		int value;

		public Gem(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Gem gem) {
			// return this.value - gem.value == 0 ? this.weight - gem.weight : gem.value - this.value;
			return this.weight - gem.weight == 0 ? gem.value - this.value : this.weight - gem.weight;
		}

		@Override
		public String toString() {
			return "Gem{" +
					"weight=" + weight +
					", value=" + value +
					'}';
		}
	}

	static class Bag implements Comparable<Bag> {
		int weight;
		int value;

		public Bag(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		@Override
		public int compareTo(Bag bag) {
			return bag.value - this.value;
		}

		@Override
		public String toString() {
			return "Bag{" +
					"weight=" + weight +
					", value=" + value +
					'}';
		}
	}
}
