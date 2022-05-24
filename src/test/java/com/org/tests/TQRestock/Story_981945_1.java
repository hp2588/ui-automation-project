package com.org.tests.TQRestock;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981945_1 extends BaseTest {
	
	String[] listSortColumns = { "Transaction Type", "Item", "Destination", "Patient name" };
	String[] listPopupItems = { "Item ID", "Item Description" };
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	String priority;
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	String updatedQty1 = "924";
	String updatedQty2 = "570";
	
	
	@Test(priority = 1, enabled=true, description = "VPLX:Transaction Queue -Restock: [UI]: Quantity is updated when clicked on quantity on active bar of Restock Transaction.")
	public void Test01_1013781(Method method) {
		// test.transactionQueueActions.pageRefresh();
		
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
		 Assert.assertTrue(test.transactionQueueActions.verifyReturnTransactionInRestockQueue(
				 TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				 "[ASSERTION FAILED]: Transaction with priority" + TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock") 
				 + " is not found in Restock Queue.");
		test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		test.transactionQueueActions.verifyOnHandQuanity();
		//String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		//test.transactionQueueActions.updateRestockTransactionQuantity("on-hand","10");
		//String updatedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		//Assert.assertNotEquals(updatedQOH, QOH, "[ASSERTION FAILED]:QOH is not updated as Expected");
		test.transactionQueueActions.clickOnQuantityOnHand(updatedQty1);
		
		test.supportDataActions.clickButtonIfPresent("primary");
		/*int updated_quan=test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		//test.transactionQueueActions.verifyUpdatedQOH(current_quan);
		Assert.assertEquals(current_quan+1, updated_quan);*/
		
		/*
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		*/
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedQOH(updatedQty1),
				"[ASSERTION FAILED]: QOH is not updated.");
		
	}
	
	
	// TODO - Yugal - needs to be updated
	@Test(priority = 2, enabled=true, description = "VPLX:Transaction Queue -Restock: [UI]: Quantity is changed using keyboard")
	public void Test02_1034228(Method method) {
		
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
		 Assert.assertTrue(test.transactionQueueActions.verifyReturnTransactionInRestockQueue(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
					"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
		test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		test.transactionQueueActions.verifyOnHandQuanity();
		test.transactionQueueActions.clickOnQuantityOnHand(updatedQty2);
		
		test.supportDataActions.clickButtonIfPresent("primary");
		/*int updated_quan=test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		//test.transactionQueueActions.verifyUpdatedQOH(current_quan);
		Assert.assertEquals(current_quan+1, updated_quan);*/
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedQOH(updatedQty2),
				"[ASSERTION FAILED]: QOH is not updated.");
		
	}
	
	// TODO - Yugal - needs to be updated
	@Test(priority = 3, enabled=true, description = "VPLX:Transaction Queue -Restock: [UI]: PUT some transaction in the Hold and it should display all the transaction with updated status")
	public void Test03_1019085(Method method) {
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		int before = test.transactionQueueActions.getTransactionTableDataCount();
		test.transactionQueueActions.clickHoldOnRestock();
		int after = test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertEquals(after + 1, before, "[ASSERTION FAILED]:Trxn not moved to Hold State");
		
	}
	
	
	@Test(priority = 11, enabled=true, description = "VPLX:Transaction Queue -Restock: PUT:  Expiry and lot information of the restock medicines during Active transaction process.")
	public void Test11_1013780(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Expiry and lot information of the restock medicines during Active transaction process.");
		test.transactionQueueActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		String facilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ISAName"));;
		test.siteConfigurationAction.clickHoldReasonCheckbox("reqRestockLotInfoFlag");
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickItemNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction
				.clickOnItemManagementFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		 
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifyPageHeader("Restock Item Details");
		test.transactionQueueActions.verifyUpdatedExpirationDate("1");
		test.transactionQueueActions.verifyUpdatedExpirationDate("1");
		
		test.transactionQueueActions.clickButtonById("save");
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
	}
	
}
