import collections
import sys

MAX_SIZE = 100000
input = sys.stdin.readline
n, m = map(int, input().rstrip().split(" "))


def solution(n, m):
    memo = [sys.maxsize for _ in range(MAX_SIZE + 1)]
    queue = collections.deque()

    memo[n] = 0
    queue.append(n)

    while queue:
        cur = queue.popleft()

        for next_ in [cur - 1, cur + 1]:
            if 0 <= next_ <= MAX_SIZE \
                    and memo[cur] + 1 < memo[next_]:
                memo[next_] = memo[cur] + 1
                queue.append(next_)

        if 0 <= cur * 2 <= MAX_SIZE \
                and memo[cur] < memo[cur * 2]:
            memo[cur * 2] = memo[cur]
            queue.append(cur * 2)

    return memo[m]


print(solution(n, m))
