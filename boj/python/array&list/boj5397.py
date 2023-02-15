import sys


def solution():
    answer = []
    for case in arr:
        st1, st2 = [], []
        for cmd in case:
            if cmd == "<":
                if st1:
                    st2.append(st1.pop())
            elif cmd == ">":
                if st2:
                    st1.append(st2.pop())
            elif cmd == "-":
                if st1:
                    st1.pop()
            else:
                st1.append(cmd)
        st1.extend(reversed(st2))
        answer.append(''.join(st1))
    return answer


if __name__ == "__main__":
    input = sys.stdin.readline
    t = int(input())
    arr = [list(input().rstrip()) for _ in range(t)]
    for i in solution():
        print(i)
        pass
