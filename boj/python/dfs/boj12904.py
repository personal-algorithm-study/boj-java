import sys


def solution():
    answer = 0
    stack = []
    stack.append(b)
    while stack:
        now = stack.pop()
        if now == a:
            answer = 1
            break

        result1, result2 = op1(now), op2(now)
        if result1 is not None:
            stack.append(result1)
        if result2 is not None:
            stack.append(result2)
    return answer


def op1(inp: str):
    if len(inp) > 0 and inp[-1] == "A":
        return inp[:-1]
    return None


def op2(inp: str):
    if len(inp) == 0 or inp[-1] != "B":
        return None
    inp = inp[:-1]

    if len(inp) == 0: return None
    _reversed = [inp[i] for i in range(len(inp) - 1, -1, -1)]
    return ''.join(_reversed)


if __name__ == "__main__":
    input = sys.stdin.readline
    a = input().rstrip()
    b = input().rstrip()
    print((solution()))
