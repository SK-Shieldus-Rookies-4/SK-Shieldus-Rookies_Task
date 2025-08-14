"""
8-1 설명: 수학 연산을 위한 모듈을 만들고 이를 사용하는 메인 프로그램을 작성하세요.

"""
import math

def circle_area(radius):
    return math.pi * radius ** 2

def rectangle_area(width, height):
    return width * height

def factorial(n):
    if n == 0 or n == 1:
        return 1
    return n * factorial(n - 1)

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a
