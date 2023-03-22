import sys


def solution(l):
    stack = []
    word = ""
    is_tag = False

    for i in range(len(l)):
        if l[i] == "<":
            is_tag = True
        elif l[i] == ">":
            is_tag = False
            if len(word) > 0 and word[-1] == "/":
                word = ""
                continue
            elif stack and stack[-1] == word[1:]:
                stack.pop()
            else:
                stack.append(word)
            word = ""

        if is_tag and l[i] == " " and l[i + 1] != "/":
            is_tag = False

        if is_tag and l[i] != "<":
            word += l[i]

    return "legal" if len(stack) == 0 else "illegal"


if __name__ == "__main__":
    input = sys.stdin.readline

    while True:
        line = input().rstrip()
        if line == "#":
            break
        print(solution(line))
