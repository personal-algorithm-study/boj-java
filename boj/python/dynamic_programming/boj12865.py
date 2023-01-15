import sys


def solution():
    for i in range(1, n + 1):
        weight = w[i][0]
        value = w[i][1]
        for j in range(1, k + 1):
            if j < weight:
                dp[i][j] = dp[i - 1][j]
            else:
                dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - weight] + value)
    return dp[n][k]


if __name__ == "__main__":
    input = sys.stdin.readline
    n, k = map(int, input().rstrip().split(" "))
    w = [[0, 0]]
    for i in range(n):
        w.append(list(map(int, input().rstrip().split(" "))))

    dp = [[0] * (k + 1) for _ in range(n + 1)]
    print(solution())
