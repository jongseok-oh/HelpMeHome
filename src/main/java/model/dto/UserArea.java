package model.dto;

public class UserArea {
	String userId;
	String dongCode;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public UserArea(String userId, String dongCode) {
		super();
		this.userId = userId;
		this.dongCode = dongCode;
	}
	
	
	
	
}
