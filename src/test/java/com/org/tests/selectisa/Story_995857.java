package com.org.tests.selectisa;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_995857 extends BaseTest {

	
	@Test(priority = 1, description = "VPLX: Select ISAs: [UI]: To verify that All the  ISAs which are mapped"
			+ " to the facility to which computer is logged in are displayed  on Select ISA Screen")
	public void Test01_1018273(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs : All the static ISAs,which are mapped to particular Facility and Workstation are displayed on the UI");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName")));
		Assert.assertTrue(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName2")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5")));
		Assert.assertFalse(test.storageAreaAction.isCheckboxSelected(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
		
	}

	@Test(priority = 2, description = "VPLX: Select ISAs: [UI]: To verify that The manual use slider "
			+ "is not displayed for static ISAs")
	public void Test02_1018274(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:The manual use slider is not present for static ISAs");
		
		test.storageAreaAction.verifyManualUseOptionForISANotAvailable(
				TestDataPropertyReaderAndWriter.getProperty("ShortName"));
		test.storageAreaAction.verifyManualUseOptionForISANotAvailable(
				TestDataPropertyReaderAndWriter.getProperty("ShortName2"));
		test.storageAreaAction.verifyManualUseOptionForISANotAvailable(
				TestDataPropertyReaderAndWriter.getProperty("ShortName5"));
	}
	

	@Test(priority = 3, description = "VPLX: Select ISAs: [UI]: To verify that The non static ISAs "
			+ "have slider for manual use,which when selected allows user to work offline on non-static ISA")
	public void Test03_1018276(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs:The non static ISAs have slider for manual use,which when selected allows user to work offline on non-static ISA");
		
		test.supportDataActions.clickOnCancel("Cancel");
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(
				TestDataPropertyReaderAndWriter.getProperty("IPAddress2").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
		
	}
	
	
	@Test(priority = 4, description = "VPLX: Select ISAs: [UI]: To verify that The manual use slider is enabled "
			+ "for non-static ISA in case carousel is not reachable")
	public void Test04_1018280(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Select ISAs : The manual use slider is enabled for non-static ISA in case carousel is not reachable");
		
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName3")));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName4")));
		Assert.assertTrue(test.storageAreaAction.verifyUserIsAbleToSelectManualUseOptionForISA(
				TestDataPropertyReaderAndWriter.getProperty("ShortName6")));
		
		test.storageAreaAction.clickOnStartWorkButton();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}

}
