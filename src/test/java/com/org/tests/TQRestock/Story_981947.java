package com.org.tests.TQRestock;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_981947 extends BaseTest {

	String[] listSortColumns = { "Transaction Type", "Item", "Destination", "Patient name" };
	String[] listPopupItems = { "Item ID", "Item Description" };
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	String priority;
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	String QOH, updatedQOH;
	
	@Test(priority = 1, description = "VPLX:Transaction Queue -Restock: [UI]: Scan Override(F2) Link is displayed on the Active Transaction Screen")
	public void Test01_1013775(Method method) {
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim(),
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		
		// test case - 1013775
		test.transactionQueueActions.verifyScanOverrideOption();
	}
	
	@Test(priority = 2, description = "VPLX:Transaction Queue -Restock: [UI]: Restock transaction is completed on clickig Scan Override Link On Active Screen")
	public void Test02_1013774(Method method) {
		QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		
		test.transactionQueueActions.clickButtonById("transactionAction");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim(),
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail = new ArrayList<String>();
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		
		updatedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertNotEquals(updatedQOH, QOH, "[ASSERTION FAILED]:QOH is not updated as Expected");
		
	}

}
