package com.sps.actions;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TenantListAction extends ActionSupport {
	public String execute(){
		Map mainMap = new HashMap();
		Map tenantMap;
		Map actionMap;
		for(int i=0; i<200; i++){
			tenantMap=new HashMap();
			actionMap=new LinkedHashMap();
			tenantMap.put("BuildName", "System Architect");
			tenantMap.put("CodeValue", "Edinburgh");
			tenantMap.put("Build/CaptivateURL", "61");
			tenantMap.put("BuildType", "Mon 25Apr 11");
			tenantMap.put("Version", "T.Nixon");
			tenantMap.put("ParsingEmail", "T.Nixon");
			tenantMap.put("ServerName", "T.Nixon");
			tenantMap.put("TomcatName", "T.Nixon");
			tenantMap.put("LastModified", "T.Nixon");
			for(int j=0; j<10; j++){
				actionMap.put("View/User Password", "javascript:void(0)");
				actionMap.put("View Audit istory", "javascript:void(0)");
				actionMap.put("View Trigger History", "javascript:void(0)");
				actionMap.put("Exequte Query", "javascript:void(0)");
				actionMap.put("View Logs", "javascript:void(0)");
				actionMap.put("Download Files", "javascript:void(0)");
				actionMap.put("Run FJS Thread", "javascript:void(0)");
			}
			tenantMap.put("actionMap", actionMap);
			mainMap.put(i, tenantMap);
		}
		ServletActionContext.getRequest().setAttribute("tenants", mainMap);
		return SUCCESS;
	}
}
