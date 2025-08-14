"""
4-5 설명: 사용자가 입력한 숫자가 소수인지 판별하는 프로그램을 작성하세요.
"""

# 소수 판별 함수
def is_prime(number):
    if number < 2:
        return False
    for i in range(2, int(number ** 0.5) + 1):
        if number % i == 0:
            return False
    return True

number = int(input("숫자를 입력하세요: "))

if is_prime(number):
    print(f"{number}은 소수입니다.")
else:
    print(f"{number}은 소수가 아닙니다.")