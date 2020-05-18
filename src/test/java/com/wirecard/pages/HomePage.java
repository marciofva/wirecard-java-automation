package com.wirecard.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
    @FindBy(css = "ul.nav > li > a")
    private List<WebElement> menuOptions;
    
    public void goToMenuOption(String option) {
    	
    	for(WebElement element : menuOptions) {
    		if(element.getText().trim().equalsIgnoreCase(option)) {
    			clickOn(element);
    			break;
    		}
    	}
    }
}
