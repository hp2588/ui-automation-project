package com.org.tests.authorization;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Auth_Create_And_Delete_Transactions extends BaseTest {
	
	String priority, location;
	
	@Test(priority = 1, description = "VPLX:Authorization: [UI]: User is able to delete the transaction only if it has delete permission.")
	public void Test01_1125070(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority","RETURN");
		location=test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		//test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));	
		test.transactionQueueActions.selectRestockTransaction_Sanity("Return");
		//test.transactionQueueActions.clickDelete_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityCodeRestock").trim());
		test.transactionQueueActions.clickDeleteButton_Sanity();
		
		test.transactionQueueActions.enterDeleteReason("deleteReason", "delete return transaction");
		test.transactionQueueActions.clickConfirmToDeleteButton("Confirm");
		Assert.assertTrue(test.transactionQueueActions.verifyRestockTransactionIsNotPresentAfterDelete("Return"),
				"[Assertion Failed] : Restock transaction is still present after perfoeming delete");
		
	}

}
