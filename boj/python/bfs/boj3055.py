import sys
from collections import deque


def solution(lengths, start, water, graph):
    r, c = lengths
    distance = [[0] * c for _ in range(r)]

    q = deque()
    for w in water:
        q.append(w)
    if start:
        q.append(start)

    while q:
        x, y = q.popleft()

        # for i in range(r):
        #     print(*distance[i])
        # print()
        # for i in range(r):
        #     print(*graph[i])
        # print()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or r <= nx or ny < 0 or c <= ny:
                continue
            elif graph[nx][ny] == "X" or graph[nx][ny] == "*":
                continue

            if graph[x][y] == "*" and (x, y) != start:
                if graph[nx][ny] == "D": continue
                graph[nx][ny] = "*"
                q.append((nx, ny))
            else:
                if distance[nx][ny] != 0: continue
                distance[nx][ny] = distance[x][y] + 1
                q.append((nx, ny))
                if (nx, ny) == "D":
                    return distance[nx][ny]
    return "KAKTUS"


def initialize():
    lengths = tuple(map(int, input().rstrip().split()))
    arr = [list(input().rstrip()) for _ in range(lengths[0])]
    start, water = [], []

    for i in range(lengths[0]):
        for j in range(lengths[1]):
            if arr[i][j] == "S":
                start = (i, j)
            elif arr[i][j] == "*":
                water.append((i, j))
    return lengths, start, water, arr


if __name__ == "__main__":
    input = sys.stdin.readline
    dx = [0, 1, 0, -1]
    dy = [1, 0, -1, 0]
    l, s, w, g = initialize()
    print(solution(l, s, w, g))
