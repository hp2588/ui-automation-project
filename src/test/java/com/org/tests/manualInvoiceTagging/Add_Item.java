package com.org.tests.manualInvoiceTagging;

import java.lang.reflect.Method;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Add_Item extends BaseTest { 

	String itemID, itemName, barcode, productID, itemName1, itemID1,barcode1,productID1;
	
	
	@Test(priority = 1, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test01_Add_Manual_Item(Method method) {
		test.landingPageActions.navigateToMenu("Item Management");
		
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		Assert.assertTrue(test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item"));
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", 
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		itemName1 = test.siteConfigurationAction.enterDataInInputField("genericName", 
				"ItemName" + System.currentTimeMillis());
		itemID1 = test.siteConfigurationAction.enterDataInInputField("itemId", 
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", 
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("ItemCode", itemID1);
		TestDataPropertyReaderAndWriter.setProperty("ItemName", itemName1);
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		barcode1 = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		//test.siteConfigurationAction.clickButton("search");
		productID1 = test.siteConfigurationAction.getParsedProductID();
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID1);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		//test.siteConfigurationAction.clickButton("add");
		//test.siteConfigurationAction.verifyAddedProductID(productID1);
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		long itemCode = System.currentTimeMillis();
		String VIC = test.siteConfigurationAction.enterDistributorItemCode1(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), Long.toString(itemCode));
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
	//	test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		
		TestDataPropertyReaderAndWriter.setProperty("BarCode", barcode1);
		TestDataPropertyReaderAndWriter.setProperty("ProductID", productID1);
		TestDataPropertyReaderAndWriter.setProperty("VIC", VIC);
		test.supportDataActions.verifyNewlyAddedRecordNameInList(itemName1);
	}
	

	@Test(priority = 2, description = "VPLX: Manage Transaction priorities:[UI] - Create and View a MedItem")
	public void Test02_Add_Electronic_Item(Method method) {
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		Assert.assertTrue(test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item"));
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName", 
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", 
				"ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", 
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		TestDataPropertyReaderAndWriter.setProperty("ItemCode_Electronic", itemID);
		TestDataPropertyReaderAndWriter.setProperty("ItemName_Electronic", itemName);
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		// test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic"));
		long itemCode=System.currentTimeMillis();
		String VIC = test.siteConfigurationAction.enterDistributorItemCode1(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName_Electronic"),  "" + System.currentTimeMillis());
		
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		
		TestDataPropertyReaderAndWriter.setProperty("ProductID_Electronic", productID);
		TestDataPropertyReaderAndWriter.setProperty("BarCode_Electronic", barcode);
		TestDataPropertyReaderAndWriter.setProperty("VIC_Electronic", VIC);
		
		test.supportDataActions.verifyNewlyAddedRecordNameInList(itemName);
	}
	
}
