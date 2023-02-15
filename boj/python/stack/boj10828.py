import sys


def solution():
    stack = []
    answer = []

    for c in cmd:
        if c[0] == "push":
            stack.append(c[1])
        elif c[0] == "pop":
            if stack:
                answer.append(stack.pop())
            else:
                answer.append(-1)
        elif c[0] == "size":
            answer.append(len(stack))
        elif c[0] == "empty":
            size = 1 if len(stack) == 0 else 0
            answer.append(size)
        elif c[0] == "top":
            if stack:
                answer.append(stack[-1])
            else:
                answer.append(-1)
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline

    n = int(input())
    cmd = [input().rstrip().split(" ") for _ in range(n)]
    for a in solution():
        print(a)
