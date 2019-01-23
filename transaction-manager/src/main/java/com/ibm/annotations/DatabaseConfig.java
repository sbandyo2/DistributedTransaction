package com.ibm.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DatabaseConfig {

	public enum DIALECTTYPE {
		DB2, ORACLE,MYSQL
	}

	public DIALECTTYPE dialectType();

	int keepAliveTimeInSeconds();
}
