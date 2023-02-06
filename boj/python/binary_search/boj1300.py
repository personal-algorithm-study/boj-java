import sys


def solution():
    start, end = 0, K
    answer = 0
    while start <= end:
        mid = (start + end) // 2
        count = 0
        for i in range(1, N + 1):
            count += min(mid // i, N)

        if count >= K:
            answer = mid
            end = mid - 1
        else:
            start = mid + 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    N = int(input())
    K = int(input())
    print(solution())
