package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981944 extends BaseTest {
	String priority, destination, firstname;

	@Test(priority = 00, description = "VPLX:Transaction Queue-Pick-UI: Verify user is able to override the scanning of the bin label")
	public void Test00_1002447(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify user is able to override the scanning of the bin label");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.landingPageActions.navigateToMenu("Transaction Queue");
	    test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip"));
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",getData("AddPick.searchItemName"));
  	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		destination=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",getData("AddPick.Priority"));
	    destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
		test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));
		/*
		 * if (!(test.transactionQueueActions.
		 * verifyBinLabelScanning("Press Enter with Correct Quantity"))) {
		 * Assert.assertTrue(test.transactionQueueActions.
		 * verifyBinLabelScanning("Waiting for Pick Label Scan")); }
		 * 
		 * else { Assert.assertTrue(test.transactionQueueActions.
		 * verifyBinLabelScanning("Press Enter with Correct Quantity")); }
		 */
	}

	@Test(priority = 01, description = "VPLX:Transaction Queue-Pick-UI: Verify a pop-up appears when override is chosen")
	public void Test02_1002457(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify a pop-up appears when override is chosen");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());
		test.transactionQueueActions.confirmPopup();

	}

	@Test(priority = 03, description = "VPLX:Transaction Queue-Pick-UI: Verify a message appears as Waiting for Item Scan. Click 'Work without the scanner' to override")
	public void Test03_1002512(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify a message appears as Waiting for Item Scan. Click 'Work without the scanner' to override");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertTrue(test.transactionQueueActions.verifyItemScanMessage(getData("TQ.itemScanMessage"),
				"Waiting For Item Scan. Hit F2 to override scanning."));
	}

	@Test(priority = 04, description = "VPLX:Transaction Queue-Pick-UI: Verify the details mentioned in the Pop-up which has appeared")
	public void Test04_1002521(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify the details mentioned in the Pop-up which has appeared");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
		test.transactionQueueActions.confirmPopup();

	}

	@Test(priority = 05, description = "VPLX: Transaction Queue-Pick-UI: Override is happening by pressing Work Without the Scanner")
	public void Test05_1002264(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX: Transaction Queue-Pick-UI: Override is happening by pressing Work Without the Scanner");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
				"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
	}

	@Test(priority = 06, description = "VPLX:Transaction Queue-Pick-UI: Verify user is able to override the scanning of the item label")
	public void Test06_1002450(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify user is able to override the scanning of the item label");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
				"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
	}

	@Test(priority = 07, description = "VPLX:Transaction Queue-Pick-UI: Verify message after override is successful")
	public void Test07_1002544(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Verify message after override is successful");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
				"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
		// Assert.assertTrue(test.transactionQueueActions.verifySuccessMessageOverrideWithoutScanner());

	}

}
