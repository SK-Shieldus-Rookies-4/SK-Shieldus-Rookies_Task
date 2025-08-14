"""
8-2 설명: datetime과 random 모듈을 사용하여 임의의 날짜와 숫자를 생성하는 프로그램을 작성하세요.

"""

import datetime
import random

# 현재 날짜와 시간
now = datetime.datetime.now()
print(f"현재 날짜와 시간: {now.strftime('%Y-%m-%d %H:%M:%S')}")

# 포맷된 날짜 (요일 포함)
formatted_date = now.strftime("%Y년 %m월 %d일 %A")
print(f"포맷된 날짜: {formatted_date}")

# 임의의 정수 (1~10)
rand_int = random.randint(1, 10)
print(f"임의의 숫자: {rand_int}")

# 임의의 실수 (0.0~10.0 중 소수 두자리로 출력)
rand_float = round(random.uniform(0.0, 10.0), 2)
print(f"임의의 실수: {rand_float}")

# 임의의 리스트 요소
fruits = ['사과', '바나나', '오렌지', '딸기', '포도']
rand_choice = random.choice(fruits)
print(f"임의의 리스트 요소: {rand_choice}")

# 리스트 섞기
random.shuffle(fruits)
print(f"섞인 리스트: {fruits}")
