import sys

def solution():
    start, end = 1, 10 ** 9
    answer = 0
    while start <= end:
        mid = (start + end) // 2
        now = 0
        for a, b, c in arr:
            if a <= mid < b:
                now += (mid - a) // c + 1
            elif b <= mid:
                now += (b - a) // c + 1
        if now >= D:
            end = mid - 1
            answer = mid
        else:
            start = mid + 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    N, K, D = map(int, input().rstrip().split(" "))
    arr = [list(map(int, input().rstrip().split(" "))) for _ in range(K)]

    print(solution())
