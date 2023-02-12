import collections
import sys


def solution():
    cursor = len(q)
    for c in cmds:
        if len(c) == 2:
            q.insert(cursor, c[1])
            cursor = min(len(q), cursor + 1)
        else:
            if c[0] == "L":
                cursor = max(0, cursor - 1)
            elif c[0] == "D":
                cursor = min(len(q), cursor + 1)
            else:
                if cursor - 1 >= 0:
                    del q[cursor - 1]
                    cursor = max(0, cursor - 1)
    return ''.join(q)


if __name__ == "__main__":
    input = sys.stdin.readline
    q = collections.deque(input().rstrip())
    m = int(input())
    cmds = [list(input().rstrip().split(" ")) for _ in range(m)]
    print(solution())
