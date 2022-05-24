package com.org.tests.transactionqueue;

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


public class Story_119  extends BaseTest {
	
	String priority, location;

	
	 @Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
		public void Test01_1016370(Method method)
		{
			
			test.landingPageActions.navigateToMenu("Transaction Queue");
			test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
			Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
					"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
			test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
			test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
			test.storageAreaAction.verifyStartWorkButtonAndClick();
			test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
			test.transactionQueueActions.verifyActionButtonAndClick();
			test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
			test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
			test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
			priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority","RETURN");
			location=test.transactionQueueActions.selectDropdownForAddPick("Source Location",TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
			test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
			test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
			test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),getData("AddPick.Quantity"));	
		}
	

}
