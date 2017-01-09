package com.sps.tagClasses;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class SPSColumn extends TagSupport {
	private String displayName, dbName;
	private Map colMap;
	private JspWriter out;
	private SPSTable parent;
	
	public int doStartTag() throws JspTagException {
		parent = (SPSTable) findAncestorWithClass(this, SPSTable.class);
		if (parent == null) {
			throw new JspTagException("condition not inside if");
		}
		return SKIP_BODY;
	}
	
	public int doEndTag() {
		try {
			pageContext.getOut().flush();
			out = pageContext.getOut();
			colMap = parent.getColMap();
			colMap.put(displayName, dbName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
}
