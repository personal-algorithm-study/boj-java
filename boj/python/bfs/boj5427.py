import sys
from collections import deque

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]


def solution():
    distance = [[0] * w for _ in range(h)]

    answer = sys.maxsize
    start = 0
    fires = []
    for i in range(h):
        for j in range(w):
            if arr[i][j] == "@":
                start = (i, j)
                distance[i][j] = 1
            elif arr[i][j] == "*":
                fires.append((i, j))

    q = deque()
    for x, y in fires:
        q.append((x, y, 0, 1))
    q.append((start[0], start[1], 1, 1))

    while q:
        x, y, _type, time = q.popleft()

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]
            if nx < 0 or h <= nx or ny < 0 or w <= ny:
                if _type == 1:
                    answer = min(answer, time)
                continue
            if arr[nx][ny] == "#": continue
            if _type == 0:
                if arr[nx][ny] != "*":
                    arr[nx][ny] = "*"
                    q.append((nx, ny, 0, -1))
            elif _type == 1:
                if arr[nx][ny] != "*" and distance[nx][ny] == 0:
                    distance[nx][ny] = distance[x][y] + 1
                    q.append((nx, ny, 1, distance[nx][ny]))
    return answer if answer != sys.maxsize else "IMPOSSIBLE"


if __name__ == "__main__":
    input = sys.stdin.readline
    for _ in range(int(input())):
        w, h = map(int, input().rstrip().split())
        arr = [list(input().rstrip()) for __ in range(h)]
        print(solution())
