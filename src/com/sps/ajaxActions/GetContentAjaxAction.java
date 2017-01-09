package com.sps.ajaxActions;

public class GetContentAjaxAction {
	
	private String tab;
	
	public String getContent(){
		System.out.println("In getContent");
		if(tab != null){
			if(tab.equals("changePassword")){
				return "changePassword";
			}
			else if(tab.equals("viewUsers")){
				return "viewUsers";
			}
			else if(tab.equals("addUser")){
				return "addUser";
			}
		}
		return null;
	}

	public String getTab() {
		return tab;
	}

	public void setTab(String tab) {
		this.tab = tab;
	}
}
