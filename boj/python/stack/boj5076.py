import sys


def solution(l):
    stack = []
    word = ""

    for i in l:
        if i == "<":
            word += i
        elif i == ">":
            if len(word) == 0 or word[0] != "<":
                return "illegal"
            elif word[-1] != "/":
                index = word.find(" ")
                if index == -1:
                    stack.append(word[1:])
                else:
                    stack.append(word[1:index])

                if stack and stack[-1][0] == "/":
                    now = stack.pop()
                    if stack and now[1:] != stack.pop():
                        return "illegal"
            word = ""
        else:
            if len(word) > 0 and word[0] == "<":
                word += i

    if word:
        return "illegal"

    return "legal" if len(stack) == 0 else "illegal"


if __name__ == "__main__":
    input = sys.stdin.readline

    while True:
        line = input().rstrip()
        if line == "#":
            break
        print(solution(line))
