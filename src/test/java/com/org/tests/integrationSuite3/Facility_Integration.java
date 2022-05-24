package com.org.tests.integrationSuite3;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Facility_Integration extends BaseTest {
	String destination, firstname, priority, location, facilityName, medItem;;

	
	@Test(priority = 1, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets "
			+ "populated in Facility dropdown on View Computer page  for a user having access to that Facility")
	public void Test01_1130458(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets "
				+ "populated in Facility dropdown on View Computer page  for a user having access to that Facility");
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	}

	@Test(priority = 2, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets "
			+ "populated in Facility dropdown on View Printer page  for a user having access to that Facility")
	public void Test02_1130460(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated in Facility dropdown on View Printer page  for a user having access to that Facility");
		
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	}

	@Test(priority = 3, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets "
			+ "populated in Facility dropdown on View Schedule page for a user having access to that Facility")
	public void Test03_1130465(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated "
				+ "in Facility dropdown on View Schedule page for a user having access to that Facility");
		
		test.landingPageActions.navigateToFeature("Schedules");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets "
			+ "populated in Facility dropdown on View  Custom Label screen for a user having access to that Facility"
			+ "\n&\n"
			+ "Obsolete : VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated "
			+ "in Facility dropdown on Add/Edit  Custom Label screen for a user having access to that Facility")
	public void Test04_Test05_1130468_1130470(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated "
				+ "in Facility dropdown on View  Custom Label screen for a user having access to that Facility"
				+ "\n&\n"
				+ "Obsolete : VPLX: Specific Facility Settings [UI]: When a new Facility is added or updated,the Facility Name gets populated "
				+ "in Facility dropdown on Add/Edit  Custom Label screen for a user having access to that Facility");
		
		test.landingPageActions.navigateToFeature("Custom Labels");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Custom Label");
		test.siteConfigurationAction.selectValueFromDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
	}
	
	
	@Test(priority = 5, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added,"
			+ "the same gets populated in a dropdown on View Destination page")
	public void Test06_1106326() {
		//test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
	}
	
	
	@Test(priority = 6, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added or updated,"
			+ "the Facility Name gets populated in Facility dropdown on View ISAs screen")
	public void Test07_1106853() { 
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 7, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added or updated, "
			+ "the Facility Name gets populated in Facility dropdown on Add/Edit Destination screen")
	public void Test08_1106341() {
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName", "Fac" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");
		
		// Facility field is disabled while editing a destination
	}

	@Test(priority = 8, description = "VPLX: Specific Facility Settings [UI]: [Integration]:  When a new Facility is added or updated,"
			+ "the Facility Name gets populated in Facility dropdown on Add/Edit ISAs")
	public void Test09_1106854() {
		
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
		
		// Facility field is disabled while editing an ISA
	}

	@Test(priority = 9, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added or updated,"
			+ "the Facility Name gets populated in Facility dropdown on Add/Edit Computer page")
	public void Test10_1106855(Method method) {
		
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnAddButtonToAddComputer();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickButton("cancel");
		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickButton("edit");
		test.siteConfigurationAction.selectValueForDropDown("defaultFacilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.clickButton("cancel");
	}
	
	
	@Test(priority = 10, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added/updated, "
			+ "the same gets populated in the mapped External System dropdown on View Item screen")
	public void Test11_1106859(Method method) {
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	}
	
	
	@Test(priority = 11, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When a new Facility is added/updated, "
			+ "the same Facility name should be displayed under the System Level column on the Add/Edit Item screen")
	public void Test12_1106861(Method method) {
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickButton("cancel");
	}
	
	
	@Test(priority = 12, description = "VPLX: Specific Facility Settings [UI]:[Integration]:  When a new Facility is added/updated, "
			+ "the same get populated under Facility dropdown on Location Management screen")
	public void Test13_1106862(Method method) {
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	}
	
	
	// TODO - refactoring - manual pick flag to be turned off
	@Test(priority = 13, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The Transaction Priority code gets populated in "
			+ "Transaction Priority dropdown on Add Pick screen when Manual Pick flag is turned on"
			+ "&"
			+ "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The Transaction Priority code does not get populated in "
			+ "Transaction Priority dropdown on Add Pick screen when Manual Pick flag is turned off")
	public void Test14_Test15_1117181_1117183(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When Require hold reason flag is checked, user is asked for a hold reason while putting a Pick transaction on hold");
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requireHoldReasonFlag");
		
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
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock")));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.clickFirstHold();
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
		
	}
	
	// TODO - Refactoring - Use Hold button on active transaction - test case with restock flag turned off
	@Test(priority = 14, description = "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The Transaction Priority code gets populated in Transaction Priority dropdown on Add Restock screen when Manual Restock flag is turned on"
			+ "&"
			+ "VPLX : Manage Transaction Priorities: [UI]: [Integration]: The Transaction Priority code does not get populated in Transaction Priority dropdown on Add Restock screen when Manual Restock flag is turned off"
			+ "&"
			+ "VPLX:Transaction Queue Actions-Hold Selected: [UI]: VPLX: Transaction Queue actions - Hold Selected: The Active Restock transaction is put on Hold when user clicks on Hold Transaction on Restock tab")
	public void Test16_Test17_Test18_1117185_1117188_1118346(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When Require hold reason flag is checked,user is asked for a hold reason while putting a Restock transaction on hold");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		Assert.assertFalse(test.supportDataActions.verifyDropdownElementsDefaultRuleNotPresent("priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName")));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickFirstRestockNow();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item7"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 3);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
		test.supportDataActions.tqCrossButton();
		
	}

	@Test(priority = 15, description = "VPLX: Specific Facility Settings [UI]: When Request destination selection for returns flag is unchecked, "
			+ "the destination dropdown gets disabled on Add return/Instant Return screen."
			+ "&"
			+ "VPLX: Specific Facility Settings: UI: When the Flag 'Add Returns to Restock Queue on Hold' is unchecked, "
			+ "the newly created Manual Return transactions are displayed in Restock tab directly")
	public void Test19_Test20_1112808_1141735(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When Request destination selection for returns flag is checked,the destination dropdown gets enable on Add return screen.");
		test.siteConfigurationAction.pageRefresh();
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.siteConfigurationAction.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickTab("Settings");
		// test.siteConfigurationAction.unCheckHoldReasonCheckbox("requestRestockDestinationFlag");
		if(test.siteConfigurationAction.checkSystemFlagIsChecked("requestRestockDestinationFlag")) {
			Assert.assertFalse(test.siteConfigurationAction.clickCheckBoxUsingId("requestRestockDestinationFlag"));
		}
		test.siteConfigurationAction.unCheckHoldReasonCheckbox("returnsOnHoldDefinitionFlag");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "Return");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransaction("Return");
		//test.transactionQueueActions.listReturnTransaction();
	}
	
	@Test(priority = 16, description = "VPLX: Specific Facility Settings [UI]: [Integration]: When \"Require Hold Reason\" Flag is checked, "
			+ "hold reason popup would appear when transactions(Pick/Restock/Cycle Count) are put on Hold for facility")
	public void Test21_1130474(Method method) throws InterruptedException {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		
		test.siteConfigurationAction.clickTab("Settings");
		//test.siteConfigurationAction.unCheckHoldReasonCheckbox("requireHoldReasonFlag");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requireHoldReasonFlag");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
//		test.transactionQueueActions.verifyActionButtonAndClick();
//		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item7"));
		test.transactionQueueActions.clickActiveTransactionButtons("Hold");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
	}
	
	
	@Test(priority = 17, description = "VPLX: Specific Facility Settings [UI]: "
			+ "When Verify pick/restock quantity flag is checked, "
			+ "the verify quantity gets populated on transaction queue dashboard.")
	public void Test22_1112807(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings [UI]: When Verify pick/restock quantity flag is checked,the verify quantity gets populated on transaction queue dashboard.");
		
		//test.landingPageActions.navigateToFeature("Main Menu");
		
		test.supportDataActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("verifyQuantityFlag");
		
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
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		
		test.transactionQueueActions.clickFirstPickNow();
		test.transactionQueueActions
				.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		
		test.transactionQueueActions.verifyQuantityFlag();
		test.transactionQueueActions.clickOnQuantity("10");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		
		if(test.transactionQueueActions.isButtonWithTextDisplayed("Cancel")) {
			test.transactionQueueActions.clickButtonWithText("Cancel");
		}
		
		if(test.transactionQueueActions.isButtonWithTextDisplayed("Yes")) {
			test.transactionQueueActions.clickButtonWithText("Yes");
		}
		/*
		test.transactionQueueActions.clickFirstPickNow();
		test.transactionQueueActions.pickLabelScanFlag();
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		*/
		test.landingPageActions.navigateToMenu("Main Menu");
		
	}
	
}
