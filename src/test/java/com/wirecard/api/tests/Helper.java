package com.wirecard.api.tests;

import java.util.Map;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
	
	public String convertMapToJson(Map<String, Object> map) {
		
		String json = "";
		
		try {
			json = new ObjectMapper().writeValueAsString(map);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}
}
