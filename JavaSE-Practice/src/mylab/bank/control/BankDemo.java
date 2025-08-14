package mylab.bank.control;

import mylab.bank.entity.*;
import mylab.bank.exception.*;

public class BankDemo {
    public static void main(String[] args) {
        Bank bank = new Bank();

        System.out.println("=== 계좌 생성 ===");
        bank.createSavingsAccount("AC1000", "홍길동", 10000, 3.0);
        bank.createCheckingAccount("AC1001", "김철수", 20000, 5000);
        bank.createSavingsAccount("AC1002", "이영희", 30000, 2.0);

        System.out.println();

        bank.printAllAccounts();

        System.out.println("\n=== 입금/출금 테스트 ===");
        try {
            bank.deposit("AC1000", 5000);
            bank.withdraw("AC1001", 3000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 이자 적용 테스트 ===");
        try {
            SavingsAccount sa = (SavingsAccount) bank.findAccount("AC1000");
            sa.applyInterest();
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        System.out.println("\n=== 계좌 이체 테스트 ===");
        try {
            bank.transfer("AC1002", "AC1001", 5000);
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
            System.out.println();
        }

        bank.printAllAccounts();

        System.out.println("\n=== 예외 테스트 ===");
        try {
            bank.withdraw("AC1001", 6000); // 출금 한도 초과
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.transfer("AC1001", "AC1002", 6000); // 출금 한도 초과
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }

        try {
            bank.deposit("AC9999", 1000); // 없는 계좌
        } catch (Exception e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}