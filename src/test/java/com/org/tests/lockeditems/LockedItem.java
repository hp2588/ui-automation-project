package com.org.tests.lockeditems;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class LockedItem extends BaseTest {

	String priorityName, code, priorityNameRestock, priorityCodeRestock;
	String app_url, ipAddress_2, computerName_2, priority, destination, firstname,firstname_1,firstname_2;
	String ipAddress_3, computerName_3;
	ArrayList<String> restocktransdetail = new ArrayList<String>();
	int lockSecs = 3600;
	// long startTime, endTime;
	
	@Test(priority = 1, description = "VPLX:Transaction queue actions-View locked items: [UI]: Restock Transactions are disabled under Locked tab.")
	public void Test01_998663(Method method) {
		priorityNameRestock = TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock");
		
		test.landingPageActions.navigateToMenu("Main Menu");
				test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", priorityNameRestock);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		// test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickOnRestockNow_Sanity(priorityNameRestock);
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		
		// startTime = System.currentTimeMillis() / 1000;
 		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNamePriority("Current Restock", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim()));
		
		
		Open_Browser_Window();
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);		
		ipAddress_2 = TestDataPropertyReaderAndWriter.getProperty("IPAddress2");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(ipAddress_2);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		
		test.transactionQueueActions.verifyTransactionIsDisabled();
		Assert.assertNotEquals(test.transactionQueueActions.getTransactionTableDataCount(), 0);
		
		test.transactionQueueActions.verifyLockedItem(TestDataPropertyReaderAndWriter.getProperty("ItemName"), 
				priorityNameRestock);
		
	}
	
	
	@Test(priority = 2, description = "VPLX:Transaction queue actions-View locked items: [UI]: Return Transactions are disabled under Locked tab.")
	public void Test02_1150851(Method method) {
		
		/*
		test.landingPageActions.navigateToMenu("Main Menu");
				test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(ipAddress_2);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		*/
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name", 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "Return");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.clickOnRestockNow_Sanity("Return");
		Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBox());
		restocktransdetail.add(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		restocktransdetail.add(getData("AddPick.Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInRestock(restocktransdetail));
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNamePriority("Current Restock", "Return"));
		
		
		Open_Browser_Window();
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		ipAddress_3 = TestDataPropertyReaderAndWriter.getProperty("IPAddress3");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(ipAddress_3);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");		
		test.transactionQueueActions.verifyTransactionIsDisabled();
		
		test.transactionQueueActions.verifyLockedItem(TestDataPropertyReaderAndWriter.getProperty("ItemName"), 
				"Return");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(), 2);
		
	}
}
