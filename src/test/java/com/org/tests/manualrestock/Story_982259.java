package com.org.tests.manualrestock;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;


public class Story_982259 extends BaseTest {
	String priority;
	
	
	
	@Test(priority=1, description= "VPLX:Manual Restock: UI: Add restock page contains save & add,save & close,cancel buttons.")
	public void Test01_1033563(Method method)
	{
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.verifyButtonOnAddRestock("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyButtonOnAddRestock("save_close_btn", "Save & Add Another");
		test.transactionQueueActions.verifyButtonOnAddRestock("save_close_btn", "Cancel");
		//test.transactionQueueActions.verifyButtonOnAddRestock("save_close_btn", "Print Label");
		
	}
	
	@Test(priority=2, description= "VPLX:Manual Restock: UI:Verify qauntity required and priority are mandatory field to enter after selecting an item")
	public void Test02_1033610(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI:Verify qauntity required and priority are mandatory field to enter after selecting an item");
		
		Assert.assertTrue(test.transactionQueueActions.verifyFieldIsMandatory("Quantity"));
		Assert.assertTrue(test.transactionQueueActions.verifyFieldIsMandatory("Transaction Priority"));
		
	}
	
	
	@Test(priority=3, description= "VPLX:Manual Restock: UI:Verify to add restock of Active items")
	public void Test03_1029572(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI:Verify to add restock of Active items");
		
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", 
				TestDataPropertyReaderAndWriter.getProperty("priorityNameRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
	} 
	
	
	@Test(priority=4, description= "VPLX:Manual Restock: UI: Restock transaction is created for an active item.")
	public void Test04_1034162(Method method)
	{
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	    test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
	    		getData("AddPick.Quantity"));   
	    
	}
	
	@Test(priority=5, description= 
			"VPLX:Manual Restock: UI:Verify quantity field accept only whole number except 0 as initials")
	public void Test05_1033620(Method method)
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Restock: UI:Verify quantity field accept only whole number except 0 as initials");
		
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
	    test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),
	    		getData("AddPick.Quantity"));
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");	
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddRestock.InvalidQuantity"));
	    // test.transactionQueueActions.verifyInvalidQuantityMessage("Quantity cannot be empty");
	}
	
	@Test(priority=6, description= 
			"VPLX:Manual Restock: UI: Restock transaction is not created if user clicks on \"Cancel\" on Add Restock page.")
	public void Test06_1033624(Method method)
	{
		
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "1000");
	    test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", "Manual Restock");
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Cancel");
	    test.transactionQueueActions.verifyCancelPopupOnAddReturn(getData("AddPick.ConfirmMessage"));
	    test.siteConfigurationAction.clickButton("primary");
		test.transactionQueueActions.verifyReturnTransactionIsNotCreated(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim(),"1000");
		
	} 	
}
