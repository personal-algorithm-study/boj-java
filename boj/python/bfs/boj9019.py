import sys
from collections import deque


def solution(before, after):
    MAX_VALUE = 10000
    answer = ""
    visited = [False for _ in range(MAX_VALUE + 1)]

    q = deque()
    q.append((before, ""))
    visited[before] = True

    while q:
        now, command = q.popleft()

        if now == after:
            answer = command
            break

        _next = now * 2 % MAX_VALUE
        if not visited[_next]:
            visited[_next] = True
            q.append((_next, command + "D"))

        _next = now - 1 if now != 0 else MAX_VALUE - 1
        if not visited[_next]:
            visited[_next] = True
            q.append((_next, command + "S"))

        _next = (now % 1000) * 10 + now // 1000
        if not visited[_next]:
            visited[_next] = True
            q.append((_next, command + "L"))

        _next = (now % 10) * 1000 + now // 10
        if not visited[_next]:
            visited[_next] = True
            q.append((_next, command + "R"))
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    for i in range(int(input())):
        b, a = map(int, input().rstrip().split())
        print(solution(b, a))
