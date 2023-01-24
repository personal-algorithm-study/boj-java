import sys


def solution():
    start, end = 0, max(arr)
    while start <= end:
        mid = (start + end) // 2
        result = 0
        for i in range(n):
            result += min(arr[i], mid)
        if result <= m:
            start = mid + 1
        else:
            end = mid - 1
    return end


if __name__ == "__main__":
    input = sys.stdin.readline

    n = int(input())
    arr = list(map(int, input().rstrip().split(" ")))
    m = int(input())

    print(solution())
