import sys


def solution():
    return len(a - b) + len(b - a)


if __name__ == "__main__":
    input = sys.stdin.readline
    n, m = map(int, input().rstrip().split())
    a = set(map(int, input().rstrip().split()))
    b = set(map(int, input().rstrip().split()))

    print(solution())
