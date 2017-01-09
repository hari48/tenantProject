package com.sps.manager;

import com.sps.dao.UserDAO;

public class CommonManager {
	private static CommonManager manager;
	private UserDAO userDAO;
	private CommonManager() {
	}
	
	public static CommonManager getInstance(){
		if(manager == null){
			manager = new CommonManager();
		}
		return manager;
	}
	
	public UserDAO getUserDAO(){
		if(userDAO == null){
			userDAO = new UserDAO();
		}
		return userDAO;
	}
}
