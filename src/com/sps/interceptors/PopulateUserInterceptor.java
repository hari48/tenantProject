package com.sps.interceptors;

import java.sql.ResultSet;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.sps.manager.CommonManager;

public class PopulateUserInterceptor implements Interceptor {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		 Map<String, Object> session = arg0.getInvocationContext().getSession();
		 String  username = (String)session.get("username");
		 Map user = CommonManager.getInstance().getUserDAO().getAllDetails(username);
		 session.remove("username");
		 session.put("user", user);
		return arg0.invoke();
	} 
}