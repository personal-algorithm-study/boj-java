import sys


def solution():
    start, end = min(arr), sum(arr)
    while start <= end:
        mid = (start + end) // 2
        now = mid
        count = 1
        for i in range(N):
            if now < arr[i]:
                now = mid
                count += 1
            now -= arr[i]

        if count > M or mid < max(arr):
            start = mid + 1
        else:
            end = mid - 1
    return start


if __name__ == "__main__":
    input = sys.stdin.readline
    N, M = map(int, input().rstrip().split(" "))
    arr = [int(input().rstrip()) for _ in range(N)]

    print(solution())
