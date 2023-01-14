import sys

sys.setrecursionlimit(10 ** 6)


def solution(entire, ran):
    if dp[entire][ran] != -1:
        return dp[entire][ran]
    dp[entire][ran] = (solution(entire - 1, ran - 1) % M + solution(entire - 1, ran) % M) % M
    return dp[entire][ran]


if __name__ == "__main__":
    input = sys.stdin.readline
    n, k = map(int, input().strip().split(" "))
    dp = [[-1] * (k + 1) for _ in range(n + 1)]
    M = 10007

    for i in range(n + 1):
        for j in range(k + 1):
            if i == 0 or j == 0 or i == j:
                dp[i][j] = 1
    print(solution(n, k))