package com.wirecard.dataprovider;

import java.util.HashMap;

public class Login {
	
	public static HashMap<String, String> existingUserSandbox() {
		HashMap<String, String> map = new HashMap<String, String>();
		 map.put("email", "marcio.fva87@gmail.com");
		 map.put("password", "1234abcd");
		return map;
	}
}
