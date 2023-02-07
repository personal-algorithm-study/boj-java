import collections


def solution():
    q = collections.deque([i for i in range(N + 1)])
    q.popleft()
    answer = 0
    for e in arr:
        index = q.index(e)
        if index <= len(q) - index - 1:
            for _ in range(index):
                q.append(q.popleft())
                answer += 1
            q.popleft()
        else:
            for _ in range(len(q) - index - 1):
                q.appendleft(q.pop())
                answer += 1
            q.pop()
    return answer


if __name__ == "__main__":
    N, M = map(int, input().rstrip().split(" "))
    arr = list(map(int, input().rstrip().split(" ")))
    print(solution())
