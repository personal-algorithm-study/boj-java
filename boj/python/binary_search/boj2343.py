def solution():
    start, end = max(arr), sum(arr)
    while start <= end:
        mid = (start + end) // 2
        now, count = 0, 1
        for i in range(N):
            if now + arr[i] <= mid:
                now += arr[i]
            else:
                now = arr[i]
                count += 1
        if M < count:
            start = min(1, mid + 1)
        else:
            end = mid - 1
    return start


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split(" "))
    arr = list(map(int, input().rstrip().split(" ")))

    print(solution())
