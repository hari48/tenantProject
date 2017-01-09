package com.sps.ajaxActions;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.sps.manager.CommonManager;

public class ModifyUserFormAction extends ActionSupport{
	public String execute(){
		HttpServletRequest request = ServletActionContext.getRequest();
		if("modify".equals(request.getParameter("event"))){
			int user_id = Integer.parseInt(request.getParameter("user_id"));
			Map detailsMap = CommonManager.getInstance().getUserDAO().getAllDetails(user_id);
			request.setAttribute("modifyUser", detailsMap);
		}
		return SUCCESS;
	}
}
