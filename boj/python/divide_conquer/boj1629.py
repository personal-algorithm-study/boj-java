import sys

input = sys.stdin.readline


def solution(base, expo, divisor):
    if expo == 1:
        return base % divisor
    half = solution(base, expo // 2, divisor)

    if expo % 2 == 0:
        return half * half % divisor
    return (half * half % divisor) * base % divisor


if __name__ == "__main__":
    print(solution(10, 11, 12) == 4)
    a, b, c = map(int, input().split(" "))
    print(solution())