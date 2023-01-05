from itertools import combinations

n = int(input())
players = [list(map(int, input().split())) for i in range(n)]
indexs = combinations(range(n), n//2)
start = 0
link = 0
results = []

x = list(combinations(range(n), n//2))

# print(x)

for i in range(len(x)):
    y = list(combinations(x[i], 2))
    z = list(combinations(x[-(i+1)], 2))  # x[len(x[i]) - i - 1], 2))
    # print(y)
    # print(z)

    start = 0
    link = 0
    for e in y:
        start += players[e[0]][e[1]]
        start += players[e[1]][e[0]]

    for e in z:
        link += players[e[0]][e[1]]
        link += players[e[1]][e[0]]

    results.append(abs(start - link))

print(min(results))
