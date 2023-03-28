import sys
from collections import deque


def solution():
    answer, max_length = 0, 0
    for name in names:
        max_length = max(max_length, len(name))

    length_q = [deque() for _ in range(max_length + 1)]
    for i in range(len(names)):
        length = len(names[i])
        while length_q[length] and i - length_q[length][-1] > k:
            length_q[length].pop()
        answer += len(length_q[length])
        length_q[length].appendleft(i)
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n, k = map(int, input().rstrip().split(" "))
    names = [input().rstrip() for _ in range(n)]
    print(solution())