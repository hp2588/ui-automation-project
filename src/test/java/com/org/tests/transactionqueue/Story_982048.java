package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982048 extends BaseTest {
	String priority, destination, firstname;

	@Test(priority = 1, description = "VPLX:Transaction Queue-Pick-UI: All checkboxes are selected after clicking on Select All checkbox")
	public void Test01_998184(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: All checkboxes are selected after clicking on Select All checkbox");
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
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.verifyCheckboxIsEnabledOrDisabled();
	}

	@Test(priority = 2, description = "VPLX:Transaction Queue-Pick-UI: Select All checkbox gets deselected after deselecting any of the selected checkbox")
	public void Test02_998188(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Select All checkbox gets deselected after deselecting any of the selected checkbox");
		
		 test.transactionQueueActions.clickFirstTransaction();
		Assert.assertFalse(test.transactionQueueActions.checkboxIsSelectedUsingJavascript("allCheckbox"));

	}

	@Test(priority = 3, description = "VPLX:Transaction Queue-Pick-UI: Pick Now button on the top gets enabled when selecting any of the checkbox")
	public void Test03_998226(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Pick Now button on the top gets enabled when selecting any of the checkbox");
				
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionActionButtonIsPresent("Pick Now"));
	}

	@Test(priority = 4, description = "VPLX:Transaction Queue-Pick-UI: Correct value of on hand is displayed for the selected transaction in current pick section")
	public void Test04_998711_999466(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Correct value of on hand is displayed for the selected transaction in current pick section");
		
		ArrayList<String> activeTransaction = test.transactionQueueActions.getTransactionDetails();
		test.transactionQueueActions.makeFirstTransactionActive();
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionQuantityOnHand(activeTransaction));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransaction));

	}

	

	

}
