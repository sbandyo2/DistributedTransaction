package com.ibm.annotations.extractor;

public class SerializableField {

	private final String name;
	private final String value;
	
	public SerializableField(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
