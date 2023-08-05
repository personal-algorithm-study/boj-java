package recursion;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 실버5 - 재귀함수가 뭔가요?
public class Boj17478 {
	static StringBuilder sb = new StringBuilder();

	public static void recursiveFunction(int depth, int n) {
		StringBuilder s = new StringBuilder();

		for (int i = 0; i < depth; i++) {
			s.append("____");
		}

		sb.append(s).append("\"재귀함수가 뭔가요?\"").append('\n');
		if (n == depth) {
			sb.append(s).append("\"재귀함수는 자기 자신을 호출하는 함수라네\"").append('\n');
			sb.append(s).append("라고 답변하였지.").append('\n');
			return;
		}
		sb.append(s).append("\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.").append('\n');
		sb.append(s).append("마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.").append('\n');
		sb.append(s).append("그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"").append('\n');
		recursiveFunction(depth + 1, n);
		sb.append(s).append("라고 답변하였지.").append('\n');
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int n = Integer.parseInt(br.readLine());
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.").append('\n');
		recursiveFunction(0, n);

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
}
