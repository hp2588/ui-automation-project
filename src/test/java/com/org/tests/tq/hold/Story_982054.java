package com.org.tests.tq.hold;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collections;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982054 extends BaseTest {

	String destination, firstname, priority, location;

	@Test(priority = 1, description = "VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Picks tab on Hold")
	public void Test01_1000362_1000909_1145030_1054062_1125072(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Picks tab on Hold");
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
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Add Another");

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
		// test.transactionQueueActions.successMessage("Transaction put on hold");
		// Assert.assertFalse(test.supportDataActions.verifyButtonIsEnabledOrDisabled("1"));
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 2, description = "VPLX:Transaction queue actions-Hold selected: Transaction data is available on UI for all compatible browser")
	public void Test02_1009399(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Transaction data is available on UI for all compatible browser");
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

		Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
	}

	@Test(priority = 3, description = "VPLX:Transaction queue actions-Hold selected: Hold Button is available in front of all the transactions available in the transaction queue and at the top right corner when multiple transactions are selected")
	public void Test03_1000899(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Hold Button is available in front of all the transactions available in the transaction queue and at the top right corner when multiple transactions are selected");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionHoldLinkCount("Hold"));
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionButtonIsPresent(getData("ButtonActions.Hold"));
	}

	@Test(priority = 4, description = "VPLX:Transaction queue actions-Hold selected: Putting multiple transaction from Picks tab on Hold")
	public void Test04_1010053(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting multiple transactions from Picks tab on Hold");
		test.transactionQueueActions.verifyTransactionActionAndClick("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 5, description = "VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Restock tab on Hold")
	public void Test05_1000373(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting a single transaction from Restock tab on Hold");
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
		test.transactionQueueActions.clickHoldOnRestock();
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 6, description = "VPLX:Transaction queue actions-Hold selected: Putting multiple transactions from Restock tab on Hold")
	public void Test06_1053919_1145006(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting multiple transactions from Restock tab on Hold");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.onHoldHeading();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.addRestock("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Add Another");

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

		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 7, description = "VPLX:Transaction queue actions-Hold selected: UI: Hold button is displayed for the active restock transaction")
	public void Test07_1145034_1145179(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: UI: Hold button is displayed for the active restock transaction");
		test.transactionQueueActions.addRestock("Add Restock");
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
		test.transactionQueueActions.clickFirstRestockNow();
		Assert.assertTrue(test.supportDataActions.verifyButtonIsEnabledOrDisabled("1"));
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		test.transactionQueueActions.clickActiveHoldButton("1");
	}

	@Test(priority = 8, description = "VPLX:Transaction queue actions-Hold selected: UI: User is able to hold the transaction from the hold button present on the active pick screen after med scan is performed.")
	public void Test08_1145178_1145182_1145181(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: UI: Hold button is displayed for the active restock transaction");
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.clickFirstPickNow();
		Assert.assertTrue(test.supportDataActions.verifyButtonIsEnabledOrDisabled("1"));
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");
		test.transactionQueueActions.clickActiveHoldButton("1");
		//test.transactionQueueActions.successMessage("Transaction put on hold");
	}

	@Test(priority = 9, description = "VPLX:Transaction queue actions-Hold selected: [UI]: Transactions which are already available in Hold Tab cannot be put on Hold State again")
	public void Test09_1001687_1144967_1145007(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: [UI]: Transactions which are already available in Hold Tab cannot be put on Hold State again");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyHoldButtonAbsent("Hold");
		test.transactionQueueActions.onHoldTextIsPresent();
		test.transactionQueueActions.onHoldHeading();
	}

	@Test(priority = 10, description = "VPLX:Transaction queue actions-Hold selected: Putting a active transaction on Hold")
	public void Test10_1009963_1000895(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: Putting a active transaction on Hold");
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
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		Assert.assertTrue(test.supportDataActions.verifyButtonIsEnabledOrDisabled("1"));
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item7"));
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
	}

	@Test(priority = 11, description = "VPLX:Transaction queue actions-Hold selected: [UI]: Comment section available in hold item dialog box is accepting maximum 20 characters")
	public void Test11_1000813_1000901_1000368_1000385(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Hold selected: [UI]: Comment section available in hold item dialog box is accepting maximum 20 characters");
		//test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
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
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Add Another");

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

		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionButtonIsPresent(getData("ButtonActions.Hold"));
		test.transactionQueueActions.verifyTransactionActionAndClick("Hold");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 0);
		test.siteConfigurationAction.verifyErrorMessageonAlert("Hold Reason cannot be empty");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription20Char();
		test.siteConfigurationAction.verifyErrorMessageonAlert("Enter max 20 characters");
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
		test.transactionQueueActions.getEmptyTransactionTableDataCount();

		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.addRestock("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Add Another");

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

		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Hold");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		Assert.assertTrue(test.transactionQueueActions.clickConfirmHoldButton());
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

}