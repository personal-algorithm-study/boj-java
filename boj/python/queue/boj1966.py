import sys
from collections import deque


def solution(print_q, seq):
    orders = [0 for _ in range(101)]
    count = 0

    for idx, order in print_q:
        orders[order] += 1

    while print_q:
        now = print_q.popleft()
        is_most = True
        for i in range(now[1] + 1, 101):
            if orders[i] > 0:
                is_most = False
                break
        if is_most:
            count += 1
            orders[now[1]] -= 1
            if seq == now[0]:
                break
        else:
            print_q.append(now)
    return count


if __name__ == "__main__":
    input = sys.stdin.readline
    for _ in range(int(input())):
        n, m = map(int, input().rstrip().split(" "))
        q = deque()
        for i, v in enumerate(input().rstrip().split(" ")):
            q.append((i, int(v)))
        print(solution(q, m))