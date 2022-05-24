package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.actions.TransactionQueue_Actions;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1004052 extends BaseTest {
	
	String wasteReasonName_old, wasteReasonName; 
	
	@Test(priority = 1, description = "VPLX:Wasting items in Transaction Queue:[UI]: Waste item button is present on dashboard for an active Pick transaction")
	public void Test01_1066108(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()); 
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity")); 
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()); 
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity")); 
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.VerifyButtons("Waste");
		
	}

	@Test(priority = 2, description = "VPLX:Wasting items in Transaction Queue:[UI]: List of defined waste reasons is displayed when user clicks on Waste Reason dropdown.")
	public void Test02_1066155(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
		
	}

	@Test(priority = 3, description = "VPLX:Wasting items in Transaction Queue:[UI]: Waste item screen is closed & user is on Transaction queue dashboard when user click on save button")
	public void Test03_1066159(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	}

	@Test(priority = 4, description = "VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while pick transaction")
	public void Test04_1066243(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while pick transaction.");
		
		if (test.transactionQueueActions.verifyActiveTransactionBox()) {
			String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
			test.transactionQueueActions.clickActiveTransactionButtons("Waste");
			test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
			
			test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
					getData("TQ.wasteQuatity"));
			test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
			test.transactionQueueActions.confirmWasteItem("Save");
			String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
			System.out.println("Quantity before: "+ decrementedQOH + "; Quantity now: "+ QOH);
			Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");
			
		}
		
	}
	
	@Test(priority = 5, description = "VPLX:Wasting items in Transaction Queue:[UI]:error message is displayed when Waste Quantity>Quantity on hand.")
	public void Test05_1066151(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]:error message is displayed when Waste Quantity>Quantity on hand.");
		
		test.transactionQueueActions.VerifyButtons("Waste");
		String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Integer qohVal = Integer.valueOf(QOH);
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		
		Assert.assertNotEquals(test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity", 
				String.valueOf(qohVal+1)), String.valueOf(qohVal+1),"[ASSERTION FAILED]: Waste Quantity>Quantity on hand.");
		
	}
	
	@Test(priority = 6, description = "VPLX:Wasting items in Transaction Queue:[UI]:Waste items screen is displayed when user clicks on waste item button")
	public void Test06_1066111(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]:Waste items screen is displayed when user clicks on waste item button");
		
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		
	}
	
	@Test(priority = 7, description = "VPLX:Wasting items in Transaction Queue:[UI]: user is redirected to Transaction queue dashboard when user click on cancel button")
	public void Test07_1066160(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]: user is redirected to Transaction queue dashboard when user click on cancel button");
		
		test.transactionQueueActions.confirmWasteItem("Cancel");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteItemPopupGetsClosed());
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	}
	
	@Test(priority = 8, description = "VPLX:Wasting items in Transaction Queue:[UI]: Waste Item Popup displays "
			+ "all the fields as per UX/VD: Item ID, Item Description, Location, QOH, Waste Reasons Dropdown")
	public void Test08_1066136(Method method) {
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()); 
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity")); 
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
		test.transactionQueueActions.verifyInputfield("wasteQuantity");
		test.transactionQueueActions.verifyInputFieldIsAutopopulated("ItemId");
		test.transactionQueueActions.verifyInputFieldIsAutopopulated("ItemDescription");
		test.transactionQueueActions.verifyInputFieldIsAutopopulated("Location");
		
	}
	
	@Test(priority = 9, description = "VPLX:VPLX:Wasting items in Transaction Queue:[UI]:Waste quantity field "
			+ "is accepted numeric values when user enters text.")
	public void Test09_1066141(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]:Waste quantity field is accepted numeric values when user enters text.");
		
		test.transactionQueueActions.verifyInputfield("wasteQuantity");
		Assert.assertTrue(test.transactionQueueActions.verifyFieldIsMandatory("Waste Quantity"));
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity", getData("TQ.wasteQuatity"));
		
	}
	
	@Test(priority = 10, description = "VPLX:Wasting items in Transaction Queue:[UI]: "
			+ "Indicate required fields message is displayed on Waste items screen.")
	public void Test10_1066244(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]: Indicate required fields message is displayed on Waste items screen.");
		
		test.transactionQueueActions.verifyFieldIsMandatoryIcon("Indicates required fields");
		test.transactionQueueActions.confirmWasteItem("Cancel");
		
	}
	
	@Test(priority = 11, description = "VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while restock a transaction.")
	public void Test11_1070109(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while restock a transaction.");
	
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		// Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		
		String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");
		
	}
	
	@Test(priority = 12, description = "VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while return a transaction.")
	public void Test12_1070113(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Wasting items in Transaction Queue:[UI]: quantity on hand is updated after wasting an item on Transaction queue dashboard while return a transaction.");
	test.landingPageActions.navigateToMenu("Transaction Queue");
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority","Return");
		//test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickRestockTransactionBasedOnPriortiyAndItemName("Return", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		String QOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.selectValueFromDropDownByIndexWasteItem(1);
		test.transactionQueueActions.confirmWasteItem("Save");
		
		String decrementedQOH = test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		Assert.assertNotEquals(decrementedQOH, QOH, "[ASSERTION FAILED]:QOH is not decremented as Expected");
		
	}
	
	// Story 997996
	@Test(priority = 13, description = "VPLX:Waste Reason:[UI]- Waste Reason gets populated in Waste Reason Dropdown "
			+ "when the user clicks on the Waste Item button for an active transaction when new Waste Reason created.")
	public void Test13_1112601(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reason");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
		.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
		test.siteConfigurationAction.selectValueFromDropDown("wasteReasonId", wasteReasonName_old);
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.confirmWasteItem("Save");
		test.transactionQueueActions.verifyWasteItemPopupGetsClosed();
		
	}
	
	// Story 997997
	@Test(priority = 14, description = "VPLX:Waste Reason:[UI]- Updated Waste Reason name gets populated in Waste Reason Dropdown "
			+ "when the user clicks on the Waste Item button for an active transaction when Waste Reason updated.")
	public void Test14_1112604(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Return: UI:Verify the Add return button after clicking action tab button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Waste Reasons");
		test.supportDataActions.verifyLabelIsPresent("Waste Reason");
		/*
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Waste Reason");
		String wasteReasonName = test.supportDataActions.EnterRandomValueInInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		*/
		
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		test.siteConfigurationAction.clickRecordNameToEdit(wasteReasonName_old);
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(
				getData("ToggleValue.WasteReason")), "[ASSERTION FAILED]: Toggle Button is Not Present");
		wasteReasonName_old = test.supportDataActions.EnterRandomValueInWasteReasonInputField("wasteReason",
				getData("WasteReasonDetails.WasteReasonName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(wasteReasonName_old);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim(), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickActiveTransactionButtons("Waste");
		test.transactionQueueActions.verifyWasteItemsPopup("Waste Items");
		Assert.assertFalse(test.transactionQueueActions.verifyWasteReasonList().isEmpty());
		test.siteConfigurationAction.selectValueFromDropDown("wasteReasonId", wasteReasonName_old);
		test.transactionQueueActions.EnterValueInInputFieldOnWasteItemPopup("wasteQuantity",
				getData("TQ.wasteQuatity"));
		test.transactionQueueActions.confirmWasteItem("Save");
		test.transactionQueueActions.verifyWasteItemPopupGetsClosed();
		
	}
	
}
