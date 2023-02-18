import sys


def solution():
    stack = []
    for i in exp:
        if i.isalpha():
            stack.append(nums[i])
        else:
            b = stack.pop()
            a = stack.pop()
            stack.append(calculate(a, b, i))
    return stack.pop()


def calculate(first, second, operator):
    if operator == "+":
        return first + second
    elif operator == "-":
        return first - second
    elif operator == "*":
        return first * second
    else:
        return first / second


if __name__ == "__main__":
    input = sys.stdin.readline

    n = int(input())
    exp = list(input().rstrip())
    nums = dict()

    for i in range(n):
        nums[chr(65 + i)] = int(input().rstrip())

    print('%.2f' % solution())
