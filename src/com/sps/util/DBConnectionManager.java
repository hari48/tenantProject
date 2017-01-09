package com.sps.util;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Driver;
import java.util.Date;
import java.util.Enumeration;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.io.InputStream;
import java.sql.Statement;

public class DBConnectionManager {
	private static DBConnectionManager instance;
	private static int clients;
	private Vector<Driver> drivers;
	private Hashtable<String, DBConnectionPool> pools;
	private static PrintStream logger = System.out; 
	
	private DBConnectionManager() {
		init();
	}

	public static synchronized DBConnectionManager getInstance() {
		if (instance == null) {
			logger.println("Creating DBConnectionManager... ");
			instance = new DBConnectionManager();
		}
		clients++;
		return instance;
	}
	
	private void init() {
		drivers = new Vector();
		pools = new Hashtable();
		InputStream is = getClass().getResourceAsStream("/db_properties.properties");
		logger.println("Is = " + is);
		Properties dbProps = new Properties();
		try {
			dbProps.load(is);
		} catch (Exception e) {
			logger.println("Can't read the properties file. " +
					"Make sure db.properties is in the CLASSPATH" + e);
			return;
		}
		loadDrivers(dbProps);
		createPools(dbProps);
	}
	
	private void loadDrivers(Properties props) {
		String driverClasses = props.getProperty("drivers");
		StringTokenizer st = new StringTokenizer(driverClasses);
		while (st.hasMoreElements()) {
			String driverClassName = st.nextToken().trim();
			System.out.println("Driver class name: "+driverClassName);
			try {
				Driver driver = (Driver) Class.forName(driverClassName).newInstance();
				DriverManager.registerDriver(driver);
				drivers.addElement(driver);
				logger.println("Registered JDBC driver " + driverClassName);
			} catch (Exception e) {
				logger.println("Can't register JDBC driver: " +
						driverClassName + ", Exception: " + e);
			}
		}
	}
	
	private void createPools(Properties props) {
		Enumeration propNames = props.propertyNames();
		while (propNames.hasMoreElements()) {
			String name = (String) propNames.nextElement();
			if (name.endsWith(".url")) {
				String poolName = name.substring(0, name.lastIndexOf("."));
				String url = props.getProperty(poolName + ".url");
				if (url == null) {
					logger.println("No URL specified for " + poolName);
					continue;
				}
				String user = props.getProperty(poolName + ".user");
				String password = props.getProperty(poolName + ".password");
				String maxconn = props.getProperty(poolName + ".maxconn", "0");
				int max;
				try {
					max = Integer.valueOf(maxconn).intValue();
				} catch (NumberFormatException e) {
					logger.println("Invalid maxconn value " + maxconn + " for " + poolName);
					max = 0;
				}
				DBConnectionPool pool =
						new DBConnectionPool(poolName, url, user, password, max);
				pools.put(poolName, pool);
				logger.println("Initialized pool " + poolName);
			}
		}
	}
	
	public void freeConnection(String name, Connection con) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			pool.freeConnection(con);
		} else {
			logger.println("Error: In Freeing Connection pool is null.");
		}
	}
	
	public Connection getConnection(String name) {
		DBConnectionPool pool = (DBConnectionPool) pools.get(name);

		if (pool != null) {
			return pool.getConnection();
		} else {
			logger.println("Error: In Calling for Connection pool is null.");
		}
		return null;
	}
	
	public Connection getConnection(String name, long time) {

		DBConnectionPool pool = (DBConnectionPool) pools.get(name);
		if (pool != null) {
			return pool.getConnection(time);
		} else {
			logger.println("Error: In Calling for Connection with time stamp pool is null.");
		}

		return null;
	}
	
	public synchronized void release() {
		// Wait until called by the last client
		if (--clients != 0) {
			return;
		}
		Enumeration allPools = pools.elements();
		while (allPools.hasMoreElements()) {
			DBConnectionPool pool = (DBConnectionPool) allPools.nextElement();
			pool.release();
		}
		Enumeration allDrivers = drivers.elements();
		while (allDrivers.hasMoreElements()) {
			Driver driver = (Driver) allDrivers.nextElement();
			try {
				DriverManager.deregisterDriver(driver);
				logger.println("Deregistered JDBC driver " + driver.getClass().getName());
			} catch (SQLException e) {
				logger.println("Can't deregister JDBC driver: " + driver.getClass().getName());
			}
		}
	}
	
	private class DBConnectionPool{
		
		private Vector<Connection> pool;
		private int maxPoolSize;
		private String name;
		private String URL;
		private String user;
        private String password;
        int checkouts;
        
        
        
        public DBConnectionPool(String name, String URL, String user, String password, int maxPoolSize){
        	pool = new Vector<Connection>();
        	this.maxPoolSize = maxPoolSize;
        	this.name = name;
        	this.URL = URL;
        	this.user = user;
        	this.password = password;
        }
        
        public synchronized Connection getConnection() {
        	Connection con = null;
            if (pool.size() > 0)
            {	
                con = (Connection) pool.firstElement();
                pool.removeElementAt(0);
				if (!isAlive(con))
				{
					logger.println("Removed bad connection from " + name);
					// Try again recursively
					con = getConnection();
				}
            }
            else if (maxPoolSize == 0 || checkouts < maxPoolSize) {
                con = newConnection();
            }
            if (con != null) {
            	checkouts++;
            }
			logger.println("No of checkout connection :" + checkouts);
            return con;
        }
        
		public synchronized Connection getConnection(long timeout) {
			long startTime = new Date().getTime();
			Connection con;
			while ((con = getConnection()) == null) {
				try {
					wait(timeout);
				} catch (InterruptedException e) {
				}
				if ((new Date().getTime() - startTime) >= timeout) {
					// Timeout has expired
					return null;
				}
			}
			return con;
		}
		
		private Connection newConnection() {
			Connection con = null;
			try {
				if (user == null) {
					con = DriverManager.getConnection(URL);
				} else {
					con = DriverManager.getConnection(URL, user, password);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				logger.println("Error: " + e);
				logger.println("Can't create a new connection for " + URL);
				return null;
			}
			return con;
		}
		
		private boolean isAlive(Connection con)
		{
			boolean alive = false;
			try
			{
				Statement stmt = con.createStatement();
				stmt.close();
				alive = true;
			}
			catch (SQLException e)
			{
				logger.println("Connection is dead " + e);
			}
			return alive;
		}
		
		public synchronized void freeConnection(Connection con) {
			// Put the connection at the end of the Vector
			if (con == null) {
				return;
			}
			pool.addElement(con);
			checkouts--;
			logger.println("FreeConnection is called, No checkout connection: " + checkouts + " , No of connection in FreePool :"
					+ pool.size());
			notifyAll();
		}
		
		public synchronized void release() {
			Enumeration allConnections = pool.elements();
			while (allConnections.hasMoreElements()) {
				Connection con = (Connection) allConnections.nextElement();
				try {
					con.close();
					logger.println("Closed connection for pool " + name);
				} catch (SQLException e) {
					logger.println("Can't close connection for pool " + name);
				}
			}
			pool.removeAllElements();
		}
	}
}
