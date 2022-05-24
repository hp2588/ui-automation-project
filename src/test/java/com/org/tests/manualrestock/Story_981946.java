package com.org.tests.manualrestock;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981946 extends BaseTest {
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	String[] listPopupFields = { "Earliest Expiration Date", "Lot Number", "Action" };
	String priority;
	int previous_count, after_count;
	
	
	@Test(priority = 1, description = "VPLX:Manual Restock: UI: No transaction is active "
			+ "on Restock tab by default.")
	public void Test01_1129515(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: No transaction is active on Restock tab by default.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
	}
	
	
	@Test(priority = 2, description = "VPLX:Manual Restock: UI:Verify pop-up restock item open "
			+ "when clicking on restock now for manual restock")
	public void Test02_1043664(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI:Verify pop-up restock item open when clicking on restock now for manual restock");
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		 
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickOnRestockNow_Sanity(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
	}
	

	@Test(priority = 2, description = "VPLX:Manual Restock: UI:Active restock transaction displays quantity, "
			+ "description, QoH destination, patient name on active transaction banner "
			+ "and Current priority on the top left.")
	public void Test02_1043673(Method method){
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI:Active restock transaction displays quantity, description,QoH destination, patient name on active transaction banner and Current priority on the top left.");
		
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNamePriority("Current Restock",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()));	
	}
	
	
	@Test(priority = 3, description = "VPLX:Manual Restock: UI: Manual restock transaction gets successfully completed using scan override.")
	public void Test03_1057386(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Manual restock transaction gets successfully completed using scan override.");
		
		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(), 
				"[Assertion Failed]: Error while processing transaction");
		
	}
	
	
	@Test(priority = 4, description = "VPLX:Manual Restock: UI: User is able to delete transactions for the queue.")
	public void Test04_1129522(Method method) {
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: User is able to delete transactions for the queue.");
		// to be fixed
		test.transactionQueueActions.deleteholdedManualPickTransactionBasedOnPriortiyAndItemName(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifyDeleteConfirmationBox("confirm");
	}
	
	
	@Test(priority = 5, description = "VPLX:Manual Restock: UI: Restock transaction does not become active when expiry lot details are duplicate..")
	public void Test05_1043682(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Restock transaction does not become active when expiry lot details are duplicate..");
		
		test.transactionQueueActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickHoldReasonCheckbox("reqRestockLotInfoFlag");
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions
				.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction
				.clickOnItemManagementFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		
		test.supportDataActions.pageRefresh();
		
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
		Assert.assertEquals(test.transactionQueueActions.getRecordCountOnRestockPopup(), 1);
	}

	@Test(priority = 6, description = "VPLX:Manual Restock: UI: Lot/expiry details are not required "
			+ "when \"lot/expiry unknown or already recorded\" flag is checked.")
	public void Test06_1043682(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Lot/expiry details are not required when \"lot/expiry unknown or already recorded\" flag is checked.");
		
		test.transactionQueueActions.clickLotExpiryUnknownCheckbox();
		test.transactionQueueActions.verifyExpirydateLotNumberAreDisabled();
		test.transactionQueueActions.clickButtonById("cancel");
		test.transactionQueueActions.clickButtonById("primary");
	}
	
	
	@Test(priority = 7, description = "VPLX:Manual Restock: UI: Transaction does not become active if user clicks on cancel button on expiry/lot popup.")
	public void Test07_1043720(Method method){
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Transaction does not become active if user clicks on cancel button on expiry/lot popup.");
		
		test.transactionQueueActions.clickButtonById("Refresh");
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifyPageHeader("Restock Item Details");
		
		test.supportDataActions.verifyButtons("cancel");
		test.supportDataActions.verifyButtons("save");
		test.transactionQueueActions.clickButtonById("cancel");
		test.transactionQueueActions.clickButtonById("primary");
		
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		Assert.assertTrue(test.transactionQueueActions.verifyReturnTransactionInRestockQueue(TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()),
				"[ASSERTION FAILED]: Return Transaction is not found in Restock Queue.");
	}
	
	
	@Test(priority = 8, description = "VPLX:Manual Restock: UI: User is unable to confirm on expiry/lot popup when expiry date and lot number fields are empty")
	public void Test08_1043720(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: User is unable to confirm on expiry/lot popup when expiry date and lot number fields are empty");
		
		test.transactionQueueActions.clickButtonById("Refresh");
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifyPageHeader("Restock Item Details");
		
		// test.transactionQueueActions.clickButtonById("save");
		// test.transactionQueueActions.verifyErrorFieldsOnRestockPopup();
		test.transactionQueueActions.verifyButtonIsDisabledById("save");
	}
	
	
	@Test(priority = 9, description = "VPLX:Manual Restock: UI: Transaction becomes active after confirming valid details on expiry/lot pop up.")
	public void Test09_1043880_1051426_1129521(Method method)  {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI: Transaction becomes active after confirming valid details on expiry/lot pop up.");
		
		test.transactionQueueActions.verifyUpdatedExpirationDate("6");
		test.transactionQueueActions.clickButtonById("save");
		
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNamePriority("Current Restock", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()));
		test.transactionQueueActions.verifyOnHandQuanity();
		test.transactionQueueActions.updateProcessedItemQuantity("qtyFont");
	}
	
	
}