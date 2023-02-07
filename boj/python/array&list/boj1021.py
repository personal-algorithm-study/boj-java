import collections


def solution():
    q = collections.deque([i for i in range(N + 1)])
    q.popleft()
    answer = 0
    for e in arr:
        index = q.index(e)
        if index <= len(q) - index:
            for _ in range(index):
                q.append(q.popleft())
                answer += 1
            q.popleft()
        else:
            for _ in range(len(q) - index):
                q.appendleft(q.pop())
                answer += 1
            q.popleft()
    return answer


if __name__ == "__main__":
    N, M = map(int, input().split(" "))
    arr = list(map(int, input().rstrip().split(" ")))
    print(solution())
