import sys


def solution():
    start, end = 1, 10 ** 12
    while start <= end:
        mid = (start + end) // 2
        now = mid
        ai = A
        is_possible = True
        print(start, end)
        for t, a, h in arr:
            # a 몬스터 공격력, h 생명력
            if t == 1:
                turn = h // ai if h % ai == 0 else h // ai + 1  # math.floor(h / ai)
                now -= a * (turn - 1)
                if now < 0:
                    is_possible = False
                    break
            # a 공격력 증가, h 생명력 증가
            else:
                now = min(now + h, mid)
                ai += a

        if is_possible:
            end = mid - 1
        else:
            start = mid + 1
    return start


if __name__ == "__main__":
    input = sys.stdin.readline
    N, A = map(int, input().rstrip().split(" "))
    arr = [list(map(int, input().rstrip().split(" "))) for _ in range(N)]

    print(solution())
