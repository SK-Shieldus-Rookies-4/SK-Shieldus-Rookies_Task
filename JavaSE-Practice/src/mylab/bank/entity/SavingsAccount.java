package mylab.bank.entity;

// 기본 이자율이 있는 저축 계좌
public class SavingsAccount extends Account {
	private double interestRate;

	public SavingsAccount(String accountNumber, String ownerName, double balance, double interestRate) {
		super(accountNumber, ownerName, balance);
		this.interestRate = interestRate;
	}
	
	public double getInterestRate() {
		return interestRate;
	}

	public void applyInterest() {
		double interest = getBalance() * (interestRate / 100);
        deposit(interest);
		System.out.println("이자 " + interest + "원이 적용되었습니다. 현재 잔액: " + getBalance() + "원");
	}
	
	@Override
    public String toString() {
        return "계좌번호: " + getAccountNumber() + ", 소유자: " + getOwnerName() +
               ", 잔액: " + getBalance() + "원, 이자율: " + interestRate + "%";
    }
}
