package com.sps.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	public String execute(){
		return "success";
	}
	public void validate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if(session != null){
			session.invalidate();
		}else{
			String message = request.getParameter("message");
			if(message == null){
				return;
			} else{
				if(message.equals("NoSession")){
					addFieldError("userName", "You are not logged in, Please Login first.");
				}
				else if(message.equals("NoLogin")){
					addFieldError("userName", "Please Login first.");
				}
			}
		}		
	}
}
