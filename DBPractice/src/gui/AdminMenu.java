package gui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import model.admin;
import model.user;
import respiratory.Driver;
import gui.menu;

public class AdminMenu {
	
	static Scanner scan = new Scanner(System.in);
	static menu m = new menu();
	static Driver database = new Driver();
	static admin TYBG = new admin();

	public int adminmenu(admin admin) throws SQLException {
		int i = 0;
		try {
			while(i < 1 || i > 4) {
		System.out.println("**** Admin Menu ****\n");
		System.out.println("BASEDGOD!! ");
		System.out.println("\n1. Create New Profile");
		System.out.println("2. Search Profile");
		System.out.println("3. View All Profile");
		System.out.println("4. Log Out\n");
		System.out.print("Action: ");
		i = scan.nextInt(); scan.nextLine();
		}
		}catch(InputMismatchException e) {
			scan.nextLine();
			adminpath(adminmenu(TYBG));
		}
		
		System.out.println("\n");
		return i;
	}
	
	public void adminpath(int i) throws SQLException {
		switch(i) {
		case 1: createProfile(register()); break;
		
		case 2: String name = "";
				while(name.isBlank()) {
				System.out.print("\nEnter Username: ");
				name = scan.nextLine(); 
				}
				adSearch(name); break;
				
		case 3: viewAll(); break;
			
		case 4: System.out.println("GOODBYE BASEDGOD.....TYBG\n");  m.path(m.mainmenu()); break;
		}
	}
	
	public user register() {
		String f_name = "";
		String l_name = "";
		String username = "";
		String password = "";
		String question = "";
		String answer = "";
		boolean check = true;
		int option = 0;
		System.out.println("***Registration***\n");
		
		while(f_name.isBlank()) {
			System.out.print("Enter First Name: ");
			f_name = scan.nextLine();
		}
		
		while(l_name.isBlank()) {
			System.out.print("Enter Last Name: ");
			l_name = scan.nextLine();
		}
		try {
		while(username.isBlank()) {
			System.out.print("Enter User Name: ");
			username = scan.nextLine();
			check = database.checkUsername(username);
			if(check) {
				System.out.println();
				System.err.println("Username In Use!!");
				username = "";
			}
		}

		
		while(password.isBlank()) {
			System.out.print("Enter Password: ");
			password = scan.nextLine();
			check = database.checkUsername(password);
			if(check) {
				System.out.println();
				System.err.println("Password Taken!!\n");
				password = "";
			}
		}
		}catch(SQLException e) {
			check = true;
		}
		
		while(question.isEmpty()) {
			System.out.print("Enter Security Question: ");
			question = scan.nextLine();
		}
		
		while(answer.isBlank()) {
			System.out.println();
			System.out.println("Security Question: " + question+ "?");
			System.out.print("Enter Security Answer: ");
			answer = scan.nextLine();
		}
		
		while(option < 1 || option > 2) {
		try {
		System.out.println("\nOption");
		System.out.println("1. Continue");
		System.out.println("2. Cancel\n");
		System.out.print("Action: ");
		option = scan.nextInt();
		scan.nextLine();
		}catch(InputMismatchException e) {
			scan.nextLine();
		}
		}
		
		if(option == 1) {
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
		if(user == null) {
			adminpath(adminmenu(TYBG));
		}else {
			created = database.newProfile(user);
		}
		} catch (SQLException e) {
				e.printStackTrace();
			}
		
		if(created) {
			System.out.println(user.getUsername() + " your bank account has been created");
			adminpath(adminmenu(TYBG));
		}else {
			System.err.println("There Seems To Be An Error With Our Database!\n\n");
		}
		
	}
	
	
	public void adSearch(String username) throws SQLException {
		user user = database.AdminSearch(username);
		int i = 0;
		boolean deleted = false;
		
		System.out.println(" *** User Profile ***\n");
		if(user != null) {
			while(i < 1 || i > 2) {
				try {
		System.out.println("Name: " + user.getF_name().concat(" " + user.getL_name()));
		System.out.println("Checking Amount: $" + user.getCheck());
		System.out.println("Saving Amount: $" + user.getSave());
		System.out.println("Security Question: " + user.getQuest());
		System.out.println("Security Answer: " + user.getAnswer());
		System.out.println("\nOption\n");
		System.out.println("1. Continue");
		System.out.println("2. Delete Profile\n"); 
		System.out.print("Action: ");
		i = scan.nextInt(); 
		scan.hasNextLine();
		
		if(i == 1) {
			adminpath(adminmenu(TYBG));
		}else if(i == 2) {
			deleted = database.delete(username);
			if(deleted) {
				System.out.println("Profile Has Been Deleted!");
			}else {
				System.err.println("Error! Profile Not Deleted");
				
			}
		}
			}catch(InputMismatchException e) {
				scan.nextLine();
			}
			}
		
		}else {
			System.err.println("Profile Not Found!!!\n");
		}
		System.out.println("\n(Press Anything To Continue)\n"); 
		scan.nextLine();
		adminpath(adminmenu(TYBG));
		
		
	}
	
	public void viewAll() throws SQLException {
		System.out.println("*** All Profiles ***\n");
		ArrayList<user> AllUsers = database.allUsers();
		if(AllUsers.isEmpty()) {
			System.err.println("Currently No Profiles");
			
		}else {
			for(user user: AllUsers) {
				System.out.println(user);
			}
			
		}
		System.out.println("\n(Press Anything To Continue)\n"); 
		scan.nextLine();
		adminpath(adminmenu(TYBG));
	}
	
}
