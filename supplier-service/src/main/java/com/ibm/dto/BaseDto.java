package com.ibm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDto   {
	
	@JsonProperty("eventId")
	public String eventId;
	
	@JsonProperty("transactionId")
	public String transactionId;
	
	

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the eventId
	 */
	public String getEventId() {
		return eventId;
	}

	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	
	
	

}
