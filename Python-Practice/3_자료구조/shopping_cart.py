"""
3-5 설명: 쇼핑 카트를 딕셔너리로 구현하여 상품을 추가하고 총 가격을 계산하는 프로그램을 작성하세요.
"""
cart = {
    '사과' : {'price' : 1000, 'count' : 2}, 
    '바나나' : {'price' : 800, 'count' : 3},
    '오렌지' : {'price' : 1500, 'count' : 1} 
}

total = 0

for item, info in cart.items():
    item_price = info['price']
    item_count = info['count']
    item_total = item_price * item_count
    total += item_total
    print(f"{item}: {item_count}개 (개당 {item_price}원) = {item_total}원")

print(f"총 가격: {total}원")
