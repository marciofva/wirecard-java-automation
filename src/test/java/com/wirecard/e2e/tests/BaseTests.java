package com.wirecard.e2e.tests;

import java.util.logging.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import com.wirecard.dataprovider.Login;
import com.wirecard.pages.OrderMenuPage;
import com.wirecard.pages.HomePage;
import com.wirecard.pages.LoginPage;
import com.wirecard.pages.OrderDetailsPage;
import com.wirecard.utils.BrowserFactory;
import com.wirecard.utils.URL;

public class BaseTests extends Helper {
	
	protected OrderMenuPage order;
	protected OrderDetailsPage orderDetails;
	protected HomePage home;

	@BeforeMethod
	@Parameters("browser")
	public void setUp(String browser) throws IllegalAccessException {
		
		createAPIResource();
		
		Logger.getGlobal().info(">>>> Start browser: " + browser.toUpperCase());
		BrowserFactory.setDriver(BrowserFactory.createBrowserInstance(browser));
		BrowserFactory.getDriver().get(URL.BASE_URL.getValue());

		home = new LoginPage(BrowserFactory.getDriver()).submitLogin(Login.existingUserSandbox().get("email"), 
																	 Login.existingUserSandbox().get("password"));
	}
	
	@AfterMethod
	public void tearDown() {
		BrowserFactory.getDriver().quit();
	}
}
