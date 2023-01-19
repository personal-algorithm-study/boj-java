def solution():
    answer = arr[0]
    for i in range(n):
        for j in range(i):
            if arr[j] < arr[i]:
                dp[i] = max(dp[i], dp[j] + arr[i])
                answer = max(answer, dp[i])
    return answer


if __name__ == "__main__":
    n = int(input())
    arr = list(map(int, input().rstrip().split(" ")))
    dp = [arr[i] for i in range(n)]
    print(solution())