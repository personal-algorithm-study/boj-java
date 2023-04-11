import sys

sys.setrecursionlimit(1000000)

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]


def solution():
    answer = 1

    def dfs(_min, r, c):
        nonlocal answer, now

        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]
            if 0 <= nr < n and 0 <= nc < n and \
                    not visited[nr][nc] and \
                    arr[nr][nc] > _min:
                visited[nr][nc] = True
                dfs(_min, nr, nc)
        return

    for i in range(1, 101):
        visited = [[False] * n for _ in range(n)]
        now = 0
        for j in range(n):
            for k in range(n):
                if not visited[j][k] and arr[j][k] > i:
                    now += 1
                    visited[j][k] = True
                    dfs(i, j, k)
        answer = max(answer, now)

    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    arr = [list(map(int, input().rstrip().split())) for _ in range(n)]
    print(solution())
