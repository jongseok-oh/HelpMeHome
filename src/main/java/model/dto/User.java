package model.dto;

public class User {
	String userId;
	String passWord;
	String name;
	String phoneNumber;
	
	public User(String userId, String passWord, String name, String phoneNumber) {
		super();
		this.userId = userId;
		this.passWord = passWord;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
}
