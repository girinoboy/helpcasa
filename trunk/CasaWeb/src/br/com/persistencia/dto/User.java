package br.com.persistencia.dto;

import java.util.Date;

public class User {
	
	private String name;
	private String email;
	private Date birthday;
	
	public final String getName() {
		return name;
	}
	public final void setName(String name) {
		this.name = name;
	}
	public final String getEmail() {
		return email;
	}
	public final void setEmail(String email) {
		this.email = email;
	}
	public final Date getBirthday() {
		return birthday;
	}
	public final void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
