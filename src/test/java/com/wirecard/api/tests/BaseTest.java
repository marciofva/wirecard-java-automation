package com.wirecard.api.tests;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import com.wirecard.api.AuthenticationCredentials;
import com.wirecard.api.BasePath;
import com.wirecard.api.CustomerAPI;
import com.wirecard.api.OrderAPI;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;
import static io.restassured.RestAssured.given;
import java.util.Map;

public class BaseTest  extends Helper {
	
	protected Object customerId = null;
	protected Object orderId = null; 
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = Configuration.getUri();
		enableLoggingOfRequestAndResponseIfValidationFails();
	}
	
	@BeforeGroups("order")
	public void preRequisitesForOrder() {
		customerId = generateCustomerId();
	}
	
	@BeforeGroups("payment")
	public void preRequisitesForPayment() {
		orderId = generateOrderId();
	}
	
	public Object generateCustomerId() {

		Map<String, Object> customerMap = CustomerAPI.generateCustomerBody();
		
		Object customerId =
			given()
				.contentType(ContentType.JSON)
				.auth()
				.preemptive()
				.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
				.body(convertMapToJson(customerMap))
			.when()
				.post(BasePath.CUSTOMER.getValue())
			.then()
				.statusCode(HttpStatus.SC_CREATED)
				.extract()
				.path("id");

		return customerId;
	}
	
	public Object generateOrderId() {

		Map<String, Object> orderMap = OrderAPI.generateOrderBody(generateCustomerId());
		
		Object orderId =
			given()
				.contentType(ContentType.JSON)
				.auth()
				.preemptive()
				.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
				.body(convertMapToJson(orderMap))
			.when()
				.post(BasePath.ORDERS.getValue())
			.then()
				.statusCode(HttpStatus.SC_CREATED)
				.extract()
				.path("id");

		return orderId;
	}
}
