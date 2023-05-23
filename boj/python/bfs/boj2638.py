import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def solution():
    global cheese
    answer = 0
    q = deque()

    while cheese > 0:
        answer += 1
        q.append((0, 0))
        visited = [[False] * m for _ in range(n)]

        while q:
            x, y = q.popleft()
            for i in range(4):
                nx = x + dx[i]
                ny = y + dy[i]

                if nx < 0 or nx >= n or ny < 0 or ny >= m:
                    continue
                if visited[nx][ny]:
                    continue

                if graph[nx][ny] >= 1:
                    graph[nx][ny] += 1
                else:
                    visited[nx][ny] = True
                    q.append((nx, ny))

        for i in range(n):
            for j in range(m):
                if graph[i][j] == 2:
                    graph[i][j] = 1
                elif graph[i][j] >= 3:
                    graph[i][j] = 0
                    cheese -= 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline

    n, m = map(int, input().rstrip().split())
    cheese = 0
    graph = []
    for i in range(n):
        graph.append(list(map(int, input().rstrip().split())))
        cheese += sum(graph[i])
    print(solution())
