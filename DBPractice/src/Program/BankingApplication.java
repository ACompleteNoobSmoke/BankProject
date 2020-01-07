package Program;

import java.sql.SQLException;

import gui.menu;

public class BankingApplication {

	public static void main(String[] args) throws SQLException {
		menu menu = new menu();
		menu.path(menu.mainmenu());


	}

}
