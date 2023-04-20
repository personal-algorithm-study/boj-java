import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def solution():
    visited = [False for _ in range(n + 1)]
    dfs_track = []
    bfs_track = []

    def dfs(now):
        visited[now] = True
        dfs_track.append(now)

        for w in graph[now]:
            if not visited[w]:
                # dfs_track.append(w)
                dfs(w)
        return

    def bfs(start):
        bfs_track.append(v)
        visited[v] = True

        q = deque()
        q.append(start)

        while q:
            now = q.popleft()
            for w in graph[now]:
                if not visited[w]:
                    q.append(w)
                    visited[w] = True
                    bfs_track.append(w)
        return

    dfs(v)

    visited = [False for _ in range(n + 1)]
    bfs(v)

    print(*dfs_track)
    print(*bfs_track)

    return


if __name__ == "__main__":
    input = sys.stdin.readline
    n, m, v = map(int, input().rstrip().split())
    graph = [[] for _ in range(n + 1)]

    for _ in range(m):
        v1, v2 = map(int, input().rstrip().split())
        graph[v1].append(v2)
        graph[v2].append(v1)

    for i in range(n + 1):
        graph[i].sort()

    solution()
