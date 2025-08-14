"""
3-6 설명: 리스트에서 최댓값, 최솟값, 두 번째로 큰 값을 찾는 프로그램을 작성하세요.
"""

numbers = [15,3,27,8,19,12,31]

max_num = numbers[0]
min_num = numbers[0]

for num in numbers :
    if num > max_num:
        max_num = num
    if num < min_num:
        min_num = num
        
# 두 번째로 큰 값 
second_max = min_num  
for num in numbers:
    if num != max_num and num > second_max:
        second_max = num

print(f"최댓값: {max_num}")
print(f"최솟값: {min_num}")
print(f"두 번째로 큰 값: {second_max}")        
