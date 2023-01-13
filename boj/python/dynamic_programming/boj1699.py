import sys


def solution():
    square_nums = []
    i = 1
    MAX_VALUE = 100_000
    while i * i <= MAX_VALUE:
        square_nums.append(i * i)
        i += 1

    dp = [MAX_VALUE for _ in range(MAX_VALUE + 1)]
    dp[0] = 0

    for square_num in square_nums:
        for i in range(square_num, n + 1):
            dp[i] = min(dp[i], dp[i - square_num] + 1)
    return dp[n]


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    print(solution())