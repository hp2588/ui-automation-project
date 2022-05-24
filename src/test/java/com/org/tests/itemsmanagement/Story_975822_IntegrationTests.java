package com.org.tests.itemsmanagement;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_975822_IntegrationTests extends BaseTest {

	String itemClass, itemName , barcode, productID ,
			itemID ,
			
			 itemName1, brandName,
			preferedNDC, app_url;
	String brandName1 , itemNameNew, itemIDNew, itemID1,
			productID2   , destinationName  , destinationCode,
			ISAName = TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();

	/*@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
*/
	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]: A new Item which is created and mapped to"
			+ " facility is searched on the Add Restock popup for Formulary search.")

	public void Test01_1112383(Method method) {

		
/*		test.landingPageActions.navigateToFeature("External Systems");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToAddedRecord(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.uncheckCheckBox("allowPISItemEditFlag");
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("allowPISItemEditFlag");
		test.supportDataActions.clickButtonWithMiniLoader("save");
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddPrinter"));
		//test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), "search");
		//Assert.assertTrue(test.siteConfigurationAction.verifySearchResults(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(), "1"));
		//test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);

		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToItemManagementFeature("Item Management");		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		System.out.println("itemName" + itemName);
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		System.out.println("itemID" + itemID);
		brandName = test.siteConfigurationAction.enterDataInInputField("brandName", "brand100");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//barcode=test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue", "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.enterRandomValueInInputField("tradeName", "brand123");
		test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);

		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode1(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());

		test.siteConfigurationAction.clickButton("primary");

		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");

		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");

				//test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue", "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");

		//test.siteConfigurationAction.clickButton("search");
		productID2 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID2);

		test.supportDataActions.clickButtonWithOutAnyWait("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));*/
		test.landingPageActions.navigateToItemManagementFeature("Item Management");		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		//test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		test.supportDataActions.enterSearchTermInSearchFieldGl((TestDataPropertyReaderAndWriter.getProperty("ItemCode")), "search");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList((TestDataPropertyReaderAndWriter.getProperty("ItemCode")));

		//test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
		//test.siteConfigurationAction.clickItemNameToEdit(itemName);
		test.siteConfigurationAction.clickItemNameToEdit((TestDataPropertyReaderAndWriter.getProperty("ItemCode")));

		/* =========Updated Item ID============== */

		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
				"ItemIDUpdated" + System.currentTimeMillis());
        System.out.println("itemID1"+itemID1);

		/* =========Updated Brand Name============== */

		brandName1 = test.siteConfigurationAction.enterDataInInputField("brandName", "brandUpdated");
		System.out.println("brandName1"+brandName1); 
		test.siteConfigurationAction.clickButton("save");

		/* =========ASSIGN ITEM LOCATION=========== */

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);

		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");

		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);

		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "10");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "200");
		test.siteConfigurationAction.enterAndReturnValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();

		test.landingPageActions.navigateToMenu("Transaction Queue");
		//test.landingPageActions.navigateToFeature("Transaction Queue");

		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");

		/* ====TC: 1112383======== */

		test.transactionQueueActions.searchItemValue(itemName);

		/* =======TC: 1112392=============== */

		
		/* ==========TC: 1112396=========== */

		
	}
	
	@Test(priority = 2, description = "VPLX: Item Setup (System and Facility): [UI]:[Integration] : When an Item ID is updated, it gets updated on  Add Restock popup for Formulary search")

	public void Test02_1112392() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemName),
				"[ASSERTION FAILED]: Item Name is not displayed on Add Restock search screen.");
		
	}
	
	@Test(priority = 3, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]:When Brand Name  is updated for an item, it gets updated on"
			+ "  Add Restock/Return popup for Formulary search")

	public void Test03_1112396() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Add Restock search screen.");

		
	}



	@Test(priority = 4, description = "VPLX: Item Setup (System and Facility): [UI]:When a Preferred productid is created for an item, it gets displayed on Add Restock popup")

	public void Test04_1112398() {
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID),
				"[ASSERTION FAILED]: Product ID is not displayed on Add Restock search screen.");
		test.siteConfigurationAction.clickActionbutton("Cancel");

	}

	@Test(priority = 5, description =  "VPLX: Item Setup (System and Facility): [UI]: [Integration]:When Brand Name  is updated for an item, it gets updated on  Add Pick popup for Formulary search")

	public void Test05_1112389() {

		test.siteConfigurationAction.clickButton("primary");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
		test.transactionQueueActions.searchItemValue(itemName);

		// ================1112389=================== 
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Add Pick search screen.");


		


		// test.siteConfigurationAction.verifyItemsonItemscreen(itemID1);

	}
	
	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]: A new Item which is created and mapped to facility is searched on the"
			+ " Add Pick popup for Formulary search.")

	public void Test06_1112382() {
		
		 //============1112382======== 

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemName),
				"[ASSERTION FAILED]: Item Name is not displayed on Add Pick search screen.");
		
	}


	@Test(priority = 7, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]:ItemID gets updated on Add Pick popup for Formulary search when it is updated from Item Management")

	public void Test07_1112388() {

		// ===========1112388============ 

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID1),
				"[ASSERTION FAILED]: Updated Item ID is not displayed on Add Pick search screen.");

	}

	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI]:[Integration]:When a Preferred ProductId is created for an item, it gets displayed on Add Pick popup")

	public void Test08_1112390() {
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID),
				"[ASSERTION FAILED]: Product ID is not displayed on Add Pick search screen.");
		
		test.siteConfigurationAction.clickActionbutton("Cancel");
		// test.siteConfigurationAction.clickActionbutton("primary");
	}

/*	@Test(priority = 9, description = "VPLX: Item Setup (System and Facility): [UI]:When a Preferred ProductId is created for an item, it gets displayed on Add Return popup")

	public void Test09_1112397() {

		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(itemName);

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID),
				"[ASSERTION FAILED]: Product ID is not displayed on Add Return search screen.");
		

	}

	@Test(priority = 10, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]: A new Item which is created and "
			+ "mapped to facility is searched on the Add Return popup for Formulary search.")

	public void Test10_1112386() {

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemName),
				"[ASSERTION FAILED]: Item Name is not displayed on Add Return search screen.");
	}

	@Test(priority = 11, description = "VPLX: Item Setup (System and Facility): [UI]:[Integration] :When Brand Name  is updated for an item, it gets updated"
			+ " on  Add Return popup for Formulary search")

	public void Test11_1112394() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Add Return search screen.");
	}

	@Test(priority = 12, description = "VPLX: Item Setup (System and Facility): [UI]:[Integration] : When an Item ID is updated, it gets updated on  Add Return popup for Formulary search")

	public void Test12_1112393() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID1),
				"[ASSERTION FAILED]: Updated Item ID is not displayed on Add Return search screen.");
		
	}

	@Test(priority = 13, description = "VPLX: Item Setup (System and Facility): [UI]:When Brand Name  is updated for an item, it gets updated on Location Management screen")

	public void Test13_1112406() {

		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.clickButton("primary");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.supportDataActions.verifyLabelIsPresent("Item Locations");
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Location Management screen.");

	}

	@Test(priority = 14, description = "VPLX: Item Setup (System and Facility): [UI]:When Brand Name  is updated for an item,it gets updated on  Add ITems Popup on Add/Edit Destination Screen")

	public void Test14_1112811() {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.clickTab("Items");
		test.supportDataActions.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(brandName1);
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Add ITems Popup on Add/Edit Destination screen");
		
		
		
	}
	
	@Test(priority = 15, description = "VPLX: Item Setup (System and Facility): [UI]:When item id is updated , it gets updated on Add ITems Popup on Add/Edit Destination Screen")
	public void Test15_1112801(){
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID1),
				"[ASSERTION FAILED]: Updated Item ID is not displayed on Add/Edit Destination screen.");

		
	}


	@Test(priority = 16, description = "VPLX: Item Setup (System and Facility): [UI]:When a new item is added and mapped to facility, then it is displayed on "
			+ "Add Items popup on Add/Edit Destination Screen with exact details: Item id, Item Description, NDC,brand")

	public void Test16_1112800() {

		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemName),
				"[ASSERTION FAILED]: Item Name is not displayed on Add Items popup on Add/Edit Destination screen.");

	}

	@Test(priority = 17, description = "VPLX: Item Setup (System and Facility): [UI]:When a productId is added for an item, it gets displayed on  Add ITems Popup on Add/Edit Destination Screen .")

	public void Test17_1112809() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID),
				"[ASSERTION FAILED]: Product ID is not displayed on Add ITems Popup on Add/Edit Destination screen.");
		
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.supportDataActions.clickButton("save");
	}

	@Test(priority = 18, description = "VPLX: Item Setup (System and Facility): [UI]When a Preferred ProductId is updated for an item, it gets updated on Add Restock popup")

	public void Test18_1112399() {
		// test.siteConfigurationAction.clickCancelButtonFormularyItems("cancel");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID1);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");

		// test.siteConfigurationAction.verifyAndClickItemFacility(facilityOnWFAScreen);
		Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID2));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		test.landingPageActions.navigateToMenu("Transaction Queue");

		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Restock");

		// ====TC: 1112399======== 

		test.transactionQueueActions.searchItemValue(itemID1);
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID1),
				"[ASSERTION FAILED]: Updated Item ID is not displayed on Add Restock screen.");

		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Product ID is not displayed on Add Restock screen.");

	}

	@Test(priority = 19, description = "VPLX: Item Setup (System and Facility):[UI]: [Integration]: When a Preferred productId is updated for an item, it gets updated on Add Pick popup")

	public void Test19_1112391() {
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.clickButton("primary");
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
		test.transactionQueueActions.searchItemValue(itemID1);
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Product ID is not displayed on Add ITems Popup on Add Pick screen.");

	}

	@Test(priority = 20, description = "VPLX: Item Setup (System and Facility): [UI]:When a Preferred NDC is updated for an item, it gets updated on Add Return popup")

	public void Test020_1112405() {
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.transactionQueueActions.verifyActionButtonAndClick();
		test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
		test.transactionQueueActions.searchItemValue(itemName);

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Preferred NDC is not displayed on Add Return screen.");


	}

	@Test(priority = 21, description = "VPLX: Item Setup (System and Facility): [UI]:When a Preferred productId is updated for an item, it gets updated on Add Return popup")
	public void Test21_1112404() {
		
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Product ID is not displayed  on Add Return screen.");
	}

	@Test(priority = 22, description = "VPLX: Item Setup (System and Facility): [UI]:When item id is updated , it gets updated on  Items tab (If Destination is already mapped to item)on Edit Destination screen")

	public void Test22_1112805() {

		// test.siteConfigurationAction.clickCancelButtonFormularyItems("cancel");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.supportDataActions.clickButton("primary");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		test.siteConfigurationAction.clickTab("Items");
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(itemID1),
				"[ASSERTION FAILED]: Updated Item ID is not displayed on on  Items tab (If Destination is already mapped to item)on Edit Destination screen");
	}

	@Test(priority = 23, description = "VPLX: Item Setup (System and Facility): [UI]:When Brand name or productIdis updated for an item which is already mapped to Destination, the details get updated in Items tab on Edit Destination screen")

	public void Test23_1112812() {

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Product ID is not displayed on Edit Destination screen.");

		test.supportDataActions.clickButton("cancel");
		test.supportDataActions.clickButton("primary");

	}

	@Test(priority = 24, description = "VPLX: Item Setup (System and Facility): [UI]:When a ProductId is updated for an item, it gets updated on  Add ITems Popup on Add/Edit Destination Screen")

	public void Test24_1112810() {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");

		test.supportDataActions.verifyLabelIsPresent("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.clickTab("Items");
		test.supportDataActions.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);

		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(productID2),
				"[ASSERTION FAILED]: Updated Product ID is not displayed on  Add ITems Popup on Add/Edit Destination Screen");
		Assert.assertTrue(test.siteConfigurationAction.verifyItemsonItemscreen(brandName1),
				"[ASSERTION FAILED]: Updated Brand Name is not displayed on Add ITems Popup on Add/Edit Destination screen");
		test.siteConfigurationAction.clickCancelButtonFormularyItems("cancel");

	}

	@Test(priority = 25, description = "VPLX: Item Setup (System and Facility): [UI]:When active flag is unchecked for an item at facility level ,manual pick is not created.")

	public void Test25_1112813() throws Throwable {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterRandomValueInRichInputField(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");

		itemNameNew = test.siteConfigurationAction.enterDataInInputField("genericName",
				"Systemlevelfacilityx" + System.currentTimeMillis());

		itemIDNew = test.siteConfigurationAction.enterDataInInputField("itemId",
				"SystemlevelItem77x" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDataInInputField("brandName", "brand100");

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.clickButton("cancel");

		test.supportDataActions.enterSearchTermInSearchFieldGl(itemIDNew, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemIDNew);
		test.siteConfigurationAction.clickfacilityonEditItem("2");
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.verifyToggleIsInActive("activeFlag");

		test.siteConfigurationAction.clickSaveButton();

		test.closeBrowserSession();
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		// test.loginPageAction.verifyUserIsOnBDLoginPage();
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(),
				TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");

		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

		Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());

		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyActionButtonAndClick();

		test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");

		test.transactionQueueActions.searchItemValue(itemIDNew);
		Assert.assertFalse(test.siteConfigurationAction.verifyItemIsMappedForRO(itemIDNew));

	}
*/
}
