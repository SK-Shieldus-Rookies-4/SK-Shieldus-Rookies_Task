"""
1-5 설명: 상품의 가격과 할인율을 입력받아 할인된 가격을 계산하는 프로그램을 작성하세요.
"""

price = int(input("상품 가격을 입력하세요: "))
rate = int(input("할인율을 입력하세요(%): "))

discount = price * rate / 100
final_price = price - discount

print(f"원래 가격: {price}원")
print(f"할인율: {rate}%")
print(f"할인 금액: {int(discount)}원")
print(f"최종 가격: {int(final_price)}원")
