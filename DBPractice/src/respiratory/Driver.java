package respiratory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.user;

public class Driver {

	public static boolean checkUserName(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Select UserName From `BankUser` Where UserName = (?)");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String found = rs.getString("UserName");
				if (found.equals(username)) {
					return true;
				}
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nCheck Username Function Not Working\n");
		}
		return false;
	}

	public static boolean checkPassword(String username, String password) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con
					.prepareStatement("Select Password From `BankUser` Where UserName = (?) AND Password = (?)");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				String found = rs.getString("Password");
				if (found.equals(password)) {
					return true;
				}
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("\nCheck Password Function Not Working\n");
		}
		return false;
	}

	public static String getUserNameByFullName(String firstName, String lastName) {
		String userName = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement(
					"Select FirstName, LastName, UserName From `BankUser` Where FirstName = (?) AND LastName = (?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString("FirstName").equals(firstName) && rs.getString("LastName").equals(lastName)) {
					userName = rs.getString("UserName");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;

	}

	public static boolean saveNewProfile(user newUser) throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Insert Into `BankUser` values(?, ?, ?, ?)");
			ps.setString(1, newUser.getF_name());
			ps.setString(2, newUser.getL_name());
			ps.setString(3, newUser.getUsername());
			ps.setString(4, newUser.getPassword());
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean saveProfileSecurity(String username, String question, String answer) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Insert Into `BankSecurity` values(?, ?, ?)");
			ps.setString(1, username);
			ps.setString(2, question);
			ps.setString(3, answer);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static boolean saveProfileAccounts(String username, double checkings, double savings) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Insert Into `BankAccounts` values (?, ?, ?)");
			ps.setString(1, username);
			ps.setDouble(2, savings);
			ps.setDouble(3, checkings);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public user loginFromDB(String username, String password) {
		user returningUser = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement(
					"Select FirstName, LastName, `BankUser`.Username, Password, UserQuestion, UserAnswer, Savings, Checkings From `BankUser` \n"
							+ "Join `BankSecurity` ON `BankUser`.Username = `BankSecurity`.Username  \n"
							+ "Join `BankAccounts` ON `BankUser`.Username = `BankAccounts`.Username  \n"
							+ "Where `BankUser`.Username = (?) AND 	`BankUser`.Password = (?) ");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString(3).equals(username) && rs.getString(4).equals(password)) {
					returningUser = new user();
					returningUser.setF_name(rs.getString(1));
					returningUser.setL_name(rs.getString(2));
					returningUser.setUsername(rs.getString(3));
					returningUser.setPassword(rs.getString(4));
					returningUser.setQuest(rs.getString(5));
					returningUser.setAnswer(rs.getString(6));
					returningUser.setSave(rs.getDouble(7));
					returningUser.setCheck(rs.getDouble(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returningUser;
	}

	public String getSecurityFromDB(String securityUserName, String securityQuestion, String securityAnswer) {
		String securityPassword = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Select `BankUser`.UserName, Password From `BankUser` \n"
					+ "JOIN `BankSecurity` ON `BankUser`.UserName = `BankSecurity`.UserName \n"
					+ "Where `BankSecurity`.UserName = (?) AND UserQuestion = (?) AND UserAnswer = (?)");
			ps.setString(1, securityUserName);
			ps.setString(2, securityQuestion);
			ps.setString(3, securityAnswer);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals(securityUserName)) {
					securityPassword = rs.getString(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return securityPassword;
	}

	public double getAccountsFromDB(String userName, String account) {
		double amountFromDB = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			String query = "Select UserName, " + account + " From `BankAccounts` Where Username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				if (rs.getString(1).equals(userName)) {
					amountFromDB = rs.getDouble(2);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return -1.0;
		}

		return amountFromDB;
	}

	public void updateMoney(String userName, String account, double newAmount) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			String query = "Update `BankAccounts` Set  " + account + " = (?) Where Username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setDouble(1, newAmount);
			ps.setString(2, userName);
			ps.executeUpdate();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static user adminSearchUsername(String username) {
		user foundUser = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement(
					"Select FirstName, LastName, `BankUser`.Username, Password, UserQuestion, UserAnswer, Savings, Checkings From `BankUser` \n"
							+ "Join `BankSecurity` ON `BankUser`.Username = `BankSecurity`.Username  \n"
							+ "Join `BankAccounts` ON `BankUser`.Username = `BankAccounts`.Username  \n"
							+ "Where `BankUser`.Username = (?)");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString(3).equals(username)) {
					foundUser = new user();
					foundUser.setF_name(rs.getString(1));
					foundUser.setL_name(rs.getString(2));
					foundUser.setUsername(rs.getString(3));
					foundUser.setPassword(rs.getString(4));
					foundUser.setQuest(rs.getString(5));
					foundUser.setAnswer(rs.getString(6));
					foundUser.setSave(rs.getDouble(7));
					foundUser.setCheck(rs.getDouble(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundUser;
	}

	public static user adminSearchFullName(String firstName, String lastName) {
		user foundUser = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement(
					"Select FirstName, LastName, `BankUser`.Username, Password, UserQuestion, UserAnswer, Savings, Checkings From `BankUser` \n"
							+ "Join `BankSecurity` ON `BankUser`.Username = `BankSecurity`.Username  \n"
							+ "Join `BankAccounts` ON `BankUser`.Username = `BankAccounts`.Username  \n"
							+ "Where `BankUser`.FirstName = (?) AND `BankUser`.LastName = (?)");
			ps.setString(1, firstName);
			ps.setString(2, lastName);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getString(1).equals(firstName) && rs.getString(2).equals(lastName)) {
					foundUser = new user();
					foundUser.setF_name(rs.getString(1));
					foundUser.setL_name(rs.getString(2));
					foundUser.setUsername(rs.getString(3));
					foundUser.setPassword(rs.getString(4));
					foundUser.setQuest(rs.getString(5));
					foundUser.setAnswer(rs.getString(6));
					foundUser.setSave(rs.getDouble(7));
					foundUser.setCheck(rs.getDouble(8));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundUser;
	}

	public static boolean adminDeleteUsername(String username) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			String query = "Delete From `BankUser` where Username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, username);
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteBankSecurity(String userName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			String query = "Delete From `BankSecurity` where Username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, userName);
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteBankAccount(String userName) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			String query = "Delete From `BankAccounts` where Username = (?)";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, userName);
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteBankUser() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Delete From  `BankUser`");
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteSecurity() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Truncate Table `BankSecurity`");
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean deleteAccounts() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement("Truncate Table `BankAccounts`");
			int delete = ps.executeUpdate();

			if (delete == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static ArrayList<user> allUsers() {
		ArrayList<user> all = new ArrayList<user>();
		user foundUser = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/BankAppSQL?autoReconnect=true&useSSL=false", "root", "codingroot1!");
			PreparedStatement ps = con.prepareStatement(
					"Select FirstName, LastName, `BankUser`.Username, Password, UserQuestion, UserAnswer, Savings, Checkings From `BankUser` \n"
							+ "Join `BankSecurity` ON `BankUser`.Username = `BankSecurity`.Username  \n"
							+ "Join `BankAccounts` ON `BankUser`.Username = `BankAccounts`.Username  \n");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				foundUser = new user();
				foundUser.setF_name(rs.getString(1));
				foundUser.setL_name(rs.getString(2));
				foundUser.setUsername(rs.getString(3));
				foundUser.setPassword(rs.getString(4));
				foundUser.setQuest(rs.getString(5));
				foundUser.setAnswer(rs.getString(6));
				foundUser.setSave(rs.getDouble(7));
				foundUser.setCheck(rs.getDouble(8));
				all.add(foundUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return all;
	}

}
