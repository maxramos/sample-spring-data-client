package com.maxaramos.springdatatestclient.model;

public enum GenderType {

	MALE("male"),
	FEMALE("female");

	private String value;

	GenderType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
