import sys
from collections import deque


def solution(end):
    q = deque([i + 1 for i in range(end)])
    while q:
        if len(q) == 1:
            break
        q.popleft()
        q.append(q.popleft())
    return q.pop()


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    print(solution(n))
