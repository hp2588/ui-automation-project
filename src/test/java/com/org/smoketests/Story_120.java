package com.org.smoketests;

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

public class Story_120 extends BaseTest {

	ArrayList<String> picktransdetail;
	String firstname, destination,priority;
	 @Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
		public void Test01_1016370(Method method)
		{
			
			test.landingPageActions.navigateToMenu("Transaction Queue");
			test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
			Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
					"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
			test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
			test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
			test.storageAreaAction.verifyStartWorkButtonAndClick();
			test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			test.transactionQueueActions.verifyAndClickAddPick();
			//test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
			test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			//test.transactionQueueActions.verifySearchedResult("Item Name",getData("AddPick.searchItemName"));
			test.transactionQueueActions.verifySearchedResult("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			test.transactionQueueActions.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			//test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
			test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "5");
		    //priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
			priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		    //destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", "Destination");
			destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
			test.transactionQueueActions.clickAdditionalInfoToggle();
		    firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		    //test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
		   //Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
//			Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
//			Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
//			Assert.assertTrue(test.transactionQueueActions.confirmPopup());
//			Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));
		   	test.transactionQueueActions.selectPickTransaction_Sanity(firstname);
		   	test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		   	Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
			test.transactionQueueActions.verifyActiveTransactionBox();
			test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			
		}
}
