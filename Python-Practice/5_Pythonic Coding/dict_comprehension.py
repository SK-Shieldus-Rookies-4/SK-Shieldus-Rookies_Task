"""
5-5 설명: 딕셔너리 컴프리헨션을 사용하여 숫자와 그 제곱값의 딕셔너리를 만드는 프로그램을 작성하세요.

"""

squares = {x: x**2 for x in range(1, 6)}
print("1부터 5까지의 제곱 딕셔너리:", squares)

even_squares = {x: x**2 for x in range(1, 11) if x % 2 == 0}
print("짝수만의 제곱 딕셔너리:", even_squares)
