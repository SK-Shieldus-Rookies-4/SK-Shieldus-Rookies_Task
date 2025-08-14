"""
3-10 설명: 학생 정보를 딕셔너리의 리스트로 저장하고 나이 순으로 정렬하는 프로그램을 작성하세요.

"""

students = {
    '김철수': {'age': 20, 'major': '컴퓨터공학과'},
    '박민수': {'age': 21, 'major': '경영학과'},
    '이영희': {'age': 22, 'major': '영어영문학과'},
    '최수진': {'age': 23, 'major': '수학과'}
}

# 딕셔너리를 리스트로 변환
students_list = []
for name, info in students.items():
    student_dict = {'name': name, 'age': info['age'], 'major': info['major']}
    students_list.append(student_dict)

# 나이 기준 정렬 함수 정의
def get_age(student):
    return student['age']

# 정렬
sorted_students = sorted(students_list, key=get_age)

# 출력
print("나이 순으로 정렬된 학생 목록:")
for s in sorted_students:
    print(f"{s['name']} ({s['age']}세) - {s['major']}")
