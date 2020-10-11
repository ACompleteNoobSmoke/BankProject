package Program;

import gui1.UserInputs;
import gui1.menu;
import model.user;

public class BankingApplication {

	public static void main(String[] args) {
		menu menu = new menu();
		user newUser = new user();
		MenuMethods menuMeth = new MenuMethods();
		UserMethods userMeth = new UserMethods();
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

			while (newUser != null) {
				newUser = userMeth.userActions(newUser);
			}
		}

	}

}
