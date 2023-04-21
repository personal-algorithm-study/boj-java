import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def solution():
    q = deque()
    visited = [-1 for _ in range(n + 1)]
    visited[start] = 0
    q.append(start)

    while q:
        now = q.popleft()
        for u in graph[now]:
            if visited[u] == -1:
                visited[u] = visited[now] + 1
                q.append(u)
    return visited[end]


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    start, end = map(int, input().rstrip().split())
    m = int(input())

    graph = [[] for _ in range(n + 1)]
    for _ in range(m):
        v1, v2 = map(int, input().rstrip().split())
        graph[v1].append(v2)
        graph[v2].append(v1)

    print(solution())
