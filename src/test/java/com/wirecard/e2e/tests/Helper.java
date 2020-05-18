package com.wirecard.e2e.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.wirecard.annotations.CreateOrder;
import com.wirecard.annotations.CreatePayment;
import com.wirecard.api.APIHelper;

public abstract class Helper {
	
	protected Map<String, Object> customerCreation = null;
	protected Map<String, Object> orderCreation = null;
	protected Map<String, Object> paymentCreation = null;
	
	public boolean isCreatePayment() {     
		return getClass().getMethods()[0].isAnnotationPresent(CreatePayment.class);
	}
	
	public boolean isCreateOrder() {
		return getClass().getMethods()[0].isAnnotationPresent(CreateOrder.class);
	}
	
	protected void createAPIResource() {
		
		APIHelper.setUpAPI();
		
		if(isCreatePayment()) {
			createCustomer();
			createOrder(customerCreation.get("id"));
			createPaymemt(orderCreation.get("id"));
		}
		if(isCreateOrder()) {
			createCustomer();
			createOrder(customerCreation.get("id"));
		}
	}
	
	protected void createCustomer() {
		customerCreation = APIHelper.createCustomer();
	}
	
	protected void createOrder(Object customerId) {
		orderCreation = APIHelper.createOrder(customerId);
	}
	
	protected void createPaymemt(Object orderId) {
		paymentCreation = APIHelper.createPaymemt(orderId);
	}
	
	public static List<Object> getListOfProductItems(List<Map<String, Object>> itemsProd){
		
		List<Object> productList = new ArrayList<Object>();
						 
		  for (Map<String, Object> map : itemsProd) {
			    for (Map.Entry<String, Object> entry : map.entrySet()) {
			        if(entry.getKey().equals("product")) {
			        	productList.add(entry.getValue());
			        }
			        if(entry.getKey().equals("price")) {
			        	productList.add(entry.getValue());
			        }
			        if(entry.getKey().equals("quantity")) {
			        	productList.add(entry.getValue());
			        }
			    }
			}
		return productList;
	}
}
