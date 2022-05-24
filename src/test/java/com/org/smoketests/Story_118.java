package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_118 extends BaseTest {

	String firstname, destination,priority;

	 @Test(priority = 1, description = "Create and View Restock Transaction")
		public void Test01_1016370(Method method)
		{
			
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
				test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		Assert.assertTrue(test.transactionQueueActions.getTransactionTableDataCount()>0);
		//test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		 Assert.assertTrue(test.transactionQueueActions.verifyReturnTransactionInRestockQueue(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
					"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
		 
		}


	 @Test(priority = 2, description = "Process Restock Transaction")
		public void Test02_1016370(Method method) {
		 
			test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
			Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
			Assert.assertFalse(test.transactionQueueActions.verifyReturnTransactionInRestockQueue(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
					"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");

			
		}
}
