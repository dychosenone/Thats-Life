package Model;

import Model.Player.Player;

public class Bank {
	private static final int LOAN_AMOUNT = 20000;
	private static final int INTEREST = 5000;
	
	
	public Bank () {
	}
	
	public void takeLoan (Player p) {
		p.addBalance(LOAN_AMOUNT);
		p.getLoan(LOAN_AMOUNT + INTEREST);
	}
	
	public void payLoan (Player p) {
		p.subtractBalance(LOAN_AMOUNT + INTEREST);
		p.payLoan(LOAN_AMOUNT + INTEREST);
	}
	
}
