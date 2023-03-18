import sys


def solution():
    curr = 0
    answer = 0
    for i in range(max_index + 1):
        curr = max(curr, arr[i])
        answer += curr

    curr = 0
    for i in range(1000, max_index, -1):
        curr = max(curr, arr[i])
        answer += curr
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    arr = [0 for _ in range(1001)]

    max_index = 0
    max_height = 0
    for i in range(int(input())):
        w, h = map(int, input().rstrip().split(" "))
        arr[w] = h
        if max_height < h:
            max_index = w
            max_height = h
    print(solution())
