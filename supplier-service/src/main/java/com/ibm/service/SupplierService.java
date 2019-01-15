package com.ibm.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ibm.dto.SupplierInfo;

@Service
public class SupplierService {
	
	@Autowired  
	JdbcTemplate jdbc;   

	public void insertSupplierInfo(SupplierInfo suppInfo){
		String query = "INSERT INTO MONITOR.TSUPP_PARTNERING_INFO"
				+ "(SUPP_PARTNER_ID, VendorID, LocationID, Name, City, Street, PostalCode, Region, Country, Phone, Fax, EmailAddress) VALUES"
				+ "("+suppInfo.getSuppPartnerId()+",'"+suppInfo.getVendorID()+"','"+suppInfo.getLocaltionID()+"','"+suppInfo.getName()+"','"
				+suppInfo.getCity()+"','"+suppInfo.getStreet()+"','"+suppInfo.getPostalcode()+"','"+suppInfo.getRegion()+"','"
				+suppInfo.getCountry()+"','"+suppInfo.getPhone()+"','"+suppInfo.getFax()+"','"+suppInfo.getEmailAddress()+"')";
		
		System.out.println("Running Query : "+query);
		
		jdbc.execute(query);
		
		
		
	}

}
