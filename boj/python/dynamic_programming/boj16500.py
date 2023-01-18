import sys


def solution(idx):
    global result
    if idx == len(s):
        result = 1
        return
    if dp[idx]:
        return
    dp[idx] = 1

    for i in range(len(a)):
        if len(s[idx:]) >= len(a[i]):
            for j in range(len(a[i])):
                if a[i][j] != s[idx + j]:
                    break
            else:
                solution(idx + len(a[i]))


if __name__ == "__main__":
    input = sys.stdin.readline
    s = input().rstrip()
    n = int(input())
    a = [input().rstrip() for _ in range(n)]

    dp = [0] * 101
    result = 0

    solution(0)
    print(result)