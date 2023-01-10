import sys

input = sys.stdin.readline


def solution(length):
    dp = [[], []]
    dp[0] = list(map(int, input().split(" ")))
    dp[1] = list(map(int, input().split(" ")))
    if length == 1:
        return max(dp[0][0], dp[1][0])
    elif length == 2:
        return max(dp[0][0] + dp[1][1], dp[1][0] + dp[0][1])
    dp[0][1] += dp[1][0]
    dp[1][1] += dp[0][0]

    for i in range(2, length):
        before = max(dp[0][i - 2], dp[1][i - 2])
        dp[0][i] += max(before, dp[1][i - 1])
        dp[1][i] += max(before, dp[0][i - 1])
    return max(dp[0][length - 1], dp[1][length - 1])


if __name__ == "__main__":
    t = int(input())
    for _ in range(t):
        n = int(input())
        print(solution(n))
