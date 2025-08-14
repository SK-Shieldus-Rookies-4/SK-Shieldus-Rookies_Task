"""
7-2 설명: CSV 형태의 학생 성적 데이터를 파일에 저장하고 읽어서 평균을 계산하는 프로그램을 작성하세요.

"""

import csv

students = [
    ['김철수', 85],
    ['이영희', 92],
    ['박민수', 78],
    ['최수진', 95]
]

file_path = r"C:\Users\user\Desktop\SK-Shieldus-Rookies_Study\task\Python-Practice\7_파일 처리\grades.csv"

# 1. CSV 파일에 저장
with open(file_path, 'w', newline='', encoding='utf-8') as f:
    writer = csv.writer(f)
    writer.writerow(['이름', '점수'])  # 헤더
    writer.writerows(students)

print("학생 성적이 grades.csv에 저장되었습니다.\n")

# 2. CSV 파일 읽기 및 평균 계산
total_score = 0
count = 0

print("성적 분석 결과:")
with open(file_path, 'r', encoding='utf-8') as f:
    reader = csv.reader(f)
    next(reader)  
    for row in reader:
        name = row[0]
        score = int(row[1])
        total_score += score
        count += 1
        print(f"{name}: {score}점")

average = total_score / count if count > 0 else 0
print(f"전체 평균: {average:.1f}점")
