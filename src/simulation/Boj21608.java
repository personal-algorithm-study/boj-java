import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

// (골드5) boj21608 상어 초등학교 - 성공
public class Boj21608 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Set<Integer>[] graph = new Set[N * N + 1];
        int[] order = new int[N * N];
        for (int i = 0; i < N * N + 1; i++) {
            graph[i] = new HashSet<>();
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            order[i] = now;
            for (int j = 0; j < 4; j++) {
                int friend = Integer.parseInt(st.nextToken());
                graph[now].add(friend);
            }
        }
        System.out.println(new Problem(N, order, graph).solve());
    }

    static class Problem {
        final int N;
        final int[] order;
        final int[][] room;
        final Set<Integer>[] graph;

        static final int[] dr = {0, 1, 0, -1};
        static final int[] dc = {1, 0, -1, 0};

        public Problem(int N, int[] order, Set<Integer>[] graph) {
            this.N = N;
            this.room = new int[N][N];
            this.order = order;
            this.graph = graph;
        }

        public int solve() {
            for (int now : order) {
                // 자리 배치
                Pos pos = search(now);
                room[pos.r][pos.c] = now;
            }
            return cal();
        }

        Pos search(int number) {
            Queue<Pos> pq = new PriorityQueue<>();
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (room[r][c] > 0)
                        continue;
                    Pos pos = new Pos(r, c);
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                            continue;
                        if (room[nr][nc] == 0)
                            pos.blank++;
                        else if (graph[number].contains(room[nr][nc]))
                            pos.friend++;
                    }
                    pq.offer(pos);
                }
            }
            return pq.poll();
        }

        int cal() {
            int answer = 0;
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    int number = room[r][c];
                    int cnt = 0;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (nr < 0 || nr >= N || nc < 0 || nc >= N)
                            continue;
                        if (graph[number].contains(room[nr][nc]))
                            ++cnt;
                    }
                    if (cnt <= 1)
                        answer += cnt;
                    else {
                        answer += (int)Math.pow(10, cnt - 1);
                    }
                }
            }
            return answer;
        }
    }

    static class Pos implements Comparable<Pos> {
        int r;
        int c;
        int friend;
        int blank;

        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Pos o) {
            if (this.friend != o.friend)
                return o.friend - this.friend;
            else if (this.blank != o.blank)
                return o.blank - this.blank;
            else if (this.r != o.r)
                return this.r - o.r;
            else if (this.c != o.c)
                return this.c - o.c;
            return 0;
        }
    }
}

/*
- 친한 사람 多
- 빈칸 多
- 작은 행, 작은 열
 */