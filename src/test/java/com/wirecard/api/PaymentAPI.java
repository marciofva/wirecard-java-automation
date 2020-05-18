package com.wirecard.api;

import static br.com.moip.helpers.PayloadFactory.payloadFactory;
import static br.com.moip.helpers.PayloadFactory.value;
import java.util.Map;
import java.util.logging.Logger;
import br.com.moip.Moip;
import br.com.moip.exception.UnauthorizedException;
import br.com.moip.exception.UnexpectedException;
import br.com.moip.exception.ValidationException;

public class PaymentAPI {
	
	public static Map<String, Object> createPaymemt(Object orderId) {

		Map<String, Object> newPay = null;

		try {
			newPay = Moip.API.payments().pay(generatePaymentBody(), orderId.toString(), APIHelper.setup);
			Logger.getGlobal().info("====> Create Payment: " + newPay + "\n");
		} catch(UnauthorizedException e) {
			Logger.getGlobal().severe(e.toString());
		} catch(UnexpectedException e) {
			Logger.getGlobal().severe(e.toString());
		} catch(ValidationException e) {
			Logger.getGlobal().severe(e.toString());
			Logger.getGlobal().severe(">>>> Status Code: " + e.getResponseCode() + " " + e.getResponseStatus());
		}
		return newPay;
	}
	
	public static Map<String, Object> generatePaymentBody(){
		
		Map<String, Object> taxDocument = payloadFactory(
				        value("type", "CPF"),
				        value("number", "33333333333"));

		Map<String, Object> phone = payloadFactory(
				        value("countryCode", "55"),
				        value("areaCode", "11"),
				        value("number", "66778899"));

		Map<String, Object> holder = payloadFactory(
				        value("fullname", "Portador Teste Moip"),
				        value("birthdate", "1988-12-30"),
				        value("taxDocument", taxDocument),
				        value("phone", phone));

		Map<String, Object> creditCard = payloadFactory(
				        value("number", "5555666677778884"),
				        value("expirationMonth", "06"),
				        value("expirationYear", "2022"),
				        value("cvc", "123"),
				        value("store", true),
				        value("holder", holder));

		Map<String, Object> fundingInstrument = payloadFactory(
				        value("method", "CREDIT_CARD"),
				        value("creditCard", creditCard));

		Map<String, Object> payment = payloadFactory(
				        value("installmentCount", 1),
				        value("statementDescriptor", "minhaLoja.com"),
				        value("fundingInstrument", fundingInstrument));

		return payment;
	}
}
