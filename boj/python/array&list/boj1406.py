import sys


def solution():
    for c in cmds:
        if c[0] == "L":
            if st1:
                st2.append(st1.pop())
        elif c[0] == "D":
            if st2:
                st1.append(st2.pop())
        elif c[0] == "B":
            if st1:
                st1.pop()
        else:
            st1.append(c[1])
    st1.extend(reversed(st2))
    return ''.join(st1)


if __name__ == "__main__":
    input = sys.stdin.readline
    st1 = list(input().rstrip())
    st2 = []
    m = int(input())
    cmds = [list(input().rstrip().split(" ")) for _ in range(m)]
    print(solution())
