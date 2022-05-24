package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_122 extends BaseTest {
	
	int previous_count, after_count, pick_count, link_count, previous_holdcount, selected_count;
	ArrayList<String> previous_data, after_data, sorted_data;
	String priority, destination, firstname;

	@Test(priority = 1, description = "VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Retock tab on Hold")
	public void Test01_Hold_Pick_Transaction(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddRestock.ValidQuantity"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
	   // test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		//test.transactionQueueActions.clickOnHold_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickHoldButton_Sanity();
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickRelease_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertTrue(test.transactionQueueActions.verifyRestockTransactionAfterRelease(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim()),"[Assertion Failed] Transaction not present in Restocks transaction queue after release");
	}
}
