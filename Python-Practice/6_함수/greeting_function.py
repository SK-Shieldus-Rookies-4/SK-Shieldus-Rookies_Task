"""
6-3 설명: 기본값 매개변수를 사용하여 인사말을 생성하는 함수를 작성하세요.

"""

def greeting(name, message="안녕하세요", suffix="님!"):
    print(f"{message}, {name}{suffix}")

# 호출 예시
greeting("김철수")                          
greeting("John", message="Hello", suffix="!")  
greeting("이영희", suffix="님! 좋은 하루 되세요!")  
