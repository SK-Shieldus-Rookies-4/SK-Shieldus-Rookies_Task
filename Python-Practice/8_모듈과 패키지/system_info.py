"""
8-3 설명: os와 sys 모듈을 사용하여 시스템 정보를 출력하고 파일 경로를 다루는 프로그램을 작성하세요.

"""
import os
import sys

# 현재 작업 디렉토리
cwd = os.getcwd()
print(f"현재 작업 디렉토리: {cwd}")

# Python 버전 정보
print(f"Python 버전: {sys.version}")

# 운영체제 정보
print(f"운영체제: {os.name}")

# 환경 변수 PATH 일부 출력
path_env = os.environ.get("PATH", "")
print(f"환경 변수 PATH 일부: {':'.join(path_env.split(os.pathsep)[:3])}")

# 파일 경로 정보
example_path = os.path.join("C:/Users/username/documents", "report.txt")
directory, filename = os.path.split(example_path)
name, ext = os.path.splitext(filename)

print("파일 경로 정보:")
print(f"- 디렉토리: {directory}")
print(f"- 파일명: {name + ext}")
print(f"- 확장자: {ext}")

# 파일 존재 여부
file_exists = os.path.exists(example_path)
print(f"파일 존재 여부: {file_exists}")
