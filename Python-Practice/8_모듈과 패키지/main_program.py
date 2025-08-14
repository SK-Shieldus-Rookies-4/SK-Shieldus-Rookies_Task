"""
8-1 설명: 수학 연산을 위한 모듈을 만들고 이를 사용하는 메인 프로그램을 작성하세요.

"""
import math_operations

print(f"원의 넓이: {math_operations.circle_area(5):.2f}")
print(f"직사각형 넓이: {math_operations.rectangle_area(5, 10)}")
print(f"팩토리얼 5! = {math_operations.factorial(5)}")
print(f"최대공약수(48, 18) = {math_operations.gcd(48, 18)}")
