package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ManualRestockFeature extends BaseTest
{
	ArrayList<String> restocktransdetail;
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };

	
	
	@Test(priority = 1, description = "VPLX:Manual Restock: UI: To verify that processed quantity "
			+ "can be updated after a transaction becomes active.")
	public void Test01_1129521(Method method)  {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: To verify that processed quantity can be updated "
				+ "after a transaction becomes active.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
        restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		test.transactionQueueActions.selectRestockNowAndVerifyPopup();
		test.transactionQueueActions.verifyItemDetailsInRestockPopup("Item ID");
		test.transactionQueueActions.verifyItemDetailsInRestockPopup("Item Description");
		test.transactionQueueActions.verifyExpirationDateLotNumber(Arrays.asList(listPopupFields));
		test.transactionQueueActions.verifyUpdatedExpirationDate("6");
		test.transactionQueueActions.clickConfirmButtonOnResotckPopup();
		//test.siteConfigurationAction.clickButton("save");
		test.transactionQueueActions.verifyActiveRestockTransaction();
		test.transactionQueueActions.verifyActiveTransactionQuantityOnHand();
		

	}
}
