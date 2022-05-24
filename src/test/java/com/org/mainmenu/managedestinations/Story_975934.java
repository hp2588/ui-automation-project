package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975934 extends BaseTest {

String brandName, itemIDwithoutItemID,itemNamewithoutNDC,barcode,productID,itemNamewithoutlocation,itemIDwithoutlocation,barcodewithoutlocation,productIDwithoutlocation,itemNamewithoutVIC,itemIDwithoutVIC; 
	
	
	@Test(priority = 1, description = "VPLX: Manage Destinations-Formulary items[UI]: To verify that user is not able to add the item in destination in case NDC is missing of the item.")
	public void Test01_1135775(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Item Management");		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
		TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());		
		itemNamewithoutNDC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		System.out.println("itemNamewithoutNDC"+ itemNamewithoutNDC);
		
		itemIDwithoutItemID = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
				"ItemID" + System.currentTimeMillis());
		System.out.println("itemIDwithoutItemID"+ itemIDwithoutItemID);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		        test.siteConfigurationAction.clickButton("save");		
		
		TestDataPropertyReaderAndWriter.setProperty("itemIDwithoutItemID", itemIDwithoutItemID);
		TestDataPropertyReaderAndWriter.setProperty("itemNamewithoutNDC", itemNamewithoutNDC);
		
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Item Locations");		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutNDC"), "search");
		
		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutNDC"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		itemNamewithoutNDC = TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutNDC");
		System.out.println(itemNamewithoutNDC);
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemNamewithoutNDC);
		test.siteConfigurationAction.verifynoresultsfound(itemNamewithoutNDC);
}


	@Test(priority = 2, description = "VPLX: Manage Destinations-Formulary items[UI]: To verify that user is not able to add the item in the destination in case location is missing of the item.")
	public void Test02_1135776(Method method) {
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Item Management");		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
		TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		itemNamewithoutlocation = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		System.out.println("itemNamewithoutNDC"+ itemNamewithoutNDC);
		
		itemIDwithoutlocation = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
				"ItemID" + System.currentTimeMillis());
		System.out.println("itemIDwithoutItemID"+ itemIDwithoutItemID);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		barcodewithoutlocation = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickOnSearchButton();
		productIDwithoutlocation = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productIDwithoutlocation=" + productIDwithoutlocation);
		
		test.supportDataActions.clickButtonWithOutAnyWait("link");
		test.siteConfigurationAction.verifyAddedProductID(productIDwithoutlocation);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		 //TestDataPropertyReaderAndWriter.setProperty("ItemCode",itemID);
		 //TestDataPropertyReaderAndWriter.setProperty("ItemName",itemName);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(
		TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
			TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		
		
		TestDataPropertyReaderAndWriter.setProperty("itemIDwithoutlocation", itemIDwithoutlocation);
		TestDataPropertyReaderAndWriter.setProperty("itemNamewithoutlocation", itemNamewithoutlocation);
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		itemNamewithoutNDC = TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutNDC");
		System.out.println(itemNamewithoutNDC);
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemNamewithoutNDC);
		test.siteConfigurationAction.verifynoresultsfound(itemNamewithoutNDC);
		
	}

	
	@Test(priority = 3, description = "VPLX: Manage Destinations-Formulary items[UI]: To verify that user is able to add the item in destination in case Preferred distributor or VIC is missing.")
	public void Test03_1135793(Method method)
	{
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToMenu("Item Management");		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
		TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		itemNamewithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		System.out.println("itemNamewithoutVIC"+ itemNamewithoutNDC);
		
		itemIDwithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
				"ItemID" + System.currentTimeMillis());
		System.out.println("itemIDwithoutVIC"+ itemIDwithoutVIC);
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		barcodewithoutlocation = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickOnSearchButton();
		productIDwithoutlocation = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productIDwithoutlocation=" + productIDwithoutlocation);		
		test.supportDataActions.clickButtonWithOutAnyWait("link");
		test.siteConfigurationAction.verifyAddedProductID(productIDwithoutlocation);		
		test.siteConfigurationAction.clickSaveButtonForISA();	
		TestDataPropertyReaderAndWriter.setProperty("itemNamewithoutVIC", itemNamewithoutVIC);
		TestDataPropertyReaderAndWriter.setProperty("itemIDwithoutVIC", itemIDwithoutVIC);
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Item Locations");		
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"), "search");
		
		// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa",
				TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		itemNamewithoutNDC = TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC");
		System.out.println(itemNamewithoutVIC);
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemNamewithoutVIC);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemNamewithoutVIC);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.clickActionbutton("Save");		
	}
	

@Test(priority = 4, description = "VPLX: Manage Destinations - Formulary Items : [UI]: To verify that user is not able to search Inactive item in formulary under destination.")
public void Test04_1144803(Method method) throws Throwable 
{
	test.landingPageActions.pageRefresh();
	test.landingPageActions.navigateToMenu("Item Management");		
	test.siteConfigurationAction.enterExternalSystemValueDropdownField(
			TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickActionbutton("Add New Item");
	test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");

	test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
	TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
	
	itemNamewithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
			"ItemName" + System.currentTimeMillis());
	System.out.println("itemNamewithoutVIC"+ itemNamewithoutNDC);
	
	itemIDwithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
			"ItemID" + System.currentTimeMillis());
	System.out.println("itemIDwithoutVIC"+ itemIDwithoutVIC);
	test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
			TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
			TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.siteConfigurationAction.clickButton("save");
	test.siteConfigurationAction.verifyAndClickFacilityNameOnItemPage(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.siteConfigurationAction.clickActiveToggle("Active");
	test.siteConfigurationAction.clickSaveButtonForISA();	
	TestDataPropertyReaderAndWriter.setProperty("itemNamewithoutVIC", itemNamewithoutVIC);
	TestDataPropertyReaderAndWriter.setProperty("itemIDwithoutVIC", itemIDwithoutVIC);
	
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");		
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items");
	test.siteConfigurationAction.clickButton("add");
	itemNamewithoutNDC = TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutNDC");
	System.out.println(itemNamewithoutNDC);
	test.siteConfigurationAction.enterItemNameForDestinationItem(itemNamewithoutNDC);
	test.siteConfigurationAction.verifynoresultsfound(itemNamewithoutNDC);

}

@Test(priority = 5, description = "VPLX: Manage Destinations - Formulary Items : [UI] To verify that user is able to search the item on the basis of NDC, BrandName, Item(Med Name), and ItemId in case item is not added already.")
public void Test05_1054623(Method method)
{
	test.landingPageActions.pageRefresh();
	test.landingPageActions.navigateToMenu("Item Management");		
	test.siteConfigurationAction.enterExternalSystemValueDropdownField(
			TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickActionbutton("Add New Item");
	test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");

	test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
	TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
	
	itemNamewithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
			"ItemName" + System.currentTimeMillis());
	System.out.println("itemNamewithoutVIC"+ itemNamewithoutNDC);
	
	itemIDwithoutVIC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", 
			"ItemID" + System.currentTimeMillis());
	System.out.println("itemIDwithoutVIC"+ itemIDwithoutVIC);
	
	brandName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("brandName", 
			"BrandName" + System.currentTimeMillis());
	
	test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
			TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
			TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.siteConfigurationAction.clickButton("save");
	
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
	test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
	
	barcodewithoutlocation = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
			"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
	test.siteConfigurationAction.clickOnSearchButton();
	productIDwithoutlocation = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productIDwithoutlocation=" + productIDwithoutlocation);		
	test.supportDataActions.clickButtonWithOutAnyWait("link");
	test.siteConfigurationAction.verifyAddedProductID(productIDwithoutlocation);		
	test.siteConfigurationAction.clickSaveButtonForISA();	
	TestDataPropertyReaderAndWriter.setProperty("itemNamewithoutVIC", itemNamewithoutVIC);
	TestDataPropertyReaderAndWriter.setProperty("itemIDwithoutVIC", itemIDwithoutVIC);
	TestDataPropertyReaderAndWriter.setProperty("brandName", brandName);
	TestDataPropertyReaderAndWriter.setProperty("productIDwithoutlocation", productIDwithoutlocation);
	
	test.landingPageActions.pageRefresh();
	test.landingPageActions.navigateToFeature("Item Locations");		
	test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
			TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
	test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"), "search");
	
	// test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
	test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"));
	test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
	test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
	test.siteConfigurationAction.selectValueForDropDown("facility",
			TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.siteConfigurationAction.selectValueForDropDown("isa",
			TestDataPropertyReaderAndWriter.getProperty("ISAName"));
	test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
	
	test.siteConfigurationAction.clickAssignLocationButton();
	test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
	test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
	test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
	test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
	test.siteConfigurationAction.clickSaveButton();
	
	
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");		
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items");
	test.siteConfigurationAction.clickButton("add");	
	test.siteConfigurationAction.enterItemNameForDestinationItem(itemIDwithoutVIC);
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemIDwithoutVIC);
	test.siteConfigurationAction.clearInputBox("formularySearch");
	test.siteConfigurationAction.enterItemNameForDestinationItem(brandName);
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(brandName);
	test.siteConfigurationAction.clearInputBox("formularySearch");
	test.siteConfigurationAction.enterItemNameForDestinationItem(productIDwithoutlocation);
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(productIDwithoutlocation);	
	test.siteConfigurationAction.clickCheckbox("activeFlag-0");
	test.siteConfigurationAction.clickActionbutton("Save & Close");
	test.siteConfigurationAction.clickActionbutton("Save");		
}

@Test(priority = 6, description = "VPLX: Manage Destinations - Formulary Items : [UI] To verify that user is able to search the item on the basis of NDC, BrandName, Item(Med Name), and ItemId in case item is not added already.")
public void Test06_1055235(Method method)
{
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");		
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items");
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("itemIDwithoutVIC"));
	test.siteConfigurationAction.ClickremoveButton((TestDataPropertyReaderAndWriter.getProperty("itemIDwithoutVIC")));
	test.siteConfigurationAction.clickActionbutton("Cancel");
	test.siteConfigurationAction.clickActionbutton("Yes");
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items");
	test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(TestDataPropertyReaderAndWriter.getProperty("itemIDwithoutVIC"));
}

@Test(priority = 7, description = "VPLX: Manage Destinations - Formulary Items : [UI] To verify that items in Destination display with complete description (dosage, volume and other details) and consistent with item management page.")
public void Test07_Test08_1054589_1135605(Method method)
{
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");		
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items"); 
    String expectedItemname =  test.siteConfigurationAction.getItemName(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"));
    String item =  TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC");
    String Dosageform = TestDataPropertyReaderAndWriter.getProperty("DosageFormCode"); 
    Assert.assertEquals(item+" "+Dosageform, expectedItemname,"Itemname is incorrect on Destination screen");
}

@Test(priority = 8, description = "VPLX: Manage Destinations - Formularly Items: [UI] : To verify that system allow the user to keep 'Daily Max Quantity' field as blank when un-selecting the Limit Order Quantity checkbox.")
public void Test09_Test10_1054735_1135747(Method method)
{
	test.landingPageActions.navigateToMenu("Main Menu");
	test.landingPageActions.navigateToFeature("Destinations");		
	test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickEditLink(TestDataPropertyReaderAndWriter.getProperty("DestinationName"));
	test.siteConfigurationAction.clickTab("Items"); 	
    test.siteConfigurationAction.getMaxdailyQuantity(TestDataPropertyReaderAndWriter.getProperty("itemNamewithoutVIC"));
    }
}