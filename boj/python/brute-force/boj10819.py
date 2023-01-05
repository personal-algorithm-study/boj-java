from itertools import permutations

n = int(input())
numbers = list(map(int, input().split()))
results = []
result = 0

numbers_permutations = list(permutations(numbers, n))

for permutation in numbers_permutations:
    result = 0
    for i in range(len(permutation) - 1):
        result += abs(permutation[i] - permutation[i+1])
    results.append(result)

print(max(results))
