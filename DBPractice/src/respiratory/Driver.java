package respiratory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.user;

public class Driver {
	
	final String dbuser = "root";
	final String dbpass = "codingroot1!";
	final String dburl = "jdbc:mysql://localhost:3306/traindb?autoReconnect=true&useSSL=false";
	
	public Connection getConnection() throws SQLException{
		Connection con = null;
		try {
			con = DriverManager.getConnection(dburl, dbuser, dbpass);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

	
	public boolean checkUsername(String username) throws SQLException{
		try {
		Connection con = getConnection();
		 PreparedStatement ps = con.prepareStatement("select * from bank");
		ResultSet rs = ps.executeQuery();
		
	
		while(rs.next()) {
			if(rs.getString(3).equals(username)) {
				return true;
			}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	public boolean checkPassword(String password) throws SQLException{
		try {
		Connection con = getConnection();
		 PreparedStatement ps = con.prepareStatement("select * from bank");
		ResultSet rs = ps.executeQuery();
		
	
		while(rs.next()) {
			if(rs.getString(4).equals(password)) {
				return true;
			}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	
	public boolean newProfile(user newUser) throws SQLException{
		try {
		Connection con = getConnection();
		PreparedStatement ps = con.prepareStatement("insert into bank values(?, ?, ?, ?, ?, ?, ?, ?)");
		ps.setString(1, newUser.getF_name());
		ps.setString(2, newUser.getL_name());
		ps.setString(3, newUser.getUsername());
		ps.setString(4, newUser.getPassword());
		ps.setString(5, newUser.getQuest());
		ps.setString(6, newUser.getAnswer());
		ps.setDouble(7, newUser.getCheck());
		ps.setDouble(8, newUser.getSave());
		ps.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	public user login(String username, String password) {
		user returningUser = null;
		try {
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from bank");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(3).equals(username) && rs.getString(4).equals(password)) {
					returningUser = new user();
					returningUser.setF_name(rs.getString(1));
					returningUser.setL_name(rs.getString(2));
					returningUser.setUsername(rs.getString(3));
					returningUser.setPassword(rs.getString(4));
					returningUser.setQuest(rs.getString(5));
					returningUser.setAnswer(rs.getString(6));
					returningUser.setCheck(rs.getDouble(7));
					returningUser.setSave(rs.getDouble(8));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returningUser;
	}
	
	public user secureLogin(String question, String answer) {
		user returningUser = null;
		try {
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from bank");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(5).equalsIgnoreCase(question) && rs.getString(6).equals(answer)) {
					returningUser = new user();
					returningUser.setF_name(rs.getString(1));
					returningUser.setL_name(rs.getString(2));
					returningUser.setUsername(rs.getString(3));
					returningUser.setPassword(rs.getString(4));
					returningUser.setQuest(rs.getString(5));
					returningUser.setAnswer(rs.getString(6));
					returningUser.setCheck(rs.getDouble(7));
					returningUser.setSave(rs.getDouble(8));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returningUser;
	}
	
	public user updateMoney(user user) throws SQLException{
		try {
		Connection con = getConnection();
	    PreparedStatement ps = con.prepareStatement("update bank set checking = ?, saving = ? where username = ?");
		ps.setDouble(1, user.getCheck());
		ps.setDouble(2, user.getSave());
		ps.setString(3, user.getUsername());
		ps.executeUpdate();
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}

	public user AdminSearch(String username) {
		user returningUser = null;
		try {
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from bank");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(3).equals(username)) {
					returningUser = new user();
					returningUser.setF_name(rs.getString(1));
					returningUser.setL_name(rs.getString(2));
					returningUser.setUsername(rs.getString(3));
					returningUser.setPassword(rs.getString(4));
					returningUser.setQuest(rs.getString(5));
					returningUser.setAnswer(rs.getString(6));
					returningUser.setCheck(rs.getDouble(7));
					returningUser.setSave(rs.getDouble(8));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return returningUser;
	}
	
	public boolean delete(String username) throws SQLException{
		try {
		Connection con = getConnection();
	    PreparedStatement ps = con.prepareStatement("delete from bank where username = ?");
		ps.setString(1, username);
		ps.executeUpdate();
		return true;
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return false;

	}
	
	public ArrayList<user> allUsers() {
		ArrayList<user> all = new ArrayList<user>();
		user returningUser = null;
		try {
			
			Connection con = getConnection();
			PreparedStatement ps = con.prepareStatement("select * from bank");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				 
					returningUser = new user();
					returningUser.setF_name(rs.getString(1));
					returningUser.setL_name(rs.getString(2));
					returningUser.setUsername(rs.getString(3));
					returningUser.setPassword(rs.getString(4));
					returningUser.setQuest(rs.getString(5));
					returningUser.setAnswer(rs.getString(6));
					returningUser.setCheck(rs.getDouble(7));
					returningUser.setSave(rs.getDouble(8));
					all.add(returningUser);
				
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return all;
	}


}
