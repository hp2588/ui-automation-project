package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982189 extends BaseTest{
	String destination,priority,firstname;
	 @Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
	public void Test01_1016370(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA6"), 5);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.verifySearchItemFields(getData("AddPick.ExpectedSearchHeader"));
		test.transactionQueueActions.clickCancelButton();
	}
 
 @Test(priority = 2, description = "VPLX:Manual Pick[UI]-User enter the item name & verifies the searched item in the grid")
	public void Test02_1016371(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User enter the item name & verifies the searched item in the grid");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",getData("AddPick.searchItemName"));
	}
 
 @Test(priority = 3, description = "VPLX:Manual Pick[UI]-User clicks on item from search result grid")
	public void Test03_1016372(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User clicks on item from search result grid");
	test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	}

 @Test(priority = 4, description = "VPLX:Manual Pick[UI]-User verifies the quantity field on Add Pick page")
	public void Test04_1016373(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the quantity field on Add Pick page");
	    test.transactionQueueActions.verifyInputfield("Quantity");
	    test.transactionQueueActions.verifyFieldIsMandatory("Quantity");
	    test.transactionQueueActions.verifyInputFieldIsBlank("Quantity");
	test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	} 

 @Test(priority = 5, description = "VPLX:Manual Pick[UI]-User verifies the priority dropdown on Add Pick page")
	public void Test05_1016374(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the priority dropdown on Add Pick page");
	    test.transactionQueueActions.verifyInputfield("Transaction Priority");
	    test.transactionQueueActions.verifyFieldIsMandatory("Transaction Priority");
	}

	
 @Test(priority = 6, description = "VPLX:Manual Pick[UI]-User verifies the destination dropdown on Add Pick page")
	public void Test06_1016409(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the destination dropdown on Add Pick page");
     	test.transactionQueueActions.verifyInputfield("Destination");
	    test.transactionQueueActions.verifyFieldIsMandatory("Destination");
	}
 
 //Bug 1102724 logged for Search Transactions
 @Test(priority = 7, description = "VPLX:Manual Pick[UI]-User verifies the Save & Close button on Add Pick page")
	public void Test07_1016410(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Save & Close button on Add Pick page");
	    priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	    destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    test.transactionQueueActions.verifyButtonAddPick("save_close_btn", "Save & Close");
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);

	}
 
 
  
 @Test(priority = 8, description = "VPLX:Manual Pick[UI]-User verifies the cancel button on Add Pick page")
	public void Test08_1016412(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the cancel button on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	    test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	    test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.verifyButtonAddPick("cancel_btn", "Cancel");
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("cancel_btn", "Cancel");
	    test.transactionQueueActions.verifyCancelPopupOnAddPick(getData("AddPick.ConfirmMessage"));
	    test.transactionQueueActions.clickConfirmPopupButton();
	}
  
 @Test(priority = 9, description = "VPLX:Manual Pick[UI]-User verifies the Save & Add button on Add Pick page")
	public void Test09_1024928(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the Save & Add button on Add Pick page");
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
	    priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority", getData("AddPick.Priority"));
	    destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
	    test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname=test.transactionQueueActions.getAlphaNumericString(50);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    test.transactionQueueActions.verifyButtonAddPick("save_add_btn", "Save & Add");
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_add_btn", "Save & Add");
	    test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
	}
 
 @Test(priority = 10, description = "VPLX:Manual Pick[UI]-User verifies the 'Indicates required fields' message on Add pick page")
	public void Test10_1026183(Method method)
	{
		
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the 'Indicates required fields' message on Add pick page");
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name", getData("AddPick.searchItemName"));
	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifyRequiredFieldMessage(getData("AddPick.ReqdMessage"));
	}
  
}
