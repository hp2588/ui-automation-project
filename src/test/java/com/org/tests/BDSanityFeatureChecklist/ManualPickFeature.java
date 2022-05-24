package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ManualPickFeature extends BaseTest {

	String destination, priority, firstname;
	String facilityOnWFAScreen,ISAName,itemName,transaction_type;

	@Test(priority = 1, description = "VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page")
	public void Test01_1016370(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the item name in search item textbox on Add pick page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		/*test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA6"), 5);*/
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.verifySearchItemFields(getData("AddPick.ExpectedSearchHeader"));
		//test.transactionQueueActions.clickCancelButton();
	}
	
	@Test(priority = 4, description = "VPLX:Manual Pick[UI]-User creates a Manual Pick transaction using 'Save & Close button'"
			+ "without clicking on 'Additional Information' toggle"
			+ "VPLX: Manual Pick: [UI]: The Manual Pick functionality is working as per the Acceptance Criteria"
			+ "VPLX:Manual Pick[UI]-User enter the item name & verifies the searched item in the grid")
	public void Test02_Test03_Test04_1016399_1042865_1031421(Method method) {
		//test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
//test.transactionQueueActions.verifyAndClickAddPick();
		
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		/*test.transactionQueueActions.searchItemValue("ItemID");
		//test.transactionQueueActions.searchItemValue(itemID);
		//test.transactionQueueActions.verifySearchedResult("Item Name", itemID);
		test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());*/
		
		itemName=test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "6");
		transaction_type=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

				
		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination",
				1);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		String[] itemName_parsed=itemName.split(" ");
		String ItemNameNew=itemName_parsed[0];
		System.out.println("ItemNameNew=" +ItemNameNew);
		
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(transaction_type, ItemNameNew);
	
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	
	}

	@Test(priority = 5, description = "VPLX:Manual Pick[UI]-User enter the item name & verifies the searched item in the grid")
	public void Test05_1016371(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User enter the item name & verifies the searched item in the grid");
		test.transactionQueueActions.verifyAndClickAddPick();
		//test.transactionQueueActions.verifyAddPickPopup(getData("AddPick.ExpectedMesage"));
		test.transactionQueueActions.searchItemValue("ItemID");
		test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID");
	}

	@Test(priority = 6, description = "VPLX:Manual Pick[UI]-User clicks on item from search result grid")
	public void Test06_1016372(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User clicks on item from search result grid");
		test.transactionQueueActions.clickSearchedItemValue("ItemName");
		
	}

	@Test(priority = 7, description = "VPLX:Manual Pick[UI]-User verifies the quantity field on Add Pick page")
	public void Test07_1016373(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the quantity field on Add Pick page");
		test.transactionQueueActions.verifyInputfield("quantity");
		test.transactionQueueActions.verifyFieldIsMandatory("Quantity");
		test.transactionQueueActions.verifyInputFieldIsBlank("quantity");
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
	}

	@Test(priority = 8, description = "VPLX:Manual Pick[UI]-User verifies the priority dropdown on Add Pick page")
	public void Test08_1016374(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]-User verifies the priority dropdown on Add Pick page");
		
		test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("priority");
		test.transactionQueueActions.verifyFieldIsMandatory("Transaction Priority");
	}

	@Test(priority = 9, description = "VPLX:Manual Pick[UI]: Additional Information toggle is displayed on Add Pick popup")
	public void Test09_1016383(Method method) {

		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manual Pick[UI]: Additional Information toggle is displayed on Add Pick popup");
		test.transactionQueueActions.clickAdditionalInfoToggle();
	}
	
	 @Test(priority = 10, description = "VPLX:Manual Pick[UI]-User verifies the destination dropdown on Add Pick page")
		public void Test10_1016409(Method method)
		{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Manual Pick[UI]-User verifies the destination dropdown on Add Pick page");
	     	//test.transactionQueueActions.verifyInputfield("Destination");
		    test.transactionQueueActions.verifyFieldIsMandatory("Destination");
		    test.siteConfigurationAction.verifyDropDownFieldOnAddSytemLabel("destination");
		}

	@Test(priority = 11, description = "VPLX:Manual Pick[UI]-The Additional Fields are displayed when User clicks on 'Additional Information' toggle on Add Pick popup")
		public void Test11_1016385(Method method)
		{
		ExtentTestManager.startTest(method.getName(),
		"VPLX:Manual Pick[UI]-The Additional Fields are displayed when User clicks on 'Additional Information' toggle on Add Pick popup");
	
	 
	 firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
	    test.siteConfigurationAction.enterRandomValueInInputField("firstname", firstname);
	    test.siteConfigurationAction.enterRandomValueInInputField("lastname", "LastName"+System.currentTimeMillis());
	    test.siteConfigurationAction.enterRandomValueInInputField("middlename","MiddleName"+System.currentTimeMillis());
	    test.siteConfigurationAction.enterRandomValueInInputField("room","10");
	    test.siteConfigurationAction.enterRandomValueInInputField("bed","02");
	    test.siteConfigurationAction.enterRandomValueInInputField("account",""+System.currentTimeMillis());
	    test.siteConfigurationAction.enterRandomValueInInputField("mrn",""+System.currentTimeMillis());
	    test.siteConfigurationAction.enterRandomValueInInputField("order",""+System.currentTimeMillis());
		
	    
		}  
	
	 @Test(priority = 13, description = "VPLX:Manual Pick[UI]-User verifies the Save & Close button on Add Pick page")
		public void Test12_Test13_1016410_1016400(Method method)
		{
			
			ExtentTestManager.startTest(method.getName(),
					"VPLX:Manual Pick[UI]-User verifies the Save & Close button on Add Pick page");
			//test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("priority", 1).replaceAll(" ", "");
			priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
					TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
			destination=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination",
					1);
		   
		   
		   /* test.transactionQueueActions.clickAdditionalInfoToggle();
		    firstname=test.transactionQueueActions.getAlphaNumericString(50);
		    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);*/
		    test.transactionQueueActions.verifyButtonAddPick("save_close_btn", "Save & Close");
		    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);

		}
	
	 
	
	
}
