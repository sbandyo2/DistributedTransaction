package com.ibm.transaction.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.ibm.annotations.extractor.AnnotationExtractor;
import com.ibm.annotations.extractor.SerializableField;
import com.ibm.exception.TransactionException;

public class ConnectionManager {
  
	  
	/**
	 * @return
	 * @throws TransactionException
	 */
	public static Connection getConnection(List<SerializableField>  fields) throws TransactionException {
		Connection connnectionObj = null;
		String url = null;
		String port = "";
		String dbInstance = "";
		String host = "";
		DriverInfo driverInfo = null;
		try {
			driverInfo = DriverInfo.getInstance();
			Class.forName(driverInfo.getDRIVER().get(AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.DIALECT_TYPE)));
			
			//form the url
			port = AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.PORT);
			dbInstance = AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.DBINSTANCE);
			host = AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.HOST);
			
			url = driverInfo.getCONN_URL().get(AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.DIALECT_TYPE)).
					replace("#hostname", host).replace("#port", port).replace("#dbInstance", dbInstance);
			
			// Create the connection using the IBM Data Server Driver for JDBC
			// and SQLJ
			connnectionObj = DriverManager.getConnection(url, AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.USER_NAME), 
					AnnotationExtractor.getAnnotationValue(fields, AnnotationExtractor.PWD_NAME));
			// Commit changes manually
			connnectionObj.setAutoCommit(false);

		} catch (ClassNotFoundException e) {
			throw new TransactionException(e.getMessage());
		} catch (SQLException e) {
			throw new TransactionException(e.getMessage());
		}

		return connnectionObj;
	}
	

	
	/**
	 * @param rs
	 * @param stmt
	 * @throws TransactionException
	 */
	public static void closeConnectionResources(ResultSet rs,PreparedStatement stmt) throws TransactionException {
		
		try {
			//close the result set
			if (rs != null)
				rs.close();
			
			// close the statement object
			if (stmt != null)
				stmt.close();
				
		} catch (SQLException e) {
			throw new TransactionException(e.getMessage());
		}
	}
	/**
	 * @param connectionObj
	 * @throws TransactionException
	 */
	public static void closeConnection(Connection connectionObj) throws TransactionException {

		try {
			
			// commit and close the connection close the connection
			if (connectionObj != null){
				connectionObj.close();
			}
				
		} catch (SQLException e) {
			throw new TransactionException(e.getMessage());
		}
	}
	    
}
