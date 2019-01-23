package com.ibm.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Transactionfield {
	public enum OPERATION_TYPE {
		READ, READWRITE
	}

	public String value() default "";

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	@Documented
	public @interface Whereclause {
		public String[] clause() default {};
	}

}
