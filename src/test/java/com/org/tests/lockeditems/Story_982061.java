package com.org.tests.lockeditems;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982061 extends BaseTest {
	
	String priorityName,code, systemLabelName;
	String app_url, ipAddress_2, computerName_2, priority, destination, firstname, firstname_1, firstname_2;
	long startTime, endTime;
	int lockSecs = 1800;
	
	
	@Test(priority = 1, enabled=true, description = "VPLX: Transaction queue actions-View locked items: [UI]: \"Locked\" is displayed on top left of the Active screen when user is on the Locked Items Tab.")
	public void Test01_1144968(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction queue actions-View locked items: [UI]: Locked is displayed on top left of the Active screen when user is on the Locked Items Tab.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		priorityName = TestDataPropertyReaderAndWriter.getProperty("PriorityName");
		
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
				TestDataPropertyReaderAndWriter.getProperty("PriorityName"));
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname_1 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		/*
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName"), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		*/
		
		test.transactionQueueActions.verifyActiveTransactionBox();
		startTime = (long)(System.currentTimeMillis() / 1000);
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");	
		
	}
	
	
	@Test(priority = 2, enabled=true, description = "VPLX: Transaction queue actions-View locked items: [UI]: The Active Header on 'Locked Items' Tab displays a message in case of no locked transaction")
	public void Test02_1019141(Method method) {
		ExtentTestManager.startTest(method.getName(), "VPLX: Transaction queue actions-View locked items: [UI]: The Active Header on 'Locked Items' Tab displays a message in case of no locked transaction");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.verifyTQTabHeaderText("Items being worked on by other users will appear locked");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("Locked items will auto-release into the queue when the maximum lock time has expired");
		test.transactionQueueActions.verifyTransactionNotDisplayedOnUI();
		
	}
	
	
	@Test(priority = 3, enabled=true, description = "VPLX: Transaction queue actions-View locked items: [UI]: The Header of Locked Items tab displays \"Locked\"")
	public void Test03_1144844(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction queue actions-View locked items: [UI]: The Header of Locked Items tab displays \"Locked\"");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.verifyTQTabHeaderText("Items being worked on by other users will appear locked");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyActiveTransactionBox();
		/*
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(
				TestDataPropertyReaderAndWriter.getProperty("PriorityName"), 
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		*/
		//for auto release
	}
	
	
	@Test(priority = 4, description = "VPLX:Transaction queue actions-View locked items: [UI]:Transactions are disabled under Locked items tab.")
	public void Test04_1014004(Method method) {
		
		Open_Browser_Window();
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		computerName_2 = TestDataPropertyReaderAndWriter.getProperty("ComputerName2");
		ipAddress_2 = TestDataPropertyReaderAndWriter.getProperty("IPAddress2");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(ipAddress_2);
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		/*
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				priorityName.trim());
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname_2 = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname_2);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		*/
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");		
		test.transactionQueueActions.clickButtonById("Refresh");
		test.transactionQueueActions.verifyTransactionIsDisabledWhenTabIsPressed();
		
	}
	
	
	@Test(priority = 5, description = "VPLX:Transaction queue actions-View locked items: [UI]:Same number of items are visible under Locked items tab."
			+ "VPLX:Transaction queue actions-View locked items: [UI]: Locked items tab on Transaction Queue page.")
	public void Test05_Test06_1014006_1014001(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction queue actions-View locked items: [UI]:Same number of items are visible under Locked items tab."
				+ "VPLX:Transaction queue actions-View locked items: [UI]: Locked items tab on Transaction Queue page.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		test.transactionQueueActions.verifyHeaderText("Locked");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(), 1);
		
	}
	
	// TODO - Urgent refactoring - Dec 23 
	@Test(priority = 6, description = "VPLX:Transaction queue actions-View locked items: [UI]: On pressing tab checkbox is not enabled.")
	public void Test07_1014154(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction queue actions-View locked items: [UI]: On pressing tab checkbox is not enabled.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionIsDisabledWhenTabIsPressed());
		
	}
	
	
	@Test(priority = 7, description = "VPLX:Transaction queue actions-View locked items: [UI]: Click on the Locked items tab so that item list is opened up.")
	public void Test08_1014163(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction queue actions-View locked items: [UI]: Click on the Locked items tab so that item list is opened up.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(),1);
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		
	}
	
	
	@Test(priority = 8, description = "VPLX: Transaction queue actions-View locked items: [UI]: A message is displayed in the Active Section of 'Locked Items' Tab when the transactions are available in it")
	public void Test09_1019501(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction queue actions-View locked items: [UI]: A message is displayed in the Active Section of 'Locked Items' Tab when the transactions are available in it");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(),1);
		test.transactionQueueActions.verifyTransactionIsDisabledWhenTabIsPressed();
		test.transactionQueueActions.verifyTQTabHeaderText("Items being worked on by other users will appear locked");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("Locked items will auto-release into the queue when the maximum lock time has expired");
		
	}

	
	@Test(priority = 9, description = "VPLX:Transaction queue actions-View locked items: [UI]: Visual design for the page Transaction queue.")
	public void Test10_1013998(Method method) {
		ExtentTestManager.startTest(method.getName()," VPLX:Transaction queue actions-View locked items: [UI]: Visual design for the page Transaction queue.\r\n");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(),1);
		test.transactionQueueActions.verifyTransactionIsDisabledWhenTabIsPressed();
		test.transactionQueueActions.verifyTQTabHeaderText("Items being worked on by other users will appear locked");	
		test.transactionQueueActions.verifyTQSecondTabHeaderText("Locked items will auto-release into the queue when the maximum lock time has expired");
		
	}
	
	@Test(priority = 10, description = "VPLX: Transaction queue actions-View locked items: [UI]: Visual Design for the Locked item tab\r\n")
	public void Test11_1019612(Method method) {
		ExtentTestManager.startTest(method.getName()," VPLX: Transaction queue actions-View locked items: [UI]: Visual Design for the Locked item tab\\r\\n");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(),1);
		test.transactionQueueActions.verifyTransactionIsDisabledWhenTabIsPressed();
		test.transactionQueueActions.verifyTQTabHeaderText("Items being worked on by other users will appear locked");	
		
	}
	
	@Test(priority = 11, description = "VPLX: Transaction queue actions-View locked items: [UI]: Type displayed for the Locked item tab")
	public void Test12_1023663(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Transaction queue actions-View locked items: [UI]: Type displayed for the Locked item tab");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		test.transactionQueueActions.verifyLockedItem(firstname_1, priority);
		test.transactionQueueActions.clickButtonById("Refresh");
		
	}
	
	
	@Test(priority = 12, description = "VPLX:Transaction queue actions-View locked items: [UI]:Sorting of Item field.")
	public void Test13_1014028(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction queue actions-View locked items: [UI]:Sorting of Item field.");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.clickButtonById("Refresh");
		
		ArrayList<String> before_sort = test.siteConfigurationAction.captureDataForParticularColumnTQ("Item");
		test.siteConfigurationAction.verifyAndClickSortIcon("Item");
		ArrayList<String> after_sort=test.siteConfigurationAction.captureDataForParticularColumnTQ("Item");
		Assert.assertEquals(before_sort, after_sort);
	
	}
	
	
	@Test(priority = 13, enabled=false, description = "VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar in case of no Active and Pending Transactions in Restock Queue")
	public void Test13_1144940(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar in case of no Active and Pending Transactions in Restock Queue.");
	    
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	    //Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsEmpty());
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	  }
	
	@Test(priority = 14, enabled=false, description = "VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar when Restock tab has pending Transactions but no Active Transaction")
	public void Test14_1144941(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction Queue -Restock: [UI]: A message is displayed on the Header of Active Bar when Restock tab has pending Transactions but no Active Transaction");
	  
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddRestock.ValidQuantity"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	  }
	
	@Test(priority = 15, enabled=false, description = "VPLX: Instant Restock: [UI]: A message is displayed in Active bar when user clicks on Instant Restock tab")
	public void Test15_1145172(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Instant Restock: [UI]: A message is displayed in Active bar when user clicks on Instant Restock tab");
		
		test.transactionQueueActions.pageRefresh();
    	test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		test.transactionQueueActions.verifyTransactionActionAndClick("InstantRestock");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	}
	
	@Test(priority = 16, enabled=false, description = "VPLX: Instant Returns: [UI]: A message is displayed in Active bar when user clicks on Instant Return tab")
	public void Test16_1145173(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX: Instant Returns: [UI]: A message is displayed in Active bar when user clicks on Instant Return tab");
	  
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	    test.transactionQueueActions.verifyTransactionActionAndClick("InstantReturn");
		test.transactionQueueActions.verifyTQTabHeaderText("Scan or select an item in queue to restock");
		test.transactionQueueActions.verifyTQSecondTabHeaderText("or change restock method to instant restock / instant return");
		
	}
	
	@Test(priority = 17, enabled=false, description = "VPLX:Transaction queue actions-View locked items: [UI]: User verifies unlock item is a part of pick queue.")
	public void Test17_1014638(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Transaction queue actions-View locked items: [UI]: User verifies unlock item is a part of pick queue.");
		
		endTime = (long)(System.currentTimeMillis() / 1000);
		if(endTime - startTime < lockSecs) {
			test.siteConfigurationAction.hardWaitForChromeBrowser(lockSecs - (int)(endTime - startTime) + 1);// intentional- to exceed maxlocked time
		}
		// TODO - needs to be updated, need to check with less time
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		test.transactionQueueActions.verifyTransaction(firstname_1.trim());
		test.transactionQueueActions.verifyTabOnTQAndClick("Locked");
		test.transactionQueueActions.verifyHeaderText("Locked");
		Assert.assertEquals(test.transactionQueueActions.getTransactionTableDataCount(), 0);
		Assert.assertFalse(test.transactionQueueActions.verifyLockedItemIsUnlocked(firstname_1, priority));
    	
	}
	
}
