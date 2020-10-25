package Program;

import gui1.UserInputs;
import gui1.menu;
import model.admin;
import model.user;

public class BankingApplication {

	public void runProgram(Object newUser, UserMethods userMeth, AdminMethod adminMeth) {
		if (newUser instanceof admin) {
			admin newAdmin = (admin) newUser;
			while (newAdmin != null) {
				newAdmin = adminMeth.adminChoices(newAdmin);
			}
		} else if (newUser instanceof user) {
			user newBankUser = (user) newUser;
			while (newBankUser != null) {
				newBankUser = userMeth.userActions(newBankUser);
			}
		}
	}

	public static void main(String[] args) {
		Object newUser = null;
		menu menu = new menu();
		MenuMethods menuMeth = new MenuMethods();
		UserMethods userMeth = new UserMethods();
		AdminMethod adminMeth = new AdminMethod();
		BankingApplication program = new BankingApplication();
		while (true) {
			int i = menu.mainMenu();
			if (i == 1) {
				newUser = menuMeth.register();
			} else if (i == 2) {
				newUser = menuMeth.login();
			} else if (i == 3) {
				System.out.println("Exiting Program...");
				UserInputs.closeScanner();
				System.exit(0);
			}
			program.runProgram(newUser, userMeth, adminMeth);
		}
	}

}
