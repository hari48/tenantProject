package com.sps.interceptors;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class CheckSessionInterceptor implements Interceptor {

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
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		System.out.println(ServletActionContext.getRequest().getSession(false));
		if(session == null){
			System.out.println("Sessionaa is Null");
			HttpServletResponse response = ServletActionContext.getResponse();
			response.sendRedirect(request.getContextPath()+"/control/login?message=NoSession");
		} else {
			String isLoggedIn = (String) session.getAttribute("isLoggedIn");
			System.out.println(isLoggedIn);
			System.out.println(session.getAttribute("user"));
			if (isLoggedIn != null && isLoggedIn.equals("true")) {
					return arg0.invoke();
			} else {
				System.out.println("isLoggedIn is Null/false");
				HttpServletResponse response = ServletActionContext.getResponse();
				response.sendRedirect(request.getContextPath()+"/control/login?message=NoLogin");
			}
		}
		return null;
	}

}
