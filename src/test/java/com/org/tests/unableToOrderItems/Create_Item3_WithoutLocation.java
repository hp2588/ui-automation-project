package com.org.tests.unableToOrderItems;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_Item3_WithoutLocation extends BaseTest{
	
	String itemID, itemName, barcode, productID, itemID1, itemName1, barcode1, productID1;

	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test01_1054082(Method method) {
		
	test.landingPageActions.navigateToItemManagementFeature("Item Management");
	test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName_UnableToOrder").trim());
	test.siteConfigurationAction.clickActionbutton("Actions");
	test.siteConfigurationAction.clickActionbutton("Add New Item");
	test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
	Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
			"[ASSERTION FAILED]: input field is not mandatory");
	Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
			"[ASSERTION FAILED]: input field is not mandatory");
	Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
			"[ASSERTION FAILED]: dropdown is not mandatory");
	test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode_U").trim());
	itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
	itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
	test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode_U").trim());
	test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName_UnableToOrder").trim());
	test.siteConfigurationAction.clickButton("save");
	
	
	
	test.siteConfigurationAction.verifyAndClickProductID("Product ID");
	test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
	test.siteConfigurationAction.verifyHeader("Barcodes");
	barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
			"01003" + System.currentTimeMillis() + System.currentTimeMillis()+ "0171005032328717621abcd123456789");

	productID1 = test.siteConfigurationAction.getParsedProductID();
	System.out.println("productID=" + productID1);
	test.siteConfigurationAction.clickButton("link");
	test.siteConfigurationAction.verifyAddedProductID(productID1);
	test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
	
	
	test.siteConfigurationAction.clickLink("Add Preferred Distributor");
	
	test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
	test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"));
	long itemCode=System.currentTimeMillis();
	test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName_UnableToOrder"),Long.toString(itemCode));
	test.siteConfigurationAction.clickButton("primary");
	test.siteConfigurationAction.clickSaveButtonForISA();

}
}
