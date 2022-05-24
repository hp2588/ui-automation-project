package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

// prerequisite - test data file should have support data for ES,Facility,ISA Computer, Printer

public class Select_ISA_Deepak extends BaseTest {
	String app_url;
	String window1;
	String window2;
	
	
	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen")
	public void Test01_1019057(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress3").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.verifyCarousalISAIsDisabledInGridView(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")),
				"[ASSERTION FAILED]: Carousal ISA is available for control but should not be "
				+ "as it is already in control from different computer");
	}
	
	
	@Test(priority = 2, description = "VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen")
	public void Test02_1019057(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen");

		test.storageAreaAction.clickButton("Cancel");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress2").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
	}
	
	
	// TODO - Yugal - TC need to be reviewed
	@Test(priority = 3, description = "VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen")
	public void Test03_1019057(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User clicks on cancel button on select ISA screen");
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName3"));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		
		test.storageAreaAction.clickOnStartWorkButton();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item6"));
		
		test.storageAreaAction.clickButton("Cancel");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}
	
	
	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed "
			+ "on Select ISA screen for the facility to which computer belongs")
	public void Test04_1152530(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs");
		
		if(!test.storageAreaAction.isButtonWithTextDisplayed("Start Work")) {
			test.landingPageActions.navigateToMenu("Main Menu");
			test.landingPageActions.navigateToMenu("Transaction Queue");
		}
		
		String bgColor = test.storageAreaAction.getISACardBackgroundColor(0);
		System.out.print("Background color of ISA card 0 - " + bgColor + "\n");
		Assert.assertEquals(bgColor, "rgba(204, 204, 204, 1)");
	}
	
	
	@Test(priority = 5, description = "VPLX: Select ISAs: [UI]:The carousel ISA(having default computer) are preselected when logged in from default computer"
			+ "VPLX: Select ISAs: [UI]:The static ISAs(having default computer) are available to take control and not selected when logged in from a non-Default Computer")
	public void Test05_1153195_1153444_1153191(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs"
						+ "VPLX: Select ISAs: [UI]:The static ISAs(having default computer) are available to take control and not selected when logged in from a non-Default Computer");
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		// login with same admin user again and access TQ via computer having control carousel flag checked
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), DateUtil.getRandomIPAddress());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress3").trim());
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(
				test.storageAreaAction.verifyCarousalISAIsDisabledInGridView(
						TestDataPropertyReaderAndWriter.getProperty("ShortName3")),
				"[ASSERTION FAILED]: Carousal ISA is available for control but should not be as it is already in control from different computer");
		
		// Logic for mouse over message 
		Assert.assertEquals(test.storageAreaAction.getTitleForTheISACard(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")), 
				"This carousel is in use by another active user",
				"[ASSERTION FAILED]: Mouse over message for the disabled ISA card is not verified");
	}
	
	
}
