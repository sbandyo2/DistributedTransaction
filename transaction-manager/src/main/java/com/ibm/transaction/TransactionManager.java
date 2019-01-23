package com.ibm.transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibm.annotations.extractor.AnnotationExtractor;
import com.ibm.annotations.extractor.SerializableField;
import com.ibm.cache.Cache;
import com.ibm.cache.InMemoryCache;
import com.ibm.exception.TransactionException;
import com.ibm.transaction.utils.ConnectionManager;
import com.ibm.transaction.utils.QueryGenerator;

public class TransactionManager {

	private static final String READ = "READ";
	private static final String READWRITE = "READWRITE";
	
	public static final String UPDATE = "UPDATE";
	public static final String INSERT = "INSERT";
	public static final String DELETE = "DELETE";
	
	private List<Object[]> datalist = null;
	
	private static Cache cache = null;

	/**
	 * 
	 */
	public TransactionManager(){
		cache = new InMemoryCache();
	}
	/**
	 * Performs the read and write operation, supported for insert,delete and
	 * update
	 * 
	 * @param transactObject
	 * @throws TransactionException 
	 */
	private void performReadWrite(TransactObject transactObject,List<SerializableField> fields) throws TransactionException {
		String operation = null;
		operation = AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.OPERATION);
		switch (operation.toUpperCase()) {
		case UPDATE:
			execute(transactObject,fields,UPDATE);
			break;

		case INSERT:
			execute(transactObject,fields,INSERT);
			break;
		case DELETE:
			execute(transactObject,fields,DELETE);
			break;

		default:
			break;
		}
	}

	/**
	 * @param transactObject
	 * @param fields
	 * @throws TransactionException 
	 */
	private void execute(TransactObject transactObject,List<SerializableField> fields,String operation) throws TransactionException {
		Connection connection = null;
		Object[] objectArray = null;
		Statement statement = null;
		Savepoint savepoint = null;
		
		String transactionID = null;
		try {
			connection = ConnectionManager.getConnection(fields);
			transactionID = AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.TRANSACTION_ID);
			
			objectArray = QueryGenerator.generatQuery(AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.OPERATION),
					AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.DATA),transactObject.getClauseMap());

			String query = operation+" INTO "+AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.SCHEMA)+"."+
					AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.TABLE_NAME)+" ("
					+ objectArray[0] + ") VALUES" + "(" + objectArray[1] + ")";
			
			statement = connection.createStatement();
			statement.executeUpdate(query);
			
			//create and cache the save point
			savepoint = (Savepoint) cache.get(transactionID);
			if(savepoint ==null){
				savepoint = connection.setSavepoint(transactionID);
				cache.add(transactionID, savepoint, Long.valueOf(AnnotationExtractor.getAnnotationValue(fields,AnnotationExtractor.KEEP_ALIVE_TIME)).longValue());
			}

		} catch (TransactionException e) {
			throw new TransactionException(e.getMessage());
		} catch (SQLException e) {
			try {
				if(connection!=null)
					connection.rollback(savepoint);
			} catch (SQLException e1) {
				throw new TransactionException(e.getMessage());
			}
			throw new TransactionException(e.getMessage());
		}finally{
			
			try {
				if(statement!=null)
				statement.close();
			} catch (SQLException e) {
				throw new TransactionException(e.getMessage());
			}
			//close connections
			ConnectionManager.closeConnection(connection);
		}
		
	}

	/**
	 * @param transactObject
	 */
	private void performRead(TransactObject transactObject,List<SerializableField> fields) {
		datalist = new ArrayList<Object[]>();
		
	}

	/**
	 * @param transactObject
	 * @throws TransactionException
	 */
	public void performTransaction(TransactObject transactObject) throws TransactionException {
		String transactionType = null;
		List<SerializableField> fields = null;

		fields = new AnnotationExtractor().getSerializableFields(transactObject);
		transactObject.setConnection(ConnectionManager.getConnection(fields));

		transactionType = AnnotationExtractor.getAnnotationValue(fields,
				AnnotationExtractor.TRANSACTION_TYPE);
		
		switch (transactionType.toUpperCase()) {
		case READ:
			performRead(transactObject,fields);
			break;

		case READWRITE:
			performReadWrite(transactObject,fields);
			break;

		default:
			break;
		}

	}
	
	/**
	 * @return
	 */
	public List<Object[]> getData(){
		return datalist;
	}

}
