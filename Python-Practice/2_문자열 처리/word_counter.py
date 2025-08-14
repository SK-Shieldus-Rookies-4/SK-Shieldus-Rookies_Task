"""
2-4 설명: 문장을 입력받아 공백을 제거하고, 단어의 개수를 세는 프로그램을 작성하세요.
"""

s = input("문장을 입력하세요: ")
print(f"공백 제거 : {s.strip()}")
words = s.split()
print(f"단어 개수: {len(words)}개")
