import sys


def solution():
    stack = [[] for _ in range(7)]
    answer = 0

    for st, fl in arr:
        while stack[st] and stack[st][-1] > fl:
            stack[st].pop()
            answer += 1
        if not stack[st] or stack[st][-1] != fl:
            stack[st].append(fl)
            answer += 1
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n, f = map(int, input().rstrip().split(" "))
    arr = [list(map(int, input().rstrip().split(" "))) for _ in range(n)]
    print(solution())
