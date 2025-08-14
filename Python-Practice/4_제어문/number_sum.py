"""
4-4 설명: 숫자를 계속 입력받아서 0을 입력할 때까지 합계를 구하는 프로그램을 작성하세요.
"""

total = 0;

while True:
    number = int(input("숫자를 입력하세요 (0을 입력하면 종료): "))
    if number == 0 :
        break
    total += number
print(f'입력된 숫자들의 합 : {total}')

