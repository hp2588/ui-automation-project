package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class TransactionQueue_Test extends BaseTest{
	
	String manual_pick_priority, location, return_priority, manual_restock_priority,destination, firstname;
	String [] ColumnList = {"Transaction Type", "Quantity", "Item", "Destination", "Patient name" };
	int previous_count, after_count;
	ArrayList<String> previous_data, after_data;


	
	@Test(priority = 1, description = "VPLX : Pick Transaction Queue - Transaction List Columns")
	public void Test01_1114474(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Pick Transaction Queue - Transaction List Columns");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertEquals(test.transactionQueueActions.verifyColumnHeaders(ColumnList),ColumnList.length, "[ASSERTION FAILED]: Coulumn Headers are incorrect");
	}
	
	@Test(priority = 2, description = "VPLX : Restock Workflow - Active Item Attributes")
	public void Test02_1114482(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Pick Transaction Queue - Transaction List Columns");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		Assert.assertEquals(test.transactionQueueActions.verifyColumnHeaders(ColumnList),ColumnList.length, "[ASSERTION FAILED]: Coulumn Headers are incorrect");
	}

	@Test(priority = 3, description = "VPLX : Pick Transaction Queue - Transaction List displays All")
	public void Test03_1114477(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Pick Transaction Queue - Transaction List displays All");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty(), "[ASSERTION FAILED]: Pending Transactions are not available in Pick Queue");
	}
	
	@Test(priority = 4, description = "VPLX : Delete Transaction - Pick")
	public void Test04_1114487(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Delete Transaction - Pick");
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		previous_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		previous_data = test.transactionQueueActions.getTransactionDetails(1);
		test.transactionQueueActions.verifyFirstActionLinkAndClick(getData("LinkAction.Delete"));
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
		after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(0).trim());
		after_data = test.transactionQueueActions.getTransactionDetails(1);
		Assert.assertNotEquals(previous_count, after_count);
	}
	
	@Test(priority = 5, description = "VPLX : Quantity on Hand - Edit during Pick")
	public void Test05_1114504(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Quantity on Hand - Edit during Restock");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.restockFirstTransaction();
		test.transactionQueueActions.updateOnHandQuantity("1");
	}
	

	@Test(priority = 6, description = "VPLX : Returns - Submit Return")
	public void Test06_1114351(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Returns - Submit Return");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		//test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.verifySearchedResultForReturn("Item Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddRestock.ValidQuantity"));
	    //priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddRestock.TransactionPriority"));
	    return_priority =test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("ReturnPriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
	    //test.transactionQueueActions.verifyTransaction(firstname, destination, manual_restock_priority);
	}
	
	@Test(priority = 7, description = "VPLX : Pick Workflow - Active Item Attributes")
	public void Test07_1114478(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Pick Workflow - Active Item Attributes");
		test.transactionQueueActions.verifyTabOnTQAndClick("Picks");
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		test.transactionQueueActions.verifyTransactionStatus();
		test.transactionQueueActions.verifyActiveTransactionQuantityOnHand(activeTransactionName);
		test.transactionQueueActions.verifyActiveTransactionLocation(activeTransactionName);
		
	}
	
	@Test(priority = 8, description = "VPLX : Pick Workflow - Active Item Attributes")
	public void Test08_1114478(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Pick Workflow - Active Item Attributes");
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		test.transactionQueueActions.verifyTransactionStatus();
		test.transactionQueueActions.verifyActiveTransactionQuantityOnHand(activeTransactionName);
		test.transactionQueueActions.verifyActiveTransactionLocation(activeTransactionName);
		
	}
	
	@Test(priority = 9, description = "VPLX : Waste Workflow - During Pick")
	public void Test09_1114484(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX : Waste Workflow - During Pick");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty(), "[ASSERTION FAILED]: Pending Transactions are not available in Pick Queue");		
		ArrayList<String> activeTransactionDetails = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionDetails));
		int QOH = Integer.parseInt(activeTransactionDetails.get(1));
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("wasteQuantity",getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndex("ItemDescription",1);
		test.transactionQueueActions.confirmWasteItem("Save");
		test.transactionQueueActions.verifyToastMessageOnSuccess();
		int decrementedQOH = Integer.parseInt(activeTransactionDetails.get(1));
		Assert.assertNotEquals(decrementedQOH, QOH,"[ASSERTION FAILED]:QOH is not decremented as Expected");
	}
	
	@Test(priority = 10, description = " VPLX : Waste Workflow - Waste Reason Required (Pick)")
	public void Test10_1114485(Method method) {
		ExtentTestManager.startTest(method.getName(), " VPLX : Waste Workflow - Waste Reason Required (Pick)");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty(), "[ASSERTION FAILED]: Pending Transactions are not available in Pick Queue");		
		ArrayList<String> activeTransactionDetails = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionDetails));
		int QOH = Integer.parseInt(activeTransactionDetails.get(1));
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("wasteQuantity",getData("TQ.wasteQuatity"));
		test.transactionQueueActions.confirmWasteItem("Save");
		Assert.assertTrue(test.transactionQueueActions.verifyErrorMessageonWasteReasonPopup(getData("TQ.ErrorMsg_On_Blank_WasteReason")),"[ASSERTION FAILED]:Item is wasted with selecting Waste Reason");
	}
	
	@Test(priority = 11, description = "  VPLX : Waste Workflow - Restock - Return - Waste Details - Reason Required")
	public void Test11_1114490(Method method) {
		ExtentTestManager.startTest(method.getName(), " VPLX : Waste Workflow - Restock - Return - Waste Details - Reason Required");
		test.transactionQueueActions.verifyTabOnTQAndClick("Picks");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		//test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.verifySearchedResultForReturn("Item Name", getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddReturn.searchItemName1"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		//priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",getData("AddReturn.TransactionPriority"));
		return_priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("ReturnPriorityName").trim());
		//location = test.transactionQueueActions.selectDropdownForAddPick("Source Location",	getData("AddReturn.Source Location"));
		location=test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		//test.transactionQueueActions.verifyReturnTransaction(getData("AddReturn.searchItemName1"),getData("AddPick.Quantity"));
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty(), "[ASSERTION FAILED]: Pending Transactions are not available in Pick Queue");	
		ArrayList<String> activeTransactionDetails = test.transactionQueueActions.getRestockTransactionDetails();
		test.transactionQueueActions.makeFirstRestockTransactionActive();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionDetails));
		test.transactionQueueActions.clickActiveTransactionButtons("Waste Item");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("wasteQuantity",getData("TQ.wasteQuatity"));
		test.transactionQueueActions.confirmWasteItem("Save");
		Assert.assertTrue(test.transactionQueueActions.verifyErrorMessageonWasteReasonPopup(getData("TQ.ErrorMsg_On_Blank_WasteReason")),"[ASSERTION FAILED]:Item is wasted with selecting Waste Reason");
	}
}
