"""
4-2 설명: 1부터 100까지의 숫자 중에서 3의 배수의 합을 구하는 프로그램을 작성하세요.
"""
a = [i for i in range(1,101) if i % 3 == 0]

sum_a = sum(a)
count_a = len(a)

print(f"1부터 100까지 3의 배수: {a}")
print(f"3의 배수의 합: {sum_a}")
print(f"3의 배수의 개수: {count_a}개")