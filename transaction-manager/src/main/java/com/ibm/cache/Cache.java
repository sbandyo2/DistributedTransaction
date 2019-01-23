package com.ibm.cache;

import java.sql.Savepoint;

public interface Cache {
	
	/**
	 * @param key
	 * @param value
	 * @param periodInMillis
	 */
	void add(String key, Savepoint value, long periodInMillis);
	 
    /**
     * @param key
     */
    void remove(String key);
 
    /**
     * @param key
     * @return
     */
    Object get(String key);
 
    /**
     * 
     */
    void clear();
 
    /**
     * @return
     */
    long size();

}
