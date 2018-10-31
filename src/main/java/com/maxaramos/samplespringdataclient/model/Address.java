package com.maxaramos.samplespringdataclient.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.maxaramos.samplespringdataclient.jsonview.UserView;

public class Address {

	@JsonView(UserView.class)
	private Long id;

	@JsonView(UserView.class)
	private String address1;

	@JsonView(UserView.class)
	private String address2;

	@JsonView(UserView.class)
	private String city;

	@JsonView(UserView.class)
	private String state;

	@JsonView(UserView.class)
	private String country;

	@JsonView(UserView.class)
	private String zipCode;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

}
