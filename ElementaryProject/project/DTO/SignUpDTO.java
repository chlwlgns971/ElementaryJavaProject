package DTO;

public class SignUpDTO {
	private String userID; //아이디
	private String passWord; //비밀번호
	private String name; //이름
	private String regNo; //주민등록번호 앞자리
	private String addr; //주소
	private String userPH; //휴대폰번호
	public SignUpDTO(String id, String pw, String name, String regNo, String addr, String ph){
		this.userID=id;
		this.passWord=pw;
		this.name=name;
		this.regNo=regNo;
		this.addr=addr;
		this.userPH=ph;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
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
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getUserPH() {
		return userPH;
	}
	public void setUserPH(String userPH) {
		this.userPH = userPH;
	}
	@Override
	public String toString() {
		return "SignUpDTO [userID=" + userID + ", passWord=" + passWord + ", name=" + name + ", regNo=" + regNo
				+ ", addr=" + addr + ", userPH=" + userPH + "]";
	}
	
}
