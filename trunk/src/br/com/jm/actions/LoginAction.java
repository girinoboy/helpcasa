package br.com.jm.actions;


public class LoginAction {
	
	private String username;
	private String password;
	
	public String checkLogin(){
		if (isInvalid(username)) return "invalid.fwd";
		if (isInvalid(password)) return "invalid.fwd";
		return "valid.fwd";
	}
	
	private boolean isInvalid(String value){
		return (value == null || value.length()==0);
	}
	
	public final String getUsername() {
		return username;
	}
	public final void setUsername(String username) {
		this.username = username;
	}
	public final String getPassword() {
		return password;
	}
	public final void setPassword(String password) {
		this.password = password;
	}

}
