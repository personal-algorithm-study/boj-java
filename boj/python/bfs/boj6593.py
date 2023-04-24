import sys
from collections import deque

dz = [0, 0, 0, 0, 1, -1]
dx = [0, 1, 0, -1, 0, 0]
dy = [1, 0, -1, 0, 0, 0]


def solution():
    start = (-1, -1, -1)
    end = (-1, -1, -1)

    for i in range(l):
        for j in range(r):
            for k in range(c):
                if arr[i][j][k] == 'S':
                    start = (i, j, k)
                elif arr[i][j][k] == 'E':
                    end = (i, j, k)

    distance = [[[-1] * c for __ in range(r)] for _ in range(l)]

    q = deque()
    q.append(start)
    distance[start[0]][start[1]][start[2]] = 0

    while q:
        z, x, y = q.popleft()

        for direct in range(6):
            nz = z + dz[direct]
            nx = x + dx[direct]
            ny = y + dy[direct]

            if nz < 0 or l <= nz \
                    or nx < 0 or r <= nx \
                    or ny < 0 or c <= ny:
                continue
            if arr[nz][nx][ny] == "#":
                continue
            if distance[nz][nx][ny] != -1:
                continue
            distance[nz][nx][ny] = distance[z][x][y] + 1
            q.append((nz, nx, ny))

    return distance[end[0]][end[1]][end[2]] if distance[end[0]][end[1]][end[2]] != -1 else -1


if __name__ == "__main__":
    input = sys.stdin.readline
    while True:
        l, r, c = map(int, input().rstrip().split())
        arr = []
        for i in range(l):
            layer = []
            for j in range(r):
                layer.append(input().rstrip())
            input().rstrip()
            arr.append(layer)
        if l == 0 and r == 0 and c == 0:
            break
        v = solution()
        if v == -1:
            print("Trapped!")
        else:
            print("Escaped in {0} minute(s).".format(v))
