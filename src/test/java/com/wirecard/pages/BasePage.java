package com.wirecard.pages;

import java.util.logging.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

	protected WebDriver driver;
	protected static final int MAX_SECONDS_TIMEOUT = 40;
	protected WebDriverWait wait;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, MAX_SECONDS_TIMEOUT);
	}

	protected void clickOn(WebElement element) {
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	protected void sendValue(WebElement element, String value) {
		wait.until(ExpectedConditions.visibilityOf(element));
		element.clear();
		element.sendKeys(value);
	}

	protected boolean isVisible(WebElement element) {
		this.wait = new WebDriverWait(driver, 4);
		try {
			wait.until(ExpectedConditions.visibilityOf(element));
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	protected void waitForElementVisibility(WebElement element) {
		wait.until(ExpectedConditions.visibilityOf(element));
		if (!element.isDisplayed()) {
			Logger.getGlobal().info("Element not visible: " + element);
		}
	}

	protected void scrollIntoElement(WebElement element) {
		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
	}
	
	protected void scrollUp() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, -document.body.scrollHeight);");
	}
	
	protected void scrollDown() {
		((JavascriptExecutor)driver).executeScript("window.scrollBy(0, 300);");
	}
	
	protected void scrolltoBottom() {
		((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
	}
}
