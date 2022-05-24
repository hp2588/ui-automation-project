package com.org.tests.manualpick;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ManualPickNewTestcases extends BaseTest {

	String destination, firstname, priority,ISAshortName, itemName,itemName1
			, genericname, itemID, facilityOnWFAScreen, ISAName,ISAName1,QOH,computerName;

	@Test(priority = 1, description = "VPLX:Manual Pick: [UI]: User is able to login transaction queue.")
	public void Test01_1130456() {
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress"));
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"),
		// 0);
		facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();

		ISAName = test.storageAreaAction.getISANameOnWFAScreen();

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());
	}

	@Test(priority = 2, description = "VPLX: Manual Pick: [UI]-The Search item displays an accurate item details which match search term")
	public void Test02_1016538() {

		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
		test.transactionQueueActions.searchItemValue("itemID");
		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		 
		 itemName1 = itemName.split(" ")[0];
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		 
	}

	@Test(priority = 3, description = "VPLX: Manual Pick: [UI]: An error is received when user enters the Quantity as zero")
	public void Test03_1042614() {
		Assert.assertEquals(test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "0"), "",
				"[Assertion Failed]: User is able to enter 0 quantity");

	}

	@Test(priority = 4, description = "VPLX: Manual Pick: [UI]: The user can enter value of MRN and Order number upto 20 characters only")
	public void Test04_1042638() {
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		priority =/* test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("priority", 1).replaceAll(" ",
				"");
*/test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
		TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		destination = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.siteConfigurationAction.enterRandomValueInInputField("firstname", firstname);

		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("mrn"), 20,
				"[ASSERTION FAILED]: Max Length for input field MRN is not 20");

	}

	@Test(priority = 5, description = "VPLX: Manual Pick: [UI]: User is able to do Add Pick from Actions tab.")
	public void Test05_1130501() {
		test.transactionQueueActions.verifyButtonAddPick("save_close_btn", "Save & Close");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTransaction(firstname, destination, priority);

	}

	@Test(priority = 6, description = "VPLX:Manual Pick: [UI]:Correct QOH and location of an item is displayed in the search result of add pick.")
 	public void Test06_1130496() {
  	 
  	/*	 
  		=============CREATE ITEM===================

 		test.landingPageActions.navigateToMenu("Main Menu");
 		test.landingPageActions.navigateToFeature("Item Management");
 		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
 		test.siteConfigurationAction.clickActionbutton("Actions");
 		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
 		genericname=	test.siteConfigurationAction.enterDataInInputField("genericName",
 				"Systemlevelfacility" + System.currentTimeMillis());
 		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
 				"SystemlevelItem" + System.currentTimeMillis());
 		test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
 		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
 		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
 		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
 		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
 	
 		test.siteConfigurationAction.clickButton("save");*/
		
test.landingPageActions.navigateToMenu("Main Menu");
		
		test.landingPageActions.navigateToFeature("Computers");
		test.siteConfigurationAction.verifyandClickAddComputerButton();
		test.siteConfigurationAction.verifyAddComputerPopup("Add Computer");
		test.siteConfigurationAction.clickRadioComputerButton();
		test.siteConfigurationAction.verifyFields();
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey", 1);
		
		test.siteConfigurationAction.selectValueFromDropDown("defaultFacilityKey", facilityOnWFAScreen);
		computerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("computerName",
				"AutomationUI-Computer" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		//test.siteConfigurationAction.clickCheckboxTransactionPriorities("useScanFixFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("controlCaraouselISA");
		/*test.siteConfigurationAction.EnterValueInMACAddressField("macaddress_text",
				getData("ComputerDetails.MACAddress"));*/
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(computerName);
 		
 		
 	// Create ISA 1 
 			test.landingPageActions.navigateToMenu("Main Menu");
 			test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
 			test.supportDataActions.verifyLabelIsPresent("ISAs");
 			test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
 			test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
 			test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");
 			
 			test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
 			
 			test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
 			test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
 			
 			test.siteConfigurationAction.selectRadioOption("isStaticFlag");
 			test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
 			test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
 			test.supportDataActions.verifyTabIsDisplayed("Display Settings");
 			test.supportDataActions.verifyTabIsDisplayed("Approved Computers");
 			
 			test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
 					"30");
 			
 			
 			test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
 			test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
 			 test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);

 			 ISAName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
 					"Name" + System.currentTimeMillis());
 			ISAshortName= test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
 					"shortName" + System.currentTimeMillis());

 			// test.siteConfigurationAction.selectValueFromDropDownByIndex("workstationComputerKey",1);
 			//test.siteConfigurationAction.selectValueFromDropDownByIndex("logisticsLabelPrinterKey",1);

 			test.siteConfigurationAction.clickTab("Carousel Settings");
 			
 			Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("carouselKey"),
 					"[ASSERTION FAILED]: Type drop down is not mandatory");
 			test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey", 1);
 			
 			test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("devicenumber");
 			 test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("devicenumber",getData("AddISA.Device")
 					);
 			
 			 test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddressValue",
 					TestDataPropertyReaderAndWriter.getProperty("IPAddress"));

 			 test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("portNumber", getData("AddISA.Port"));
 			test.siteConfigurationAction.clickTab("ISA Configuration");
 			test.siteConfigurationAction.clickTab("Display Settings");

 			test.storageAreaAction.clickTab("Approved Computers");
 			test.storageAreaAction.verifyPageTitleContains("Approved Computers".trim());
 			if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
 				test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
 				Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
 			}
 			test.storageAreaAction.verifyButtonIsEnabled("Add");
 			
 			test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
 			test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
 			test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
 			
 			test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);

 			test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");

 			test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
 			test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
 			test.siteConfigurationAction.selectRadioOption("insideFlag");

 			test.supportDataActions.selectValueFromDropdownByIndex("Computer", 1);
 			test.supportDataActions.selectValueFromDropdownByIndex("printer", 1);
 			test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
 			

 			test.siteConfigurationAction.clickSaveButton();
 			
 			 			test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ISAName1);
 		
 		/*=============ASSIGN LOCATION TO THE ITEM with 'MAX Quantity' > QOH===================*/
 		
 		test.landingPageActions.navigateToFeature("Main Menu");
 		test.landingPageActions.navigateToFeature("Item Locations");
 		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",facilityOnWFAScreen);
 		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName1,"search");
 		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName1);
 		
 		
 		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
 		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
 		
 		test.siteConfigurationAction.selectValueForDropDown("facility", facilityOnWFAScreen);
 		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName1);
 		
 		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
 		test.siteConfigurationAction.clickAssignLocationButton();
 		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
 		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "200");
 		QOH=test.siteConfigurationAction.enterAndReturnValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
 		//test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
 		test.siteConfigurationAction.clickSaveButton();
 		test.landingPageActions.navigateToMenu("Transaction Queue");
 		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
 		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
 		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
 				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
 		test.storageAreaAction.verifyStartWorkButtonAndClick();
 		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
 		
 		/*==================Add Pick Transaction such that 'Max Quantity' < QOH==================*/
 		
 		test.transactionQueueActions.verifyAndClickAddPick();

 		test.transactionQueueActions.searchItemValue(itemName1);
 		
 		test.transactionQueueActions.verifyItemIsDisplayed(QOH);
 		
 		Assert.assertTrue(test.transactionQueueActions.verifyLocationIsPresent(ISAshortName)
 				,"[ASSERTION FAILED]: Location is not displayed for searched Item on Add Pick Screen.");
 		
 		

  	 
	}
	
	@Test(priority = 7, description = "VPLX: Manual Pick: [UI]-User is able to search an item that is assigned to multiple locations.")
 	public void Test07_1130471() {
		
		//test.transactionQueueActions.verifySearchedResult("Item Name", itemName1);
		test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		
	}
	
	@Test(priority = 8, description = "VPLX:Manual Pick[UI]:All records of the item are fetched that is assigned to multiple locations.")
 	public void Test08_1130491() {
		
		test.transactionQueueActions.clickSearchedItemValue(itemName1);

 		//itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
 		
 		// itemName1 = itemName.split(" ")[0];
 		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
	}
	
	
	
	
	
	@Test(priority = 9, description = "VPLX:Manual Pick: UI: Create transaction for the item that is assigned to multiple locations.")
 	public void Test09_1129869() {
		
		
 	
 		
 		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("quantity", getData("AddPick.Quantity"));
		priority = /*test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("priority", 1).replaceAll(" ",
				"");*/
				test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
						TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		destination = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.siteConfigurationAction.enterRandomValueInInputField("firstname", firstname);
		
		test.transactionQueueActions.verifyButtonAddPick("save_close_btn", "Save & Close");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");

 		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
	    test.transactionQueueActions.verifyTransaction(firstname, destination, priority);
	}
	
	
  	  
  	

}
