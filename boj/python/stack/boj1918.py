import sys


def solution():
    stack = []
    answer = []
    for i in exp:
        if i.isalpha():
            answer.append(i)
        elif i == "(":
            stack.append(i)
        elif i == ")":
            while stack and stack[-1] != "(":
                answer.append(stack.pop())
            stack.pop()
        else:
            while True:
                if not stack:
                    stack.append(i)
                    break
                if stack[-1] == "(" or priority[stack[-1]] < priority[i]:
                    stack.append(i)
                    break
                answer.append(stack.pop())
    while stack:
        answer.append(stack.pop())
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    exp = list(input().rstrip())

    priority = dict()
    priority["+"], priority["-"] = 1, 1
    priority["*"], priority["/"] = 2, 2

    print(''.join(solution()))
