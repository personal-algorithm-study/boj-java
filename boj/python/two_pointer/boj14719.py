import sys

input = sys.stdin.readline
h, w = map(int, input().rstrip().split(" "))
heights = list(map(int, input().rstrip().split(" ")))

left, left_height_max = 0, heights[0]
right, right_height_max = len(heights) - 1, heights[-1]
answer = 0

while left < right:
    left_height_max = max(left_height_max, heights[left])
    right_height_max = max(right_height_max, heights[right])

    if left_height_max < right_height_max:
        answer += left_height_max - heights[left]
        left += 1
    else:
        answer += right_height_max - heights[right]
        right -= 1

print(answer)
