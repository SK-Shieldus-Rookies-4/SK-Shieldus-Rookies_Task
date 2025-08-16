package mylab.book.control;

import java.text.DecimalFormat;
import java.util.*;

import mylab.book.entity.*;

public class StatisticsAnalyzer {

    public Map<String, Double> calculateAveragePriceByType(Publication[] publications) {
        Map<String, Integer> total = new HashMap<>();
        Map<String, Integer> count = new HashMap<>();

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            total.put(type, total.getOrDefault(type, 0) + pub.getPrice());
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> avg = new HashMap<>();
        for (String key : total.keySet()) {
            avg.put(key, total.get(key) / (double)count.get(key));
        }
        return avg;
    }

    public Map<String, Double> calculatePublicationDistribution(Publication[] publications) {
        Map<String, Integer> count = new HashMap<>();
        int totalCount = publications.length;

        for (Publication pub : publications) {
            String type = getPublicationType(pub);
            count.put(type, count.getOrDefault(type, 0) + 1);
        }

        Map<String, Double> distribution = new HashMap<>();
        for (String key : count.keySet()) {
            distribution.put(key, (count.get(key) * 100.0) / totalCount);
        }
        return distribution;
    }

    public double calculatePublicationRatioByYear(Publication[] publications, String year) {
        int match = 0;
        for (Publication pub : publications) {
            if (pub.getPublishDate().startsWith(year)) match++;
        }
        return publications.length == 0 ? 0 : (match * 100.0 / publications.length);
    }
    
    private String getPublicationType(Publication pub) {
        if (pub instanceof Novel) return "소설";
        if (pub instanceof Magazine) return "잡지";
        if (pub instanceof ReferenceBook) return "참고서";
        return "기타";
    }

    public void printStatistics(Publication[] publications) {
        DecimalFormat df = new DecimalFormat("#,###.##");
        System.out.println("===== 출판물 통계 분석 =====");

        System.out.println("1. 타입별 평균 가격:");
        Map<String, Double> avg = calculateAveragePriceByType(publications);
        for (String key : avg.keySet()) {
            System.out.println("   - " + key + ": " + df.format(avg.get(key)) + "원");
        }

        System.out.println("\n2. 출판물 유형 분포:");
        Map<String, Double> dist = calculatePublicationDistribution(publications);
        for (String key : dist.keySet()) {
            System.out.println("   - " + key + ": " + df.format(dist.get(key)) + "%");
        }

        System.out.println("\n3. 2007년에 출판된 출판물 비율: " +
                           df.format(calculatePublicationRatioByYear(publications, "2007")) + "%");
    }
}