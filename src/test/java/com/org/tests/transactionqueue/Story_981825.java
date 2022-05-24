package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_981825 extends BaseTest {
	
	String priority, destination, firstname;
	String systemLabelName;
	String priorityName, code, priorityNameRestock, priorityCodeRestock, priorityNameReturn, priorityCodeReturn;

	/*=========This Test Case is obsolete now======================*/
	
	@Test(priority = 1, description = "VPLX:Transaction Queue-Pick-UI:Tabs count is displayed as per transaction")
	public void Test01_998803(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI:Tabs count is displayed as per transaction");
		
		/*===================Create Priority=================*/
		
		/*test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Priorities");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		priorityName = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityName",
				"Name" + System.currentTimeMillis());
		code = test.siteConfigurationAction.enterRandomValueInInputField("transactionPriorityCode",
				"Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickInputButton("colorbutton form-group");
		test.siteConfigurationAction.enterRandomValueInInputField("maxHoldMinutes", "200");
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
		TestDataPropertyReaderAndWriter.setProperty("PriorityCode", code);*/
		
		/*===================Create Standard Label and map priority=================*/
		
		/*test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Standard Labels");
		test.siteConfigurationAction.clickAddButtonToAddNewSystemLabel("Add a new Standard Label");
		systemLabelName=test.siteConfigurationAction.enterDataInInputField("labelName", getData("SystemLabel.LabelName")+System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDown("facility",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("stock",1);
		test.siteConfigurationAction.selectValueFromDropDown("category","Auto Dispensing Cabinet");
		test.siteConfigurationAction.selectPriority(TestDataPropertyReaderAndWriter.getProperty("PriorityName"), true);
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);
		test.siteConfigurationAction.enterSearchTermInSearchField(systemLabelName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(systemLabelName);*/
		
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		for(int i=0;i<2;i++){
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
				test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		//test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		}
		
		
		/*test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");*/
		/*test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA2"), 1);
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"), 0);*/
		//test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		/*test.transactionQueueActions.verifyAndClickAddPick();
		test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",getData("AddPick.searchItemName"));
  	    test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		destination=test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",getData("AddPick.Priority"));
	    destination=test.transactionQueueActions.selectDropdownForAddPick("Destination", getData("AddPick.Destination"));
		test.transactionQueueActions.clickAdditionalInfoToggle();
	    firstname="UI_"+ test.transactionQueueActions.getAlphaNumericString(4);
	    test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
	    test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
	    test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		Assert.assertTrue(test.transactionQueueActions.verifyTabName(getData("TQ.pickQueue")));*/
	
		//Assert.assertTrue(test.transactionQueueActions.verifyTabCount());
		
		

	}

	@Test(priority = 2, description = "VPLX:Transaction Queue-Pick-UI: Pick now button throws error when multiple checkboxes are selected")
	public void Test02_999473(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: Pick now button throws error when multiple checkboxes are selected");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyButtonisDisabled("Pick Now"));

	}

	@Test(priority = 3, description = " VPLX:Transaction Queue-Pick-UI: On mouse hover transaction gets highlighted in the background as per the color given in VD")
	public void Test03_998220(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" VPLX:Transaction Queue-Pick-UI: On mouse hover transaction gets highlighted in the background as per the color given in VD");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		// test.transactionQueueActions.deselectAllTransaction();
		// test.transactionQueueActions.clickFirstTransaction();
		Assert.assertTrue(test.transactionQueueActions
				.verifyTransactionQueueBackgroundColorOnHover(getData("TQ.backgroundColor")));

	}

	

	@Test(priority = 4, description = "VPLX: Transaction Queue-Pick-UI: Valid item name is reflected under Current Pick"
			+ ""
			+ "VPLX:Transaction Queue-Pick-UI:Valid Item Location is reflected for selected transaction under current pick section"
			+ "VPLX:Transaction Queue-Pick-UI: The selected transaction from Transaction queue becomes active when corresponding Pick Now button is clicked"
			+ "VPLX:Transaction Queue-Pick-UI:Transaction status below the medicine name and status at the rightmost panel match"
			+ "VPLX:Transaction Queue-Pick-UI:Valid destinations is reflected for selected transaction under current pick section.")
	public void Test04_Test05_Test06_Test07_Test08_998694_999667_999466_999670_998699() {
				Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		 test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionLocation());
	}

	/*
	 * @Test(priority = 6, description =
	 * " VPLX:Transaction Queue-Pick-UI:Transaction status below the medicine name and status at the rightmost panel match"
	 * ) public void Test06_999670(Method method) {
	 * ExtentTestManager.startTest(method.getName(),
	 * "VPLX:Transaction Queue-Pick-UI:Transaction status below the medicine name and status at the rightmost panel match"
	 * ); Assert.assertTrue(test.transactionQueueActions.
	 * verifyUserIsOnTransactionQueuePage()); Assert.assertEquals(true,
	 * test.transactionQueueActions.verifyTransactionStatus()); }
	 */
	/*@Test(priority = 7, description = "VPLX: Transaction Queue-Pick-UI: Valid item name is reflected under Current Pick")
	public void Test07_998694(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Transaction Queue-Pick-UI: Valid item name is reflected under Current Pick");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
	}*/

	@Test(priority = 9, description = "VPLX:Transaction Queue-Pick-UI:Sorting is available for type, Item, Patient name and Destination in Queue section")
	public void Test09_998707(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI:Sorting is available for Xtype, Item, Patient name and Destination in Queue section");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Priority"));
		Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Item"));
		Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Patient name"));
		Assert.assertTrue(test.transactionQueueActions.verifySortingIsAvailableForColumnHeader("Destination"));
	}

	@Test(priority = 10, description = "VPLX:Transaction Queue-Pick-UI: All the transactions are visible under Transaction Queue")
	public void Test10_999475(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI: All the transactions are visible under Transaction Queue");
		test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());

	}

	@Test(priority = 15, description = "VPLX:Transaction Queue-Pick-UI: 'Pick Now' button on the top gets enabled when selecting any of the checkbox."
			+ "VPLX:Transaction Queue-Pick-UI: The selected transaction from Transaction queue becomes active when Pick Now button on the top panel is clicked"
			+ ""
			+ "VPLX:Transaction Queue-Pick-UI: Correct value of 'on hand' is displayed for the selected transaction in current pick section."
			+ "VPLX:Transaction Queue-Pick-UI:Selected quantity is displayed for 'Active' transaction under current pick section."
			+ "VPLX:Transaction Queue-Pick-UI:Current Status is reflected for selected transaction under current pick section."
			)
	public void Test11_Test12_Test13_Test14_Test15_998226_999651_998711_998700_998698() {
				Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		//test.transactionQueueActions.clickHoldButton("Pick");
		test.transactionQueueActions.clickFirstPickNow();
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
	}

	@Test(priority = 16, description = "VPLX:Transaction Queue-Pick-UI:Color of Current Pick queue is as per the Transaction priority.")
	public void Test16_998701(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI:Color of Current Pick queue is as per the Transaction priority");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();		
		String tqColor = test.transactionQueueActions.getColorOfFirstTransactionAndMakeItActive();
		Assert.assertTrue(test.transactionQueueActions.verifyColorCurrentPickIsAsPerTransactionPriority(tqColor));

	}

	@Test(priority = 21, description = "VPLX:Transaction Queue-Pick-UI: Pick now button throws error when multiple checkboxes are selected"
			+ "VPLX:Transaction Queue-Pick-UI: All checkboxes are selected after clicking on 'Select All' checkbox"
			+ "VPLX:Transaction Queue-Pick-UI: Select All checkbox gets deselected after deselecting any of the selected checkbox")
	public void Test17_Test18_Test19_Test20_Test21_999658_998184_998690_998692_998188() {
		
		/*===TC : 998184,998690,998692=====*/
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		//test.transactionQueueActions.selectAllPickQueueCheckboxes();
		Assert.assertTrue(test.transactionQueueActions.verifyButtonisDisabled("Pick Now"));

	}
	
	@Test(priority = 22, description = " VPLX:Transaction Queue-Pick-UI:When transactions are already selected, on mouse hover the another transaction gets highlighted in the background as per the color given in VD")
	public void Test22_999665(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI:When transactions are already selected, on mouse hover the another transaction gets highlighted in the background as per the color given in VD");
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
		Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());
		Assert.assertEquals(true, test.transactionQueueActions
				.verifySelectedTransactionBackgroundColor(getData("TQ.selectedTransactionColor")));
	}
}
