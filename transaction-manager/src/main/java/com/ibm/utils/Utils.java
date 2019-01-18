package com.ibm.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	
	/**
	 * @param str
	 * @return
	 */
	public static boolean isNullOrEmpty(String str) {
		if (str == null) {
			return true;
		} else if (str.trim().equals("")) {
			return true;
		} 
		return false;
	}
	
	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}
	
	/**
	 * @param array
	 * @return
	 * @throws JSONException
	 */
	private static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}
	
	/**
	 * @param key
	 * @param jsonObject
	 * @return
	 */
	public static Object getValue(String key, JSONObject jsonObject){
		Map<String, Object> map  = null;
		map = toMap(jsonObject);
		return (map.containsKey(key))?jsonObject.get(key):"";
	}
	
	/**
	 * @param obj
	 * @return
	 */
	public static Object[] generateInsertQuery(Object obj){
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


	/**
	 * @param dataObject
	 */
	public static Map<String, Object> toMap (String dataObject){
		String data = null;
		Map<String, Object> map = null;
		
		map = new HashMap<String, Object>();
		data = dataObject.substring(1, dataObject.length() - 1);
		String val  = null;
		String key = null;
		System.out.println("Inserting for data "+data);
		
		if(data.contains(",")){
			String[] pair = data.split(",");
			if(pair!= null && pair.length>0){
				for(String pairItem : pair){
					if(pairItem.contains("=")){
						String [] pairItemArr = pairItem.split("=");
						if(pairItemArr!= null){
							key = pairItemArr[0];
							if(pairItemArr.length==1){
								val = "";
							}else if(pairItemArr.length==2){
								val = pairItemArr[1];
							}
							key = key.replaceAll("\\s","");
							map.put(key, val);
						}	
					}
						
				}
				
			}
		}
		
		return map;
		
	}
	

}
