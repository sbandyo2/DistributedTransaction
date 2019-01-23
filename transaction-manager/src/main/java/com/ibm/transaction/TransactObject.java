package com.ibm.transaction;

import java.sql.Connection;
import java.util.Map;

import com.ibm.annotations.DatabaseConfig;
import com.ibm.annotations.DatabaseConfig.DIALECTTYPE;
import com.ibm.annotations.Transactionfield;

@DatabaseConfig(dialectType=DIALECTTYPE.DB2,keepAliveTimeInSeconds = 5)
public class TransactObject {

	@Transactionfield
	private String transactionType;
	
	@Transactionfield
	private String transactionID;
	
	@Transactionfield
	private String data;
	
	@Transactionfield
	private String operation;
		
	@Transactionfield
	private String host;
	
	@Transactionfield
	private String port;
	
	@Transactionfield
	private String dbInstance;
	
	@Transactionfield
	private String schema;
	
	@Transactionfield
	private String table;
	
	@Transactionfield
	private String user;
	
	@Transactionfield
	private String password;
	
	
	
	private Connection connection;
    private Map<String, Map<String, String>> clauseMap ;
	
	

	

	/**
	 * @return the clauseMap
	 */
	public Map<String, Map<String, String>> getClauseMap() {
		return clauseMap;
	}

	/**
	 * @param clauseMap the clauseMap to set
	 */
	public void setClauseMap(Map<String, Map<String, String>> clauseMap) {
		this.clauseMap = clauseMap;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
	}

	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @return the operation
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * @return the host
	 */
	public String getHost() {
		return host;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @return the dbInstance
	 */
	public String getDbInstance() {
		return dbInstance;
	}

	/**
	 * @return the schema
	 */
	public String getSchema() {
		return schema;
	}

	/**
	 * @return the table
	 */
	public String getTable() {
		return table;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * @param host the host to set
	 */
	public void setHost(String host) {
		this.host = host;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(String port) {
		this.port = port;
	}

	/**
	 * @param dbInstance the dbInstance to set
	 */
	public void setDbInstance(String dbInstance) {
		this.dbInstance = dbInstance;
	}

	/**
	 * @param schema the schema to set
	 */
	public void setSchema(String schema) {
		this.schema = schema;
	}

	/**
	 * @param table the table to set
	 */
	public void setTable(String table) {
		this.table = table;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}
}
