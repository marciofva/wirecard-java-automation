package com.wirecard.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderMenuPage extends BasePage {

	public OrderMenuPage(WebDriver driver) {
		super(driver);
	}

    @FindBy(css = "input[placeholder='Buscar por pedido ou comprador']")
    private WebElement searchOrderIdInput;
    
    @FindBy(css = "#order-search-filters > label > a.btn-moip-blue.btn-search.top > span")
    private WebElement searchButton;
    
    @FindBy(css = "a.joyride-close-tip")
    private List<WebElement> closeButton;
    
    @FindBy(css = "div[data-index='0']")
    private WebElement modal;
    
    @FindBy(id = "entries-rows")
    private WebElement indic;
    
    @FindBy(css = "a[data-content='Acessar detalhes do pedido']")
    private WebElement tableOrderId;
    
    @FindBy(css = "#entries-rows > tr:nth-child(1) > td:nth-child(2) > div > div:nth-child(2) > span")
    private WebElement tableCustomer;
    
    @FindBy(css = "#entries-rows > tr:nth-child(1) > td:nth-child(5) > span")
    private WebElement tableTotalPrice;
   
    public OrderMenuPage submitSearch(String orderId) throws InterruptedException {
    	searchForOrder(orderId);
    	return this;
    }
      
    public void searchForOrder(String orderId) {
    	closeModal();
    	sendValue(searchOrderIdInput, orderId);
    	clickOn(searchButton);
    }
    
    public void closeModal() {
    	waitForElementVisibility(modal);
    	clickOn(closeButton.get(0));
    	scrollUp();
    }
    
    public OrderDetailsPage goToOrderDetails(String orderId) throws InterruptedException {
    	searchForOrder(orderId);
    	scrollDown();
    	Thread.sleep(2000);
    	waitForElementVisibility(tableOrderId);
    	clickOn(tableOrderId);
    	closeModal();
    	return new OrderDetailsPage(driver);
    }
    
    public String getOrderId() {
    	return tableOrderId.getText();
    }
    
    public String getFullName() {
    	return tableCustomer.getText().split("\n")[0];
    }
    
    public String getEmail() {
    	return tableCustomer.getText().split("\n")[1];
    }
    
    public int getTotalPrice() {
    	String price = tableTotalPrice.getText().split(" ")[1];
	    price = price.replace(",", "");
	    price = price.replace(".", "");
    	return Integer.parseInt(price);
    }
}
