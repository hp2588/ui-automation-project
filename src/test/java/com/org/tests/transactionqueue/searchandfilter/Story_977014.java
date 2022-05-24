package com.org.tests.transactionqueue.searchandfilter;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_977014 extends BaseTest {
	String priorityName,code;
	String app_url, ipAddress_2, computerName_2, priority, destination, firstname,firstname_1,firstname_2;
	ArrayList<String> restocktransdetail = new ArrayList<String>();

	@Test(priority = 1, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data with transaction type pick.")
	public void Test01_1016670(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Search transaction queue data with transaction type pick..");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		
		// adding first txn
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priorityName = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname_1 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		//adding second trxn
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priorityName = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname_1 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		 
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	}

	@Test(priority = 2, description = "VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Pick Transaction")
	public void Test02_1016886(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Pick Transaction");
		
		test.transactionQueueActions.enterDatainSearchBox("---");
		test.transactionQueueActions.verifyTransactionQueueIsEmpty();
		
	}


	/*
	 * @Test(priority = 3, description =
	 * "VPLX: Search and Filter Queue: [UI]: Data gets loaded instantly after login on transactionQueue page"
	 * ) public void Test03_1043842(Method method) {
	 * ExtentTestManager.startTest(method.getName()
	 * ,"VPLX: Search and Filter Queue: [UI]: Data gets loaded instantly after login on transactionQueue page"
	 * );
	 * 
	 * test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
	 * test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	 * test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	 * test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	 * 
	 * }
	 */
	
	@Test(priority = 4, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data for Restock transaction type.")
	public void Test04_1016669(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Search transaction queue data for Restock transaction type.");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", 
	    		getData("AddRestock.ValidQuantity"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	}
	
	@Test(priority = 5, description = "VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Restock Transaction")
	public void Test05_1016887(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Restock Transaction");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.enterDatainSearchBox("---");
		test.transactionQueueActions.verifyTransactionQueueIsEmpty();
		
	}

	@Test(priority = 6, description = "VPLX: Search and Filter Queue: [UI]: Data gets filtered instantly while searching any record")
	public void Test06_1043847(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Data gets filtered instantly while searching any record");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	}
	
	// TODO - Yugal - Dec 30 - Refactoring required, add hold tab, add cycle count on hold 
	@Test(priority = 7, description = "VPLX: Search and Filter Queue: [UI]: Perform searching with search criteria and switch between tabs.")
	public void Test07_1034210(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: VPLX: Search and Filter Queue: [UI]: Perform searching with search criteria and switch between tabs.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	}
	

	@Test(priority = 8, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data with transaction type hold.")
	public void Test08_1016671(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: VPLX: Search and Filter Queue: [UI]: Perform searching with search criteria and switch between tabs.");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority","Return");
		//test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.verifyTransactionWithPriority("Return", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickHoldOnReturn();
		test.transactionQueueActions.verifyTabOnTQAndClick("Hold");
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.verifyTransaction("Return");
		
	}
	
	@Test(priority = 9, description = "VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Hold Transaction")
	public void Test09_1016885(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Search and Filter Queue: [UI]: Invalid search text value in search text for Hold Transaction");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Hold");
		test.transactionQueueActions.enterDatainSearchBox("---");
		test.transactionQueueActions.verifyTransactionQueueIsEmpty();
		
	}
	
	@Test(priority = 10, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data for Restock transaction type with Transaction Priority")
	public void Test10_1166555(Method method) {
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransaction(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		
	}
	
	@Test(priority = 11, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data for Pick transaction type with Transaction Priority")
	public void Test11_1166557(Method method) {
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyAndClickAddPick();
		
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priorityName = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		
		firstname_1 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTransaction(TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		
	}
	
	@Test(priority = 12, description = "VPLX: Search and Filter Queue: [UI]: Search transaction queue data for Pick transaction type with Transaction Priority")
	public void Test12_1166560(Method method) {
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priorityName = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname_1 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		// TODO - yugal - urgent refactoring required - Dec - 23 - 2020
		// cycle count not added during facility creation - needed to be manually here
		test.transactionQueueActions.verifyTransaction("Cycle Count");
		
	}
}
