package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Process_Pick_Transactions extends BaseTest {

	ArrayList<String> picktransdetail;
	String firstname, destination, priority;

	@Test(priority = 1, description = "To verify user can refresh the Transaction Queue in Pick Mode to update transaction list with new transactions")
	public void Test01_1114488(Method method) {
		ExtentTestManager.startTest(method.getName(), "To verify user can process a manually added Pick Transaction");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
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
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.pageRefresh();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	}

	@Test(priority = 2, description = "To verify user can process a manually added Pick Transaction")
	public void Test02_1114493(Method method) {
		ExtentTestManager.startTest(method.getName(), "To verify user can process a manually added Pick Transaction");
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));

	}

	@Test(priority = 3, description = "Authorized user can save quantity on hand for an item")
	public void Test03_1016370(Method method) {
		ExtentTestManager.startTest(method.getName(), "Authorized user can save quantity on hand for an item");
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		// test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResultForReturn("Item
		// Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		// priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddRestock.TransactionPriority"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyTransaction(firstname, destination, priority);

	}

}
