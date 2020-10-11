package gui1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInputs {

	static Scanner scan = new Scanner(System.in);

	// Method to get integers from users.
	public static int getInt() {
		int newInt = 0;
		try {
			newInt = scan.nextInt();
			scan.nextLine();
		} catch (InputMismatchException e) {
			scan.nextLine();
		}
		return newInt;
	}

	// Method to get currensy from users.
	public static double getAmount() {
		double newDouble = 0;
		try {
			newDouble = scan.nextDouble();
		} catch (InputMismatchException e) {
			scan.nextLine();
		}

		return newDouble;
	}

	// Method to get Strings from users.
	public static String getString() {
		String newString = "";
		newString = scan.nextLine();
		return newString;
	}

	// Method to get Withdrawal Amounts from users.
	public static double getWithdrawAmount() {
		System.out.print("Please Enter Withdraw Amount: $");
		double withdrawAmount = getAmount();
		return withdrawAmount;
	}

	// Method to get Deposit Amounts from users.
	public static double getDepositAmount() {
		System.out.print("Please Enter Deposit Amount: $");
		double depositAmount = getAmount();
		return depositAmount;
	}

	// Method to get Transfer Amounts from users.
	public static double getTransferAmount() {
		System.out.print("Please Enter Transfer Amount: $");
		double transferAmount = getAmount();
		return transferAmount;
	}

	// Method to get first name from users.
	public static String getFirstName() {
		String f_name = "";
		while (f_name.isBlank()) {
			System.out.print("Enter First Name: ");
			f_name = getString();
		}
		return f_name;
	}

	// Method to get last name from users.
	public static String getLastName() {
		String l_name = "";
		while (l_name.isBlank()) {
			System.out.print("Enter Last Name: ");
			l_name = getString();
		}
		return l_name;
	}

	// Method to get username from users.
	public static String getUserName() {
		String userName = "";
		while (userName.isBlank()) {
			System.out.print("Enter Username: ");
			userName = getString();
		}
		return userName;
	}

	// Method to get password from users.
	public static String getPassword() {
		String passWord = "";
		while (passWord.isBlank()) {
			System.out.print("Enter Password: ");
			passWord = getString();
		}
		return passWord;
	}

	// Method to get security question from users.
	public static String getSecurityQuestion() {
		String securityQuestion = "";
		while (securityQuestion.isBlank()) {
			System.out.print("Enter Security Question: ");
			securityQuestion = getString();
		}
		return securityQuestion;
	}

	// Method to get the answer to the security question from users.
	public static String getSecurityAnswer(String question) {
		String securityAnswer = "";
		while (securityAnswer.isBlank()) {
			System.out.println("Security Question: " + question);
			System.out.print("Enter Security Answer: ");
			securityAnswer = getString();
		}
		return securityAnswer;
	}

	public static String getAccountString(int acc) {
		String accountString = "";
		if (acc == 1) {
			accountString = "Checkings";
		} else if (acc == 2) {
			accountString = "Savings";
		}
		return accountString;
	}

	public static String[] getTransferAccountString(int accTransfer) {
		String[] accounts = null;
		if (accTransfer == 1) {
			accounts = new String[] { "Checkings", "Savings" };
		} else if (accTransfer == 2) {
			accounts = new String[] { "Savings", "Checkings" };
		}
		return accounts;
	}

	// Method to close scanner
	public static void closeScanner() {
		scan.close();
	}
}