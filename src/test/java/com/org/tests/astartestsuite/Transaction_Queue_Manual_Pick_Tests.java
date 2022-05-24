package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Transaction_Queue_Manual_Pick_Tests extends BaseTest {

	String firstname, destination, priority;

	
	 @Test(priority = 1, description = "VPLX : Manual Pick from Transaction Queue: To verify user can create a Manual Pick from the Transaction Queue")
		public void Test01_1114429_1114444(Method method)
		{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX : Manual Pick from Transaction Queue: To verify user can create a Manual Pick from the Transaction Queue");
			test.landingPageActions.navigateToMenu("Transaction Queue");
			test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
			Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
					"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
			test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
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
			test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		    //priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
			priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
		    //destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", "Destination");
			destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
			test.transactionQueueActions.clickAdditionalInfoToggle();
		    firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("lastname", "LastName"+System.currentTimeMillis());
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("middlename","MiddleName"+System.currentTimeMillis());
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("room","10");
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("bed","02");
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("account",""+System.currentTimeMillis());
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("mrn",""+System.currentTimeMillis());
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("order",""+System.currentTimeMillis());
		    
		    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
			
		}
	 
	 
	 @Test(priority = 2, description = "VPLX : Manual Pick: user can create a Manual Pick from the Pharmogistics Inventory Manager application")
		public void Test02_1114322(Method method)
		{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX : Manual Pick: user can create a Manual Pick from the Pharmogistics Inventory Manager application");
			test.transactionQueueActions.verifyActionButtonAndClick();
	 test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
	 test.transactionQueueActions.verifyUserIsOnAddPickPage("Add Pick");
	 
	 test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.verifySearchedResult("Item Name",getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		//test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	    //priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
	    //destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", "Destination");
		destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("lastname", "LastName"+System.currentTimeMillis());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("middlename","MiddleName"+System.currentTimeMillis());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("room","10");
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("bed","02");
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("account",""+System.currentTimeMillis());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("mrn",""+System.currentTimeMillis());
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("order",""+System.currentTimeMillis());
	    
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
	 
		}
	 
	 


}
