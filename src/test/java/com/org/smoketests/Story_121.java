package com.org.smoketests;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_121 {
	
	public class Process_Restock_Transaction  extends BaseTest {
		
		ArrayList<String> restocktransdetail = new ArrayList<String>();

		
		String priority, location;
		
		
		 @Test(priority = 1, description = "Process_Restock_Transaction")
			public void Test01_1016370(Method method)
			{
				
			
			 test.landingPageActions.navigateToMenu("Transaction Queue");
			 test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			  test.transactionQueueActions.verifyTQPageAndAppendIP(
			 TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
			 Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage()
			 , "\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
			 test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter. getProperty("ShortName"), 0);
			 test.storageAreaAction.selectPrinterForSelectedISA(
			 TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
			 test.storageAreaAction.verifyStartWorkButtonAndClick();
			 test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			/* test.transactionQueueActions.verifyActionButtonAndClick();
			 * test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
			 * test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.
			 * getProperty("ItemName").trim());
			 * test.transactionQueueActions.verifySearchedResultForReturn("Item Name"
			 * ,TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			 * test.transactionQueueActions.clickSearchedItemValueForReturn(
			 * TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
			 * getData("AddRestock.ValidQuantity")); priority=test.transactionQueueActions.
			 * selectDropdownForAddPick("Transaction Priority",
			 * TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
			 * test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton(
			 * "save_close_btn", "Save & Close");
			 */
			 test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
			 
			   // test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(), TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim(), "RETURN");
				test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
				//test.transactionQueueActions.selectRestockTransaction_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
				//test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
				test.transactionQueueActions.clickRestockButton_SanityNew();
				
//				Assert.assertTrue(test.transactionQueueActions.verifyWorkWithoutScannerOption());
//				Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopup());
//				Assert.assertTrue(test.transactionQueueActions.confirmPopup());
				
				Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
				//test.transactionQueueActions.verifyNoActiveRestockTransaction("Waiting for Item Scan");
				Assert.assertTrue(test.transactionQueueActions.verifyTransactionListIsEmpty());

			}
		 

}
}
