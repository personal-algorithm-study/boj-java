import sys

sys.setrecursionlimit(10000)


def solution():
    answer = 0

    def dfs(now: int):
        for node in edges[now]:
            if not visited[node]:
                visited[node] = True
                dfs(node)

    for i in range(1, n + 1):
        if not visited[i]:
            dfs(i)
            answer += 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n, m = map(int, input().rstrip().split(" "))

    visited = [False for _ in range(n + 1)]
    edges = [[] for _ in range(n + 1)]
    for _ in range(m):
        u, v = map(int, input().rstrip().split(" "))
        edges[u].append(v)
        edges[v].append(u)

    print(solution())
