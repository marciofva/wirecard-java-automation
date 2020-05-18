package com.wirecard.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wirecard.api.AuthenticationCredentials;
import com.wirecard.api.BasePath;
import com.wirecard.api.PaymentAPI;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;

@Epic("API Testing")
@Listeners({com.wirecard.listener.ListenerForAPI.class})
public class PaymentAPITest extends BaseTest {

	
	@Test(groups= "payment")
	public void createNewPayment()  {
				
		Map<String, Object> paymentMap = PaymentAPI.generatePaymentBody();
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(paymentMap))
    	.when()
			.post(BasePath.ORDERS.getValue() + "/{orderId}" + BasePath.PAYMENTS.getValue(), orderId)
		.then()
			.statusCode(HttpStatus.SC_CREATED);
	}
	

	@Test(groups= "payment")
	public void invalidTokenAuthentication()  {
				
		Map<String, Object> paymentMap = PaymentAPI.generatePaymentBody();
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic("MJK96F43D", AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(paymentMap))
    	.when()
			.post(BasePath.ORDERS.getValue() + "/{orderId}" + BasePath.PAYMENTS.getValue(), orderId)
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	

	@Test(groups= "payment")
	public void invalidKeyAuthentication()  {
				
		Map<String, Object> paymentMap = PaymentAPI.generatePaymentBody();
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), "KJH564FGD")
			.body(convertMapToJson(paymentMap))
    	.when()
			.post(BasePath.ORDERS.getValue() + "/{orderId}" + BasePath.PAYMENTS.getValue(), orderId)
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
	
	@Test(groups= "payment")
	public void passingNullBody()  {
				
		Map<String, Object> paymentMap = null;
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(paymentMap))
    	.when()
			.post(BasePath.ORDERS.getValue() + "/{orderId}" + BasePath.PAYMENTS.getValue(), orderId)
		.then()
			.statusCode(HttpStatus.SC_BAD_REQUEST)
			.body("errors[0].description", equalTo("{payment.fundinginstrument.notnull}"));
	}
	
	
	@Test(groups= "payment")
	public void orderIdNotFound()  {
				
		Map<String, Object> paymentMap = PaymentAPI.generatePaymentBody();
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(paymentMap))
    	.when()
			.post(BasePath.ORDERS.getValue() + "/{orderId}" + BasePath.PAYMENTS.getValue(), (orderId+"ABCD"))
		.then()
			.statusCode(HttpStatus.SC_NOT_FOUND)
			.body("error", equalTo("resource not found"));
	}
}
