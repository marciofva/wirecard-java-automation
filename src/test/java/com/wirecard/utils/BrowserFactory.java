package com.wirecard.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

public class BrowserFactory {
	
	private static WebDriver driver;
	
	public static WebDriver createBrowserInstance(String browser) throws IllegalAccessException {		
		WebDriver driver = null;
			
		switch (Browser.valueOf(browser.toUpperCase())) {
			case CHROME:
				System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
				System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
	    		
	    		ChromeOptions chromeOptions = new ChromeOptions();
	            chromeOptions.addArguments("--ignore-certificate-errors");
	            chromeOptions.addArguments("--disable-popup-blocking");
	            driver = new ChromeDriver(chromeOptions);
	            break;
			case FIREFOX:
		        FirefoxOptions options = new FirefoxOptions();
		        FirefoxProfile profile = new FirefoxProfile();
		        profile.setAcceptUntrustedCertificates(true);
		        profile.setAssumeUntrustedCertificateIssuer(false);
		        options.setCapability(FirefoxDriver.PROFILE, profile);
				driver = new FirefoxDriver(options);
				break;
			default:
				throw new IllegalAccessException("Browser '" + browser + "' is invalid");
		}
		return driver;
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	public static void setDriver(WebDriver driver) {
		BrowserFactory.driver = driver;
	}
}
