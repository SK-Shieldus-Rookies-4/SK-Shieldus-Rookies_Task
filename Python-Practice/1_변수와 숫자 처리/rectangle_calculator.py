"""
1-4 설명: 직사각형의 가로와 세로 길이를 입력받아 넓이와 둘레를 계산하는 프로그램을 작성하세요.
"""

a = int(input("가로 길이를 입력하세요: "))
b = int(input("세로 길이를 입력하세요: "))

area = a * b            
perimeter = 2 * (a + b) 

print(f"직사각형의 넓이: {area}")
print(f"직사각형의 둘레: {perimeter}")