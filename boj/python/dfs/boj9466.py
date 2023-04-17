import sys

sys.setrecursionlimit(1000000)


def solution():
    team = 0

    def dfs(now):
        nonlocal team

        visited[now] = True
        _next = graph[now]

        if visited[_next]:
            if not finished[_next]:
                while _next != now:
                    _next = graph[_next]
                    team += 1
                team += 1
        else:
            dfs(graph[now])
        finished[now] = True

    visited = [False for _ in range(n + 1)]
    finished = [False for _ in range(n + 1)]

    for i in range(1, n + 1):
        if not visited[i]:
            dfs(i)

    return n - team


if __name__ == "__main__":
    input = sys.stdin.readline

    for _ in range(int(input())):
        n = int(input())
        graph = [0] + list(map(int, input().rstrip().split()))
        print(solution())
