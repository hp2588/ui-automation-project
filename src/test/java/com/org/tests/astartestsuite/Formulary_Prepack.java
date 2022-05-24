package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Formulary_Prepack extends BaseTest {

	String itemID, itemName, itemName1,itemName2, barcode, productID;

	@Test(priority = 1, description = "VPLX -Formulary (Facility->Prepack): When Prepack is checked all Prepack fields become\r\n" + 
			"available Bulk item, prepack qty, prepack fixed qty, fast mover, prepack conversion factor\r\n" + 
			"and restock rounding factor",enabled=false)
	public void Test01_1117210(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Create and View a MedItem");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",	"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.siteConfigurationAction.clickOnPrepackCheckBox("prepackFlag");
		//test.siteConfigurationAction.checkBulkItemIDIsMandatory("bulkItemID");
		//test.siteConfigurationAction.checkPrepackConversionFactorIsMandatory("prepackConversion");
		test.siteConfigurationAction.clickButton("save");
		
	}
	
	@Test(priority = 2, description = "VPLX (Formulary : Prepack ) : When Prepack is checked bulk item becomes available and required")
	public void Test02_1117211_1117212_1117213(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Create and View a MedItem");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",	"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		
		//Assign Location to Item
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectFacilityDropdown(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "1");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "500");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "500");
		test.siteConfigurationAction.clickSaveButton();
		
		//Create item 2
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",	"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.siteConfigurationAction.clickOnPrepackCheckBox("prepackFlag");
		test.siteConfigurationAction.clickButton("setBulkItem");
	//	test.siteConfigurationAction.enterBulkItemNameInSearch(itemName);
		//test.siteConfigurationAction.clickOnSearchedBulkItem();
		test.siteConfigurationAction.clickButton("primary");
		//save item
		test.siteConfigurationAction.clickButton("save");
		
		//Create item 3
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName2 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",	"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.siteConfigurationAction.clickOnPrepackCheckBox("prepackFlag");
		test.siteConfigurationAction.clickButton("setBulkItem");
		//test.siteConfigurationAction.enterBulkItemNameInSearch(itemName);
		//test.siteConfigurationAction.clickOnSearchedBulkItem();
		test.siteConfigurationAction.clickButton("primary");
		//save item
		test.siteConfigurationAction.clickButton("save");
	}
}
