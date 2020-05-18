package com.wirecard.api;

import br.com.moip.auth.Authentication;
import br.com.moip.auth.BasicAuth;
import br.com.moip.models.Setup;
import java.util.Map;

public class APIHelper {
	
	public static Authentication auth = null;
	public static Setup setup = null;

	public static void setUpAPI() {
		auth = new BasicAuth(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue());
		setup = new Setup().setAuthentication(auth).setEnvironment(Setup.Environment.SANDBOX);
	}

	public static Map<String, Object> createCustomer() {
		return CustomerAPI.createCustomer();
	}

	public static Map<String, Object> createOrder(Object customerId) {
		return OrderAPI.createOrder(customerId);
	}

	public static Map<String, Object> createPaymemt(Object orderId) {
		return PaymentAPI.createPaymemt(orderId);
	}
}
