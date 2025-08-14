"""
5-2 설명: 리스트에서 짝수만 추출하여 제곱하는 코드를 리스트 컴프리헨션으로 작성하세요.

"""

numbers = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

even_numbers = [n for n in numbers if n % 2 == 0]

squared_evens = [n**2 for n in even_numbers]

print("짝수들:", even_numbers)
print("짝수의 제곱:", squared_evens)
