import sys


def solution():
    answer = []
    for p in arr:
        if is_valid(p):
            answer.append("YES")
        else:
            answer.append("NO")
    return answer


def is_valid(vsp):
    stack = []
    for l in vsp:
        if l == "(":
            stack.append(l)
        else:
            if stack:
                stack.pop()
            else:
                return False
    return len(stack) == 0


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    arr = [input().rstrip() for _ in range(n)]
    for i in solution():
        print(i)
