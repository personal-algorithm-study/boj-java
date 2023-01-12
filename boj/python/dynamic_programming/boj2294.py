import sys


def solution():
    MAX_VALUE = sys.maxsize
    dp = [MAX_VALUE for _ in range(k + 1)]
    dp[0] = 0

    for coin in coins:
        for i in range(coin, k + 1):
            dp[i] = min(dp[i], dp[i - coin] + 1)
    return dp[k] if dp[k] != MAX_VALUE else -1


if __name__ == "__main__":
    input = sys.stdin.readline
    n, k = map(int, input().rstrip().split(" "))
    coins = [int(input()) for _ in range(n)]
    print(solution())