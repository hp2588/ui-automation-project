package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ProcessingRestockActiveTransactionFeature  extends BaseTest {
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	
	@Test(priority = 1, description = "VPLX:Manual Restock: UI: To verify that active Restock Transaction "
			+ "displays quantity, item, QoH, destination, patient name on active transaction banner "
			+ "and Current priority on the top left.")
	public void Test01_1043673(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Manual Restock: UI:Verify all the fields id, quantity, description,QoH present in Restock item details pop-up");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddReturn.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddRestock.TransactionPriority"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		restocktransdetail = test.transactionQueueActions.getFirstRestockTransactionDetails();
		//test.transactionQueueActions.selectRestockNowAndVerifyPopup();
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.verifyActiveRestockTransactionQuantity(restocktransdetail);
		test.transactionQueueActions.verifyActiveRestockItemName(restocktransdetail);
		Assert.assertFalse(test.transactionQueueActions.getActiveQuantity().isEmpty());
	}
	
	
	@Test(priority = 2, description = "VPLX: Manual Restock: UI: Manual restock transaction gets "
			+ "successfully completed using Scan Override (F2)")
	public void Test02_1057386(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Manual Restock: UI: Manual restock transaction gets successfully completed using scan override");

		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", getData("AddPick.searchItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddReturn.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				getData("AddRestock.TransactionPriority"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
		test.transactionQueueActions.verifyNoActiveRestockTransaction("Waiting for Item Scan");
	}
	
	
	@Test(priority = 0, description = "VPLX:Manual Restock: UI: To verify than "
			+ "no transaction is active on Restock tab by default.")
	public void Test03_1129515(Method method) {
		ExtentTestManager.startTest(method.getName(), 
				"VPLX:Manual Restock: UI: No transaction is active on Restock tab by default");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertFalse(test.transactionQueueActions.verifyActiveTransactionBox());

	}
	
	
	@Test(priority = 3, description = "VPLX:Manual Restock: UI: To verify that "
			+ "processed quantity can be updated after a transaction becomes active.")
	public void Test01_1129521(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Manual Restock: UI: Processed quantity can be updated after a transaction becomes active");

		test.transactionQueueActions.updateRestockTransactionQuantity("update-quantity", "20");
	}
	
}
