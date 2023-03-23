import sys
from collections import deque


def solution(command):
    if command[0] == "push":
        q.appendleft(command[1])
    elif command[0] == "pop":
        if q:
            answer.append(q.pop())
        else:
            answer.append(-1)
    elif command[0] == "size":
        answer.append(len(q))
    elif command[0] == "empty":
        if len(q) == 0:
            answer.append(1)
        else:
            answer.append(0)
    elif command[0] == "front":
        if len(q) > 0:
            answer.append(q[-1])
        else:
            answer.append(-1)
    elif command[0] == "back":
        if len(q) > 0:
            answer.append(q[0])
        else:
            answer.append(-1)


if __name__ == "__main__":
    input = sys.stdin.readline
    q = deque()
    answer = []
    for _ in range(int(input())):
        c = input().rstrip().split(" ")
        solution(c)

    for a in answer:
        print(a)
