package mylab.publication.control;

import mylab.publication.entity.*;
import java.util.*;

public class ShoppingCart {
    private List<Publication> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(Publication item) {
        items.add(item);
        System.out.println(item.getTitle() + "이(가) 장바구니에 추가되었습니다.");
    }

    public boolean removeItem(String title) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getTitle().equals(title)) {
                Publication removed = items.remove(i);
                System.out.println(removed.getTitle() + "이(가) 장바구니에서 제거되었습니다.");
                return true;
            }
        }
        System.out.println("해당 제목의 출판물을 찾을 수 없습니다.");
        return false;
    }

    public void displayCart() {
        int total = 0;
        int discounted = 0;
        System.out.println("====== 장바구니 내용 ======");
        for (Publication item : items) {
            System.out.println(item);
            total += item.getPrice();
        }
        discounted = calculateDiscountedPrice();
        System.out.println("총 가격: " + total + "원, 할인 적용 가격: " + discounted + "원");
    }

    public int calculateDiscountedPrice() {
        int total = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) total += item.getPrice() * 0.9;
            else if (item instanceof Novel) total += item.getPrice() * 0.85;
            else if (item instanceof ReferenceBook) total += item.getPrice() * 0.8;
            else total += item.getPrice();
        }
        return total;
    }

    public void printStatistics() {
        int magazineCount = 0, novelCount = 0, referenceBookCount = 0;
        for (Publication item : items) {
            if (item instanceof Magazine) magazineCount++;
            else if (item instanceof Novel) novelCount++;
            else if (item instanceof ReferenceBook) referenceBookCount++;
        }
        System.out.println("====== 장바구니 통계 ======");
        System.out.println("잡지: " + magazineCount + "권");
        System.out.println("소설: " + novelCount + "권");
        System.out.println("참고서: " + referenceBookCount + "권");
        System.out.println("총 출판물: " + items.size() + "권");
    }
}