"""
7-1 설명: 텍스트 파일에 여러 줄의 문자열을 저장하고 읽어오는 프로그램을 작성하세요.

"""

lines = [
    "안녕하세요",
    "파이썬 파일 처리를 연습하고 있습니다",
    "오늘은 좋은 날씨입니다"
]

file_path = r"C:\Users\user\Desktop\SK-Shieldus-Rookies_Study\task\Python-Practice\7_파일 처리\7-1.txt"

with open(file_path, "w", encoding="utf-8") as f:
    for line in lines:
        f.write(line + "\n")

print("파일에 저장할 내용:")
for line in lines:
    print(line)

print("\n파일에서 읽어온 내용:")
with open(file_path, "r", encoding="utf-8") as f:
    content = f.read()
    print(content.strip())
