package com.ibm.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public final class SupplierInfo extends BaseDto implements Serializable {

	private static final long serialVersionUID = 1L;
    
	
	@JsonProperty("suppPartnerId")
    private Integer suppPartnerId;
	
	@JsonProperty("vendorID")
    private String vendorID;
	
	@JsonProperty("localtionID")
	private String localtionID;

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("city")
	private String city;
	
	@JsonProperty("street")
	private String street;
	
	@JsonProperty("postalcode")
	private String postalcode;
	
	@JsonProperty("region")
	private String region;
	
	@JsonProperty("country")
	private String country;
	
	@JsonProperty("phone")
	private String phone;
	
	@JsonProperty("fax")
	private String fax;
	
	@JsonProperty("emailAddress")
	private String emailAddress;
	
    // Default constructor is needed to de-serialize JSON
    public SupplierInfo() {
    }
	
    


	/**
	 * @return the suppPartnerId
	 */
	public Integer getSuppPartnerId() {
		return suppPartnerId;
	}




	/**
	 * @param suppPartnerId the suppPartnerId to set
	 */
	public void setSuppPartnerId(Integer suppPartnerId) {
		this.suppPartnerId = suppPartnerId;
	}




	/**
	 * @return the vendorID
	 */
	public String getVendorID() {
		return vendorID;
	}




	/**
	 * @param vendorID the vendorID to set
	 */
	public void setVendorID(String vendorID) {
		this.vendorID = vendorID;
	}




	/**
	 * @return the localtionID
	 */
	public String getLocaltionID() {
		return localtionID;
	}




	/**
	 * @param localtionID the localtionID to set
	 */
	public void setLocaltionID(String localtionID) {
		this.localtionID = localtionID;
	}




	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}




	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}




	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}




	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}




	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}




	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}




	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}




	/**
	 * @param postalcode the postalcode to set
	 */
	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}




	/**
	 * @return the region
	 */
	public String getRegion() {
		return region;
	}




	/**
	 * @param region the region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}




	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}




	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}




	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}




	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}




	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}




	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}




	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}




	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}




	@Override
	public String toString() {
		return "Listening to event [eventId=" + eventId + ",suppPartnerId=" + suppPartnerId + ", vendorID=" + vendorID + ", localtionID=" + localtionID + "]";
	}
}
