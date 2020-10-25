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
			System.out.print("\nAction: ");
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
			System.out.print("\nAction: ");
			accountTransfer = UserInputs.getInt();
		}
		return accountTransfer;
	}

	public int adminMenu(admin admin) {
		int menuPick = 0;
		while (menuPick < 1 || menuPick > 4) {
			System.out.println("********ADMINSISTRATION********\n");
			System.out.println("Welcome Back BASEGOD!!!\n");
			System.out.println("1. Search");
			System.out.println("2. Delete");
			System.out.println("3. View");
			System.out.println("4. Log Out");
			System.out.print("\nAction: ");
			menuPick = UserInputs.getInt();
		}
		return menuPick;
	}

	public int adminSearchMenu() {
		int searchPick = 0;
		while (searchPick < 1 || searchPick > 3) {
			System.out.println("****Search Option****");
			System.out.println("1. Search By Username");
			System.out.println("2. Search By Name");
			System.out.println("3. Back");
			System.out.print("\nAction: ");
			searchPick = UserInputs.getInt();
		}
		return searchPick;
	}

	public int adminDeleteMenu() {
		int deletePick = 0;
		while (deletePick < 1 || deletePick > 4) {
			System.out.println("****Delete Option****");
			System.out.println("1. Delete By Username");
			System.out.println("2. Delete By Name");
			System.out.println("3. Delete All User");
			System.out.println("4. Back");
			System.out.print("\nAction: ");
			deletePick = UserInputs.getInt();
		}
		return deletePick;
	}

	public int adminViewMenu() {
		int menuPick = 0;
		while (menuPick < 1 || menuPick > 3) {
			System.out.println("****View Option****");
			System.out.println("1. View Searched Profile");
			System.out.println("2. View All");
			System.out.println("3. Back");
			System.out.print("\nAction: ");
			menuPick = UserInputs.getInt();
		}
		return menuPick;
	}
}