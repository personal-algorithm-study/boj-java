def solution():
    return


def find(p, a):
    if p[a] != a:
        p[a] = find(p, p[a])
    return p[a]


def union(p, a, b):
    pa = find(p, a)
    pb = find(p, b)

    if pa < pb:
        p[pb] = pa
    else:
        p[pa] = pb


n = int(input())
m = int(input())
answer = "YES"

parent = [i for i in range(n)]
for i in range(n):
    adj = list(map(int, input().split()))
    for j in range(n):
        if adj[j]:
            union(parent, i, j)

path = list(map(int, input().split()))
_set = find(parent, path[0])

for i in range(m):
    if find(parent, path[i] - 1) != _set:
        answer = "NO"
        break
print(answer)
