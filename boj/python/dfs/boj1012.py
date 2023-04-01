import sys

dr = [0, 1, 0, -1]
dc = [1, 0, -1, 0]


def solution(field):
    answer = 0

    def dfs(r, c):
        for d in range(4):
            nr = r + dr[d]
            nc = c + dc[d]
            if 0 <= nr < n and 0 <= nc < m and field[nr][nc] == 1:
                field[nr][nc] = 0
                dfs(nr, nc)
        return

    for i in range(n):
        for j in range(m):
            if field[i][j] == 1:
                answer += 1
                dfs(i, j)

    for k in range(n):
        print(*field[k])
    print()

    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    for _ in range(int(input())):
        n, m, k = map(int, input().rstrip().split(" "))
        _map = [[0] * m for _ in range(n)]
        for a in range(k):
            x, y = list(map(int, input().rstrip().split(" ")))
        _map[x][y] = 1
        print(solution(_map))
