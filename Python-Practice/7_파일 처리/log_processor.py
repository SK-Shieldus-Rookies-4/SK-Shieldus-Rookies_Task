"""
7-5 설명: 로그 파일을 생성하고 특정 레벨의 로그만 필터링하여 출력하는 프로그램을 작성하세요.

"""
from datetime import datetime

logs = [
    "2025-07-20 08:00:00 - INFO - 시스템 시작",
    "2025-07-20 09:15:00 - WARNING - 메모리 사용량이 높습니다",
    "2025-07-20 10:30:00 - ERROR - 데이터베이스 연결 실패",
    "2025-07-20 11:00:00 - INFO - 사용자 로그인 성공",
    "2025-07-20 11:45:00 - ERROR - 파일을 찾을 수 없음",
    "2025-07-20 12:00:00 - WARNING - 디스크 공간 부족"
]


log_file_path = r"C:\Users\user\Desktop\SK-Shieldus-Rookies_Study\task\Python-Practice\7_파일 처리\processor.log"

with open(log_file_path, "w", encoding="utf-8") as f:
    for log in logs:
        f.write(log + "\n")

print("로그 파일이 생성되었습니다.\n")

def print_logs_by_level(file_path, level):
    print(f"{level} 레벨 로그들:")
    with open(file_path, "r", encoding="utf-8") as f:
        for line in f:
            if f" - {level} - " in line:
                print(line.strip())
    print()

print_logs_by_level(log_file_path, "ERROR")
print_logs_by_level(log_file_path, "WARNING")
