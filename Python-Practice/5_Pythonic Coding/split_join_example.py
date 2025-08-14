"""
5-1 설명: 문자열을 단어별로 분리했다가 다시 합치는 코드를 split과 join을 사용하여 작성하세요.
"""

origin = "Python is awesome programming language"

words = origin.split()


print("원본 문자열:", origin)
print("분리된 단어들:", words)

joined = "-".join(words)
print("하이픈으로 연결:", joined)


upper_joined = " ".join([word.upper() for word in words])
print("대문자로 변환 후 공백으로 연결:", upper_joined)
