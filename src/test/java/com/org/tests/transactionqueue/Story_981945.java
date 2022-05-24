package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981945 extends BaseTest {

	String[] listSortColumns = { "Transaction Type", "Item", "Destination", "Patient name" };
	String[] listPopupItems = { "Item ID", "Item Description" };
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };


 @Test(priority = 1, description = "VPLX:Transaction Queue -Restock: PUT: Sorting of restock transactions")
	public void Test01_1013776(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Sorting of restock transactions.");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Transaction Type"));
	Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Item"));
	Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Destination"));
	Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Patient name")); 
	} 
	

	@Test(priority = 2, description = "VPLX:Transaction Queue -Restock: PUT:  Labels present on Restock Queue screen")
	public void Test02_1013778(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Labels present on Restock Queue screen");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
			test.transactionQueueActions.verifyRestock();
			test.transactionQueueActions.clickRestockNowButton();
		test.transactionQueueActions.veifyItemsOnRestockPopup(Arrays.asList(listPopupItems));
	}
 
	@Test(priority = 3, description = "VPLX:Transaction Queue -Restock: PUT:  Expiry and lot information of the restock medicines during Active transaction process.")
	public void Test03_1013780(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Expiry and lot information of the restock medicines during Active transaction process.");
		test.transactionQueueActions.verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
		test.transactionQueueActions.verifyUpdatedExpirationDate();
	} 

}