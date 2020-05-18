package com.wirecard.api;

import static br.com.moip.helpers.PayloadFactory.payloadFactory;
import static br.com.moip.helpers.PayloadFactory.value;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import org.apache.commons.lang3.RandomStringUtils;
import br.com.moip.Moip;
import br.com.moip.exception.UnauthorizedException;
import br.com.moip.exception.UnexpectedException;
import br.com.moip.exception.ValidationException;

public class OrderAPI {
	
	public static Map<String, Object> createOrder(Object customerId) {
		
		Map<String, Object> responseCreation = null;

		try {
			responseCreation = Moip.API.orders().create(generateOrderBody(customerId), APIHelper.setup);
			Logger.getGlobal().info("====> Create Order: " + responseCreation + "\n");
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
        return ("my_order_" + RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5)).toLowerCase();
	}
	
	public static Map<String, Object> generateOrderBody(Object customerId) {
		
		Map<String, Object> subtotals = payloadFactory(
				        value("shipping", 15000));

		Map<String, Object> amount = payloadFactory(
				        value("currency", "BRL"),
				        value("subtotals", subtotals));

		Map<String, Object> product1 = payloadFactory(
				        value("product", "Product 1 Description"),
				        value("category", "CLOTHING"),
				        value("quantity", 2),
				        value("detail", "Anakin's Light Saber"),
				        value("price", 1000000));

		Map<String, Object> product2 = payloadFactory(
				        value("product", "Product 2 Description"),
				        value("category", "PHOTOGRAPHY"),
				        value("quantity", 5),
				        value("detail", "Pym particles"),
				        value("price", 2450000));
		
		Map<String, Object> product3 = payloadFactory(
				        value("product", "Product 3 Description"),
				        value("category", "AUDIO"),
				        value("quantity", 1),
				        value("detail", "Pym particles"),
				        value("price", 9000));

		List items = new ArrayList();
		items.add(product1);
		items.add(product2);
		items.add(product3);

		Map<String, Object> customer = payloadFactory(
				        value("id", customerId));

		Map<String, Object> order = payloadFactory(
				        value("ownId", generateOwnId()),
				        value("amount", amount),
				        value("items", items),
				        value("customer", customer));
		
		return order;
	}
}
