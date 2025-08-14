"""
7-3 설명: 텍스트 파일의 단어 빈도를 계산하는 프로그램을 작성하세요.

"""
from collections import Counter
import re

# 텍스트 파일 경로
file_path = r"C:\Users\user\Desktop\SK-Shieldus-Rookies_Study\task\Python-Practice\7_파일 처리\7-3.txt"

# 1. 파일에서 텍스트 읽기
with open(file_path, "r", encoding="utf-8") as f:
    text = f.read()

# 2. 단어만 추출 (한글, 영어 단어)
words = re.findall(r'\b[가-힣a-zA-Z]+\b', text)

# 3. 단어 빈도 계산
word_counts = Counter(words)


print("단어 빈도 분석 결과:")
for word, count in word_counts.most_common():
    print(f"{word}: {count}번")
