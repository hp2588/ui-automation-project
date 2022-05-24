package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_111 extends BaseTest { 

	String itemID, itemName, barcode, productID;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test01_1054082(Method method) {
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
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

		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		//test.siteConfigurationAction.clickOnCancelButton();
		// test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		// test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		

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
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		//test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		//test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID);

		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		//test.siteConfigurationAction.enterCostValue("averageSalesPriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("ItemCode",itemID);
		TestDataPropertyReaderAndWriter.setProperty("ItemName",itemName);

	}
}
