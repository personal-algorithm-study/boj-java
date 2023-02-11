import sys


def solution():
    idx = 0
    for _next, alp in arr:
        idx = (idx - int(_next) + N) % N
        if answer[idx] != "?":
            if answer[idx] != alp:
                return "!"
        answer[idx] = alp

    for i in range(N - 1):
        if answer[i] == "?":
            continue
        for j in range(i + 1, N):
            if answer[i] == answer[j]:
                return "!"
    return ''.join(answer[idx:] + answer[:idx])


if __name__ == "__main__":
    input = sys.stdin.readline
    N, K = map(int, input().rstrip().split(" "))
    arr = [list(input().rstrip().split(" ")) for i in range(K)]
    answer = ["?" for _ in range(N)]
    print(solution())
