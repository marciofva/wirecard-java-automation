package com.wirecard.pages;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderDetailsPage extends BasePage {

	public OrderDetailsPage(WebDriver driver) {
		super(driver);
	}
	
    @FindBy(css = "div[data-index='0']")
    private WebElement modal;
    
    @FindBy(css = "h1 > span")
    private WebElement orderIdLabel;
    
    @FindBy(css = ".row-fluid.order-h-id > span")
    private WebElement wirecardIdLabel;
    
    @FindBy(css = ".span3.label-detail")
    private List<WebElement> creditCardLabel;
    
    @FindBy(css = ".well > ul > li")
    private List<WebElement> productItemList;
    
    @FindBy(css = "#amount-detail > bdi")
    private List<WebElement> financialSummaryList;
    
    @FindBy(css = ".label-detail.blue.thin.info.span8")
    private WebElement customerDataLable;
    
    @FindBy(css = ".well > div > div:nth-child(1) > p:nth-child(6)")
    private WebElement documentNumberLabel;
    
    @FindBy(css = ".table.table-bordered.table-advance.order-payments.table-disable-hover > tbody > tr > td")
    private List<WebElement> paymentOrderList;

    public String getOrderId() {
    	return orderIdLabel.getText().trim();
    }

    public String getWirecardId() {
    	return wirecardIdLabel.getText().trim();
    }

    public int getCreditCardLastDigits() {
    	return Integer.parseInt((creditCardLabel.get(0).getText().split("\n")[0].trim()).split(" ")[1].trim());
    }

    public int getTotalPrice() {
    	String price = creditCardLabel.get(0).getText().split("\n")[1].trim();
	    price = price.replace(",", "");
	    price = price.replace(".", "");
    	return Integer.parseInt(price);
    }

    public String getFullNameOrder() {
    	return creditCardLabel.get(2).getText().split("\n")[1].trim();
    }

    public String getProductName(WebElement element) {
    	String productName = "";
    	productName = element.getText();
    	productName = productName.split("R")[0];
    	productName = productName.trim();
    	return productName;
    }
    
    public int getProductUnitPrice(WebElement element) {
    	String price = "";
    	price = element.getText();
    	price = price.split("R")[1];
    	price = price.replace("$", "");
    	price = price.trim();
    	price = price.split(" ")[0];
	    price = price.replace(",", "");
	    price = price.replace(".", "");
    	return Integer.parseInt(price);
    }
    
    public int getCountOfProduct(WebElement element) {
    	String unit = "";
    	unit = element.getText();
    	unit = unit.split("R")[1];
    	unit = unit.split(" ")[2];
    	unit = unit.replace("(", "");
    	return Integer.parseInt(unit); 
    }

    public List<Object> getAllProducts() {
    	List<Object> productList = new ArrayList<Object>();
    	for(WebElement element : productItemList) {
    		productList.add(getProductName(element));
    		productList.add(getProductUnitPrice(element));
    		productList.add(getCountOfProduct(element));
    	}
    	return productList;
    }

    public int countOfProducts() {
    	return productItemList.size();
    }
    
    public int getSummaryTotalPrice() {
    	String price = "";
    	price = financialSummaryList.get(0).getAttribute("innerText");
	    price = price.replace("R", "");
	    price = price.replace("$", "");
	    price = price.replace(",", "");
	    price = price.replace(".", "");
	    price = price.trim();
    	return Integer.parseInt(price);
    }
    
    public String getFullNameCustomer() {
    	scrollIntoElement(customerDataLable);
    	return customerDataLable.getText().split("\n")[0].trim();
    }
    
    public Object getDocumentNumber() {
    	return documentNumberLabel.getText().replace(".", "").replace("-", "").replace("/", "").trim();
    }
    
    public String getEmailCustomer() {
    	return customerDataLable.getText().split("\n")[1].trim();
    }
    
    public String getPaymentOrder() {
    	scrolltoBottom();
    	return paymentOrderList.get(0).getAttribute("innerText");
    }
    
    public int getInstallCount() {
    	return Integer.parseInt(paymentOrderList.get(3).getAttribute("innerText"));
    }
}
