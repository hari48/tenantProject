package com.sps.ajaxActions;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;
import com.sps.manager.CommonManager;
import com.sps.util.QueryUtil;

public class AddModifyUserAction extends ActionSupport{
	private String username, name, password, role;
	private int code;
	private String response;
	
	public String execute(){
		System.out.println("In adduser action");
		boolean check = userExixts();
		if(check){
			System.out.println("user already exists");
			code = 0;
			response = "Username already exists"; 
		}
		else{
			System.out.println("user do not exist");
			addUser();
			code = 1;
			response = "User successfully added";
		}
		return SUCCESS;
	}
	
	private void addUser() {
		int user_id =(int)(Math.random()*90000 + 10000);
		while(checkIdExists(user_id)){
			user_id =(int)(Math.random()*90000 + 10000);
		}
		int role_id=0;
		if(role.equals("1")){
			role="Admin";
			role_id=255;
		}
		else if(role.equals("2")){
			role="FSS";
			role_id=254;
		}
		else if(role.equals("3")){
			role="Guest";
			role_id=252;
		}
		CommonManager.getInstance().getUserDAO().addUser(user_id, username, password, role_id, role, name);
	}

	private boolean checkIdExists(long user_id) {
		String query = "Select * from USERS where user_id=?";
		ResultSet rs=null;
		try {
			rs = QueryUtil.executeQuery(query, new Object[]{user_id});
		
		if(rs!=null && rs.next()){
			return true;
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(rs!=null)
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}

	private boolean userExixts() {
		String query = "Select user_id from USERS where username=?;";
		ResultSet rs=null;
		try {
			rs = QueryUtil.executeQuery(query, new Object[]{username});
			if(rs!=null && rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	finally{
		if(rs!=null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
		return false;
	}
	
	public int getCode() {
		return code;
	}

	public String getResponse() {
		return response;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
