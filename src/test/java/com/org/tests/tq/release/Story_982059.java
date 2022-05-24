package com.org.tests.tq.release;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982059 extends BaseTest {

	String destination, firstname, priority, location;

	@Test(priority = 1, description = "VPLX:Transaction queue actions-Release selected: Releasing a single transaction from Hold tab")
	public void Test01_999492(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Releasing a single transaction from Hold tab");
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
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.onHoldHeading();
		test.transactionQueueActions.clickFirstRelease();
		test.transactionQueueActions.getEmptyTransactionTableDataCount();	
	}

	@Test(priority = 2, description = "VPLX:Transaction queue actions-Release selected: Releasing multiple transactions from Hold tab")
	public void Test02_999498_999505_999513(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Releasing multiple transactions from Hold tab");
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
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
		test.transactionQueueActions.verifyTransactionActionAndClick("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
		
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.onHoldHeading();
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionButtonIsPresent(getData("ButtonActions.Release"));
		test.transactionQueueActions.verifyTransactionActionAndClick("Release");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 3, description = "VPLX:Transaction queue actions-Release selected: Restock tab related transaction is moved to Restock tab once it is released from the Hold tab")
	public void Test03_999508_999568_999568_999573(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Restock tab related transaction is moved to Restock tab once it is released from the Hold tab");
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
		
		test.transactionQueueActions.clickHoldOnRestock();
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.onHoldHeading();
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionButtonIsPresent(getData("ButtonActions.Release"));
		test.transactionQueueActions.verifyTransactionActionAndClick("Release");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

	@Test(priority = 4, description = "VPLX:Transaction queue actions-Release selected: Release button is disabled for the transactions which are not on Hold")
	public void Test04_999576(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Release button is disabled for the transactions which are not on Hold");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		Assert.assertFalse(
				test.transactionQueueActions.verifyActionLinkIsNotPresent(getData("PriorityList.Priority2"),
						getData("LinkAction.Release")),
				"[ASSERTION FAILED] : Release button is not disabled for the Pick transactions which are not on Hold");
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		Assert.assertFalse(
				test.transactionQueueActions.verifyActionLinkIsNotPresent(getData("PriorityList.Priority2"),
						getData("LinkAction.Release")),
				"[ASSERTION FAILED] : Release button is not disabled for the Restock transactions which are not on Hold");
	}

	@Test(priority = 5, description = "VPLX:Transaction queue actions-Release selected: Release button is not enabled when none of the transactions are selected on Hold tab")
	public void Test05_999578(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Release button is not enabled when none of the transactions are selected on Hold tab");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		Assert.assertFalse(
				test.transactionQueueActions
						.verifyTransactionActionButtonIsNotPresent(getData("ButtonActions.Release")),
				"[ASSERTION FAILED] : VPLX:Transaction queue actions-Release selected: Release button is not enabled when none of the transactions are selected on Hold tab");
	}

	@Test(priority = 6, description = "VPLX:Transaction queue actions-Release selected: Transactions related with both tabs (Picks/Restock) are released from Hold Tab simultaneously")
	public void Test06_999599(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction queue actions-Release selected: Transactions related with both tabs (Picks/Restock) are released from Hold Tab simultaneously");
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Hold");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyTransactionActionAndClickRestock("Release");
		test.transactionQueueActions.getEmptyTransactionTableDataCount();
	}

}
