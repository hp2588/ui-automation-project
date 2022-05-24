package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Transaction_Queue_Add_Restock extends BaseTest {
	
	String firstname, destination,priority;
	 @Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
		public void Test01_1016370(Method method)
		{
			
			ExtentTestManager.startTest(method.getName(),"VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page");
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
		//test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.verifySearchedResultForReturn("Item Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddRestock.ValidQuantity"));
	    //priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddRestock.TransactionPriority"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "PriorityName"+System.currentTimeMillis());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);

			
		}


}
