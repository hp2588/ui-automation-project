package com.org.tests.WastingAnItem;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_WastingAnItemCycleCount extends BaseTest {
	
	@Test(priority = 1, description = "VPLX: Transaction Queue-Cycle Count: [UI]: UI for Waste Item in Pick Queue with type Cycle Count")
	public void Test01_1014755(Method method) {
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
		test.transactionQueueActions.verifyInputfield("quantityWasted");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
	}

	@Test(priority = 2, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item through of type cycle count when Quantity Wasted is greater than Quantity on hand")
	public void Test02_1014840(Method method) {
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantity","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantityReConfirm","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","10000");
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.siteConfigurationAction.clickButton("saveAndCloseButton");
		test.siteConfigurationAction.verifySuccessMessageOnAddFacility("Waste quantity cannot be greater than on hand quantity.");	
	}
	

	@Test(priority = 3, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item without providing any value for Quantity Wasted")
	public void Test03_1014852(Method method) {
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","10000");
		test.siteConfigurationAction.clearInputBox("quantityWasted");
		test.siteConfigurationAction.verifyErrorMessageFacility("Wasted quantity cannot be empty");
	}
	
	
	@Test(priority = 4, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item without providing any Reason for Quantity Wasted")
	public void Test04_1014854(Method method) {
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","10000");
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.siteConfigurationAction.selectValueFromDropDown("wasteReasonId","Select");
		test.siteConfigurationAction.verifyErrorMessageFacility("Waste reason cannot be empty");
	}
	
	@Test(priority = 6, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item of type cycle count and click on Cancel")
	public void Test05_1014860(Method method) {
		test.transactionQueueActions.clickButton("CycleCountCancelButton");
		test.transactionQueueActions.verifyWasteItemPopupGetsClosed();
	}
	
	@Test(priority = 5, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item of type Cycle count when Quantity Wasted is provided other than Integer value")
	public void Test06_1014861(Method method) {
		try{
			test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","AB");
		}catch(Exception e)
		{
			test.transactionQueueActions.verifyInputFieldIsBlank("quantityWasted");
		}
	}
	
	@Test(priority = 7, description = "VPLX: Transaction Queue-Cycle Count: [UI]: Waste an Item in Pick Queue with type \"Cycle Count\" with valid details")
	public void Test07_1014832(Method method) {
		test.transactionQueueActions.clickScanOverrideOnce();
		test.siteConfigurationAction.clickToggleButton("true", "wasteItems");
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("quantityWasted","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantity","1");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("confirmQuantityReConfirm","1");
		test.siteConfigurationAction.clickButton("saveAndCloseButton");
	}
	
}
