import sys

dx = [0, 1]
dy = [1, 0]


def solution():
    answer = [0 for _ in range(c + 1)]
    visited = [[0] * (m + 1) for _ in range(n + 1)]

    def dfs(x, y, cnt):
        if x == n and y == m:
            answer[cnt] += 1
            if cnt == c:
                print("cnt:", cnt)
            for i in range(n + 1):
                print(visited[i])
            print()
            return

        for i in range(2):
            nx = x + dx[i]
            ny = y + dy[i]
            if 0 < nx <= n and 0 < ny <= m and not visited[nx][ny]:
                if (nx, ny) in c_set:
                    visited[nx][ny] = 1
                    dfs(nx, ny, cnt + 1)
                    visited[nx][ny] = 0
                else:
                    visited[nx][ny] = 1
                    dfs(nx, ny, cnt)
                    visited[nx][ny] = 0
        return

    visited[1][1] = 1
    dfs(1, 1, 0)

    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n, m, c = map(int, input().rstrip().split())

    c_set = set()
    for _ in range(c):
        p, q = map(int, input().rstrip().split())
        c_set.add((p, q))
    print(solution())
