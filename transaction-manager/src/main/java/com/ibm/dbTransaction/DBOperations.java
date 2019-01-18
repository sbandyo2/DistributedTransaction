package com.ibm.dbTransaction;

import java.nio.charset.Charset;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ibm.utils.Utils;

@Service
public class DBOperations {

	@Autowired
	JdbcTemplate jdbc;
	private static final Logger log = LoggerFactory.getLogger(DBOperations.class);
	
	public  void insertSupplierInfo(Object obj) {

		Object[] objectArray = null;
		objectArray = Utils.generateInsertQuery(obj);

		String query = "INSERT INTO MONITOR.TSUPP_PARTNERING_INFO" + " ("
				+ objectArray[0] + ") VALUES" + "(" + objectArray[1] + ")";

		log.info("Running Query :>>>> " + query);

		jdbc.execute(query);

	}
	

}
