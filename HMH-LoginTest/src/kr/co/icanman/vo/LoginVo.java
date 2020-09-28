package kr.co.icanman.vo;

import java.sql.Timestamp;

public class LoginVo {
	
	private String userID;
	private String userName;
	private String userPassword;
	private int loginFalsecount;
	private Timestamp loginSuccessdate;

	public LoginVo() {
		super();
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getLoginFalsecount() {
		return loginFalsecount;
	}

	public void setLoginFalsecount(int loginFalsecount) {
		this.loginFalsecount = loginFalsecount;
	}

	public Timestamp getLoginSuccessdate() {
		return loginSuccessdate;
	}

	public void setLoginSuccessdate(Timestamp loginSuccessdate) {
		this.loginSuccessdate = loginSuccessdate;
	}

	@Override
	public String toString() {
		return "LoginVo [userID=" + userID + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", loginFalsecount=" + loginFalsecount + ", loginSuccessdate=" + loginSuccessdate + "]";
	}
}
