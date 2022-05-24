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

public class Story_981945 extends BaseTest {

	String[] listSortColumns = { "Transaction Type", "Item", "Destination", "Patient name" };
	String[] listPopupItems = { "Item ID", "Item Description" };
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	String priority;
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	
	
	@Test(priority = 1, description = "VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar in case of no Active and Pending Transactions in Restock Queue")
	public void Test01_1144940(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar in case of no Active and Pending Transactions in Restock Queue");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions
				.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsEmpty());
		
	}
	
	
	@Test(priority = 2, description = "VPLX:Transaction Queue -Restock: [UI]: Search an item present in the queue using search box")
	public void Test02_1019131(Method method) {
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
		test.siteConfigurationAction.clickButton("primary");
		
	}
	
	
	@Test(priority = 3, description = "VPLX:Transaction Queue -Restock: [UI]: Page framing should be correctly divided and cross lining should not be there")
	public void Test03_1019082(Method method) {
		
		test.supportDataActions.pageRefresh();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions
				.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		Assert.assertNotEquals(test.transactionQueueActions.getTransactionTableDataCount(), 0);
		
	}
	
	// TODO - Yugal - Refactoring required
	@Test(priority = 4, description = "VPLX:Transaction Queue -Restock: [UI]: Actions which are available in the action click Box should display correctly and no line frame should break")
	public void Test04_1020751(Method method) {
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		
	}
	
	@Test(priority = 5, description = "VPLX: Search and Filter Queue: [UI]: Data gets loaded instantly after login on transactionQueue page")
	public void Test05_1043842(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Search and Filter Queue: [UI]: Data gets loaded instantly after login on transactionQueue page");

		//test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		//test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
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
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		
	}
	
	
	@Test(priority = 8, description = "VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar when Restock tab has pending Transactions but no Active Transaction")
	public void Test08_1144941(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar when Restock tab has pending Transactions but no Active Transaction");

		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		test.transactionQueueActions
				.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		Assert.assertNotEquals(test.transactionQueueActions.getTransactionTableDataCount(), 0);
		
	}
	
	@Test(priority = 9, description = "VPLX: Instant Restock: [UI]: A message is displayed in Active bar when user clicks on Instant Restock tab")
	public void Test09_1145172(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Instant Restock: [UI]: A message is displayed in Active bar when user clicks on Instant Restock tab");
		
		test.transactionQueueActions.pageRefresh();
    	test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransactionActionAndClick("InstantRestock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	}
	
	@Test(priority = 10, description = "VPLX: Instant Returns: [UI]: A message is displayed in Active bar when user clicks on Instant Return tab")
	public void Test10_1145173(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Instant Returns: [UI]: A message is displayed in Active bar when user clicks on Instant Return tab");
	  
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	    test.transactionQueueActions.verifyTransactionActionAndClick("InstantReturn");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	}
	
	
	
	@Test(priority = 11, description = "VPLX:Transaction Queue -Restock: [UI]: User is not able to sort restock transactions")
	public void Test11_1013776(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Sorting of restock transactions.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority","Return");
		//test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Priority");
		
		//Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Priority"));
		//Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Item"));
		//Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Destination"));
		//Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Patient name"));
		//ArrayList<String> before_sort=test.siteConfigurationAction.captureDataForParticularColumnTQ("Transaction Type");
		//Assert.assertTrue(before_sort.get(0).contentEquals("RETURN"));
		
	}
	
	
	@Test(priority = 12, description = "VPLX:Transaction Queue -Restock: PUT:  Labels present on Restock Queue screen")
	public void Test12_1013778(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue -Restock: PUT:  Labels present on Restock Queue screen");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyRestock();
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNamePriority("Current Restock", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()));
		test.transactionQueueActions.verifyOnHandQuanity();
		
	}
	
	
	
	// covered in next story - 981945_1
	// @Test(priority = 11, description = "VPLX:Transaction Queue -Restock: PUT:  Expiry and lot information of the restock medicines during Active transaction process.")
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
		test.siteConfigurationAction
				.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ItemName"));;
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
		
	}
	
	
}
