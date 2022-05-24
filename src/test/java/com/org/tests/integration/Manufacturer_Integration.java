package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Manufacturer_Integration extends BaseTest{
    String manufacturerName,manufacturerName_updated;

	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

	@Test(priority = 1, description = "When a Manufacturer is added, it gets populated in Manufacturer dropdown on Product ID screen.")
	public void Test01_1130717(Method method) {
		
		ExtentTestManager.startTest(method.getName(),
				"When a Manufacturer is added, it gets populated in Manufacturer dropdown on Product ID screen.");
		
		
		test.landingPageActions.navigateToFeature("Manufacturers");
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		test.supportDataActions.verifyAddButtonOnPage();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);
		
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
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("manufacturerKey", manufacturerName);


		test.siteConfigurationAction.clickSaveButtonForISA();
		
		
	}
	
	@Test(priority = 2, description = "When a Manufacturer is updated, it gets populated in Manufacturer dropdown Product ID screen")
	public void Test02_1130722(Method method) {
		
		ExtentTestManager.startTest(method.getName(),
				"When a Manufacturer is updated, it gets populated in Manufacturer dropdown Product ID screen");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");

		test.supportDataActions.clickButton("edit");
		test.supportDataActions.verifyEditPopUpOnManufacturer(manufacturerName);
		manufacturerName_updated = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.landingPageActions.navigateToMenu("Main Menu");
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
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID1);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("manufacturerKey", manufacturerName_updated);
		test.siteConfigurationAction.clickSaveButtonForISA();
		
	}
	
	@Test(priority = 3, description = "VPLX: Manufacturer : [UI][Integration]When a Manufacturer is inactivated(Not mapped to an item), it is not populated in Manufacturer dropdown Product ID screen")
	public void Test03_1130724(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manufacturer : [UI][Integration]When a Manufacturer is inactivated(Not mapped to an item), it is not populated in Manufacturer dropdown Product ID screen");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Manufacturers");
		
		test.supportDataActions.verifyLabelIsPresent("Manufacturers");
		test.supportDataActions.verifyAddButtonOnPage();
		test.supportDataActions.clickOnAddButtonToAddNewRecord("Add Manufacturer");
		manufacturerName = test.supportDataActions.EnterRandomValueInManufacturerInputField("manufacturer",
				getData("ManufacturerDetails.ManufacturerName") + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifyNewlyAddedRecordNameInList(manufacturerName);
		test.supportDataActions.enterSearchTermInSearchField(manufacturerName, "search");

		test.supportDataActions.clickButton("edit");
		test.supportDataActions.verifyEditPopUpOnManufacturer(manufacturerName);
		
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.Manufacturer"));
		test.supportDataActions.clickButton("save");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Management");
		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemID1, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID1);
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyValueinDropDownDoesNotExist("manufacturerKey",manufacturerName);
		
	}
	}

