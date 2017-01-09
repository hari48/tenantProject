package com.sps.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.sps.util.QueryUtil;

public class UserDAO extends BaseDAO {
	public UserDAO() {
	}
	
	public Map getAllDetails(int user_id){
		Map detailsMap=null;
		ResultSet rs = QueryUtil.selectQuery("USERS", new String[]{"*"}, new String[]{"user_id"}, new Integer[]{user_id});
		try {
			if(rs.next()){
				detailsMap = new HashMap();
				detailsMap.put("user_id", rs.getInt("user_id"));
				detailsMap.put("username", rs.getString("username"));
				detailsMap.put("name", rs.getString("name"));
				detailsMap.put("role_id", rs.getInt("role_id"));
				detailsMap.put("role", rs.getString("role"));
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return detailsMap;
	}
	
	public Map getAllDetails(String username){
		Map detailsMap=null;
		ResultSet rs = QueryUtil.selectQuery("USERS", new String[]{"*"}, new String[]{"username"}, new String[]{username});
		try {
			if(rs.next()){
				detailsMap = new HashMap();
				detailsMap.put("user_id", rs.getInt("user_id"));
				detailsMap.put("username", rs.getString("username"));
				detailsMap.put("name", rs.getString("name"));
				detailsMap.put("role_id", rs.getInt("role_id"));
				detailsMap.put("role", rs.getString("role"));
				rs.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return detailsMap;
	}
	
	public boolean addUser(int user_id, String username, String password, int role_id, String role, String name){
		String query = "Insert into USERS(user_id, username, password, role_id, role, name) value(?,?,?,?,?,?);";
		boolean rs = false;
			rs = QueryUtil.executeUpdate(query, new Object[]{user_id, username, password, role_id, role, name});
		return rs;
	}
	
	public Map getAllUsers(){
		Map mainMap = new HashMap();
		Map userMap;
		try {
			ResultSet rs = QueryUtil.executeQuery("Select user_id, username, name, role, role_id from USERS;", new Object[]{});
			while(rs.next()){
				userMap = new HashMap();
				userMap.put("username", rs.getString("username"));
				userMap.put("name", rs.getString("name"));
				userMap.put("role", rs.getString("role"));
				userMap.put("role_id", rs.getString("role_id"));
				userMap.put("user_id", rs.getString("user_id"));
				mainMap.put(rs.getInt("user_id"), userMap);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return mainMap;
	}
}
