package com.org.automation.getpageobjects;

import static com.org.automation.utils.NetworkLogger.printLog;
import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.extentmanagers.ExtentTestManager;

public class BaseTest {

	public TestSessionInitiator test;
	String app_url;

	public static String testName;

	public WebDriver driver;

	public WebDriver getDriver() {
		this.driver = test.getDriver();
		return driver;
	}

	@BeforeClass
	public void Open_Browser_Window() {
		System.out.println(this.getClass().getSimpleName());
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		try {
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
					getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
			test.landingPageActions.navigateToMenu("Main Menu");
			Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		} catch (Exception e) {
			test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
					getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
			test.landingPageActions.navigateToMenu("Main Menu");
			Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		}
	}

	@BeforeMethod(alwaysRun = true)
	public void start_extent_test(Method method) {
		ExtentTestManager.startTest(method.getName(), method.getAnnotation(Test.class).description());

	}

	@AfterMethod(alwaysRun = true)
	public void take_screenshot_on_failure(ITestResult result) throws IOException {
		test.takescreenshot.takeScreenShotOnException(result);
		// testName=result.getMethod().toString();
		// printLog(getDriver(),testName);
	}

	@AfterClass(alwaysRun = true)
	public void Close_Browser_Session() {
		//test.closeBrowserSession();
	}

}