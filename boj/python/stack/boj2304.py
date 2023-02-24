import sys


def solution():
    stack = [arr[0]]
    answer = 0
    for i in range(n):
        if stack and stack[-1][1] < arr[i][1]:
            start, height = stack.pop()
            answer += height * (arr[i][0] - start)
            stack.append(arr[i])

    stack = [arr[-1]]
    for i in range(n - 2, -1, -1):
        if stack and stack[-1][1] < arr[i][1]:
            end, height = stack.pop()
            answer += height * (end - arr[i][0])
            stack.append(arr[i])

    max_height = 0
    for i in range(n):
        if max_height < arr[i][1]:
            max_height = arr[i][1]

    max_list = []
    for i in range(n):
        if arr[i][1] == max_height:
            max_list.append(arr[i])
    answer += max_height * (max_list[-1][1] - max_list[0][1]) if max_list[-1][1] - max_list[0][1] != 0 else max_height
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    n = int(input())
    arr = []
    for i in range():
        a, b = map(int, input().rstrip().split(" "))
        arr.append((a, b))
    arr.sort(key=lambda x: x[0])
    # arr = [list(map(int, input().rstrip().split(" "))) for _ in range(n)]

    print(solution())
