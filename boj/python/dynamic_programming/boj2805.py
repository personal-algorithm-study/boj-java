import sys


def solution():
    left, right = 1, max(arr)

    while left <= right:
        mid = (left + right) // 2
        result = 0
        for i in range(n):
            result += max(0, arr[i] - mid)
        if result < m:
            right = mid - 1
        else:
            left = mid + 1
    return right


if __name__ == "__main__":
    input = sys.stdin.readline

    n, m = map(int, input().rstrip().split(" "))
    arr = list(map(int, input().rstrip().split(" ")))
    print(solution())
