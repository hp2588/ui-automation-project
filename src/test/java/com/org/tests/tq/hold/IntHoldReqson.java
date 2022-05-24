package com.org.tests.tq.hold;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class IntHoldReqson  extends BaseTest {
	
	String holdReasonName_old, holdReasonName_new, holdReasonName;
	ArrayList<String> previous_data, sorted_data;
	String[] columnHeaders = { "Name", "Description", "Status" };
	String destination, firstname, priority, location;

	String app_url;

	@Test(priority = 6, description = "VPLX:Hold Reason: UI - User is able to get the inactive and active hold reason list by enabling show inactive toggle button.")
	public void Test06_1029353(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Hold Reason: UI - User is able to get the inactive and active hold reason list by enabling show inactive toggle button.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.supportDataActions.verifyHoldReasonStatus());
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Hold Reason");
		holdReasonName = test.supportDataActions.EnterRandomValueInInputField("descriptionText",
				getData("HoldReasonDetails.HoldReasonName") + System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.EnterRandomValueInInputField("holdReasonSummaryText",
				getData("HoldReasonDetails.HoldReasonName")+ System.currentTimeMillis(),"Hold Reason");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.HoldReason"));
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchField(holdReasonName, "search");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(holdReasonName);
		test.supportDataActions.verifyNewlyAddedHoldReasonStatus(holdReasonName, "Inactive");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(), "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("returnsOnHoldDefinitionFlag", true);
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
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
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Add Another");

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
		Assert.assertTrue(test.supportDataActions.verifyButtonIsEnabledOrDisabled("1"));
		test.transactionQueueActions.clickFirstHold();
		Assert.assertFalse(test.transactionQueueActions.verifyHoldReasonDropdownValue("holdReasonId","holdReasonName"));	
	}

}
