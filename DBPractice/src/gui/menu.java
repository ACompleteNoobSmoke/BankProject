package gui;

import java.sql.SQLException;
import java.util.InputMismatchException;
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
	static AdminMenu AdMenu;

	public menu() {
		scan = new Scanner(System.in);
		connection = new Driver();
		ad = new admin();
		AdMenu = new AdminMenu();
	}

	public int mainmenu() {
		int choice = 0;
		while (choice < 1 || choice > 3) {
			try {
				System.out.println("*** Weenie Hut Banking ***\n");
				System.out.println("1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				System.out.print("\nAction: ");
				choice = scan.nextInt();
				System.out.println("");
				scan.nextLine();
			} catch (InputMismatchException e) {
				scan.nextLine();
			}
		}
		return choice;
	}

	public void path(int choice) throws SQLException {
		switch (choice) {

			case 1:
				createProfile(register());
				break;

			case 2:
				login();
				break;

			case 3:
				System.out.println("Thank You\nHave A Good Day....");
				scan.close();
				AdminMenu.scan.close();
				System.exit(0);
		}

		System.out.println("");
		path(mainmenu());

	}

	public user register() {
		String username = "";
		String password = "";
		String question = "";
		String answer = "";
		boolean check = true;
		int option = 0;
		System.out.println("***Registration***\n");
		String f_name = UserInputs.getFirstName();
		String l_name = UserInputs.getLastName();
		while (check) {
			username = UserInputs.getUserName();
			check = connection.checkUsername(username);
		}
		try {
			while (username.isBlank()) {
				System.out.print("Enter User Name: ");
				username = scan.nextLine();
				check = connection.checkUsername(username);
				if (check) {
					System.out.println();
					System.err.println("Username In Use!!");
					username = "";
				}
			}

			while (password.isBlank()) {
				System.out.print("Enter Password: ");
				password = scan.nextLine();
				check = connection.checkUsername(password);
				if (check) {
					System.out.println();
					System.err.println("Password Taken!!\n");
					password = "";
				}
			}
		} catch (SQLException e) {
			check = true;
		}

		while (question.isEmpty()) {
			System.out.print("Enter Security Question: ");
			question = scan.nextLine();
		}

		while (answer.isBlank()) {
			System.out.println();
			System.out.println("Security Question: " + question + "?");
			System.out.print("Enter Security Answer: ");
			answer = scan.nextLine();
		}

		while (option < 1 || option > 2) {
			try {
				System.out.println("\nOption");
				System.out.println("1. Continue");
				System.out.println("2. Cancel\n");
				System.out.print("Action: ");
				option = scan.nextInt();
				scan.nextLine();
			} catch (InputMismatchException e) {
				scan.nextLine();
			}
		}

		if (option == 1) {
			user newUser = new user();
			newUser.setF_name(f_name);
			newUser.setL_name(l_name);
			newUser.setUsername(username);
			newUser.setPassword(password);
			newUser.setQuest(question);
			newUser.setAnswer(answer);
			newUser.setCheck(0);
			newUser.setSave(0);

			return newUser;
		}

		return null;
	}

	public void createProfile(user user) throws SQLException {

		boolean created = false;
		try {
			if (user == null) {
				mainmenu();
			} else {
				created = connection.newProfile(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (created) {
			System.out.println(user.getUsername() + " your bank account has been created");
			userMenu(user);
		} else {
			System.err.println("There Seems To Be An Error With Our Database!\n\n");
		}

	}

	public void login() throws SQLException {
		String username = "";
		String password = "";
		String ques = "";
		String ans = "";
		int counter = 0;
		user user = null;

		while (counter < 3) {

			System.out.println("**** Login ****");
			while (username.isBlank()) {
				System.out.print("Enter Username: ");
				username = scan.nextLine();
			}
			while (password.isBlank()) {
				System.out.print("Enter Password: ");
				password = scan.nextLine();
			}

			user = connection.login(username, password);
			if (user == null) {
				counter++;
				if (ad.getUsername().equals(username) && ad.getPassword().equals(password)) {
					System.out.println("\n");
					AdMenu.adminpath(AdMenu.adminmenu(ad));
				}
				username = "";
				password = "";
				System.out.println();
			} else {
				System.out.println("\n");
				userMenu(user);
			}

			if (counter == 3) {
				System.err.println("Too Many Attempts!!");
				while (ques.isBlank()) {
					System.err.println("**** SECURITY ****");
					System.err.print("Enter Security Question: ");
					ques = scan.nextLine();
				}
				while (ans.isBlank()) {
					System.err.print("Enter Security Answer: ");
					ans = scan.nextLine();
				}

				user = connection.secureLogin(ques, ans);

				if (user != null) {
					System.out.println("\n");
					userMenu(user);
				}

				path(mainmenu());
			}
		}
	}

	public void userMenu(user user) throws SQLException {
		int i = 0;
		try {
			while (i < 1 || i > 5) {
				System.out.println("**** User Menu ****\n");
				System.out.println("Hello " + user.getF_name().concat(" " + user.getL_name()) + " || Checking Amount: $"
						+ user.getCheck() + " || Saving Amount: $" + user.getSave());
				System.out.println("\n1. Withdraw");
				System.out.println("2. Deposit");
				System.out.println("3. Transfer");
				System.out.println("4. View Profile");
				System.out.println("5. Log Out\n");
				System.out.print("Action: ");
				i = scan.nextInt();
				scan.nextLine();
			}
		} catch (InputMismatchException e) {
			scan.nextLine();
		}

		userMethods(i, user);
	}

	public void userMethods(int i, user user) throws SQLException {
		switch (i) {
			case 1:
				withdraw(user);
				break;
			case 2:
				deposit(user);
				break;
			case 3:
				transfer(user);
				break;
			case 4:
				userInfo(user);
				break;
			case 5:
				path(mainmenu());
				break;
		}
	}

	public void withdraw(user user) throws SQLException {
		int i = 0;
		double with = 0;
		while (i < 1 || i > 2) {

			try {
				System.out.println("*** Withdraw ***\n");
				System.out.println("Select Account");
				System.out.println("\n1. Checking");
				System.out.println("2. Savings\n");
				System.out.print("Action: ");
				i = scan.nextInt();
				scan.nextLine();
				if (i == 1) {
					System.out.println("Checking Amount: $" + user.getCheck());
					System.out.print("Withdrawal Amount: $");
					with = scan.nextDouble();
					scan.nextLine();
				} else if (i == 2) {
					System.out.println("Saving Amount: $" + user.getSave());
					System.out.print("Withdrawal Amount: $");
					with = scan.nextDouble();
					scan.nextLine();
				}

			} catch (InputMismatchException e) {
				scan.nextLine();
			}
		}
		userMenu(bankmethod.withdraw(i, with, user));
	}

	public void deposit(user user) throws SQLException {
		int i = 0;
		double dep = 0;
		while (i < 1 || i > 2) {

			try {
				System.out.println("*** Deposit ***\n");
				System.out.println("Select Account");
				System.out.println("\n1. Checking");
				System.out.println("2. Savings\n");
				System.out.print("Action: ");
				i = scan.nextInt();
				scan.nextLine();
				if (i == 1) {
					System.out.println("Checking Amount: $" + user.getCheck());
					System.out.print("Deposit Amount: $");
					dep = scan.nextDouble();
					scan.nextLine();
				} else if (i == 2) {
					System.out.println("Saving Amount: $" + user.getSave());
					System.out.print("Depost Amount: $");
					dep = scan.nextDouble();
					scan.nextLine();
				}

			} catch (InputMismatchException e) {
				scan.nextLine();
			}
		}
		userMenu(bankmethod.deposit(i, dep, user));
	}

	public void transfer(user user) throws SQLException {
		int i = 0;
		double trans = 0;
		while (i < 1 || i > 2) {

			try {
				System.out.println("*** Transfer ***\n");
				System.out.println("Select Account");
				System.out.println("\n1. Checking -> Saving");
				System.out.println("2. Saving -> Checking\n");
				System.out.print("Action: ");
				i = scan.nextInt();
				scan.nextLine();
				if (i == 1) {
					System.out.println("Checking Amount: $" + user.getCheck());
					System.out.print("Transfering Amount: $");
					trans = scan.nextDouble();
					scan.nextLine();
				} else if (i == 2) {
					System.out.println("Saving Amount: $" + user.getSave());
					System.out.print("Transfering Amount: $");
					trans = scan.nextDouble();
					scan.nextLine();
				}

			} catch (InputMismatchException e) {
				scan.nextLine();
			}
		}
		userMenu(bankmethod.transfer(i, trans, user));
	}

	public void userInfo(user user) throws SQLException {
		System.out.println(" *** User Profile ***");
		System.out.println("Name: " + user.getF_name().concat(" " + user.getL_name()));
		System.out.println("Checking Amount: $" + user.getCheck());
		System.out.println("Saving Amount: $" + user.getSave());
		System.out.println("Security Question: " + user.getQuest());
		System.out.println("Security Answer: " + user.getAnswer());
		System.out.println("\n(Press Anything To Continue)\n");
		scan.nextLine();

		userMenu(user);

	}

}
