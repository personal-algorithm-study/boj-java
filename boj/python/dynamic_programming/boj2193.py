import sys

input = sys.stdin.readline


def solution(digits):
    if n <= 2:
        return 1

    dp = [[0, 0] for _ in range(digits + 1)]
    dp[1] = [0, 1]
    dp[2] = [1, 0]

    for i in range(2, digits):
        dp[i + 1][0] += dp[i][0]
        dp[i + 1][1] += dp[i][0]

        dp[i + 1][0] += dp[i][1]
    return sum(dp[digits])


if __name__ == "__main__":
    n = int(input())
    print(solution(n))