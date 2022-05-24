package com.org.tests.vplxLanding;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1086456 extends BaseTest {

	@Test(priority = 1, description = "VPLX:Landing Page: [UI]:User is able to see the landing page when user login from valid credentials")
	public void Test01_1128290(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Landing Page: [UI]:User is able to see the landing page when user login from valid credentials");
		test.landingPageActions.navigateToFeature("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	}

	@Test(priority = 2, description = "VPLX:Landing Page: [UI]:User is able to see the landing page icons and links as per the user permissions")
	public void Test02_1128302(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Landing Page: [UI]:User is able to see the landing page icons and links as per the user permissions");
		test.landingPageActions.navigateToFeature("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}

	@Test(priority = 3, description = "VPLX:Landing Page: [UI]:User is able to see the landing page links under Settings used across your Health System")
	public void Test03_1128282(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Landing Page: [UI]:User is able to see the landing page links under Settings used across your Health System");
		test.landingPageActions.navigateToFeature("Printers");
	}

	@Test(priority = 4, description = "VPLX:Online help:[UI]:User is able to see a help icon on the landing page ")
	public void Test04_1108916(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Online help:[UI]:User is able to see a help icon on the landing page ");
		test.landingPageActions.navigateToFeature("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		Assert.assertTrue(test.landingPageActions.helpIconAvailabe(), "Help icon is not available on landing page.");

	}

	@Test(priority = 5, description = "VPLX:Landing Page: [UI]:User is able to see the landing page icons on login")
	public void Test05_1128278(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Landing Page: [UI]:User is able to see the landing page icons on login");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Computers");
	}

	@Test(priority = 6, description = "Case VPLX:Landing Page: [UI]:User is able to see the landing page links under Settings per facility ")
	public void Test06_1128283(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"Case VPLX:Landing Page: [UI]:User is able to see the landing page links under Settings per facility");
		test.landingPageActions.navigateToFeature("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Destinations");
	}

}
