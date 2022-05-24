package com.org.tests.InventoryMove;
import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


public class AddPick extends BaseTest{

String firstname, destination, priority;
	
	@Test(priority = 1, description = "Create and View Pick Transaction",testName="Create and View Pick Transaction")
	public void Test01_1016370(Method method) {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
	//	test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity","960");

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "960");
	 test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				"STAT Order");
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
		test.transactionQueueActions.verifyActiveTransactionBox();
		
	//	test.transactionQueueActions.verifyTransactionInActiveOrPending(TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
		
		
	//	test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName_Inventory").trim());
	   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");

	 
	}
}
