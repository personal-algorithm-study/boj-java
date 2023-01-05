from itertools import combinations

n, m = list(map(int, input().split()))
numbers = list(map(int, input().split()))
answer = int()
diff = int(100000)

numbers_permutation = list(combinations(numbers, 3))
results = set(map(sum, numbers_permutation))

for result in results:
    temp = m - result
    if temp < 0:
        continue

    if diff > temp:
        diff = temp
        answer = result

print(answer)
