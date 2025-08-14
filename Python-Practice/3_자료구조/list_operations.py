"""
3-9 설명: 두 개의 리스트를 병합하고 공통 요소를 찾는 프로그램을 작성하세요.

"""
list1 = [1, 2, 3, 4, 5]
list2 = [4, 5, 6, 7, 8]

a = list1 + list2

b = list(set(list1) & set(list2))

print(f"병합된 리스트: {a}")
print(f"공통 요소: {b}")