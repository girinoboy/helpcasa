package br.com.jm.actions;

import java.util.List;

import br.com.jm.beans.User;
import br.com.jm.dao.UserDAO;

public class UserAction {
	
	private User user;
	private List<User> users;
	
	public String listAll(){
		users = UserDAO.getUserList();
		return "listAll";
	}
	
	public void insertUser(){
		UserDAO.addUser(user);
	}
	
	public String clear(){
		users = null;
		UserDAO.clear();
		return "listAll";
	}
	
	public final User getUser() {
		return user;
	}
	public final void setUser(User user) {
		this.user = user;
	}
	public final List<User> getUsers() {
		return users;
	}
	public final void setUsers(List<User> users) {
		this.users = users;
	}

}
