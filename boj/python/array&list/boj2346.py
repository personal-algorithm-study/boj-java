import collections
import sys


def solution():
    answer = []
    while q:
        idx, _next = q.popleft()
        answer.append(idx + 1)
        idx += _next
        if _next > 0:
            q.rotate(-(idx - 1))
        else:
            q.rotate(-idx)
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    q = collections.deque(enumerate(map(int, input().rstrip().split(" "))))
    print(*solution())
