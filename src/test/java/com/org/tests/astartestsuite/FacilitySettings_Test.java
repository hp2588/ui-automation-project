package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class FacilitySettings_Test extends BaseTest {
	int previous_count, after_count, previous_holdcount;
	ArrayList<String> previous_data, after_data;
	String manual_pick_priority, destination, firstname, manual_restock_priority, receiving_priority, return_priority,priority,location;
	String itemID, itemName, barcode, productID;
	String PrepackitemName;
	
	
	@Test(priority = 1, description = "VPLX : Facility Settings (Reprint Label on Quantity Change - Pick) : System re-prints the pick label when the user changes the pick quantity when 'Reprint Label on Quantity Change' is enabled on Facility.")
	public void Test01_1116969(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Facility Settings (Reprint Label on Quantity Change - Pick) : System re-prints the pick label when the user changes the pick quantity when 'Reprint Label on Quantity Change' is enabled on Facility.");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("printLabelOnQuantityChangeFlag", true);
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyAndClickAddPick();
		// test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResult("Item
		// Name",getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		// priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddPick.Priority"));
		manual_pick_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ManualPickPriorityName").trim());
		// destination=test.transactionQueueActions.selectDropdownForAddPick("Destination",
		// "Destination");
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.verifyTransaction(firstname, destination, manual_pick_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));
		test.transactionQueueActions.updateOnHandQuantity("1");
	}

	@Test(priority = 2, description = " Facility Setting (Restock/Return Printer) : The system print the restock transactions labels when printer is selected under Restock/Return Printer dropdown. ")
	public void Test02_1116970(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting (Restock/Return Printer) : The system print the restock transactions labels when printer is selected under Restock/Return Printer dropdown.");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("manualRestockPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey",
				getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		// test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResultForReturn("Item
		// Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		// priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddRestock.TransactionPriority"));
		manual_restock_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ManualRestockPriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyTransaction(firstname, destination, manual_restock_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getRestockTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	}

	@Test(priority = 3, description = " Facility Setting (Receiving Printer) : The system print the receiving priority transaction label  when printer is selected under \"Receiving Printer\" dropdown.")
	public void Test03_1116972(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" Facility Setting (Receiving Printer) : The system print the receiving priority transaction label  when printer is selected under \"Receiving Printer\" dropdown. ");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("receivingPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("receivingPrinterKey", getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyAndClickAddPick();
		// test.transactionQueueActions.searchItemValue(getData("AddPick.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResult("Item
		// Name",getData("AddPick.searchItemName"));
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValue(getData("AddPick.searchItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		// priority=test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddPick.Priority"));
		receiving_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ReceivingPriorityName").trim());
		// destination=test.transactionQueueActions.selectDropdownForAddPick("Destination",
		// "Destination");
		destination = test.transactionQueueActions.selectDropdownForAddPick("Destination",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		test.transactionQueueActions.verifyTransaction(firstname, destination, receiving_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	}

	@Test(priority = 4, description = "  Facility Setting (Restock/Return Printer) : The system print the return transaction labels when printer is selected under \"Restock/Return Printer\" dropdown..")
	public void Test04_1117117(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"  Facility Setting (Restock/Return Printer) : The system print the return transaction labels when printer is selected under \"Restock/Return Printer\" dropdown. ");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("manualRestockPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey",
				getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		// test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResultForReturn("Item
		// Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		// priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddRestock.TransactionPriority"));
		return_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ReturnPriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyTransaction(firstname, destination, manual_restock_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getRestockTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	}

	@Test(priority = 5, description = "Facility Setting ( Restock/Return Printer - TQ ) : On click on \"Reprint label\",  the system print the return transactions labels from printer which is selected under \"Restock/Return Printer\" dropdown.")
	public void Test05_1117118(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting ( Restock/Return Printer - TQ ) : On click on \"Reprint label\",  the system print the return transactions labels from printer which is selected under \"Restock/Return Printer\" dropdown.");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("manualRestockPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey",
				getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		// test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResultForReturn("Item
		// Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		// priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddRestock.TransactionPriority"));
		return_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ReturnPriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyTransaction(firstname, destination, manual_restock_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getRestockTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		test.transactionQueueActions.clickActiveTransactionButtons("Reprint Label");

	}

	@Test(priority = 6, description = "Facility Setting ( Restock/Return Printer - TQ ) : On click on \"Reprint label\",  the system print the restock transactions labels from printer which is selected under \"Restock/Return Printer\" dropdown.")
	public void Test06_1117141(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting ( Restock/Return Printer - TQ ) : On click on \"Reprint label\",  the system print the restock transactions labels from printer which is selected under \"Restock/Return Printer\" dropdown.");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("manualRestockPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey",
				getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");
		// test.transactionQueueActions.searchItemValue(getData("AddReturn.searchItemName"));
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.verifySearchedResultForReturn("Item
		// Name",getData("AddReturn.searchItemName"));
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		// test.transactionQueueActions.clickSearchedItemValueForReturn(getData("AddRestock.ValidItemName"));
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity",
				getData("AddRestock.ValidQuantity"));
		// priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction
		// Priority", getData("AddRestock.TransactionPriority"));
		manual_restock_priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("ManualRestockPriorityName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyTransaction(firstname, destination, manual_restock_priority);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getRestockTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.makeFirstRestockTransactionActive());
		test.transactionQueueActions.verifyActiveTransactionBox();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));
		test.transactionQueueActions.clickActiveTransactionButtons("Reprint Label");

	}

	@Test(priority = 7, description = " Facility Setting ( Bin Label Printer - Location & Inventory Mgmt - ISA Map ) : When printer is selected under \"Bin Label Printer\" dropdown, the system print the bin labels for the items assigned to a shelf.")
	public void Test07_1116971(Method method) {
		ExtentTestManager.startTest(method.getName(),
				" Facility Setting ( Bin Label Printer - Location & Inventory Mgmt - ISA Map ) : When printer is selected under \"Bin Label Printer\" dropdown, the system print the bin labels for the items assigned to a shelf. ");

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyDropDownFieldOnEditRestockPrinterPopup("binLabelPrinterKey");
		test.siteConfigurationAction.selectValueForDropDown("binLabelPrinterKey", getData("Facility.RestockPrinter1"));
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction
				.selectFacilityDropdown(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction
				.enterSearchTextForLocation(TestDataPropertyReaderAndWriter.getProperty("LocationName").trim());
		test.siteConfigurationAction
				.clickEditLinkItemLocation(TestDataPropertyReaderAndWriter.getProperty("LocationName").trim());
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.navaigateToSelectedShelf();
		test.siteConfigurationAction.clickShelfOption("Print Bin Labels");

	}

	// Partial Automation
	@Test(priority = 8, description = "VPLX : Facility Item Details (Restock rounding factor) : System uses the Restock Rounding Factor while generating prepack orders.")
	public void Test08_1117248(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Facility Item Details (Restock rounding factor) : System uses the Restock Rounding Factor while generating prepack orders.");

		// **************Create Prepack Item*********************************
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		// test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		PrepackitemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction
				.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorCode"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);

		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction
				.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickCheckboxForPrepackItems("prepackFlag");
		test.siteConfigurationAction.clickButton("save");

		// TestDataPropertyReaderAndWriter.setProperty("ItemName","itemName");

		// *********Assign
		// Location************************************************************************
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectFacilityDropdown("Seattle Grace".trim());
		// test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");

		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "110");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "0");
		test.siteConfigurationAction.clickSaveButton();

		// ********Edit Prepack Item************

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction
				.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterCostValue("prepackConversionFactor", "10");
		test.siteConfigurationAction.clickButton("save");

	}

	// should be a bug
	@Test(priority = 9, description = "Facility Setting(Enable receive-n-send for remote orders): Select checkbox of Enable receiven-send for remote orders")
	public void Test09_1117256(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting(Enable receive-n-send for remote orders): Select checkbox of Enable receiven-send for remote orders");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("receiveAndSendRemoteOrdersFlag", true);
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickEditLink(destination);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyCheckboxLabel("enableSplitOrders", "Enable Split Orders");
		test.siteConfigurationAction.verifyCheckboxLabel("enableReceiveNSend", "Enable Receive-N send");
		test.siteConfigurationAction.verifyWebOrderCheckboxIsDisabled("enableReceiveNSend");
		Assert.assertFalse(test.siteConfigurationAction.checkboxIsSelectedUsingJavascript("enableSplitOrders"));
		Assert.assertTrue(test.siteConfigurationAction.checkboxIsSelectedUsingJavascript("enableReceiveNSend"));
		test.siteConfigurationAction.clickButton("save");

	}

	@Test(priority = 10, description = "Facility Setting( Enable receive-n-send for remote orders): If we dont select Enable receiven-send for remote orders on facility setting page than we can select Enable receive-n-send\n"
			+ "for remote orders or Enable Split orders checkbox")
	public void Test10_1117269(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting( Enable receive-n-send for remote orders): If we dont select Enable receiven-send for remote orders on facility setting page than we can select Enable receive-n-send for remote orders or Enable Split orders checkbox");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("receiveAndSendRemoteOrdersFlag", false);
		test.siteConfigurationAction.clickButton("save");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickEditLink(destination);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyCheckboxLabel("enableSplitOrders", "Enable Split Orders");
		test.siteConfigurationAction.verifyCheckboxLabel("enableReceiveNSend", "Enable Receive-N send");
		test.siteConfigurationAction.verifyWebOrderCheckboxIsDisabled("enableReceiveNSend");
		Assert.assertFalse(test.siteConfigurationAction.checkboxIsSelectedUsingJavascript("enableSplitOrders"));
		if (!test.siteConfigurationAction.checkboxIsSelectedUsingJavascript("enableReceiveNSend")) {
			test.siteConfigurationAction.clickCheckboxForPrepackItems("enableReceiveNSend");
		}

		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.clickEditLink(destination);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyCheckboxLabel("enableSplitOrders", "Enable Split Orders");
		test.siteConfigurationAction.verifyCheckboxLabel("enableReceiveNSend", "Enable Receive-N send");
		test.siteConfigurationAction.verifyWebOrderCheckboxIsDisabled("enableReceiveNSend");
		test.siteConfigurationAction.verifyWebOrderCheckboxIsDisabled("enableSplitOrders");
	}
	
	@Test(priority = 11, description = "Facility Setting (Enable Receive-N-Send for remote orders) : when Enable Receive-N-Send\n" + "for remote orders checkbox is enabled at destination, orders placed from remote web page\n" +"are created under NEW orders at Dashboard")
	public void Test11_1117282(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Setting (Enable Receive-N-Send for remote orders) : when Enable Receive-N-Send\n" + "for remote orders checkbox is enabled at destination, orders placed from remote web page\n" + "are created under NEW orders at Dashboard");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("receiveAndSendRemoteOrdersFlag", true);
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickEditLink(destination);
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.enterItemNameForUsersOnDestination((getData("Auth.userNameWebOrderUser")));
		test.siteConfigurationAction.selectCheckboxForUsers("activeFlag");
		test.siteConfigurationAction.clickButton("save");
		
		test.loginPageAction._logoutApplication(getData("Auth.userNameWebOrderUser"), "logout", "confirm");
		test.loginPageAction.navigateToUrl(getData("Auth.weborder_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.selectDropdownForRO("selectDestination", "StarkHospital"+" - "+destination);
		test.siteConfigurationAction.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemID, "textValue");
		//test.siteConfigurationAction.clickAvailableItemOnRO(itemID,itemName);
		//test.siteConfigurationAction.enterItemQuantityOnROCard("1");
		test.siteConfigurationAction.clickButton("buildOrderSubmitButton");
		
		test.loginPageAction._logoutApplication(getData("Auth.userNameWebOrderUser"), "logout", "confirm");
		test.loginPageAction.navigateToUrl(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToFeature("Purchasing Dashboard");
		
		test.siteConfigurationAction.verifyOrderInPODashboard("New",itemName+"_RO");
		
		
	}
	
	@Test(priority = 12, description = "Facility Settings : When calculate fast mover prepack is checked, the system generates a\n" +"separate purchase order per distributor and drug class category for Fast Mover Prepack\n" +"items when distributor POs are created")
	public void Test12_1116944(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Facility Settings : When calculate fast mover prepack is checked, the system generates a\\n\" +\"separate purchase order per distributor and drug class category for Fast Mover Prepack\\n\" +\"items when distributor POs are created\"");


		// **************Create Prepack Item*********************************
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		// test.siteConfigurationAction.enterDataInInputField("genericName","Systemlevelfacilitybb");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		String FastMoverPrepackitemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction
				.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorCode"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorCode"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);

		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction
				.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickCheckboxForPrepackItems("prepackFlag");
		//*******************Assign Bulk Id**********************************
		test.siteConfigurationAction.clickBulkItemButton();
		test.siteConfigurationAction.setBulkItemId(TestDataPropertyReaderAndWriter.getProperty("itemID"));
		test.siteConfigurationAction.clickButton("save");
		
		
		// *********Assign Location************************************************************************
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectFacilityDropdown("Seattle Grace".trim());
		// test.supportDataActions.enterSearchTermInSearchFieldGl(itemID,"search");

		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "110");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "0");
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("StarkHospital", "Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectCheckboxItemsTab("calculatePrepackWithPurchaseOrderFlag", true);
		test.siteConfigurationAction.clickButton("save");

		
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.supportDataActions.SearchPOItem("Item Name", FastMoverPrepackitemName);
		//test.supportDataActions.verifyPOItemSearchResult();
		//FastMoverPrepackitemName=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(),true);
		
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		//test.supportDataActions.verifyPOItemSearchResult();
		//String medItem=test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(),true);
		
	}
	
	@Test(priority = 13, description = "VPLX: Facility Settings (Require hold reason - Pick) : When Require hold reason flag is checked,user is asked for a hold reason while putting pick transaction on hold.")
	public void Test13_1116942(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Facility Settings (Require hold reason - Pick) : When Require hold reason flag is checked,user is asked for a hold reason while putting pick transaction on hold.");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requireHoldReasonFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
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
		// test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		// test.transactionQueueActions.verifyTransaction(firstname, destination,
		// priority);
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		test.transactionQueueActions.verifyAndReleaseHoldTransaction(getData("TabActions.Release"),"Release");
		previous_holdcount = Integer.parseInt(test.transactionQueueActions.getTabCount(3));
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Pick"), 0);
		previous_count = previous_holdcount + 1;
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item7"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(3));
		Assert.assertEquals(previous_count, after_count, "[ASSERTION FAILED] : The hold count didnot match");
	}
	@Test(priority = 14, description = "VPLX: Facility Settings (Require hold reason - Restock) : When Require hold reason flag is checked,user is asked for a hold reason while putting restock transaction on hold.")
	public void Test14_1116943(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Facility Settings (Require hold reason - Restock) : When Require hold reason flag is checked,user is asked for a hold reason while putting restock transaction on hold.");
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
				TestDataPropertyReaderAndWriter.getProperty("PriorityRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		// test.transactionQueueActions.verifyTransaction(firstname, destination,
		// priority);
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		test.transactionQueueActions.verifyAndReleaseHoldTransaction(getData("TabActions.Release"),"Release");
		previous_holdcount = Integer.parseInt(test.transactionQueueActions.getTabCount(3));
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Restock"), 1);
		previous_count = previous_holdcount + 1;
		test.transactionQueueActions.verifyFirstActionLinkAndClick("Restock Now");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick(getData("ActionsItemsList.Item7"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("holdReasonId", 2);
		test.transactionQueueActions.enterHoldDescription();
		test.transactionQueueActions.clickConfirmHoldButton();
		test.transactionQueueActions.verifyActionTabAndClick(getData("TabActions.Held"), 3);
		after_count = Integer.parseInt(test.transactionQueueActions.getTabCount(3));
		Assert.assertEquals(previous_count, after_count, "[ASSERTION FAILED] : The hold count didnot match");
	}
	@Test(priority = 15, description = "VPLX : Facility Settings (Require destination selection for returns ): System requires to select the destination while submitting return when \"Request destination selection for returns\" flag is checked.")
	public void Test15_1116938(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX : Facility Settings (Require destination selection for returns ): System requires to select the destination while submitting return when \"Request destination selection for returns\" flag is checked.");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockDestinationFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityNameReturn").trim());
		location = test.transactionQueueActions.selectDropdownForAddPick("Source Location",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				getData("AddPick.Quantity"));
	}
	@Test(priority = 16, description = "VPLX: Facility Setting(Add returns to restock queue on hold): Added Return transaction will automatically move to Hold tab")
	public void Test16_1117165(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Facility Setting(Add returns to restock queue on hold): Added Return transaction will automatically move to Hold tab");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("returnsOnHoldDefinitionFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResultForReturn("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValueForReturn(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", getData("AddPick.Quantity"));
		priority = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityNameReturn").trim());
		location = test.transactionQueueActions.selectDropdownForAddPick("Source Location",
				TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("On Hold");
		test.transactionQueueActions.verifyReturnTransaction(TestDataPropertyReaderAndWriter.getProperty("ItemName"),
				getData("AddPick.Quantity"));
	}
	@Test(priority = 17, description = "VPLX: Facility Settings (Require Lot/Exp during Restock - Restock) : System requires users to input each item’s lot and expiration date during restock when 'Require Lot/Expiration' checkbox is checked at Item, facility and ISA.")
	public void Test17_1116960(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Facility Settings (Require Lot/Exp during Restock - Restock) : System requires users to input each item’s lot and expiration date during restock when 'Require Lot/Expiration' checkbox is checked at Item, facility and ISA.");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Inventory Storage Areas (ISAs)");
		test.siteConfigurationAction.clickISAEditLink("StaticISA");
		test.siteConfigurationAction.clickHoldReasonCheckbox("reqRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickEditLinkOnItemManagement("8050");
		test.siteConfigurationAction
				.clickOnItemManagementFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
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
				TestDataPropertyReaderAndWriter.getProperty("PriorityRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.clickManualRestockTrans();
		test.transactionQueueActions.enterPopUpDetails();
	}
	
	@Test(priority = 18, description = "VPLX: Facility Settings (Require Lot/Exp during Restock - Restock) : System requires users to input each item’s lot and expiration date during restock when 'Require Lot/Expiration' checkbox is checked at Item, facility and ISA.")
	public void Test18_1116960(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Facility Settings (Require Lot/Exp during Restock - Restock) : System requires users to input each item’s lot and expiration date during restock when 'Require Lot/Expiration' checkbox is checked at Item, facility and ISA.");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.clickFacilityEditLink(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Inventory Storage Areas (ISAs)");
		test.siteConfigurationAction.clickISAEditLink("StaticISA");
		test.siteConfigurationAction.clickHoldReasonCheckbox("reqRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.selectFacilityDropDownOnItemManagement(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickEditLinkOnItemManagement("8050");
		test.siteConfigurationAction
				.clickOnItemManagementFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickHoldReasonCheckbox("requestRestockLotInfoFlag");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"[ASSERTION FAILED]: User is not able to Navigate to ISA Page");
		test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
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
				TestDataPropertyReaderAndWriter.getProperty("PriorityRestock").trim());
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		test.transactionQueueActions.clickManualRestockTrans();
		test.transactionQueueActions.enterPopUpDetails();
	}
	

}
