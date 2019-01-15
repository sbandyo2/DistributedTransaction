package com.ibm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDto {
	
	@JsonProperty("eventid")
	public String eventId;

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
