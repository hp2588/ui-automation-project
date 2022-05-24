package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class AStar_TQ extends BaseTest {

	Integer NumberOfISA;
	String itemName1,itemID,itemName, barcode, productID, destination, firstname, quantityEntered,ISAName=TestDataPropertyReaderAndWriter.getProperty("ISAName").trim(),facilityOnWFAScreen=TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),priority,hold_reason;
	String ISAName1, shortName1, ISAName2, shortName2, ISAName3;

	@Test(priority = 1, description = "VPLX : Transaction Queue: Tranqueue withstand multiple workstations to to process a transaction.")
	public void Test01_1121593(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Tranqueue withstand multiple workstations to to process a transaction.");
		
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
		//test.transactionQueueActions.verifyActiveTransactionBox();
		test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
	   	Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),""
				+ "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");
	}
	
	
	@Test(priority = 2, description = "VPLX : System creates the Inventory Move/Restock transactions as soon as a location falls below par.")
	public void Test02_1121567(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : System creates the Inventory Move/Restock transactions as soon as a location falls below par.");
		
		/* ================Item creation ================= */
		test.landingPageActions.navigateToFeature("Item Management");

		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		
		test.supportDataActions.clickButton("cancel");
		
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		// Create ISA 1 
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		// test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		ISAName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());

		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber","2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber","2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber","3");
		Thread.sleep(3000);
		//test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		
		/* * Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory(
		 * "carouselKey"), "[ASSERTION FAILED]: Type drop down is not mandatory");
		 * test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey",
		 * 1); test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup(
		 * "devicenumber"); deviceNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "devicenumber", getData("AddISA.Device"));
		 * 
		 * ipAddress =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "ipAddressValue", getData("AddISA.IPAddress")); portNumber =
		 * test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		 * "portNumber", getData("AddISA.Port"));
		 */
		// test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic);

		test.storageAreaAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		//test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("facilities",
		//		getData("AddISA.FacilityList"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ISAName1);

		 //Create ISA 2 
		
		test.supportDataActions.clickOnAddButtonToAddNewISA("Add ISA");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isCarouselFlag");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("isStaticFlag");

		// test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");

		test.siteConfigurationAction.selectRadioOption("isStaticFlag");
		test.supportDataActions.verifyTabIsNotDisplayed("Carousel Settings");
		test.supportDataActions.verifyTabIsDisplayed("ISA Configuration");
		test.supportDataActions.verifyTabIsDisplayed("Display Settings");
		test.supportDataActions.verifyTabIsDisplayed("Approved Computers");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("transactionQueueLockExpirationValue",
				"30");

		// test.siteConfigurationAction.selectRadioOption("isCarouselFlag");
		// test.supportDataActions.verifyTabIsDisplayed("Carousel Settings");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		ISAName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("name",
				"Name" + System.currentTimeMillis());
		shortName2 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("input",
				"shortName" + System.currentTimeMillis());

		test.siteConfigurationAction.selectValueForDropDown("workstationComputerKey",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName").trim());
		test.siteConfigurationAction.selectValueForDropDown("logisticsLabelPrinterKey",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName").trim());

		test.siteConfigurationAction.clickTab("ISA Configuration");
		test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersHorizontalNumber","2");
		test.storageAreaAction.enterDataInInputField("defaultBinDividersVerticalNumber","2");
		test.storageAreaAction.enterDataInInputField("maxRackNumber", "3");
		test.storageAreaAction.enterDataInInputField("maxShelvesNumber","3");
		Thread.sleep(3000);
		//test.storageAreaAction.enterDataInInputField("maxBinNumber","1");
		
		/* Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory(
		  "carouselKey"), "[ASSERTION FAILED]: Type drop down is not mandatory");
		  test.siteConfigurationAction.selectValueFromDropDownByIndex("carouselKey",
		  1); test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup(
		  "devicenumber"); 
		  deviceNumber =
		  test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		  "devicenumber", getData("AddISA.Device"));
		  
		  ipAddress =
		  test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		  "ipAddressValue", getData("AddISA.IPAddress")); portNumber =
		  test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup(
		  "portNumber", getData("AddISA.Port"));*/
		 
		// test.siteConfigurationAction.clickTab("ISA Configuration");
		test.siteConfigurationAction.clickTab("Display Settings");
		test.siteConfigurationAction.clickTab("Approved Computers");
		if (!(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"))) {
			test.storageAreaAction.clickCheckboxTransactionPriorities("restrictControlFlag");
			Assert.assertTrue(test.storageAreaAction.verifyCheckboxIsCheckedApprovedComputer("restrictControlFlag"));
		}
		
		test.storageAreaAction.clickButtonOnApprovedComputerPage("Add");
		test.storageAreaAction.verifyApprovedComputerPopupPage("Add Approved Computer");
		System.out.println("Got THE DATA " + getData("ISAApprovedComputers.ComputerStatic"));
		String ComputerStatic2 = test.siteConfigurationAction.getAllDataFromDropDown("Computer").get(1);
		test.siteConfigurationAction.selectValueForDropDown("Computer",
				TestDataPropertyReaderAndWriter.getProperty("ComputerName"));
		test.siteConfigurationAction.selectValueForDropDown("printer",
				TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		test.storageAreaAction.addApprovedComputersByClickingonPopup("Add");
		test.storageAreaAction.verifyRecordNameIsAvailableInTheList(ComputerStatic2);

		test.storageAreaAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		//test.siteConfigurationAction.selectValueFromDropDownForExternalSystem("facilities",
		//		getData("AddISA.FacilityList"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ISAName2);

		
		//Assign Location A to Item 
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName1);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "100");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		//test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		
		//Assign Location B to Item 
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName2);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "100");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		//test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		//Assign Location C to Item 
		/*test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",facilityOnWFAScreen );
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName3);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "100");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		test.siteConfigurationAction.clickSaveButton();*/
	}
	
	
	@Test(priority = 3, description = "VPLX :Pick: System does not allow user to submit 0 quantity for pick.")
	public void Test03_1121512(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : System does not allow user to submit 0 quantity for pick.");
		
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(facilityOnWFAScreen);
		
		itemID = test.siteConfigurationAction.getItemNameHavingLocationAssigned();
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyAndClickAddPick();
		
		test.transactionQueueActions.searchItemValue("itemID");
		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		 
		 itemName1 = itemName.split(" ")[0];
		 test.transactionQueueActions.verifySearchResults(itemName1,
				  "1");
		test.transactionQueueActions.clicksearchedItemValue(itemName1, "1");
		
		//test.transactionQueueActions.searchItemValue("ItemID1588005335184");
		test.transactionQueueActions.searchItemValue(itemID);
		test.transactionQueueActions.verifySearchedResult("Item Name", itemID);
		//test.transactionQueueActions.verifySearchedResult("Item Name", "ItemID1588005335184");
		test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		Assert.assertEquals(test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "0"), 
				"", "[Assertion Failed]: User is able to enter 0 quantity");
	}
	
	
	@Test(priority = 4, description = "VPLX :Transaction Queue: Active Item Quantity or Quantity on Hand is editable")
	public void Test04_1121505(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Active Item Quantity or Quantity on Hand is editable");
		
		quantityEntered = test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "5");
		test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());
				
		destination = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		
		/* 
		 * test.transactionQueueActions.verifyActiveTransactionBoxItemName(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		*/
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
	   	Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),""
				+ "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");
	   	
		test.transactionQueueActions.clickScanOverrideOnce();
		test.transactionQueueActions.clickOnQuantity("3");
		Assert.assertTrue(test.transactionQueueActions.clickScanOverrideOnce());
	}
	
	
	@Test(priority = 5, description = "VPLX : Notification is recieved when user tried to inactive a hold reason"
			+ " that is currently assigned to a transaction in the transaction queue")
	public void Test05_1121635(Method method) throws Throwable {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Notification is recieved when user tried to inactive a hold reason "
				+ "that is currently assigned to a transaction in the transaction queue");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.supportDataActions.verifyLabelIsPresent("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(facilityOnWFAScreen, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		//test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit Facility", facilityOnWFAScreen);
		//test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord("Edit Facility", "Fac1588002935976");
		test.siteConfigurationAction.clickTab("Settings");
		//test.siteConfigurationAction.verifyOtherfieldsOptionsareVisible();
		test.siteConfigurationAction.uncheckCheckBox("requireHoldReasonFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("requireHoldReasonFlag");
		//test.siteConfigurationAction.selectCheckboxItemsTab("requireHoldReasonFlag", true);
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("Success! Changes have been saved.");
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		test.transactionQueueActions.clickFirstTransaction();
		//Assert.assertFalse(test.transactionQueueActions.verifyButtonisDisabled("Hold"));
		test.transactionQueueActions.clickHoldButton("Hold");
		test.transactionQueueActions.verifyPageHeader("Hold Item");
		hold_reason=test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("holdReasonId", 1);
		test.transactionQueueActions.clickConfirmHoldButton();
		//	test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader("Transaction put on hold", 90, 1);
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
			"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToFeature("Hold Reasons");
		test.supportDataActions.verifyLabelIsPresent("Hold Reasons");
		test.supportDataActions.clickEditLinkInTable(hold_reason);
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(hold_reason,"Edit Hold Reason");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		//	test.siteConfigurationAction.verifyErrorMessageForAlreadyExistingExternalSystem("The selected hold reason is assigned to one or more transactions in the transaction queue. Please process these transactions before changing hold reason state");
		
	}
	
	
	@Test(priority = 6, description = "VPLX : Restock: System requests expiration and lot number "
			+ "during a restock if the Facility, ISA and item all are configured "
			+ "to require lot/expiration during restock.")
	public void Test06_1121603(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"System requests expiration and lot number during a restock if the Facility, ISA and item all are configured to require lot/expiration during restock.");
		//test.siteConfigurationAction.clickButton("cancel");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(facilityOnWFAScreen, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.uncheckCheckBox("requestRestockLotInfoFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("requestRestockLotInfoFlag");
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("ISAs (Inventory Storage Areas)");
		
		test.siteConfigurationAction.clickISAEditLink(ISAName);
		test.siteConfigurationAction.uncheckCheckBox("reqRestockLotInfoFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("reqRestockLotInfoFlag");
		
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(facilityOnWFAScreen);
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		
		test.siteConfigurationAction.clickOnItemManagementFacility(facilityOnWFAScreen);
		

		test.siteConfigurationAction.uncheckCheckBox("requestRestockLotInfoFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("requestRestockLotInfoFlag");
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		//test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		test.transactionQueueActions.searchItemValue(itemID);
		
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				itemID);
		itemName=test.transactionQueueActions.getAddedItemNameFromAddRestockForm();
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(itemID);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				"Receiving");
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restock");
		
		String[] itemName_parsed=itemName.split(" ");
		String ItemNameNew=itemName_parsed[0];
		System.out.println("ItemNameNew=" +ItemNameNew);
		test.transactionQueueActions.clickManualRestockTransactionBasedOnPriortiyAndItemName("Receiving", ItemNameNew);
		test.transactionQueueActions.enterRestockItemPopUpDetails();
		test.siteConfigurationAction.clickButton("add");
		
	}
	
	
	@Test(priority = 7, description = "VPLX : Restock: System does not allow to enter the duplicate "
			+ "Lot/Exp date for same item while receiving/restocking.")
	public void Test07_1121560(Method method) {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : System does not allow to enter the duplicate Lot/Exp date for same item while receiving/restocking.");

		test.transactionQueueActions.enterRestockItemPopUpDetails();
		test.siteConfigurationAction.clickButton("add");
		test.transactionQueueActions.verifyRecordCount();
		//	test.transactionQueueActions.enterPopUpDetails();
	}
	
	
	@Test(priority = 8, description = "VPLX : Item location: Alert message is displaying when "
			+ "user tries to remove a location for an item for which pending restock/pick/cyclecount "
			+ "transaction(s) are available in Transaction Queue")
	public void Test08_1121632(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Alert message is displaying when user tries to remove a location for an item for which pending restock/pick/cyclecount transaction(s) are available in Transaction Queue");
		
		/*check checkboxes for cycle count Tab by editing Facility */
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.supportDataActions.verifyLabelIsPresent("Facilities");
		test.supportDataActions.enterSearchTermInSearchFieldGl(facilityOnWFAScreen, "search");
		
		 test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityOnWFAScreen);
		//test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.siteConfigurationAction.clickTab("Cycle Counts");
		//test.siteConfigurationAction.verifyOtherfieldsOptionsareVisible();
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("autoGenCycleCountFlag", true);	 
		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader("Success! Changes have been saved.");
		
		/*Enter cycle count interval for facility mapped item*/ 
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				facilityOnWFAScreen);
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		//test.siteConfigurationAction.clickEditLinkOnItemManagement(itemID);
		test.siteConfigurationAction
				.clickOnItemManagementFacility(facilityOnWFAScreen);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("cycleCountIntervalDayAmount", "1");
		test.siteConfigurationAction.clickButton("save");
		
		
		/*=============Assign Item Location=======================*/
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
		//test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",facilityOnWFAScreen );
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickEditLocation("unassign", "1");
		
		test.siteConfigurationAction.confirmPopupOnUnassign(getData("EditItemLocation.UnassignHeader"));	
	}
	
	
	@Test(priority = 9, description = "VPLX :Transactions queue: Processing Transactions tied to a Cycle Count "
			+ "will not have a delay in making the next item in the queue Active.")
	public void Test09_1121617(Method method) throws InterruptedException {
		ExtentTestManager.startTest(getClass().getName() + " :: " + method.getName(),
				"VPLX : Processing Transactions tied to a Cycle Count will not have a delay in making the next item in the queue Active.");
	
        /*===================Navigate to TQ to verify Cycle Count transaction is created==============*/
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		//Thread.sleep(300000);
		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName("CYCLE", itemName);
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));	
	}
	
	
}
