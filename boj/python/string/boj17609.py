import sys


def solution():
    result = 2
    length = len(s)

    start = 0
    end = length - 1

    if check_palindrome(s, start, end):
        return 0

    while start < end:
        if s[start] == s[end]:
            start += 1
            end -= 1
        else:
            left = check_palindrome(s, start + 1, end)
            right = check_palindrome(s, start, end - 1)
            if left or right:
                result = 1
            break
    return result


def check_palindrome(string, left, right):
    while left < right:
        if string[left] != string[right]:
            return False
        left += 1
        right -= 1
    return True


if __name__ == "__main__":
    input = sys.stdin.readline
    for _ in range(int(input())):
        s = input().rstrip()
        print(solution())
