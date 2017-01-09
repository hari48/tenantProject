package com.sps.tagClasses;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SPSTable extends TagSupport {
	private String map, tagId, scope, tagClass;
	private JspWriter out;
	private HttpServletRequest request;
	private Map mainMap;
	private LinkedHashMap colMap;
	
	public Map getColMap(){
		return colMap;
	}
	
	public String getMap() {
		return map;
	}

	public void setMap(String map) {
		this.map = map;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getTagClass() {
		return tagClass;
	}

	public void setTagClass(String tagClass) {
		this.tagClass = tagClass;
	}

	public int doStartTag() throws JspTagException {
		try {
			request = (HttpServletRequest) pageContext.getRequest();
			pageContext.getOut().flush();
			out = pageContext.getOut();
			if (scope.equalsIgnoreCase("request")) {
				mainMap = (Map) request.getAttribute(map);
			} else if (scope.equalsIgnoreCase("session")) {
				mainMap = (Map) request.getSession().getAttribute(map);
			}
			if (mainMap == null) {
				throw new Exception("Sequence Object Not Found.");
			}
			validateAttributes();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_INCLUDE;
	}

	private void startTable() throws IOException {
			out.println("<table id='" + tagId + "' class='display' width='100%' cellspacing='0' style='padding-bottom: 10px;'>");
	}

	private void validateAttributes(){
		colMap = new LinkedHashMap();
		if (tagId == "" || tagId == null)
			tagId = "example";
		if (tagClass == "" || tagClass == null)
			tagClass = "display";
		if (scope == "" || scope == null)
			scope = "request";
	}

	public int doEndTag() {
		try {
		startTable();
		createColumns();
		createRows();
		out.println("</table>");//Closing table
		} catch (IOException e) {
			e.printStackTrace();
		}
		out = null;
		request = null;
		colMap = null;
		return EVAL_PAGE;
	}

	private void createColumns() throws IOException {
		out.println("<thead style='background-color: #4078C0; color: #FFFFFF'>");
		out.println("<tr>");
		Iterator itr = colMap.keySet().iterator();
		String column;
		while (itr.hasNext()) {
			column = (String) itr.next();
			if (column.equalsIgnoreCase("checkbox")) {
				out.println("<th style=\"padding: 10px; width: 10px\"><input id=\"select_all\" type=\"checkbox\" /></th>");
			}
			else {
				out.println("<th>"+column+"</th>");
			}
		}
		out.println("</tr></thead>");
	}

	private void createRows() throws IOException {
			out.println("<tbody>");
			out.println("<tr>");
			Iterator colitr;
			Iterator usritr = mainMap.keySet().iterator();
			String column;
			Map userMap;
			int n = mainMap.size();
			while(usritr.hasNext()){
				userMap = (Map) mainMap.get(usritr.next());
				colitr = colMap.keySet().iterator();
			while (colitr.hasNext()) {
				column = (String) colitr.next();
				if (column.equalsIgnoreCase("checkbox")) {
					out.println("<td><input class=\"checkbox\" type=\"checkbox\" /></td>");
				} else if (column.equalsIgnoreCase("action")) {
					createAction(n, (Map)userMap.get("actionMap"));
				} else {
					out.println("<td>"+userMap.get(colMap.get(column))+"</td>");
				}
			}
			out.println("</tr>");
			n--;
			}
			out.println("</tbody>");
	}

	private void createAction(int n, Map actionMap) throws IOException {
		out.println("<td><a href=\"javascript:void(0)\" id=\"img"+n+"\" onclick=\"pulldownMenu("+n+")\"> <img class=\"actionImage\" src=\""+request.getContextPath()+"/img/edit.gif\"></a>");
		out.println("<div id=\"dynamicMenu"+n+"\" class=\"myMenu-options\">");
		Iterator itr = actionMap.keySet().iterator();
		String action;
		while(itr.hasNext()){
			action = (String) itr.next();
			out.println("<span id=\"menu0"+n+"\" class=\"myMenu-item\" onclick=\""+actionMap.get(action)+"\">"+action+"</span>");
		}
		out.println("</div></td>");
	}

}
