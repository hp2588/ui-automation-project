package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class Story_985353_2 extends BaseTest {
	String app_url;
	
	
	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]:ISAs having any computer as the Default or Approved computer "
			+ "must be pre-selected when a user opens the Select ISA screen using that computer.")
	public void Test01_1152601(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]:ISAs having any computer as the Default or Approved computer "
				+ "must be pre-selected when a user opens the Select ISA screen using that computer.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName") 
				+ "is not selected by default");
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName2")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName2") 
				+ "is not selected by default");
	}
	
	
	@Test(priority = 2, description="VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same"
			+ " when user clicks on the same view button,which is currently being displayed on Select ISA screen")
	public void Test02_1032666(Method method) {
		ExtentTestManager.startTest(getClass().getName() + "::" + method.getName(),
				"VPLX: Select ISAs: [UI]: The focus of view button(Grid/List) remains the same when user "
				+ "clicks on the same view button,which is currently being displayed on Select ISA screen");
		
		// by default, grid view is selected 
		Assert.assertTrue(test.storageAreaAction.getDivById("tile").getAttribute("class").contains("selected"),
				"[ASSERTION FAILED]: Grid view buttton is not in focus, that is, it doesn't have class 'selected'");
		Assert.assertNotEquals( test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(), 0);
		
		// click again on grid view button and make sure it remains in focus
		test.storageAreaAction.switchToGridViewOfISA();
		Assert.assertTrue(test.storageAreaAction.getDivById("tile").getAttribute("class").contains("selected"),
				"[ASSERTION FAILED]: Grid view buttton is not in focus, that is, it doesn't have class 'selected'");
		Assert.assertNotEquals(test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(), 0);
		
		// switch to list view
		test.storageAreaAction.switchToListViewOfISA();
		Assert.assertTrue(test.storageAreaAction.getDivById("list").getAttribute("class").contains("selected"),
				"[ASSERTION FAILED]: List view buttton is not in focus, that is, it doesn't have class 'selected'");
		// make sure ISA rows is there
		Assert.assertNotEquals(test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0);
		
		// click again on list view button and make sure it remains in focus
		test.storageAreaAction.switchToListViewOfISA();
		Assert.assertTrue(test.storageAreaAction.getDivById("list").getAttribute("class").contains("selected"),
				"[ASSERTION FAILED]: List view buttton is not in focus, that is, it doesn't have class 'selected'");
		Assert.assertNotEquals(test.storageAreaAction.verifyTableOfAvailableISAsOnStorageAreaPage(), 0);
		
		// switch back to grid view
		test.storageAreaAction.switchToGridViewOfISA();
	}
	
	
	@Test(priority = 3,description = "VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed "
			+ "on Select ISA screen for the facility to which computer belongs")
	public void Test03_1152530(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs");
		
		Assert.assertEquals(test.storageAreaAction.verifyListOfAvailableISAsOnStorageAreaPage(), 6,
				"[ASSERTION FAILED]: Expected number of ISA are 6 and the actual number is not same");
		
		// to make sure all 6 ISAs are there, we check whether the corresponding checkboxes are selected or not
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName2")));
		
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName4")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName5")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
	}
	
	
	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]:The carousel ISA(having default computer) "
			+ "are preselected when logged in from default computer")
	public void Test04_Test05_1153195_1153444(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]: User verifies all the ISAs are displayed on Select ISA screen for the facility to which computer belongs");
		
		test.supportDataActions.clickOnCancel("Cancel");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress2").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName3")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName3")
				+ " is not enabled by default");
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName6")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName6")
				+ " is not enabled for default");
	}
	
	
	@Test(priority = 5, description = "VPLX: Select ISAs: [UI]:The static ISAs(having default computer) "
			+ "are available to take control and not selected when logged in from a non-Default Computer")
	public void Test06_1153191(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]:The static ISAs(having default computer) are available to take control and not selected when logged in from a non-Default Computer");
		
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName2")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName2")
				+ " is enabled for IP: " + TestDataPropertyReaderAndWriter.getProperty("IPAddress2"));
		
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		
		test.storageAreaAction.clickISACheckbox(TestDataPropertyReaderAndWriter.getProperty("ShortName2"));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(TestDataPropertyReaderAndWriter.getProperty("ShortName2")),
				"[ASSERTION FAILED]: Checkbox for ISA " + TestDataPropertyReaderAndWriter.getProperty("ShortName2")
				+ " is not enabled after clicking");
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}
	
	
	@Test(priority = 6,description = "VPLX: Select ISAs: [UI]:To verify that the  Manual Use toggle option "
			+ "is disabled in list view and grid view for already used or disabled carousel")
	public void Test07_1019072(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Select ISAs: [UI]:To verify that the  Manual Use toggle option is disabled in list view and grid view for already used or disabled carousel");
		
		if(test.storageAreaAction.isButtonWithTextDisplayed("Start Work")) {
			test.storageAreaAction.clickButton("Cancel");
		}
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
		
		test.storageAreaAction.verifyStartWorkButtonAndClick(); 
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		//login with same admin user again and access TQ via computer having control carousel flag checked
		
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),DateUtil.getRandomIPAddress());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress3").trim());
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertTrue(test.storageAreaAction.verifyCarousalISAIsDisabledInGridView(TestDataPropertyReaderAndWriter.getProperty("ShortName3"))
				,"[ASSERTION FAILED]: Carousal ISA is available for control but should not be as it is already in control from different computer");
		Assert.assertTrue(test.storageAreaAction.verifyCarousalISAIsDisabledInListView(TestDataPropertyReaderAndWriter.getProperty("ShortName3"))
				,"[ASSERTION FAILED]: Carousal ISA is available for control but should not be as it is already in control from different computer");
		
	}
	
}
