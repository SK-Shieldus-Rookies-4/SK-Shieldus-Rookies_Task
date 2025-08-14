package mylab.bank.entity;

import mylab.bank.exception.InsufficientBalanceException;

//인출 한도가 있는 체킹 계좌
public class CheckingAccount extends Account {
	private double withdrawalLimit;

	public CheckingAccount(String accountNumber, String ownerName, double balance, double withdrawalLimit) {
		super(accountNumber, ownerName, balance);
		this.withdrawalLimit = withdrawalLimit;
	}
	
    public double getWithdrawalLimit() {
		return withdrawalLimit;
	}

	@Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount > withdrawalLimit) {
            throw new InsufficientBalanceException("출금 한도를 초과했습니다. 한도: " + withdrawalLimit + "원");
        }
        super.withdraw(amount);
    }

	@Override
	public String toString() {
		return super.toString() + ", 출금 한도: " + withdrawalLimit + "원";
	}

}
