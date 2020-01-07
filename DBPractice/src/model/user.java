package model;

public class user {
	
	private String f_name;
	private String l_name;
	private String username;
	private String password;
	private String quest;
	private String answer;
	private double check;
	private double save;
	
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getQuest() {
		return quest;
	}
	public void setQuest(String quest) {
		this.quest = quest;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public double getCheck() {
		return check;
	}
	public void setCheck(double check) {
		this.check = check;
	}
	public double getSave() {
		return save;
	}
	public void setSave(double save) {
		this.save = save;
	}
	
	public String toString() {
		return "Name: " + f_name.concat(" " + l_name) + "\n" +
				"Checking Amount: $" + check + "\n" +
				"Saving Amount: $" + save + "\n\n";
	}
	

}
