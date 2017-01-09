package com.sps.ajaxActions;

import java.sql.SQLException;

import com.opensymphony.xwork2.ActionSupport;

import com.sps.util.QueryUtil;

public class ChangePasswordAjaxAction extends ActionSupport {
	private String passChange;
	private String userId;
	private String newPass;
	
	public String execute() {
		String query = "UPDATE USERS set password=? where user_id=?;";
			if(QueryUtil.updateQuery("USERS", new String[]{"password"}, new Object[]{newPass}, new String[]{"user_id"}, new Object[]{userId}))
				passChange = "Password successfully changed!  ";
		return SUCCESS;
	}

	public String getPassChange() {
		return passChange;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
}
