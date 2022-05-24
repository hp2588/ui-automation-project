package com.org.tests.WastingAnItem;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981824 extends BaseTest {
	
	@Test(priority = 1, description = "VPLX: Transaction Queue-Cycle Count: [UI]: DB updated for Transaction for which Items are Wasted.")
	public void Test01_1014835(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.clickScanOverrideOnce();
		test.siteConfigurationAction.clickToggleButton("true", "wasteItems");
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantity","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantityReConfirm","1");
		test.siteConfigurationAction.clickButton("saveAndCloseButton");
		
	}

}
