package gui1;

import java.util.Scanner;

import Program.method;
import model.admin;
import model.user;
import respiratory.Driver;

public class menu {

	static Scanner scan;
	static Driver connection;
	static method bankmethod = new method();
	static admin ad;
	// static AdminMenu AdMenu;

	public menu() {
		scan = new Scanner(System.in);
		connection = new Driver();
		ad = new admin();
		// AdMenu = new AdminMenu();
	}

	public int mainMenu() {
		int choice = 0;
		while (choice < 1 || choice > 3) {
			System.out.println("*** Weenie Hut Banking ***\n");
			System.out.println("1. Register");
			System.out.println("2. Login");
			System.out.println("3. Exit");
			System.out.print("\nAction: ");
			choice = UserInputs.getInt();
			System.out.println("");
		}
		return choice;
	}

	public int optionMenu() {
		int option = 0;
		while (option < 1 || option > 2) {
			System.out.println("\nOption");
			System.out.println("1. Save & Continue");
			System.out.println("2. Save & Back");
			System.out.println("3. Cancel");
			System.out.print("\nAction: ");
			option = UserInputs.getInt();
			System.out.println("");
		}
		return option;
	}

	public int userMenu(user user) {
		int i = 0;
		while (i < 1 || i > 5) {
			System.out.println("**** User Menu ****\n");
			System.out.println("Hello " + user.getUsername() + " || Checking Amount: $" + user.getCheck()
					+ " || Saving Amount: $" + user.getSave());
			System.out.println("\n1. Withdraw");
			System.out.println("2. Deposit");
			System.out.println("3. Transfer");
			System.out.println("4. View Profile");
			System.out.println("5. Log Out\n");
			System.out.print("Action: ");
			i = UserInputs.getInt();
		}
		return i;
	}

	public int pickAccount() {
		int account = 0;
		while (account < 1 || account > 2) {
			System.out.println("**** Select Account ****");
			System.out.println("1. Checkings Account");
			System.out.println("2. Savings Account");
			System.out.print("Action: ");
			account = UserInputs.getInt();
			System.out.println("");
		}
		return account;
	}

	public int pickAccountTransfer() {
		int accountTransfer = 0;
		while (accountTransfer < 1 || accountTransfer > 2) {
			System.out.println("**** Select Account(Transfer) ****");
			System.out.println("1. Checkings -> Savings");
			System.out.println("2. Savings -> Checkings");
			System.out.print("Action: ");
			accountTransfer = UserInputs.getInt();
		}
		return accountTransfer;
	}
}