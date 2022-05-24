package com.org.tests.mainmenu.holdreasons;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_HoldReasonInt extends BaseTest {
	
	String holdReasonName, holdReasonName_old;
	String destination, firstname, priority, location;
	String priorityName;
	String[] listSortColumns = { "Priority", "Quantity", "Item", "Location", "Destination", 
			"Patient name", "Hold Reason", "Actions" };
	
	@Test(priority = 1, description = "VPLX:Hold Reason: UI - Verify the new Hold Reason gets populated "
			+ "in Hold Reason the dropdown while holding a transaction in the TQ")
	public void Test01_1130402(Method method) {
		test.landingPageActions.navigateToFeature("Facilities");
		
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requireHoldReasonFlag");
		// clickSaveButton method already called in the method above
		// test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Hold Reason");
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		
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
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickFirstHold();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.siteConfigurationAction.selectValueForDropDown("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
		
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickHoldOnRestock();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.siteConfigurationAction.selectValueForDropDown("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
	}
	
	
	@Test(priority = 2, description = "VPLX:Hold Reason: UI - Hold Reason gets populated "
			+ "in Hold Reason dropdown while holding a transaction when a Hold Reason name is updated.")
	public void Test02_1130411(Method method) {
		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		test.siteConfigurationAction.clickRecordNameToEdit(holdReasonName);
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName")+System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.clickFirstHold();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.siteConfigurationAction.selectValueForDropDown("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
		
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickHoldOnRestock();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.siteConfigurationAction.selectValueForDropDown("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
	}

	
	@Test(priority = 3, description = "VPLX: Hold Reasons UI: The Hold Reason is not available "
			+ "in the Hold Reason dropdown while holding a transaction when a hold Reason is inactive")
	public void Test03_1130415(Method method) {
		// test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		
		test.supportDataActions.clickRecordNameToEdit(holdReasonName);
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(holdReasonName, "Inactive");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.clickFirstHold();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.transactionQueueActions.verifyInactiveRecordIsNotAvailable("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
		
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickHoldOnRestock();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.transactionQueueActions.verifyInactiveRecordIsNotAvailable("holdReasonId", holdReasonName);
		test.siteConfigurationAction.clickButtonUsingId("holdCancelButton");
	}
	
	
	@Test(priority = 4, description = "VPLX: Facility Settings (Require hold reason - Pick) : "
			+ "When Require hold reason flag is checked,user is asked for a hold reason "
			+ "while putting pick transaction on hold.")
	public void Test04_1116942(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.siteConfigurationAction.pageRefresh();
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		
		test.transactionQueueActions.clickFirstHold();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 1);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
	}


	@Test(priority = 5, description = "VPLX: Facility Settings (Require hold reason - Restock) : "
			+ "When Require hold reason flag is checked,user is asked for a hold reason "
			+ "while putting restock transaction on hold.")
	public void Test05_1116943(Method method) {
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickHoldOnRestock();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
			
	}
	
	//part of CR
	@Test(priority = 6, description = "VPLX: Transaction Queue -Pick:[UI]: To verify "
			+ "Hold Reason column is added in On Hold tab after patient name column")
	public void Test06_1151881(Method method) {
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		Assert.assertEquals(test.transactionQueueActions.verifyColumnHeaders_TQtabs(listSortColumns), 
				listSortColumns.length);
		// Assert.assertEquals(test.transactionQueueActions.getColumnHeaderOrder(),listSortColumns);	
	}
	
	//part of CR
	@Test(priority = 7, description = "VPLX: Transaction Queue -Pick:[UI]: To verify "
			+ "User is able to sort the value of the hold reason column alphabetically.")
	public void Test07_1151964(Method method) {
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		ArrayList<String> before_sort=test.siteConfigurationAction.captureDataForParticularColumnTQ("Hold Reason");
		test.siteConfigurationAction.verifyAndClickSortIcon("Hold Reason");
		ArrayList<String> after_sort=test.siteConfigurationAction.captureDataForParticularColumnTQ("Hold Reason");
		Assert.assertEquals(before_sort, after_sort);
	}
	
}
