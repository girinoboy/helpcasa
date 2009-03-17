package br.com.jm.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.jm.beans.User;

public class UserDAO {
	
	private static List<User> users;
	
	public static List<User> getUserList(){
		List<User> list2 =  new ArrayList<User>();
			if(users!= null)
				list2.addAll(users);
			return Collections.unmodifiableList(list2);
	}
	public static void addUser (User user){
		
		if(users == null)
			users = new ArrayList<User>();
		users.add(user);
	}
	public static void clear(){
		if (users != null)
			users.clear();
	}
}
