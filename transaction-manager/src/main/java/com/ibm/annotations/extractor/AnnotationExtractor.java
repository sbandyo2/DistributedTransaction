package com.ibm.annotations.extractor;

import static java.util.Objects.requireNonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.ibm.annotations.DatabaseConfig;
import com.ibm.annotations.Transactionfield;
import com.ibm.exception.TransactionException;

public class AnnotationExtractor {
	
	public static String DIALECT_TYPE = "dialectType";
	public static String KEEP_ALIVE_TIME = "keepAliveTimeInSeconds";
	
	public static String TRANSACTION_ID = "transactionID";
	public static String TRANSACTION_TYPE = "transactionType";
	public static String DATA= "data";
	public static String OPERATION = "operation";
	public static String HOST = "host";
	public static String PORT = "port";
	public static String SCHEMA = "schema";
	public static String TABLE_NAME = "table";
	public static String USER_NAME = "user";
	public static String PWD_NAME = "password";
	public static String DBINSTANCE = "dbInstance";
	

	/**
	 * @param object
	 * @return
	 * @throws TransactionException
	 */
	public List<SerializableField> getSerializableFields(Object object) throws TransactionException {
		try {
			Class<?> objectClass = requireNonNull(object).getClass();
			List<SerializableField> fields = new ArrayList<>();
			
			for (Field field: objectClass.getDeclaredFields()) {
				field.setAccessible(true);
				
				if (field.isAnnotationPresent(Transactionfield.class)) {
					fields.add(new SerializableField(getSerializedKey(field), (String) field.get(object)));
				} 
				
				else if(field.isAnnotationPresent(DatabaseConfig.class)) {
					fields.add(new SerializableField(getSerializedKey(field), (String) field.get(object)));
				}
			}
			
			fields.add(new SerializableField(DIALECT_TYPE, (String) objectClass.getAnnotation(DatabaseConfig.class).dialectType().toString()));
			fields.add(new SerializableField(KEEP_ALIVE_TIME, String.valueOf(objectClass.getAnnotation(DatabaseConfig.class).keepAliveTimeInSeconds())));
			
			return fields;
		}
		catch (IllegalAccessException e) {
			throw new TransactionException(e.getMessage());
		}
	}
	
	/**
	 * @param <T>
	 * @param field
	 * @return
	 */
	private static <T> String getSerializedKey(Field field) {
		String annotationValue = null;
		
		annotationValue = field.getAnnotation(Transactionfield.class).value();
		
		if (annotationValue.isEmpty()) {
			return field.getName();
		}
		else {
			return annotationValue;
		}
	}
	
	/**
	 * @param fields
	 * @param fieldName
	 * @return
	 */
	public static String getAnnotationValue(List<SerializableField> fields,String fieldName){
		SerializableField serializedObj = null;
		serializedObj = fields.stream()
				  .filter(customer -> fieldName.equals(customer.getName()))
				  .findAny().orElse(null);
	    
		
		return (serializedObj!=null)?serializedObj.getValue() :"";
	}
	
}
