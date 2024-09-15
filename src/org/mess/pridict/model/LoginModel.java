package org.mess.pridict.model;

public class LoginModel {
	private String urname;
	private String pass;
	public String getUrname() {
		return urname;
	}
	public void setUrname(String urname) {
		this.urname = urname;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public LoginModel(String urname, String pass) {
		this.urname = urname;
		this.pass = pass;
	}
	public LoginModel() {
	}
	
}
