package backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// (골드 4) boj 6987 월드컵
public class Boj6987 {
	static char answer;
	static StringBuilder sb = new StringBuilder();
	static int[][] scoreBoard = new int[6][3];

	/*
		15 경기 시뮬레이션
		0 -> 1, 2, 3, 4, 5
		1 -> 2, 3, 4, 5
		2 -> 3, 4, 5
		3 -> 4, 5
		4 -> 5
		 */
	// 경기 대진표
	static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
	static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

	// 실제 경기를 진행시켜보며, 실제 이 대진표로 모순 없이 15경기가 진행되는지 시뮬레이션 해본다.
	static void dfs(int round) {
		if (round == 15) {
			answer = '1';
			return;
		}
		int player1 = home[round];
		int player2 = away[round];

		// home팀이 이긴 경우로 가정하고 다음 경기 진행해 본다.
		if (scoreBoard[player1][0] > 0 && scoreBoard[player2][2] > 0) {
			scoreBoard[player1][0]--;
			scoreBoard[player2][2]--;
			dfs(round + 1);
			scoreBoard[player1][0]++;
			scoreBoard[player2][2]++;
		}

		// away 팀이 이긴 경우로 가정하고 다음 경기 진행해 본다.
		if (scoreBoard[player1][2] > 0 && scoreBoard[player2][0] > 0) {
			scoreBoard[player1][2]--;
			scoreBoard[player2][0]--;
			dfs(round + 1);
			scoreBoard[player1][2]++;
			scoreBoard[player2][0]++;
		}

		// 두 팀이 비긴 경우로 가정하고 다음 경기 진행해 본다.
		if (scoreBoard[player1][1] > 0 && scoreBoard[player2][1] > 0) {
			scoreBoard[player1][1]--;
			scoreBoard[player2][1]--;
			dfs(round + 1);
			scoreBoard[player1][1]++;
			scoreBoard[player2][1]++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 4; i++) {
			int total = 0;

			st = new StringTokenizer(br.readLine());
			answer = '0';

			for (int j = 0; j < 6; j++) {
				for (int k = 0; k < 3; k++) {
					scoreBoard[j][k] = Integer.parseInt(st.nextToken());
					total += scoreBoard[j][k];
				}
			}

			// 대진표 상의 경기 수는 30을 넘을 수 없다.
			// 15경기를 home, away 각각 기록하여 15 * 2 = 30인 경기수가 나온다.
			if (total > 30) {
				sb.append(answer).append(' ');
				continue;
			}

			dfs(0);
			sb.append(answer).append(' ');
		}

		System.out.println(sb);
	}
}
