"""
6-5 설명: 람다 함수를 사용하여 리스트를 다양한 방식으로 정렬하는 프로그램을 작성하세요.

"""

students = [('김철수', 85), ('이영희', 92), ('박민수', 78), ('최수진', 95)]

name_sorted = sorted(students, key=lambda x: x[0])

score_sorted = sorted(students, key=lambda x: x[1])

score_desc_sorted = sorted(students, key=lambda x: x[1], reverse=True)

# 출력
print(f"학생 정보: {students}")
print(f"이름순 정렬: {name_sorted}")
print(f"점수순 정렬: {score_sorted}")
print(f"점수 내림차순: {score_desc_sorted}")
