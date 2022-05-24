package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;
import com.org.smoketests.Story_127;

public class Story_982053 extends BaseTest {
	
	
	String priority, destination, firstname,facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
	String systemLabelName;
	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, priorityCodeReturn;
	

	
	@Test(priority = 1, description = "VPLX:Transaction queue actions-VPLX:Transaction queue actions-View Locked Items:[UI]:Message “The current transaction was active for a long period and it may be assigned to another user."
			+ "Press OK to refresh the transaction queue”is not displayed on Add Pick/Restock/Return Popup")
	public void Test03_1153970() {
		
/*===================Create Priority=================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "30");
		test.siteConfigurationAction.enterRandomValueInInputField("maxLockedSeconds", "2400");
		test.siteConfigurationAction.clickCheckboxOfNewRecord();
		test.siteConfigurationAction.clickCheckBoxOfNewlyCreatedTransactionPriority("forManualPickFlag");
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(priorityName, "search");
		Assert.assertTrue(test.siteConfigurationAction.verifySearchResultsOfTransactionPriorities(priorityName));
		TestDataPropertyReaderAndWriter.setProperty("PriorityName", priorityName);
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);
		
		/*===================Create Standard Label and map priority=================*/
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add Standard Label");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), true);		
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
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
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		Assert.assertFalse(test.transactionQueueActions.verifyNotificationPopupIsDisplayed("The current transaction was active for a long period and it may be assigned to another user. Press OK to refresh the transaction queue."),
				"[ASSERTION FAILED]: Notification Popup is not displayed.");
		
	}
	
     
	//@Test(priority = 2, description = "VPLX:Transaction queue actions-VPLX:Transaction queue actions-View Locked Items:[UI]:Message “The current transaction was active for a long period and it may be assigned to another user."
			//	+ "Press OK to refresh the transaction queue”is not displayed on Add Pick/Restock/Return Popup")
		public void Test04_996705() throws InterruptedException {
			/*============wait for 30 secs===========*/
			Thread.sleep(30000);
			Assert.assertTrue(test.transactionQueueActions.verifyNotificationPopupIsDisplayed("The current transaction was active for a long period and it may be assigned to another user. Press OK to refresh the transaction queue."),
					"[ASSERTION FAILED]: Notification Popup is not displayed.");
			//test.supportDataActions.clickButton("primary");
			
			
		   //Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),"[Assertion Failed]: Error while processing transaction");
		  // 	Assert.assertTrue(test.transactionQueueActions.verifyActiveTransactionBoxSanity(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim()));
			//test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		   	//Assert.assertFalse(test.transactionQueueActions.verifyPatientNameInPickQueue(firstname),
				//	"[ASSERTION FAILED]: Patient is displayed in Pick Queue");
			
		}
	
	@Test(priority = 3, description = "VPLX:Transaction queue actions-View Locked Items:[UI]:Message “The current transaction was active for a"
			+ " long period and it may be assigned to another user.Press OK to refresh the transaction queue”is not displayed on cycle count Popup")
	public void Test01_1153972() {
		
		//test.landingPageActions.navigateToMenu("Transaction Queue");
		//test.transactionQueueActions
			//	.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		//Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			//	"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
//		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
	//	test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		//Assert.assertTrue(test.storageAreaAction.verifyTransactionCountForCycleCountTransactionForAvailableISA(TestDataPropertyReaderAndWriter.getProperty("ISAName")));
		//test.storageAreaAction.verifyStartWorkButtonAndClick();
		//test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyCycleCountTransactionInTQ();
		Assert.assertFalse(test.transactionQueueActions.verifyNotificationPopupIsDisplayed("The current transaction was active for a long period and it may be assigned to another user. Press OK to refresh the transaction queue."),
				"[ASSERTION FAILED]: Notification Popup is displayed.");
		
		
		
	
	}
	
	//@Test(priority = 2, description = "VPLX:Transaction queue-View Locked Items:[UI]:Modal Popup with the message“The current transaction was active for a long period and it may be assigned to another user."
		//	+ "Press OK to refresh the transaction ")
	public void Test02_996668() throws InterruptedException {
		/*============wait for 10 secs===========*/
		
		Thread.sleep(10000);
		Assert.assertTrue(test.transactionQueueActions.verifyNotificationPopupIsDisplayed("The current transaction was active for a long period and it may be assigned to another user. Press OK to refresh the transaction queue."),
				"[ASSERTION FAILED]: Notification Popup is not displayed.");
		test.supportDataActions.clickButton("primary");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce(),
				"[Assertion Failed]: Error while processing transaction");		
        test.siteConfigurationAction.enterDataInInputField("confirmQuantityReConfirm", "1");		
		test.supportDataActions.clickButton("saveAndCloseButton");
		
		
	}
	
	
	
	
	
}
