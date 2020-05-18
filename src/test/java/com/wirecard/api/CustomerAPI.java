package com.wirecard.api;

import static br.com.moip.helpers.PayloadFactory.payloadFactory;
import static br.com.moip.helpers.PayloadFactory.value;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import br.com.moip.Moip;
import br.com.moip.exception.UnauthorizedException;
import br.com.moip.exception.UnexpectedException;
import br.com.moip.exception.ValidationException;

public class CustomerAPI {

	public static Map<String, Object> createCustomer() {
		
		Map<String, Object> responseCreation = null;
		
		try {
			responseCreation = Moip.API.customers().create(generateCustomerBody(), APIHelper.setup);
			Logger.getGlobal().info("====> Create Customer: " + responseCreation + "\n");				
		    
		} catch(UnauthorizedException e) {
			Logger.getGlobal().severe(e.toString());
		} catch(UnexpectedException e) {
			Logger.getGlobal().severe(e.toString());
		} catch(ValidationException e) {
			Logger.getGlobal().severe(e.toString());
			Logger.getGlobal().severe(">>>> Status Code: " + e.getResponseCode() + " " + e.getResponseStatus());
		}
		return responseCreation;
	}
	
	public static Object generateOwnId() {
        return ("my_customer_" + RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(10)).toLowerCase();
	}
	
	public static Map<String, Object> generateCustomerBody(){
		
		Map<String, Object> taxDocument = payloadFactory(
				        value("type", "CPF"),
				        value("number", "10013390023"));

		Map<String, Object> phone = payloadFactory(
				        value("countryCode", "55"),
				        value("areaCode", "11"),
				        value("number", "22226842"));

		Map<String, Object> shippingAddress = payloadFactory(
				        value("city", "Sao Paulo"),
				        value("district", "Itaim BiBi"),
				        value("street", "Av. Brigadeiro Faria Lima"),
				        value("streetNumber", "3064"),
				        value("state", "SP"),
				        value("country", "BRA"),
				        value("zipCode", "01451001"));

		Map<String, Object> customerRequestBody = payloadFactory(
				        value("ownId", generateOwnId()),
				        value("fullname", "Marcio Test Moip da Silva"),
				        value("email", "marcio.test.moip@mail.com"),
				        value("birthDate", "1980-11-10"),
				        value("taxDocument", taxDocument),
				        value("phone", phone),
				        value("shippingAddress", shippingAddress));
		
		return customerRequestBody;
	}
}
