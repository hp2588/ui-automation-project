

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class IntItemSetupTest extends BaseTest {

	String itemClass,itemName, barcode, productID, itemID,facilityOnWFAScreen,External,itemName1,brandName,preferedNDC;
	String brandName1,itemID1,productID2;
    String destinationName, destinationCode, facilityName,searched_item;
	
	@Test(priority = 1, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]: A new Item which is created and "
			+ "mapped to facility is searched on the Add Return popup for Formulary search."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:[Integration] :When Brand Name  is updated for an item, it gets updated"
			+ " on  Add Return popup for Formulary search"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:When a Preferred NDC is updated for an item, it gets updated on Add Return popup"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:[Integration] : When an Item ID is updated, it gets updated on  Add Return popup for Formulary search"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:When a Preferred productId is updated for an item, it gets updated on Add Return popup")
	public void Test01_Test02_Test03_Test04_Test05_1112386_1112394_1112405_1112393_1112404(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]:[Integration] :When Brand Name  is updated for an item, it gets updated on  Add Return popup for Formulary search");
	
	test.landingPageActions.navigateToMenu("Transaction Queue");
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
	Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

	Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
	facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
	test.storageAreaAction.verifyStartWorkButtonAndClick();
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.landingPageActions.navigateToFeature("Main Menu"); 
	test.landingPageActions.navigateToFeature("Facilities");
	
	String External = test.siteConfigurationAction.getExternalSystemMappedToFacility(facilityOnWFAScreen);
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.enterRandomValueInRichInputField(External);
	
	
	//test.landingPageActions.navigateToItemManagementFeature("Item Management");
	//test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacilityx" + System.currentTimeMillis());
	itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	brandName=test.siteConfigurationAction.enterDataInInputField("brandName","brand100");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
	
	//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
	test.siteConfigurationAction.verifyHeader("Barcodes");
	barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
			"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
	test.siteConfigurationAction.clickButton("search");
	productID = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productID=" + productID);
	test.siteConfigurationAction.clickButton("link");
	test.siteConfigurationAction.verifyAddedProductID(productID);
	
	test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
	test.siteConfigurationAction.enterRandomValueInInputField("tradeName","brand123");
	test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);
	//test.siteConfigurationAction.clickButton("add");
	//test.siteConfigurationAction.verifyAddedProductID(productID);
	test.siteConfigurationAction.clickLink("Add Preferred Distributor");
	test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
	test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
	preferedNDC =test.siteConfigurationAction.enterDistributorItemCode1(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
	System.out.println(preferedNDC);
	test.siteConfigurationAction.clickButton("primary");
	
	
	
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");

	test.siteConfigurationAction.verifyHeader("Barcodes");
	barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
			"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

	test.siteConfigurationAction.clickButton("search");
	productID2 = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productID=" + productID);
	test.siteConfigurationAction.clickButton("link");
	test.siteConfigurationAction.verifyAddedProductID(productID2);

	Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID2));
	
	
	test.siteConfigurationAction.clickSaveButtonForISA();
	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
	
	
	test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
	
	test.supportDataActions.enterSearchTermInSearchFieldGl(itemID, "search");
	test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
	
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	Assert.assertTrue(test.siteConfigurationAction.selectPreferredDistributor(productID));
	
	itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	brandName1=test.siteConfigurationAction.enterDataInInputField("brandName","brand200");
	
	
	test.siteConfigurationAction.clickButton("save");
	
	test.landingPageActions.navigateToMenu("Transaction Queue");
	
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
	Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

	Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
	facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
	test.storageAreaAction.verifyStartWorkButtonAndClick();
	test.transactionQueueActions.verifyActionButtonAndClick();

	test.transactionQueueActions.verifyActionItemsAndClick("Add Return");
	test.transactionQueueActions.searchItemValue("itemID1");
	itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

	itemName1 = itemName.split(" ")[0];
	//test.transactionQueueActions.verifySearchResults(itemName1, "1");
	//test.transactionQueueActions.verifySearchedResult(itemName1, "1");
	test.siteConfigurationAction.verifyItemsonItemscreen(brandName1);
	test.siteConfigurationAction.verifyItemsonItemscreen(productID);
	test.siteConfigurationAction.clickActionbutton("Cancel");
	}
	
	@Test(priority = 6, description = "VPLX: Item Setup (System and Facility): [UI]: [Integration]:ItemID gets updated on Add Pick popup for Formulary search when it is updated from Item Management"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:[Integration]:When a Preferred ProductId is created for an item, it gets displayed on Add Pick popup")
	public void Test06_Test07_1112388_1112390(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]: [Integration]:ItemID gets updated on Add Pick popup for Formulary search when it is updated from Item Management");
					test.landingPageActions.navigateToMenu("Transaction Queue");
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
	Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

	Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
	facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
	test.storageAreaAction.verifyStartWorkButtonAndClick();
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.landingPageActions.navigateToFeature("Main Menu"); 
	test.landingPageActions.navigateToFeature("Facilities");
	
	String External = test.siteConfigurationAction.getExternalSystemMappedToFacility(facilityOnWFAScreen);
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.enterRandomValueInRichInputField(External);
	
	
	//test.landingPageActions.navigateToItemManagementFeature("Item Management");
	//test.siteConfigurationAction.enterRandomValueInRichInputField(getData("ExternalSystem.Name7"));
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
	test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
	test.siteConfigurationAction.enterDataInInputField("genericName",
			"Systemlevelfacilityx" + System.currentTimeMillis());
	itemID = test.siteConfigurationAction.enterDataInInputField("itemId",
			"SystemlevelItem77x" + System.currentTimeMillis());
	brandName=test.siteConfigurationAction.enterDataInInputField("brandName","brand100");
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);
	test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);
	
	//test.siteConfigurationAction.clickCheckboxfacilityitemlevel(getData("ExternalSystem.Name8"));
	
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
	test.siteConfigurationAction.verifyHeader("Barcodes");
	barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
			"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
	test.siteConfigurationAction.clickButton("search");
	productID = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productID=" + productID);
	test.siteConfigurationAction.clickButton("link");
	test.siteConfigurationAction.verifyAddedProductID(productID);
	test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
	test.siteConfigurationAction.enterRandomValueInInputField("tradeName","brand123");
	test.supportDataActions.selectValueFromDropDownForDosagePISSystemByIndex("manufacturerKey", 1);
	//test.siteConfigurationAction.clickButton("add");
	//test.siteConfigurationAction.verifyAddedProductID(productID);
	test.siteConfigurationAction.clickLink("Add Preferred Distributor");
	test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
	test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
	preferedNDC =test.siteConfigurationAction.enterDistributorItemCode1(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
	test.siteConfigurationAction.clickButton("primary");
	test.siteConfigurationAction.clickSaveButtonForISA();
	
	test.landingPageActions.navigateToMenu("Transaction Queue");
	
	test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
	test.transactionQueueActions.verifyTQPageAndAppendIP(getData("Auth.ip").trim());
	Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
			"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");

	Assert.assertNotNull(test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
	facilityOnWFAScreen = test.siteConfigurationAction.getFacilityFromISAScreen();
	test.storageAreaAction.verifyStartWorkButtonAndClick();
	test.transactionQueueActions.verifyActionButtonAndClick();

	test.transactionQueueActions.verifyActionItemsAndClick("Add Pick");
	test.transactionQueueActions.searchItemValue("itemID1");
	itemName = test.transactionQueueActions.getAddedItemNameFromAddRestockForm();

	itemName1 = itemName.split(" ")[0];
	//test.transactionQueueActions.verifySearchResults(itemName1, "1");
	
	}
	
	@Test(priority = 8, description = "VPLX: Item Setup (System and Facility): [UI]:When a productId is added for an item, it gets displayed on  Add ITems Popup on Add/Edit Destination Screen ."
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:When a new item is added and mapped to facility, then it is displayed on "
			+ "Add Items popup on Add/Edit Destination Screen with exact details: Item id, Item Description, NDC,brand"
			+ ""
			+ "VPLX: Item Setup (System and Facility): [UI]:When Brand name or productIdis updated for an item which is already mapped to Destination, the details get updated in Items tab on Edit Destination screen")
	public void Test08_Test09_Test10_1112809_1112800_1112812(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Item Setup (System and Facility): [UI]:When a productId is added for an item, it gets displayed on  Add ITems Popup on Add/Edit Destination Screen .");
		test.siteConfigurationAction.clickActionbutton("Cancel");
		test.landingPageActions.navigateToFeature("Main Menu"); 
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.siteConfigurationAction.selectFacilityDropdownForDestination(facilityOnWFAScreen);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickAddItemButtonOnDestinationScreen();
		test.siteConfigurationAction.verifyAddItemPopup();
		searched_item = test.siteConfigurationAction
				.enterItemNameForDestinationItem(itemID1);
		test.siteConfigurationAction.verifyItemSearchResult();
		test.siteConfigurationAction.verifyItemsonItemscreen(brandName1);
		test.siteConfigurationAction.verifyItemsonItemscreen(preferedNDC);
}
}