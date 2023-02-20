import sys


def solution():
    answer = 0
    stack = [(0, arr[0])]
    for i in range(1, n + 1):
        s = i
        while stack and stack[-1][1] >= arr[i]:
            s, h = stack.pop()
            answer = max(answer, h * (i - s))
        stack.append((s, arr[i]))
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    arr = [int(input().rstrip()) for _ in range(n)]
    arr.append(0)
    print(solution())
