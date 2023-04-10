import sys


def solution():
    answer = [[0] * n for _ in range(n)]

    def dfs(start, now):
        if answer[start][now]:
            return
        answer[start][now] = 1

        for v in range(n):
            if graph[now][v]:
                dfs(start, v)
        return

    for i in range(n):
        for j in range(n):
            if graph[i][j]:
                dfs(i, j)

    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    graph = [list(map(int, input().rstrip().split())) for _ in range(n)]

    answer = solution()
    for i in range(n):
        print(*answer[i])
