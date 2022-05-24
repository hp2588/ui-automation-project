package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1091046 extends BaseTest {
	String itemName,itemID,destination,priority,facilityOnWFAScreen,ISAName,quantityEntered,transaction_type,firstname;
	
	@Test(priority = 1, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:Verify that User"
			+ " should be able to change the On hand qty displayed on the active TQ while performing RESTOCK "
			+ ""
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:Clicking outside the text box saves the number."
			
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:User is able to change the processed qty displayed"
			+ " on the Transaction queue while performing restock transaction."
			+ ""
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]: Clicking on the number system,"
			+ " text box is enabled and user is able to edit the number."
			+ ""
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:When text box is active,"
			+ " number is in selected mode,is replaced not appended.")
	public void Test01_Test02_Test03_Test04_Test05_998803_1119332_1118718_1118734_1118743(Method method) {
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
	
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue("itemID");
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				"Item");
		itemName=test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(itemName);
		
		
		
	
		 quantityEntered=test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				"3");
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				"RECEIVING");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		
		
		String[] itemName_parsed=itemName.split(" ");
		String ItemNameNew=itemName_parsed[0];
		System.out.println("ItemNameNew=" +ItemNameNew);
		test.transactionQueueActions.clickManualRestockTransactionBasedOnPriortiyAndItemName("Receiving", ItemNameNew);
		/*test.transactionQueueActions.enterRestockItemPopUpDetails();
		test.siteConfigurationAction.clickButton("add");*/
		
		//ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		//Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		//test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		//Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		//test.transactionQueueActions.clickFirstTransaction();
		//test.transactionQueueActions.clickHoldButton("Restock");
		
		//ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		//test.transactionQueueActions.clickRestockButton_Sanity();
		//Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		//test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantity("robotoMedium");
		
		//test.transactionQueueActions.updateActiveItemQuantityAndOnHandQuantity();
		test.transactionQueueActions.verifyOnHandQuanity();
		/*int current_quan=test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		test.transactionQueueActions.clickOnQuantityOnHand(current_quan+1);*/
		//test.transactionQueueActions.getxpathOfQOH();
		test.transactionQueueActions.clickOnQuantityOnHand("924");
		
		test.supportDataActions.clickButtonIfPresent("primary");
		/*int updated_quan=test.transactionQueueActions.getQuantityOnHandActiveTransaction();
		//test.transactionQueueActions.verifyUpdatedQOH(current_quan);
		Assert.assertEquals(current_quan+1, updated_quan);*/
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedQOH("924"),
				"[ASSERTION FAILED]: QOH is not updated.");
		
		//test.transactionQueueActions.updateProcessedItemQuantity("qtyFont");
	}
	
	@Test(priority = 6, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:When user does not"
			+ " input any processed quantity in restock active transaction."
			+ ""
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:User does not enter any number"
			+ " and clicks outside the text box.")
	public void Test06_Test07_1118745_1119333() {
		//test.transactionQueueActions.ClickAndVerifyActiveItemQuantityAndOnHandQuantity();
		String prev_value=test.transactionQueueActions.getActiveQuantity();
		test.transactionQueueActions.clickProcessedQuantity();
		test.transactionQueueActions.clickOutside();
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedProcessedQuantity(prev_value),
				"[ASSERTION FAILED]: Processed Quantity is changed.");
		
	}
	
	@Test(priority = 8, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:ESCAPE discards the change,"
			+ " deactivates the field and quantity is roll backed to the original quantity ")
	public void Test08_1119335() {
		
		String prev_value=test.transactionQueueActions.getActiveQuantity();
		test.transactionQueueActions.clickProcessedQuantity();
		test.transactionQueueActions.pressKeyUsingAction(Keys.ESCAPE);
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedProcessedQuantity(prev_value),
				"[ASSERTION FAILED]: Processed Quantity is changed.");
		/*String prev_value=test.transactionQueueActions.getActiveQuantity();
		test.transactionQueueActions.clickProcessedQuantity();*/
				//test.transactionQueueActions.updateActiveItemQuantityAndPressESCAPE();
	}
	
	@Test(priority = 9, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:Clicking on ENTER ,"
			+ "quantity is saved and field is deactivated.")
	public void Test09_1119334() {
		test.transactionQueueActions.inputProcessedQuantity("32");
		//String prev_value=test.transactionQueueActions.getActiveQuantity();
		test.transactionQueueActions.pressKeyUsingAction(Keys.ENTER);
		test.supportDataActions.clickButton("primary");
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedProcessedQuantity("32"),
				"[ASSERTION FAILED]: Processed Quantity is not updated on Pressing ENTER.");

		//test.transactionQueueActions.updateActiveItemQuantityAndPressENTER();
		
	}
	
	@Test(priority = 10, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:On hover over the quantity,"
			+ " system should display a outline")
	public void Test10_1119331() {
		
		test.transactionQueueActions.hoverItemQuantity();
		
	}
	
	@Test(priority = 11, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:Behavior of Process Quantity is same except font size"
			+ " of process quantity decreases.")
	public void Test11_1118815() {
		
		String fontsizebefore=test.transactionQueueActions.getFontSize("qtyFont");
		test.transactionQueueActions.inputProcessedQuantity("11111");
		test.transactionQueueActions.pressKeyUsingAction(Keys.ENTER);
		test.supportDataActions.clickButton("primary");
		String fontsizeafter=test.transactionQueueActions.getFontSize("qtyFont");
		/*Assert.assertTrue(test.transactionQueueActions.verifyFontSize("qtyFont"),
				"[ASSERTION FAILED]: Font does not decrease with increasing data.");*/
		
Assert.assertNotEquals(fontsizebefore, fontsizeafter,"[ASSRTION FAILED]: Font size does not change.");
	}
	
	@Test(priority = 12, description = "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]: User does not input"
			+ " any processed quantity in the active pick transaction")
	public void Test12_1118752() {
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Pick");
		

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
		//test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	 	test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
	   	Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),""
				+ "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");
	
		//ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		//Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
   
		String prev_value=test.transactionQueueActions.getActiveQuantity();
		test.transactionQueueActions.clickProcessedQuantity();
		test.transactionQueueActions.pressKeyUsingAction(Keys.ESCAPE);
		Assert.assertTrue(test.transactionQueueActions.verifyUpdatedProcessedQuantity(prev_value),
				"[ASSERTION FAILED]: Processed Quantity is changed.");


	}
}
