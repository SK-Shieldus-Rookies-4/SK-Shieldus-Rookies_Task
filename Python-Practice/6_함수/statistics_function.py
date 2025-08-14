"""
6-4 설명: 리스트의 통계 정보(평균, 최댓값, 최솟값, 표준편차)를 반환하는 함수를 작성하세요.

파이썬 내장 모듈 중 하나인 statistics 모듈을 사용해서도 가능, 
이 모듈은 통계 계산에 자주 쓰이는 함수들을 제공
"""

def calculate_statistics(numbers):
    # 평균
    mean = sum(numbers) / len(numbers)
    
    # 최댓값과 최솟값
    max_val = max(numbers)
    min_val = min(numbers)
    
    # 표준편차
    variance = sum((x - mean) ** 2 for x in numbers) / (len(numbers) - 1)
    std_dev = variance ** 0.5

    return mean, max_val, min_val, std_dev

data = [10, 20, 30, 40, 50]
mean, max_val, min_val, std_dev = calculate_statistics(data)

print(f"숫자들: {data}")
print(f"평균: {mean:.1f}")
print(f"최댓값: {max_val}")
print(f"최솟값: {min_val}")
print(f"표준편차: {std_dev:.2f}")
