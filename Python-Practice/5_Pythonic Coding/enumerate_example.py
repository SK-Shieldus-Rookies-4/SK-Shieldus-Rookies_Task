"""
5-3 설명: enumerate를 사용하여 리스트의 인덱스와 값을 함께 출력하는 프로그램을 작성하세요.

"""

fruits = ['사과', '바나나', '오렌지', '포도', '딸기']

print("\n과일 목록:")
for index, fruit in enumerate(fruits):
    print(f"{index}: {fruit}")
