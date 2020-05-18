package com.wirecard.api;

public enum AuthenticationCredentials {
	
	TOKEN("LUYYLAKKKRXXQPWDP2QQUM38AKT9YBSP"),
	KEY("8SCJMUDA21PTZZCEVRGRF23KFW6YZWHDOHT9X0DY");
	
	public String value;
	
	AuthenticationCredentials(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
}
