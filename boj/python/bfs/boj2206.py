import collections

dx = [0, 1, 0, -1]
dy = [1, 0, -1, 0]

'''
[
    [[0, 0], [0, 0], [0, 0], [0, 0]],
    [[0, 0], [0, 0], [0, 0], [0, 0]]
]

[x][y][z]
[2][4][2]

'''


def solution():
    q = collections.deque()
    visited = [[[0] * 2 for _ in range(m)] for _ in range(n)]
    answer = -1

    q.append((0, 0, 0))
    visited[0][0][0] = 1

    while q:
        x, y, broken = q.popleft()

        if x == n - 1 and y == m - 1:
            answer = visited[x][y][broken]
            break

        for i in range(4):
            nx = x + dx[i]
            ny = y + dy[i]

            if nx < 0 or nx >= n or ny < 0 or ny >= m: continue
            if visited[nx][ny][broken] != 0: continue
            if arr[nx][ny] == "0":
                visited[nx][ny][broken] = visited[x][y][broken] + 1
                q.append((nx, ny, broken))
            elif arr[nx][ny] == "1" and not broken:
                visited[nx][ny][1] = visited[x][y][broken] + 1
                q.append((nx, ny, 1))

    return answer


if __name__ == '__main__':
    n, m = map(int, input().rstrip().split())
    arr = [input().rstrip() for _ in range(n)]
    print(solution())
