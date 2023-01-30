import sys


def solution():
    start, end = 1, arr[-1] - arr[0]
    for i in range(N - 1):
        start = min(start, arr[i + 1] - arr[i])
        end = max(end, arr[i + 1] - arr[i])

    while start <= end:
        mid = (start + end) // 2
        count, now = 1, arr[0]
        for i in range(1, N):
            if arr[i] - now >= mid:
                count += 1
                now = arr[i]

            if count >= C:
                break

        if count < C:
            end = mid - 1
        else:
            start = mid + 1
    return end


if __name__ == "__main__":
    input = sys.stdin.readline
    N, C = map(int, input().rstrip().split(" "))
    arr = [int(input().rstrip()) for _ in range(N)]
    arr.sort()
    print(solution())
