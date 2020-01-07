package Program;

import java.sql.SQLException;


import model.user;
import respiratory.Driver;

public class method {
	
	static Driver database = new Driver();
	 
	
	public user withdraw(int choice, double withdrawal, user user) {
		double total = 0;
		user updatedUser = new user();

		if(choice == 1) {
			if(withdrawal > user.getCheck() || withdrawal <=0) {
				total = 0;
			}else {
			total = user.getCheck() - withdrawal;
			user.setCheck(total);
			}
		}else if(choice == 2){
			if(withdrawal > user.getSave() || withdrawal <=0) {
				total = 0;
			}else {
			total = user.getSave() - withdrawal;
			user.setSave(total);
			}
		}
		
		try {
			updatedUser = database.updateMoney(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedUser;
		
	}
	
	public user deposit(int choice, double deposit, user user) {
		double total = 0;
		user updatedUser = null;
		if(deposit > 0) {
		
			if(choice == 1) {
			total = user.getCheck() + deposit;
			user.setCheck(total);
			}else if(choice == 2){
			total = user.getSave() + deposit;
			user.setSave(total);
			}
		}
		
		try {
			updatedUser = database.updateMoney(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedUser;
		
	}
	
	public user transfer(int choice, double amount, user user) {
		double newCheck = 0;
		double newSave = 0;
		user updatedUser = null;
		if(amount > 0) {
		
		if(choice == 1) {
			if(amount > user.getCheck()) {
				System.err.println("Insufficient Funds!!");
			}else {
			newCheck = user.getCheck() - amount;
			newSave = user.getSave() + amount;
			user.setCheck(newCheck);
			user.setSave(newSave);
			}
		}else if(choice == 2){
			if(amount > user.getSave()) {
				System.err.println("Insufficient Funds!!");
			}else {
			newCheck = user.getCheck() + amount;
			newSave = user.getSave() - amount;
			user.setCheck(newCheck);
			user.setSave(newSave);
			}
		}
		}
		
		
		
		try {
			updatedUser = database.updateMoney(user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return updatedUser;
		
	}
	
	



}
