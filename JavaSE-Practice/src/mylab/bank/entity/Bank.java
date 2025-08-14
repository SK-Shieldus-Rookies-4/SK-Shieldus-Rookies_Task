package mylab.bank.entity;

import java.util.ArrayList;
import java.util.List;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class Bank {
    private List<Account> accounts;

    public Bank() {
        this.accounts = new ArrayList<>();
    }

    public Account createSavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
        SavingsAccount account = new SavingsAccount(accountNumber, ownerName, balance, interestRate);
        accounts.add(account);
        System.out.println("저축 계좌가 생성되었습니다: " + account);
        return account;
    }

    public Account createCheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
        CheckingAccount account = new CheckingAccount(accountNumber, ownerName, balance, withdrawalLimit);
        accounts.add(account);
        System.out.println("체킹 계좌가 생성되었습니다: " + account);
        return account;
    }

    public Account findAccount(String accountNumber) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountNumber().equals(accountNumber)) {
                return acc;
            }
        }
        throw new AccountNotFoundException("계좌번호 " + accountNumber + "에 해당하는 계좌를 찾을 수 없습니다.");
    }

    public void deposit(String accountNumber, double amount) throws AccountNotFoundException {
        Account acc = findAccount(accountNumber);
        acc.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account acc = findAccount(accountNumber);
        if (acc instanceof CheckingAccount) {
            CheckingAccount ca = (CheckingAccount) acc;
            if (amount > ca.getWithdrawalLimit()) {
                throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + ca.getWithdrawalLimit() + "원");
            }
        }
        acc.withdraw(amount);
    }

    public void transfer(String fromAccountNumber, String toAccountNumber, double amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account fromAcc = findAccount(fromAccountNumber);
        Account toAcc = findAccount(toAccountNumber);

        if (fromAcc instanceof CheckingAccount) {
            CheckingAccount ca = (CheckingAccount) fromAcc;
            if (amount > ca.getWithdrawalLimit()) {
                throw new WithdrawalLimitExceededException("출금 한도를 초과했습니다. 한도: " + ca.getWithdrawalLimit() + "원");
            }
        }

        fromAcc.withdraw(amount);
        toAcc.deposit(amount);

        System.out.println(amount + "원이 " + fromAccountNumber + "에서 " + toAccountNumber + "로 송금되었습니다.");
    }

    public void printAllAccounts() {
        System.out.println("=== 모든 계좌 목록 ===");
        for (Account acc : accounts) {
            System.out.println(acc);
        }
        System.out.println("===================");
    }
}