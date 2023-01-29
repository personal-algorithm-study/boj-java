import sys


def solution():
    start, end = 1, min(arr)
    while start <= end:
        mid = (start + end) // 2
        result = 0
        for i in range(K):
            result += arr[i] // mid
        if result < N:
            end = mid - 1
        else:
            start = mid + 1
    return end


if __name__ == "__main__":
    input = sys.stdin.readline
    K, N = map(int, input().rstrip().split(" "))
    arr = [int(input()) for _ in range(K)]
    print(solution())
