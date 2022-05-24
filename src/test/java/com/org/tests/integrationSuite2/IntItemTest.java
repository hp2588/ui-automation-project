package com.org.tests.integrationSuite2;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntItemTest extends BaseTest {

	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

	@Test(priority = 1, description = "VPLX : Item Setup - ES Parity (Formulary Reference Data) Medication Classes : [UI] : A medication class is saved with PIS and will be visible in same PIS for Item management for Add/Edit Item screen"
			+ "&"
			+ "VPLX: Dispense Unit: [UI]: [Integration]: When a Dispense Unit is added, it gets populated in drop-down on Add/Edit Item screen"
			+ "&"
			+ "VPLX: Dosage Form: [UI]: [Integration]: When a Dosage form is added, it gets populated in Dosage Form drop-down on Add/Edit Item page")
	public void Test01_1107001_1107005_1129518_1037908(Method method) {
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
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
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName);
		TestDataPropertyReaderAndWriter.setProperty("ItemID", itemID);
	}
	

	@Test(priority = 2, description = "VPLX: Manage Distributors: [UI]: When a Distributor is added , it gets populated under Distributors dropdown in Products ID Tab on Item Management screen")
	public void Test02_1130466(Method method) {
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownFieldNew(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim(),
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickAddNewItemPOP("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction
				.clickCheckboxfacilityitemlevel(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ItemName1", itemName1);
		TestDataPropertyReaderAndWriter.setProperty("ItemID1", itemID1);
	}
}
