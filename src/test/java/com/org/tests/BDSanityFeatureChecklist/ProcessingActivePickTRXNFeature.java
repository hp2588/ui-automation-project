package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class ProcessingActivePickTRXNFeature extends BaseTest {
	ArrayList<String> previous_data, after_data;
	String priority, destination, firstname, itemName, transaction_type,
			facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(), ISAName,
			genericname, itemID, brandName;
	int previous_count, after_count;

	@Test(priority = 1, description = "VPLX:Transaction Queue-Pick-UI: Select the Pick tab if not selected."
			+ "VPLX:Transaction Queue-Pick-UI: User logs in with valid credentials")
	public void Test01_Test02_1129531_1002440() {

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"),
		// 0);
		// test.storageAreaAction.UncheckCheckboxForAllAvailableISAAndVerifyStartWorkButtonGetsDisabled();
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA6"),
		// 5);
		//facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();

		// ISAName=test.storageAreaAction.getISANameOnWFAScreen();
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

	}

	@Test(priority = 4, description = "VPLX:Transaction Queue-Pick-UI: The selected transaction from Transaction queue becomes active when Pick Now button on the top panel is clicked"
			+ ""
			+ "VPLX:Transaction Queue-Pick-[UI]:Active Transaction should not be removed from the active bar even if any high priority transaction transaction is added.")
	public void Test03_Test04_999651_1129534() {
		Assert.assertTrue(test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());

		/* ==============ADD MANUAL PICK================== */

		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

		/*
		 * test.transactionQueueActions.searchItemValue("ItemID"); //
		 * test.transactionQueueActions.searchItemValue(itemID); //
		 * test.transactionQueueActions.verifySearchedResult("Item Name", //
		 * itemID);
		 * test.transactionQueueActions.verifySearchedResult("Item Name",
		 * "ItemID");
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 */

		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "6");
		transaction_type = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");
		Assert.assertEquals(true, test.transactionQueueActions.verifyTransactionQueueIsNotEmpty());

		/* =====TC: 1129534========= */
		ArrayList<String> activeCurrentTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeCurrentTransactionName));

		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		// test.transactionQueueActions.clickHoldButton("Pick");
		test.transactionQueueActions.clickFirstPickNow();
		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

	}

	@Test(priority = 5, description = "VPLX:Transaction Queue-Pick: [UI]-  Transaction priority color is displayed in status bar for the active pick transaction.")
	public void Test05_1024456() throws InterruptedException {

		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		String tqColor = test.transactionQueueActions.getColorOfFirstTransactionAndMakeItActive();
		Assert.assertTrue(test.transactionQueueActions.verifyColorCurrentPickIsAsPerTransactionPriority(tqColor));

	}

	@Test(priority = 6, description = "VPLX:Transaction Queue-Pick-UI: The selected transaction from Transaction queue becomes active when corresponding Pick Now button is clicked")
	public void Test06_999466() {

		/* ==============ADD MANUAL PICK================== */

		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions.verifySearchedResult("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.transactionQueueActions
				.clickSearchedItemValue(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());

		/*
		 * test.transactionQueueActions.searchItemValue("ItemID"); //
		 * test.transactionQueueActions.searchItemValue(itemID); //
		 * test.transactionQueueActions.verifySearchedResult("Item Name", //
		 * itemID);
		 * test.transactionQueueActions.verifySearchedResult("Item Name",
		 * "ItemID");
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 */

		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "4");
		transaction_type = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");

		/*
		 * String[] itemName_parsed = itemName.split(" "); String ItemNameNew =
		 * itemName_parsed[0]; System.out.println("ItemNameNew=" + ItemNameNew);
		 * 
		 * test.transactionQueueActions.
		 * clickManualPickTransactionBasedOnPriortiyAndItemName(
		 * transaction_type, ItemNameNew);
		 */

		test.transactionQueueActions.selectPickTransaction_Sanity(firstname);
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),
				"" + "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");

		/*
		 * ArrayList<String> activeTransactionName =
		 * test.transactionQueueActions.getTransactionDetails();
		 * Assert.assertTrue(test.transactionQueueActions.
		 * verifyValidItemNameInCurrentPick(activeTransactionName));
		 */

	}

	@Test(priority = 7, description = "VPLX:Transaction Queue-Pick: [UI] The QOH value is displayed for the current active transaction")
	public void Test07_1046297() throws InterruptedException {
		
		test.transactionQueueActions.getActiveQuantity();

		//test.transactionQueueActions.verifyActiveTransactionQuantityOnHand();
	}

	@Test(priority = 8, description = "VPLX:Transaction Queue-Pick: UI:Active pick transaction displays quantity, description,QoH destination, patient name on active transaction banner.")
	public void Test08_1136244(Method method) throws InterruptedException {

		Assert.assertTrue(test.transactionQueueActions.verifyCurrentPickHeaderDispaysPriorityName(transaction_type),
				"[ASSERTION FAILED]: Priority of the Active Pick is not displayed on the Top left as 'Current Pick-Priority Name'");
	}

	@Test(priority = 9, description = "VPLX:Transaction Queue-Pick-UI:Color of Current Pick queue is as per the Transaction priority.")
	public void Test09_998701(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Transaction Queue-Pick-UI:Color of Current Pick queue is as per the Transaction priority");
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		String tqColor = test.transactionQueueActions.getColorOfFirstTransactionAndMakeItActive();
		Assert.assertTrue(test.transactionQueueActions.verifyColorCurrentPickIsAsPerTransactionPriority(tqColor));

	}

	@Test(priority = 11, description = "VPLX:Transaction Queue-Pick-UI: A message appears as Waiting for Item Scan. Click 'F2' to override"
			+ "VPLX:Transaction Queue-Pick: [UI] The accurate message 'Waiting For Item Scan. Click 'scan override' to override scanning.' is displayed on TQ screen under Status bar while completing transaction")
	public void Test10_Test11_1002512__1032675() throws InterruptedException {

		Assert.assertTrue(test.transactionQueueActions.verifyItemScanMessage(getData("TQ.itemScanMessage"),
				"Waiting For Item Scan. Hit F2 to override scanning."));
	}

	@Test(priority = 13, description = "VPLX: Transaction Queue-Pick-UI: Override is happening by pressing Work Without the Scanner")
	public void Test12_Test13_1002264_1129549() {
		// test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		// Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
		// Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
				"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
	}

	@Test(priority = 14, description = "VPLX:Transaction Queue-Pick-UI: User is able to override item or label scan by Pressing F2 key.")
	public void Test14_1129960() throws InterruptedException {

		test.transactionQueueActions.pressKeyUsingAction(Keys.F2);
		Assert.assertTrue(test.transactionQueueActions.verifyItemScanMessage(getData("TQ.itemScanMessage"),
				"Waiting For Item Scan. Hit F2 to override scanning."));
	}

	@Test(priority = 20, description = "VPLX:Transaction Queue-Pick: [UI] User is able to Change Quantity for an active transaction by entering value from keyboard"
			+ "VPLX:Transaction Queue - Add ability to change the Quantity on Hand of an active transaction:[UI]:User is able to change the processed"
			+ " qty displayed on the Transaction queue while performing PICK transaction." + ""
			+ "VPLX:Transaction Queue-Pick-UI: User is able to Change the processed quantity in the box above 'on hand ' box for the active pick transaction."
			+ "VPLX:Transaction Queue-Pick-UI: User is able to update the QOH in the On hand Box for the active pick transaction."
			+ "VPLX:Transaction Queue-Pick-UI: User is able to edit the On hand Box for the active pick transaction.")
	public void Test15_Test16_Test17_Test18_Test19_Test20_1037062_1118716_1121900_1012163_1012162_1009372()
			throws InterruptedException {

		
		test.transactionQueueActions.clickScanOverrideOnce();
	//	test.transactionQueueActions.clickOnQuantity("3");
	//	test.transactionQueueActions.verifyActiveTransactionQuantityOnHand();
		
		
        test.transactionQueueActions.clickOnQuantityOnHand("3");
		
		test.supportDataActions.clickButtonIfPresent("primary");
				Assert.assertTrue(test.transactionQueueActions.verifyUpdatedQOH("3"),
				"[ASSERTION FAILED]: QOH is not updated.");
	}

	@Test(priority = 21, description = "VPLX:Transaction queue actions-Delete selected:-Deletion of single transaction from the Picks tab")
	public void Test21_996665() {

		// test.landingPageActions.navigateToMenu("Transaction Queue");
		// test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress"));
		// test.storageAreaAction.selectCheckboxForISA(getData("ISAList.ISA1"),
		// 0);
		// test.storageAreaAction.verifyStartWorkButtonAndClick();
		// Assert.assertTrue(test.transactionQueueActions.verifyUserIsOnTransactionQueuePage());

		/*
		 * * test.transactionQueueActions.verifyAndClickAddPick();
		 * test.transactionQueueActions.searchItemValue(getData(
		 * "AddPick.searchItemName" ));
		 * test.transactionQueueActions.verifySearchedResult("Item Name"
		 * ,getData( "AddPick.searchItemName"));
		 * test.transactionQueueActions.clickSearchedItemValue(getData(
		 * "AddPick.searchItemName").trim());
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup(
		 * "Quantity", getData("AddPick.Quantity"));
		 * destination=test.transactionQueueActions.
		 * selectDropdownForAddPick("Transaction Priority",getData(
		 * "AddPick.Priority"));
		 * destination=test.transactionQueueActions.selectDropdownForAddPick(
		 * "Destination", getData("AddPick.Destination"));
		 * test.transactionQueueActions.clickAdditionalInfoToggle();
		 * firstname="UI_"+
		 * test.transactionQueueActions.getAlphaNumericString(4);
		 * test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup(
		 * "patient_first_name", firstname);
		 * test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton(
		 * "save_close_btn", "Save & Close");
		 */

		test.transactionQueueActions.verifyTransactionQueueIsNotEmpty();
		// test.transactionQueueActions.verifyActionTabAndClick("Pick", 0);

		previous_count = test.transactionQueueActions.getTransactionTableDataCount();
		test.transactionQueueActions.selectAllCheckboxes();
		test.transactionQueueActions.deselectAllTransaction();
		test.transactionQueueActions.clickFirstTransaction();
		test.transactionQueueActions.clickHoldButton("Delete");

		// Assert.assertTrue(test.transactionQueueActions.verifyFirstActionLinkAndClick("Delete"));

		test.supportDataActions.clickButtonWithMiniLoader("deleteConfirmButton");

		after_count = test.transactionQueueActions.getTransactionTableDataCount();
		Assert.assertNotEquals(previous_count, after_count);

	}

	@Test(priority = 22, description = "VPLX:Transaction Queue-Pick: UI:User is able to complete the pick transaction whose QOH after completion will"
			+ " become greater than maximum quantity  set at assigning location to the item.")
	public void Test22_1136250() {

		/* =============CREATE ITEM=================== */

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueoffacilityInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		genericname = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacility" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem" + System.currentTimeMillis());
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand1");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickButton("save");

		/*
		 * =============ASSIGN LOCATION TO THE ITEM with 'MAX Quantity' >
		 * QOH===================
		 */

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.enterSearchTermInSearchFieldGl(genericname, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(genericname);

		// test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");

		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName").trim());

		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "201");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "210");
		// test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval",
		// "2");
		test.siteConfigurationAction.clickSaveButton();

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		/*
		 * ==================Pick Transaction such that 'Max Quantity' <
		 * QOH==================
		 */

		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue(genericname);
		// test.transactionQueueActions.searchItemValue(itemID);
		// test.transactionQueueActions.verifySearchedResult("Item Name",
		// itemID);
		test.transactionQueueActions.verifySearchedResult("Item Name", genericname);
		test.transactionQueueActions.clickSearchedItemValue(genericname);

		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "5");
		transaction_type = test.transactionQueueActions.selectDropdownForAddPick("Transaction Priority",
				TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");

		/*String[] itemName_parsed = itemName.split(" ");
		String ItemNameNew = itemName_parsed[0];
		System.out.println("ItemNameNew=" + ItemNameNew);

		test.transactionQueueActions.clickManualPickTransactionBasedOnPriortiyAndItemName(transaction_type,
				ItemNameNew);

		ArrayList<String> activeTransactionName = test.transactionQueueActions.getTransactionDetails();
		Assert.assertTrue(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName));

		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());

		Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner("Please scan item.",
				"Transaction Completed"));*/
		
		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),
				"" + "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");

		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		Assert.assertFalse(test.transactionQueueActions.verifyPatientNameInPickQueue(firstname),
				"[ASSERTION FAILED]: Patient is displayed in Pick Queue");

	}

	@Test(priority = 23, description = "VPLX:Transaction Queue-Pick: UI:User is able to complete the pick transaction whose"
			+ " QOH after completion will become lesser than minimum quantity  set at assigning location to the item.")
	public void Test23_1136248() {

		/*
		 * ==================Pick Transaction such that 'Min Quantity' >
		 * QOH==================
		 */

		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		test.transactionQueueActions.verifyAndClickAddPick();

		test.transactionQueueActions.searchItemValue(genericname);
		// test.transactionQueueActions.searchItemValue(itemID);
		// test.transactionQueueActions.verifySearchedResult("Item Name",
		// itemID);
		test.transactionQueueActions.verifySearchedResult("Item Name", genericname);
		test.transactionQueueActions.clickSearchedItemValue(genericname);

		itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("Quantity", "10");
		transaction_type = transaction_type = test.transactionQueueActions.selectDropdownForAddPick(
				"Transaction Priority", TestDataPropertyReaderAndWriter.getProperty("PriorityName").trim());

		test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("destination", 1);
		test.transactionQueueActions.clickAdditionalInfoToggle();
		firstname = "UI_" + test.transactionQueueActions.getAlphaNumericString(4);
		test.transactionQueueActions.EnterValueInInputFieldOnAddPickPopup("patient_first_name", firstname);
		test.transactionQueueActions.clickSaveCloseORCancelORSaveAddButton("save_close_btn", "Save & Close");

		/*
		 * String[] itemName_parsed = itemName.split(" "); String ItemNameNew =
		 * itemName_parsed[0]; System.out.println("ItemNameNew=" + ItemNameNew);
		 * 
		 * test.transactionQueueActions.
		 * clickManualPickTransactionBasedOnPriortiyAndItemName(
		 * transaction_type, ItemNameNew);
		 * 
		 * ArrayList<String> activeTransactionName =
		 * test.transactionQueueActions.getTransactionDetails();
		 * Assert.assertTrue(test.transactionQueueActions.
		 * verifyValidItemNameInCurrentPick(activeTransactionName));
		 * 
		 * Assert.assertEquals(true,
		 * test.transactionQueueActions.verifyActiveTransactionBox());
		 * Assert.assertEquals(true,
		 * test.transactionQueueActions.verifyWorkWithoutScannerOption());
		 * 
		 * Assert.assertTrue(test.transactionQueueActions.
		 * verifyOverrideWithoutScanner( "Please scan item.",
		 * "Transaction Completed"));
		 */

		test.transactionQueueActions.clickOnPickNow_Sanity(firstname);
		Assert.assertTrue(test.transactionQueueActions.verifyDestinationInCurrentPickWindow(firstname),
				"" + "[ASSERTION FAILED]: Patient Name is not displayed on Current pick Window");

		Assert.assertTrue(test.transactionQueueActions.clickScanOverride(),
				"[Assertion Failed]: Error while processing transaction");
		Assert.assertFalse(test.transactionQueueActions.verifyPatientNameInPickQueue(firstname),
				"[ASSERTION FAILED]: Patient is displayed in Pick Queue");

	}

}
