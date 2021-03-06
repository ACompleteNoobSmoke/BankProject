package Program;

import gui1.UserInputs;

public class method {

	// Method to perform withdraw actions.
	public static double performWithdraw(double oldAmount) {
		double withdrawAmount = UserInputs.getWithdrawAmount();
		if (withdrawAmount > oldAmount) {
			System.err.println("WARNING: Overdraft!!");
		} else if (withdrawAmount < 0) {
			System.err.println("WARNING: Incorrect Input!!");
		} else if (withdrawAmount == 0) {
			System.err.println("No Amount Was Withdrawn From Account");
		} else {
			double newAmount = oldAmount - withdrawAmount;
			System.out.println("UPDATE: $" + withdrawAmount + " Has Been Withdrawn");
			return newAmount;
		}
		return oldAmount;
	}

	// Method to perform deposit actions.
	public static double performDeposit(double oldAmountDep) {
		double depositAmount = UserInputs.getDepositAmount();
		if (depositAmount < 0) {
			System.err.println("WARNING: Incorrect Input");
		} else if (depositAmount == 0) {
			System.out.println("No Amount Was Deposited Into Account");
		} else {
			double newAmountDep = oldAmountDep + depositAmount;
			System.out.println("UPDATE: $" + newAmountDep + " Has Been Deposited");
			return newAmountDep;
		}
		return oldAmountDep;
	}

	// Method to perform transfers between saving and checking accounts.
	public static double[] performTransfer(double oldAccount, double newAccount) {
		double transferAccounts[] = new double[2];
		transferAccounts[0] = oldAccount;
		transferAccounts[1] = newAccount;
		if (isEmpty(oldAccount)) {
			return transferAccounts;
		}
		double transferAmount = UserInputs.getTransferAmount();
		if (transferAmount > oldAccount) {
			System.err.println("WARNING: This Amount Can Cause An Overdraft!");
		} else if (transferAmount < 0) {
			System.err.println("WARNING: Incorrect Input");
		} else if (transferAmount == 0) {
			System.err.println("WARNING: No Amount Was Transferred!");
		} else {
			oldAccount = oldAccount - transferAmount;
			newAccount = newAccount + transferAmount;
			transferAccounts[0] = oldAccount;
			transferAccounts[1] = newAccount;
			System.out.println("UPDATE: The Amount Of $" + transferAmount + " Has Been Transferred");
		}

		return transferAccounts;
	}

	// Method to check if the account is empty.
	public static boolean isEmpty(double amount) {
		if (amount == 0 || amount <= 0) {
			System.err.println("WARNING: Currently No Money In Account To Perform Action!!");
			return true;
		}

		return false;
	}

}
