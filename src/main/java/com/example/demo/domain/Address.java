package com.example.demo.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class Address {

	private String postalCode;
	private String streetAddress;
	private String addressLocality;
	private String addressRegion;
	private String addressCountry;
	
	public Address() {}

	public Address(String postalCode, String streetAddress, String addressLocality, String addressRegion,
			String addressCountry) {
		super();
		this.postalCode = postalCode;
		this.streetAddress = streetAddress;
		this.addressLocality = addressLocality;
		this.addressRegion = addressRegion;
		this.addressCountry = addressCountry;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getAddressLocality() {
		return addressLocality;
	}

	public void setAddressLocality(String addressLocality) {
		this.addressLocality = addressLocality;
	}

	public String getAddressRegion() {
		return addressRegion;
	}

	public void setAddressRegion(String addressRegion) {
		this.addressRegion = addressRegion;
	}

	public String getAddressCountry() {
		return addressCountry;
	}

	public void setAddressCountry(String addressCountry) {
		this.addressCountry = addressCountry;
	}
	
	
}
