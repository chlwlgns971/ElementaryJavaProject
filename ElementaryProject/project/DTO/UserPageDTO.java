package DTO;

public class UserPageDTO {
	private String loginID;
	public UserPageDTO(String userID) {
		this.loginID=userID;
	}
	public String getLoginID() {
		return loginID;
	}
	public void setLoginID(String loginID) {
		this.loginID = loginID;
	}
}
