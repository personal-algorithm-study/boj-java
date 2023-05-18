import heapq
import sys


def solution():
    q = []
    heapq.heappush(q, (0, start))

    dist = [INF for _ in range(v + 1)]
    dist[start] = 0

    while q:
        cost, now = heapq.heappop(q)

        for n, c in graph[now]:
            if dist[n] > cost + c:
                dist[n] = cost + c
                heapq.heappush(q, (dist[n], n))
    return dist


if __name__ == "__main__":
    input = sys.stdin.readline
    INF = sys.maxsize

    v, e = map(int, input().rstrip().split())
    start = int(input())
    graph = [[] for _ in range(v + 1)]

    for _ in range(e):
        u, x, w = map(int, input().rstrip().split())
        graph[u].append((x, w))

    dist = solution()
    for i in range(1, v + 1):
        if dist[i] == INF:
            print("INF")
        else:
            print(dist[i])
