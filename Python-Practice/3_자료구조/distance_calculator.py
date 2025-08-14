"""
3-4 설명: 좌표를 튜플로 저장하고 두 점 사이의 거리를 계산하는 프로그램을 작성하세요.
"""

import math

point1, point2 = (0, 0), (3, 4)

x1, y1 = point1
x2, y2 = point2

distance = math.sqrt((x2 - x1)**2 + (y2 - y1)**2)

print(f"두 점 사이의 거리: {distance}")