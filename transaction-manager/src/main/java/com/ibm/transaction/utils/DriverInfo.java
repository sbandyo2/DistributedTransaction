package com.ibm.transaction.utils;

import java.util.HashMap;
import java.util.Map;

public class DriverInfo {

	 
    private static DriverInfo single_instance = null; 
  
    // variable of type String 
    private Map<String, String> DRIVER;
    private Map<String, String> CONN_URL;
  
    // load the db provider details
    private DriverInfo() 
    { 
    	DRIVER = new HashMap<String, String>();
    	DRIVER.put("db2", "com.ibm.db2.jcc.DB2Driver");
    	DRIVER.put("oracle","oracle.jdbc.driver.OracleDriver");
    	DRIVER.put("mysql","com.mysql.jdbc.Driver"); 
    	
    	CONN_URL = new HashMap<String, String>();
    	
    	CONN_URL.put("db2", "jdbc:db2:#hostname:#port/#databaseName");
    	CONN_URL.put("oracle","jdbc:oracle:thin:@#hostname:#port:#databaseName");
    	CONN_URL.put("mysql","jdbc:mysql://#hostname:#port/#databaseName");
    } 
  
    // static method to create instance of Singleton class 
    public static DriverInfo getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DriverInfo(); 
  
        return single_instance; 
    }

	/**
	 * @return the dRIVER
	 */
	public Map<String, String> getDRIVER() {
		return DRIVER;
	}

	/**
	 * @return the cONN_URL
	 */
	public Map<String, String> getCONN_URL() {
		return CONN_URL;
	}
}
