"""
3-3 설명: 학생들의 성적을 딕셔너리로 저장하고 평균 점수를 계산하는 프로그램을 작성하세요.
"""
scores = {"김철수":85, "이영희":92, "박민수":78, "최수진":95}

total = sum(scores.values())      
avg = total / len(scores)

print(f"평균 점수: {avg:.1f}점")