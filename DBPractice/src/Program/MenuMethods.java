package Program;

import java.sql.SQLException;

import gui1.UserInputs;
import gui1.menu;
import model.user;
import respiratory.Driver;

public class MenuMethods {

	// Method used to login with username and password.
	public user loginWithInfo() {
		user returningUser = null;
		Driver SQL = new Driver();

		if (returningUser == null) {
			System.out.println("**** Login ****");
			String logUsername = UserInputs.getUserName();
			String logPassword = UserInputs.getPassword();
			returningUser = SQL.loginFromDB(logUsername, logPassword);
			if (returningUser == null) {
				System.err.println("\nUsername Or Password Is Incorrect!\n");
			}
		}
		return returningUser;
	}

	// Method used to login with username, user question and user answers.
	public user loginWithSecurity() {
		user secureUser = null;
		Driver SQL = new Driver();

		if (secureUser == null) {
			System.out.println("**** Login ****");
			System.out.println("Security Method\n");
			String secureUserName = UserInputs.getUserName();
			String secureQuestion = UserInputs.getSecurityQuestion();
			String secureAnswer = UserInputs.getSecurityAnswer(secureQuestion);
			String secureInfo = SQL.getSecurityFromDB(secureUserName, secureQuestion, secureAnswer);
			if (!secureInfo.isEmpty()) {
				secureUser = SQL.loginFromDB(secureUserName, secureInfo);
			} else {
				System.out.println("Couldn't Find Account Incorrect Information Provided!");
			}
		}
		return secureUser;
	}

	// Method that combines both method of login(info & serurity) also with
	// counters.
	public user login() {
		int counter1 = 0;
		int counter2 = 0;
		user loginUser = null;

		while (counter1 < 3) {
			loginUser = loginWithInfo();
			if (loginUser == null) {
				counter1++;
				System.out.println("\nAttempts: " + (3 - counter1));
			} else {
				break;
			}
		}

		if (loginUser == null) {
			System.out.println("WARNING: You will have 3 more attempts using the Security Method\n");
			do {
				loginUser = loginWithSecurity();
				if (loginUser == null) {
					counter2++;
					System.out.println("\nAttempts: " + (3 - counter2));
				} else {
					break;
				}
			} while (counter2 < 3);
		}

		return loginUser;

	}

	// Method used to register new users.
	public user register() {
		menu menu = new menu();
		String username = "";
		boolean check = true;
		user newUser = null;
		int option = 0;
		System.out.println("***Registration***\n");
		String f_name = UserInputs.getFirstName();
		String l_name = UserInputs.getLastName();
		while (check) {
			username = UserInputs.getUserName();
			check = Driver.checkUserName(username);
			if (check) {
				System.err.println("WARNING: Username Already In Use");
			}
		}
		String password = UserInputs.getPassword();
		String question = UserInputs.getSecurityQuestion();
		String answer = UserInputs.getSecurityAnswer(question);
		option = menu.optionMenu();
		if (option == 1 || option == 2) {
			newUser = new user(f_name, l_name, username, password, question, answer, 0, 0);
			saveNewUser(newUser);
			if (option == 2) {
				return null;
			}
		} else {
			System.out.println("Registration Has Been Cancelled");
		}

		return newUser;
	}

	// Methods used to save information into the database provided by users
	public void saveNewUser(user saveUser) {
		try {
			Driver.saveNewProfile(saveUser);
			Driver.saveProfileSecurity(saveUser.getUsername(), saveUser.getQuest(), saveUser.getAnswer());
			Driver.saveProfileAccounts(saveUser.getUsername(), saveUser.getCheck(), saveUser.getSave());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}