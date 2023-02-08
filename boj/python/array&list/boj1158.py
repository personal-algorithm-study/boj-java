import collections
import sys


def solution():
    q = collections.deque([i for i in range(1, N + 1)])
    arr = []
    while q:
        for _ in range(K - 1):
            if not q:
                break
            q.append(q.popleft())
        if not q:
            break
        arr.append(str(q.popleft()))
    return arr


if __name__ == "__main__":
    input = sys.stdin.readline
    N, K = map(int, input().rstrip().split(" "))
    print("<", ", ".join(solution()), ">", sep="")
