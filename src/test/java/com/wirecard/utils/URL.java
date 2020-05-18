package com.wirecard.utils;

public enum URL {
	
	BASE_URL("https://connect-sandbox.moip.com.br/login");
	
	public String value;
	
	URL(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
