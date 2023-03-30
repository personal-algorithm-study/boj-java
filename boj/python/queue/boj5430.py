import sys
from collections import deque


def solution():
    f = input().rstrip()
    n = int(input().rstrip())
    arr_str = input().rstrip()
    if n == 0:
        arr = []
    elif n == 1:
        arr = [int(arr_str[1:-1])]
    else:
        arr = list(map(int, arr_str[1:-1].split(",")))

    q = deque(arr)
    is_front = True
    for i in f:
        if i == "R":
            is_front = not is_front
        else:
            if not q:
                return "error"
            elif is_front:
                q.popleft()
            else:
                q.pop()
    return "[" + ",".join(map(str, q)) + "]" if is_front \
        else "[" + ",".join(reversed(list(map(str, q)))) + "]"


if __name__ == "__main__":
    input = sys.stdin.readline
    for _ in range(int(input())):
        print(solution())
