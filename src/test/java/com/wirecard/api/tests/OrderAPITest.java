package com.wirecard.api.tests;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import java.util.Map;
import org.apache.http.HttpStatus;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wirecard.api.AuthenticationCredentials;
import com.wirecard.api.BasePath;
import com.wirecard.api.OrderAPI;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;

@Epic("API Testing")
@Listeners({com.wirecard.listener.ListenerForAPI.class})
public class OrderAPITest extends BaseTest {


	@Test(groups= "order")
	public void createNewOrder()  {
				
		Map<String, Object> orderMap = OrderAPI.generateOrderBody(customerId);
				
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
			.body("ownId", equalTo(orderMap.get("ownId")));
	}
	
	
	@Test(groups= "order")
	public void invalidTokenAuthentication()  {
				
		Map<String, Object> orderMap = OrderAPI.generateOrderBody(customerId);
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), "JHG54L08PS355F")
			.body(convertMapToJson(orderMap))
    	.when()
			.post(BasePath.ORDERS.getValue())
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
	
	@Test(groups= "order")
	public void invalidKeyAuthentication()  {
				
		Map<String, Object> orderMap = OrderAPI.generateOrderBody(customerId);
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic("HJK087BM54D", AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(orderMap))
    	.when()
			.post(BasePath.ORDERS.getValue())
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	

	@Test
	public void orderWithoutCustomer()  {
				
		Map<String, Object> orderMap = OrderAPI.generateOrderBody("");
				
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(orderMap))
    	.when()
			.post(BasePath.ORDERS.getValue())
		.then()
			.statusCode(HttpStatus.SC_BAD_REQUEST)
			.body("errors.size", is(3));
	}
}
