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
import com.wirecard.api.CustomerAPI;
import io.qameta.allure.Epic;
import io.restassured.http.ContentType;

@Epic("API Testing")
@Listeners({com.wirecard.listener.ListenerForAPI.class})
public class CustomerAPITest extends BaseTest {

	
	@Test
	public void createNewCustomer()  {
		
		Map<String, Object> customerMap = CustomerAPI.generateCustomerBody();
		
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
			.body("ownId", equalTo(customerMap.get("ownId")));
	}
	

	@Test
	public void invalidTokenAuthentication()  {
		
		Map<String, Object> customerMap = CustomerAPI.generateCustomerBody();
		
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic("12345ABCD", AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(customerMap))
    	.when()
			.post(BasePath.CUSTOMER.getValue())
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	

	@Test
	public void invalidKeyAuthentication()  {
		
		Map<String, Object> customerMap = CustomerAPI.generateCustomerBody();
		
		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), "JHG454AB")
			.body(convertMapToJson(customerMap))
    	.when()
			.post(BasePath.CUSTOMER.getValue())
		.then()
			.statusCode(HttpStatus.SC_UNAUTHORIZED);
	}
	
	
	@Test
	public void passingNullBody()  {
		
		Map<String, Object> customerMap = null;

		given()
			.contentType(ContentType.JSON)
			.auth()
			.preemptive()
			.basic(AuthenticationCredentials.TOKEN.getValue(), AuthenticationCredentials.KEY.getValue())
			.body(convertMapToJson(customerMap))
    	.when()
			.post(BasePath.CUSTOMER.getValue())
		.then()
			.statusCode(HttpStatus.SC_BAD_REQUEST)
			.body("errors.size", is(3));
	}
}
