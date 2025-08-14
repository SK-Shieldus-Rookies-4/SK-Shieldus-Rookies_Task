"""
6-2 설명: 팩토리얼을 계산하는 재귀함수와 반복문을 사용한 함수를 각각 작성하세요.

"""
# 재귀함수
def factorial_recursive(n):
    if n == 1:
        return 1
    return n * factorial_recursive(n - 1)

# 반복문 
def factorial_iterative(n):
    result = 1
    for i in range(2, n + 1):
        result *= i
    return result

# 테스트
print(f"5! (재귀) = {factorial_recursive(5)}")
print(f"5! (반복) = {factorial_iterative(5)}")
print(f"7! (재귀) = {factorial_recursive(7)}")
print(f"7! (반복) = {factorial_iterative(7)}")
