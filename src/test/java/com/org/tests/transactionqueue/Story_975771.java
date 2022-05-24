package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.mozilla.javascript.regexp.SubString;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975771  extends BaseTest {

	@Test(priority = 1, description = "VPLX: Manual Cycle Count : [UI] Availability of Cycle Count option under the Actions tab")
	public void Test01_1034192(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manual Cycle Count : [UI] Availability of Cycle Count option under the Actions tab");
		test.landingPageActions.navigateToMenu("Transaction Queue");	
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Cycle Count");
		
	}
	
	@Test(priority = 2, description = "VPLX: Manual Cycle Count :[UI] Availability of Bin Scan Screen after Selecting the Cycle Count option.")
	public void Test02_1034193(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manual Cycle Count :[UI] Availability of Bin Scan Screen after Selecting the Cycle Count option.");
		
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountBinScanMessage());

	}
	
	@Test(priority = 3, description = "VPLX:Manual Cycle Count : [UI]: \"Scan the Bin Label To Continue...\" text is shown with Bar Code.")
	public void Test03_1039584(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"\"VPLX:Manual Cycle Count : [UI]: Scan the Bin Label To Continue.. text is shown with Bar Code.");
		
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountBinScanMessage());
		
	}
	
	@Test(priority = 4, description = "VPLX:Manual Cycle Count : [UI]:Pop should get closed on clicking on cancel button")
	public void Test04_1039600(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Cycle Count : [UI]:Pop should get closed on clicking on cancel button");
		
		test.supportDataActions.clickButton("CycleCountCancelButton");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());
		
	}
	
	@Test(priority = 5, description = "VPLX: Manual Cycle Count: [UI]: Pop up is getting closed by pressing enter or space bar")
	public void Test05_1071738(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manual Cycle Count: [UI]: Pop up is getting closed by pressing enter or space bar");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Cycle Count");
		Assert.assertTrue(test.transactionQueueActions.verifyCycleCountBinScanMessage());
		test.transactionQueueActions.pressKeyUsingAction(Keys.TAB);
		test.transactionQueueActions.pressKeyUsingAction(Keys.ENTER);
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());
		
	}

}
