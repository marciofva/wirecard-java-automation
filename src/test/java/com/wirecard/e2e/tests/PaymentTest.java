package com.wirecard.e2e.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.wirecard.annotations.CreatePayment;
import com.wirecard.pages.OrderMenuPage;
import com.wirecard.utils.BrowserFactory;
import com.wirecard.utils.Menu;
import io.qameta.allure.Epic;

@Epic("E2E Testing")
@Listeners({com.wirecard.listener.ListenerForE2E.class})
public class PaymentTest extends BaseTests {


  @Test
  @CreatePayment
  public void searchForOrder() throws InterruptedException {

	  home.goToMenuOption(Menu.ORDERS.getValue());
	  
	  order = new OrderMenuPage(BrowserFactory.getDriver()).submitSearch(orderCreation.get("ownId").toString());

	  //Check OrderId
	  assertEquals(order.getOrderId(), orderCreation.get("ownId"));
	  
	  //Check FullName
	  assertTrue(order.getFullName().equalsIgnoreCase(((Map<String, Object>) orderCreation.get("customer")).get("fullname").toString()));	  
	  
	  //Check Email
	  assertTrue(order.getEmail().equalsIgnoreCase(((Map<String, Object>) orderCreation.get("customer")).get("email").toString()));
	  
	  //Check Total Price
	  assertEquals(order.getTotalPrice(), ((Map<String, Object>) orderCreation.get("amount")).get("total"));
  }
 
 
  @Test
  @CreatePayment
  public void validatePaymentOrderDetails() throws InterruptedException {
	  
	  home.goToMenuOption(Menu.ORDERS.getValue());
	  
	  orderDetails = new OrderMenuPage(BrowserFactory.getDriver()).goToOrderDetails(orderCreation.get("ownId").toString());  
	  	  
	  //Check Order
	  assertEquals(orderDetails.getOrderId(), orderCreation.get("ownId"));
	  
	  assertEquals(orderDetails.getWirecardId(), orderCreation.get("id"));
	  
	  
	  //Check Product Description
	  Map<String, Object> fundingInstrument = ((Map<String, Object>) paymentCreation.get("fundingInstrument"));
	  assertEquals(orderDetails.getCreditCardLastDigits(), Integer.parseInt(((Map<String, Object>) fundingInstrument.get("creditCard")).get("last4").toString()));
	  
	  assertEquals(orderDetails.getTotalPrice(), ((Map<String, Object>) paymentCreation.get("amount")).get("total"));

	  assertTrue(orderDetails.getFullNameOrder().equalsIgnoreCase(((Map<String, Object>) orderCreation.get("customer")).get("fullname").toString()));	  

	  List<Map<String, Object>> itemsProd = (List<Map<String, Object>>) orderCreation.get("items");
	  assertEquals(orderDetails.getAllProducts(), getListOfProductItems(itemsProd));
	  assertEquals(orderDetails.countOfProducts(), itemsProd.size());
	  
	  
	  //Check Financial Summary
	  assertEquals(orderDetails.getSummaryTotalPrice(), ((Map<String, Object>) paymentCreation.get("amount")).get("total"));
	  
	  
	  //Check Customer
	  assertTrue(orderDetails.getFullNameCustomer().equalsIgnoreCase(((Map<String, Object>) orderCreation.get("customer")).get("fullname").toString()));	  
	  assertTrue(orderDetails.getEmailCustomer().equalsIgnoreCase(((Map<String, Object>) orderCreation.get("customer")).get("email").toString()));	  
	  
	  Map<String, Object> taxDocument = ((Map<String, Object>) orderCreation.get("customer"));
	  assertEquals(orderDetails.getDocumentNumber(), ((Map<String, Object>) taxDocument.get("taxDocument")).get("number"));	  

	  
	  //Check Order Payment
	  assertEquals(orderDetails.getPaymentOrder(), paymentCreation.get("id"));	  
	  assertEquals(orderDetails.getInstallCount(), paymentCreation.get("installmentCount"));	  
  }
}
