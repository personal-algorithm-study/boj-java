import math
import sys


def solution():
    start, end = 1, 10 ** 18
    answer = 0
    while start <= end:
        mid = (start + end) // 2
        now = mid
        ai = A
        for t, a, h in arr:
            if t == 1:
                turn = math.ceil(h / ai)
                now -= a * (turn - 1)
                if now <= 0:
                    break
            else:
                now = min(now + h, mid)
                ai += a

        if now > 0:
            end = mid - 1
            answer = mid
        else:
            start = mid + 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    N, A = map(int, input().rstrip().split(" "))
    arr = [list(map(int, input().rstrip().split(" "))) for _ in range(N)]

    print(solution())
