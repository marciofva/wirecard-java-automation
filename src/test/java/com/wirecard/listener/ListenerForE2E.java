package com.wirecard.listener;

import java.util.logging.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.wirecard.utils.BrowserFactory;
import io.qameta.allure.Attachment;

public class ListenerForE2E implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		Logger.getGlobal().info("===================     Start Test < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Logger.getGlobal().info("===================     Test Passed  < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Logger.getGlobal().severe("===================     Test failed  < " + result.getMethod().getMethodName() + " > ===================\n");

		saveScreenshot();
		saveTextLog(getMethodName(result) + " Failed and screenshot taken");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Logger.getGlobal().warning("===================     Test Skipped  < " + result.getMethod().getMethodName() + " > ===================\n");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	@Override
	public void onStart(ITestContext context) {
		Logger.getGlobal().info("===================     Start Suite  < " + context.getName() + " > ===================\n");
	}

	@Override
	public void onFinish(ITestContext context) {
		Logger.getGlobal().info("===================     Finish Suite  < " + context.getName() + " > ===================\n");
	}

	@Attachment(value = "Screenshot", type = "image/png")
	private byte[] saveScreenshot() {
		return ((TakesScreenshot) BrowserFactory.getDriver()).getScreenshotAs(OutputType.BYTES);
	}

	@Attachment(value = "Log", type = "text/plain")
	private String saveTextLog(String message) {
		return message;
	}

	private String getMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}
}
