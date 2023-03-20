import sys


def solution(word):
    global answer
    stack = []
    for w in word:
        if stack and stack[-1] == w:
            stack.pop()
        else:
            stack.append(w)

    if len(stack) == 0:
        answer += 1


if __name__ == "__main__":
    input = sys.stdin.readline
    answer = 0
    for _ in range(int(input())):
        solution(input().rstrip())
    print(answer)
