import sys

sys.setrecursionlimit(1000000)


def solution():
    visited = [0 for _ in range(m + 1)]
    is_cycle = False

    def dfs(now):
        nonlocal is_cycle, answer
        if is_cycle or len(graph[now]) <= 0:
            return

        _next = graph[now][0]

        if not visited[_next]:
            visited[_next] = 1
            dfs(_next)
        else:
            is_cycle = True
        return

    dfs(p)

    return sum(visited) if not is_cycle else -1


if __name__ == "__main__":
    input = sys.stdin.readline
    n, m, p = map(int, input().rstrip().split())
    graph = [[] for _ in range(m + 1)]
    for _ in range(n):
        u, v = map(int, input().rstrip().split())
        graph[v].append(u)
    print(solution())
