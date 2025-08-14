"""
5-7 설명: map과 filter를 사용하여 리스트를 처리하는 프로그램을 작성하세요.

"""

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

squared = list(map(lambda x: x**2, numbers))

greater_than_5 = list(filter(lambda x: x > 5, numbers))

squared_gt_5 = list(map(lambda x: x**2, filter(lambda x: x > 5, numbers)))

print("원본 숫자:", numbers)
print("모든 수의 제곱:", squared)
print("5보다 큰 수들:", greater_than_5)
print("5보다 큰 수들의 제곱:", squared_gt_5)
