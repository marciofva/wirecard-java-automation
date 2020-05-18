package com.wirecard.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

    @FindBy(name = "login")
    private WebElement emailInput;
    
    @FindBy(name = "password")
    private WebElement passwordInput;
    
    @FindBy(css = "button[type='submit']")
    private WebElement submitBtn;
    
    @FindBy(css = "#modal-verificate-phone > div.modal-header > button")
    private WebElement modalPhone;
    
    public HomePage submitLogin(String email, String password) {
    	sendValue(emailInput, email);
    	sendValue(passwordInput, password);
    	clickOn(submitBtn);
    	checkPhoneModal();
    	return new HomePage(driver);
    }
    
    public void checkPhoneModal() {
    	if (isVisible(modalPhone)) {
    		clickOn(modalPhone);
    	}
    }
}
