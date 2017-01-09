package com.sps.ajaxActions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;
import com.sps.manager.CommonManager;

public class ViewUsersAction extends ActionSupport{
	public String execute(){
		Map mainMap = CommonManager.getInstance().getUserDAO().getAllUsers();
		Iterator itr = mainMap.keySet().iterator();
		Map userMap;
		Map actionMap;
		while(itr.hasNext()){
			userMap = (Map) mainMap.get(itr.next());
			actionMap = new LinkedHashMap();
			actionMap.put("Modify", "modifyUser("+userMap.get("user_id")+")");
			actionMap.put("Delete", "javascript:void(0)");
			userMap.put("actionMap", actionMap);
		}
		ServletActionContext.getRequest().setAttribute("allUsers", mainMap);
		return SUCCESS;
	}
}
