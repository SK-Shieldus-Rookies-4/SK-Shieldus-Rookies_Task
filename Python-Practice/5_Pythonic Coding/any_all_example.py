"""
5-6 설명: any와 all 함수를 사용하여 조건을 검사하는 프로그램을 작성하세요.

all(조건식 for 항목 in 리스트)
→ 모든 항목이 조건을 만족하면 True, 하나라도 아니면 False

any(조건식 for 항목 in 리스트)
→ 하나라도 조건을 만족하면 True, 모두 아니면 False

"""

numbers1 = [2, 4, 6, 8, 10]
numbers2 = [1, 3, 5, 7, 12]

def check_conditions(numbers):
    all_even = all(num % 2 == 0 for num in numbers)
    any_over_10 = any(num > 10 for num in numbers)
    
    print(f"숫자 리스트: {numbers}")
    print(f"모든 수가 짝수인가? {all_even}")
    print(f"하나라도 10보다 큰 수가 있는가? {any_over_10}")
    print()  

check_conditions(numbers1)

check_conditions(numbers2)
