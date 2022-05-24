package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Barcode_Test extends BaseTest {

	String itemID, itemName, barcode1, barcode2, preferredproductID1,productID2;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test01_1114512(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Transaction priorities:[UI] -  Create and View a MedItem");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
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

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction
				.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		// test.siteConfigurationAction.clickOnCancelButton();
		// test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		// test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode1 = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		preferredproductID1 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + preferredproductID1);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(preferredproductID1);
		test.siteConfigurationAction.enterItemPackageSize(preferredproductID1, "5");

		test.siteConfigurationAction.clickButton("save");

		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode2 = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");

		productID2 = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID2);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID2);
		test.siteConfigurationAction.enterItemPackageSize(productID2, "5");

		test.siteConfigurationAction.clickButton("save");

		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Barcodes");
		test.siteConfigurationAction.clickButton("unverified");
		//test.siteConfigurationAction.enterItemNameUnverifiedLinks(itemName);
		

	}

}
