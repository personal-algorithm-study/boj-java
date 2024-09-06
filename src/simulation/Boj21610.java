import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.StringTokenizer;

// (골드 5) boj 21610 - 마법사 상어와 비바라기
public class Boj21610 {
    static int N, M;
    static final int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 정사각형 격자의 크기
        M = Integer.parseInt(st.nextToken()); // 물 이동 횟수

        int[][] mat = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Set<Pos> clouds = new HashSet<>();
        clouds.add(new Pos(N - 1, 0));
        clouds.add(new Pos(N - 1, 1));
        clouds.add(new Pos(N - 2, 0));
        clouds.add(new Pos(N - 2, 1));

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());
            clouds = moveCloud(clouds, D, S);
            water(clouds, mat);
            copyWater(clouds, mat);
            clouds = createCloud(clouds, mat);
        }

        int answer = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                answer += mat[i][j];
            }
        }

        System.out.println(answer);
        br.close();
    }

    private static Set<Pos> createCloud(Set<Pos> clouds, int[][] arr) {
        Set<Pos> newClouds = new HashSet<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (arr[x][y] >= 2 && !clouds.contains(new Pos(x, y))) {
                    arr[x][y] -= 2;
                    newClouds.add(new Pos(x, y));
                }
            }
        }
        return newClouds;
    }

    private static Set<Pos> moveCloud(Set<Pos> clouds, int d, int s) {
        Set<Pos> movedClouds = new HashSet<>();
        --d;
        for (Pos cloud : clouds) {
            int nx = cloud.x + (dx[d] * s) % N;
            int ny = cloud.y + (dy[d] * s) % N;
            if (nx < 0)
                nx += N;
            if (ny < 0)
                ny += N;
            nx %= N;
            ny %= N;
            movedClouds.add(new Pos(nx, ny));
        }
        return movedClouds;
    }

    private static void water(Set<Pos> clouds, int[][] mat) {
        for (Pos cloud : clouds) {
            ++mat[cloud.x][cloud.y];
        }
    }

    private static void copyWater(Set<Pos> clouds, int[][] mat) {
        for (Pos cloud : clouds) {
            int x = cloud.x;
            int y = cloud.y;
            for (int i = 1; i < 8; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (mat[nx][ny] <= 0) continue;
                ++mat[x][y];
            }
        }
    }

    static class Pos {
        int x;
        int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Pos pos = (Pos) o;
            return x == pos.x && y == pos.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}