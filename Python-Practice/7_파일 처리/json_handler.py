"""
7-4 설명: JSON 형태의 데이터를 파일에 저장하고 읽어오는 프로그램을 작성하세요.

"""
import json

data = {
    "이름": "김철수",
    "나이": 25,
    "직업": "개발자",
    "취미": ["독서", "영화감상", "코딩"],
    "주소": "서울시 강남구"
}

# 저장할 파일 경로 
file_path = r"C:\Users\user\Desktop\SK-Shieldus-Rookies_Study\task\Python-Practice\7_파일 처리\data.json"

# JSON 파일 저장
with open(file_path, "w", encoding="utf-8") as f:
    json.dump(data, f, ensure_ascii=False, indent=4)

print("데이터가 data.json에 저장되었습니다.\n")

# JSON 파일 읽기
with open(file_path, "r", encoding="utf-8") as f:
    loaded_data = json.load(f)

print("JSON에서 읽어온 데이터:")
print(f"이름: {loaded_data['이름']}")
print(f"나이: {loaded_data['나이']}")
print(f"직업: {loaded_data['직업']}")
print(f"취미: {loaded_data['취미']}")
print(f"주소: {loaded_data['주소']}")

