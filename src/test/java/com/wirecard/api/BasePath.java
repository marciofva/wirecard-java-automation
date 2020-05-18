package com.wirecard.api;

public enum BasePath {

	CUSTOMER("/customers"), ORDERS("/orders"), PAYMENTS("/payments");

	private String value;

	BasePath(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
