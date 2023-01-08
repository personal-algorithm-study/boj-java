import sys

input = sys.stdin.readline


def solution(width):
    dp = [0 for _ in range(1000 + 1)]
    dp[1] = 1
    dp[2] = 3
    for i in range(3, width + 1):
        dp[i] = (dp[i - 1] % DIVISOR + dp[i - 2] * 2 % DIVISOR) % DIVISOR
    return dp[width]


if __name__ == "__main__":
    DIVISOR = 10007
    n = int(input())
    print(solution(n))
