package com.ibm.transaction.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ibm.transaction.TransactionManager;
import com.ibm.utils.Utils;

public class QueryGenerator {
	
	/**
	 * Generates the query based on the opearation and clauses
	 * @param opearation
	 * @param obj
	 */
	public static Object[] generatQuery(String operation,Object obj,Map<String, Map<String, String>> clauseMap) {
		Object[] query = null;
		switch (operation.toUpperCase()) {
		case TransactionManager.UPDATE:
			query = generateInsertQuery(obj);
			break;

		case TransactionManager.INSERT:
			query = generateUpdateQuery(obj,clauseMap);
			break;
		case TransactionManager.DELETE:
			query = generateDeleteQuery(obj,clauseMap);
			break;

		default:
			break;
		}
		return query;
	}
	
	/**
	 * @param obj
	 * @param clauseMap
	 * @return
	 */
	private static Object[] generateDeleteQuery(Object obj,Map<String, Map<String, String>> clauseMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param obj
	 * @param clauseMap
	 * @return
	 */
	private static Object[] generateUpdateQuery(Object obj,Map<String, Map<String, String>> clauseMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param obj
	 * @return
	 */
	private static Object[] generateInsertQuery(Object obj){
		String keys = null;
		String values = null;
		Object [] objArray = null;
		Map<String, Object>  map = null;
		map = Utils.toMap(obj.toString());
		String key = null;
		Object value = null;
		
		Set<String> set = null;
		Iterator<String> it = null;
		objArray = new Object[2];
		
		set = map.keySet();
		it = set.iterator();
		while(it.hasNext()){
			key = it.next();
			//set the columns
			if(!"eventId".equalsIgnoreCase(key) &&
					!"transactionId".equalsIgnoreCase(key)){
				
				if(!Utils.isNullOrEmpty(keys)){
					keys = keys + key+ ", ";
				}else {
					keys = key + ", ";
							
				}
			}
			
			//set the values
			value =  map.get(key) ;
			if(!"eventId".equalsIgnoreCase(key) &&
					!"transactionId".equalsIgnoreCase(key)){
				
				if(!Utils.isNullOrEmpty(values)) {
					if(value instanceof Integer){
						values = values +value + ", ";	
					}else {
						values = values +"'"+value + "', ";
					}
					
				} else {
					if(value instanceof Integer){
						values = value + ", ";	
					}else {
						values = "'"+value + "', ";
					}
				}	
			}
			
		}
		
		keys = keys.substring(0,keys.lastIndexOf(","));
		values = values.substring(0,values.lastIndexOf(","));
		objArray [0] = keys;
		objArray[1] = values;
		
		return objArray;
	}

}
