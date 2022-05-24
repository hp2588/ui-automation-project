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

public class Select_ISA_Hitesh extends BaseTest {

	String app_url;

	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same when user clicks on the same view button,which is currently being displayed on Select ISA "
			+ "VPLX: Select ISAs: [UI]:ISAs having any computer as the Default or Approved computer must be pre-selected when a user opens the Select ISA screen using that computer.")
	public void Test01_1152601(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same when user clicks on the same view button,which is currently being displayed on Select ISA"
						+ "VPLX: Select ISAs: [UI]:ISAs having any computer as the Default or Approved computer must be pre-selected when a user opens the Select ISA screen using that computer.");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		// test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"),
		// 0); -- step to select isa
		test.storageAreaAction.switchToGridViewOfISA();
		Assert.assertTrue(test.storageAreaAction.verifyCheckboxSelectedForISA(0));
	}

	@Test(priority = 2, description = "VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same"
			+ " when user clicks on the same view button,which is currently being displayed on Select ISA screen")
	public void Test02_1032666(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				"VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same when user "
						+ "clicks on the same view button,which is currently being displayed on Select ISA screen");

		// by default, grid view is selected
		// click again on grid view button and make sure it remains in focus
		test.storageAreaAction.switchToGridViewOfISA();
		// make sure ISA Cards are visible
		Assert.assertNotEquals(test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(), 0);

		// switch to list view
		test.storageAreaAction.switchToListViewOfISA();
		// make sure ISA row is there
		Assert.assertNotEquals(test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0);
		// click again on list view button and make sure it remains in focus
		test.storageAreaAction.switchToListViewOfISA();
		Assert.assertNotEquals(test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0);
		// switch back to grid view
//		test.storageAreaAction.switchToGridViewOfISA();
	}

	@Test(priority = 2, description = "VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs")
	public void Test02_1152530(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs");

		// test.landingPageActions.navigateToMenu("Transaction Queue");
		// test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage();
	}

	@Test(priority = 3, description = "VPLX: Select ISAs: [UI]:The carousel ISA(having default computer) are preselected when logged in from default computer"
			+ "VPLX: Select ISAs: [UI]:The static ISAs(having default computer) are available to take control and not selected when logged in from a non-Default Computer")
	public void Test03_1153195_1153444_1153191(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs"
						+ "VPLX: Select ISAs: [UI]:The static ISAs(having default computer) are available to take control and not selected when logged in from a non-Default Computer");
		test.supportDataActions.clickOnCancel("Cancel");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress2").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		Assert.assertTrue(test.storageAreaAction.verifyCheckboxSelectedForISA(2),
				"[ASSERTION FAILED]: Static ISA is not selected and available to tke control");
		Assert.assertFalse(test.storageAreaAction.verifyCheckboxSelectedForISA(1),
				"[ASSERTION PASSED]: Static ISA is not selected and available to tke control");
	}

	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs")
	public void Test4_1019072(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs");

		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		// login with same admin user again and access TQ via computer having control
		// carousal flag checked

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
		Assert.assertTrue(
				test.storageAreaAction.verifyCarousalISAIsDisabledInListView(
						TestDataPropertyReaderAndWriter.getProperty("ShortName3")),
				"[ASSERTION FAILED]: Carousal ISA is available for control but should not be as it is already in control from different computer");

	}
}
