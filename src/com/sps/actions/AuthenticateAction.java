package com.sps.actions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionSupport;
import com.sps.util.PrivilegeUtil;
import com.sps.util.QueryUtil;

public class AuthenticateAction extends ActionSupport implements SessionAware{
	private String userName;
	private String password;
	private Map<String, Object> session;
	
	public String execute(){
		System.out.println("Inside login action");
		Map user = (Map) session.get("user");
		int role_id = (int)user.get("role_id");
		Set<Integer> p_ids = PrivilegeUtil.getprivilegeIds(role_id);
		user.put("p_ids", p_ids);
		session.put("isLoggedIn", "true");
		return SUCCESS;
	}
	
	public void validate() {
		String query = "Select password from USERS where username=?;";
		System.out.println("query : "+query);
		ResultSet rs = null;
		try {
			rs = QueryUtil.executeQuery(query, new Object[]{userName});
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if(rs.next()){
				if (rs.getString("password").equals(password)) {
					session.put("username", userName);
				} else {
					addFieldError("password", "Incorrect Password");
				}
			}else {
				addFieldError("userName", "Incorrect Login ID");				
			}
		} catch (SQLException e) {
			addFieldError("userName", "Incorrect Login ID");
			e.printStackTrace();
		}
		finally{
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
   	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.session = arg0;
	}
}
