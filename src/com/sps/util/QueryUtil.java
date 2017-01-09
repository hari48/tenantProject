package com.sps.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.PreparedStatement;

public class QueryUtil {
	public static ResultSet executeQuery(String query, Object[] params) throws SQLException{
		System.out.println("Inside query util");
		DBConnectionManager manager = DBConnectionManager.getInstance();
		Connection con = manager.getConnection("sps");
		PreparedStatement st = con.prepareStatement(query);
		for(int i=0; i<params.length; i++){
			if (params[i] instanceof Timestamp)
				st.setTimestamp(i+1,(Timestamp)params[i]);
			else if(params[i] instanceof Integer)
				st.setInt(i+1,(Integer)params[i]);
			else if(params[i] instanceof String)
				st.setString(i+1,(String)params[i]);
			else
				st.setObject(i+1, params[i]);
		}
		ResultSet rs = st.executeQuery();
		manager.freeConnection("sps", con);
		return rs;
	}
	
	public static boolean updateQuery(String tablename, String setColumnnames[], Object setValues[], String whereColumnnames[], Object values[]){
		System.out.println("Inside query util");
		int rs=-1;
		DBConnectionManager manager = DBConnectionManager.getInstance();
		Connection con = manager.getConnection("sps");
		StringBuilder query = new StringBuilder();
		query.append("UPDATE "+tablename+" SET ");
		for(int i=0; i<setColumnnames.length; i++){
			query.append(setColumnnames[i]+"=? ");
		}
		query.append("WHERE");
		query.append(" "+whereColumnnames[0]+"=?");
		for(int i=1; i<whereColumnnames.length; i++){
			query.append(" && "+whereColumnnames[i]+"=?");
		}
		query.append(";");
		PreparedStatement st;
		try {
			st = con.prepareStatement(query.toString());
		
		for(int i=0; i<setValues.length; i++){
			if (setValues[i] instanceof Timestamp)
				st.setTimestamp(i+1,(Timestamp)setValues[i]);
			else if(setValues[i] instanceof Integer)
				st.setInt(i+1,(Integer)setValues[i]);
			else if(setValues[i] instanceof String)
				st.setString(i+1,(String)setValues[i]);
			else
				st.setObject(i+1, setValues[i]);
		}
		for(int i=setValues.length, j=0; j<values.length; i++,j++){
			if (values[j] instanceof Timestamp)
				st.setTimestamp(i+1,(Timestamp)values[j]);
			else if(values[j] instanceof Integer)
				st.setInt(i+1,(Integer)values[j]);
			else if(values[j] instanceof String)
				st.setString(i+1,(String)values[j]);
			else
				st.setObject(i+1, values[j]);
		}
		
		System.out.println("Query: "+query.toString());
		rs = st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.freeConnection("sps", con);
		return rs > -1;
	}
	
	public static ResultSet selectQuery(String tablename, String getColumns[], String whereColumnnames[], Object values[]){
		System.out.println("Inside query util");
		ResultSet rs=null;
		DBConnectionManager manager = DBConnectionManager.getInstance();
		Connection con = manager.getConnection("sps");
		StringBuilder query = new StringBuilder();
		query.append("SELECT ");
		if(getColumns.length == 0 || getColumns[0] == "*"){
			query.append(" * ");
		}
		else{
			query.append(getColumns[0]);
		for(int i=1; i<getColumns.length; i++){
			query.append(", "+getColumns[i]);
		}
		}
		query.append(" FROM "+tablename);
		if(whereColumnnames.length != 0)
		{
			query.append(" where "+whereColumnnames[0]+"=?");
			for(int i=1; i<whereColumnnames.length; i++){
				query.append(" && "+whereColumnnames[i]+"=?");
			}
		}
		query.append(";");
		PreparedStatement st;
		try {
			st = con.prepareStatement(query.toString());
		
		for(int i=0; i<values.length; i++){
			if (values[i] instanceof Timestamp)
				st.setTimestamp(i+1,(Timestamp)values[i]);
			else if(values[i] instanceof Integer)
				st.setInt(i+1,(Integer)values[i]);
			else if(values[i] instanceof String)
				st.setString(i+1,(String)values[i]);
			else
				st.setObject(i+1, values[i]);
		}
		
		System.out.println("Query: "+query.toString());
		rs = st.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		manager.freeConnection("sps", con);
		return rs;
	}
	public static boolean executeUpdate(String query, Object[] params){
		System.out.println("Inside query util");
		int rs = -1;
		DBConnectionManager manager = DBConnectionManager.getInstance();
		Connection con = manager.getConnection("sps");
		PreparedStatement st;
		try {
			st = con.prepareStatement(query);
	
		for(int i=0; i<params.length; i++){
			if (params[i] instanceof Timestamp)
				st.setTimestamp(i+1,(Timestamp)params[i]);
			else if(params[i] instanceof Integer)
				st.setInt(i+1,(Integer)params[i]);
			else if(params[i] instanceof String)
				st.setString(i+1,(String)params[i]);
			else
				st.setObject(i+1, params[i]);
		}
		rs = st.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		manager.freeConnection("sps", con);
		return rs > 0;
	}
}
