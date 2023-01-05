import sys

input = sys.stdin.readline


def solution(start, end):
    if start == end:
        return 0
    elif start + 1 == end:
        return heights[start]

    mid = (start + end) // 2
    result = max(solution(start, mid), solution(mid, end))

    width = 1
    height = heights[mid]

    left = mid
    right = mid

    while right - left + 1 < end - start:
        l_height = min(height, heights[left - 1]) if left > start else -1
        r_height = min(height, heights[right + 1]) if right < end - 1 else -1
        if l_height >= r_height:
            height = l_height
            left -= 1
        else:
            height = r_height
            right += 1
        width += 1
        result = max(result, width * height)
    return result


if __name__ == "__main__":
    n = int(input())
    heights = [int(input()) for _ in range(n)]
    print(solution(0, n))