def solution(answers):
    result = []
    scores = []

    first = [1, 2, 3, 4, 5]
    second = [2, 1, 2, 3, 2, 4, 2, 5]
    third = [3, 3, 1, 1, 2, 2, 4, 4, 5, 5]

    students = [first, second, third]

    for student in students:
        score = 0
        for index, answer in enumerate(answers):
            if answer == student[index % len(student)]:
                score += 1
        scores.append(score)

    best_score = max(scores)

    while best_score in scores:
        student_index = scores.index(best_score)
        result.append(student_index + 1)
        scores[student_index] = -1

    return result
