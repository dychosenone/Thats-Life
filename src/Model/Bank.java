package Model;

import Model.Player.Player;

public class Bank {
	private static final int LOAN_AMOUNT = 20000;
	private static final int INTEREST = 5000;
	
	
	public Bank () {
	}

	/**
	 * Takes a loan from the bank.
	 * @param p Indicates which player is taking a loan.
	 */
	public void takeLoan (Player p) {
		p.addBalance(LOAN_AMOUNT);
		p.getLoan(LOAN_AMOUNT + INTEREST);
	}

	/**
	 * Pays back the loan from the bank.
	 * @param p Indicates which player is paying back a loan.
	 */
	public void payLoan (Player p) {
		p.subtractBalance(LOAN_AMOUNT + INTEREST);
		p.payLoan(LOAN_AMOUNT + INTEREST);
	}
	
}
