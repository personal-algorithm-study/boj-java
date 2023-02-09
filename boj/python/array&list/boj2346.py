import sys


def solution():
    now = 0
    answer = []
    is_completed = 0
    while is_completed != n:
        if flags[now]:
            now = (now + 1) % n
        else:
            is_completed += 1
            answer.append(now + 1)
            now = (now + arr[now]) % len(arr)
            if now < 0:
                now += n
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    arr = list(map(int, input().rstrip().split(" ")))
    flags = [0 for _ in range(n)]
    print(solution())

'''




'''
