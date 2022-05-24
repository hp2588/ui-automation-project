package com.org.tests.mainmenu.prepack;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class Story_1116055 extends BaseTest {
	String itemID, itemName, barcode, productID,itemName1,itemID1;

	@Test(priority = 1, enabled = true, description = "Bulk item is successfully saved when user mapped valid NDC and distributor to the item")
	public void Test01_116055_1122634()

	{
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",
				TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		Assert.assertTrue(test.siteConfigurationAction.verifyAndClickAddProductIDOnPage());
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		test.siteConfigurationAction.verifyAndClickProductID("Attribute");
		test.siteConfigurationAction.clickButton("save");
		test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName")); 
		test.siteConfigurationAction.checkPrePackFlag();
		test.siteConfigurationAction.clickSetBulkItem();
		test.siteConfigurationAction.clickSelectBulkItemAndSave();
		test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickButton("save");
	    test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		
	}
	
	 @Test(priority = 2,enabled = true, description = "Popup having validation message is displayed when NDC and distributor is not mapped with the prepack item") 
		public void Test02_116055_1122780()
	  
	  { 
		  test.landingPageActions.navigateToItemManagementFeature("Item Management");
		  test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		  test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		  test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		  test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		  test.siteConfigurationAction.checkPrePackFlag();
		  test.siteConfigurationAction.uncheckPrePackPopUpNoButton();
	  }	
	 
	 @Test(priority = 3,enabled = true, description = "VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item: Popup having validation message is displayed when NDC is not mapped with the prepack item") 
		public void Test03_116055_1122781()
	  
	  { 
		  test.landingPageActions.navigateToItemManagementFeature("Item Management");
		  test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		  test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		  test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		  test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		  test.siteConfigurationAction.checkPrePackFlag();
		  test.siteConfigurationAction.uncheckPrePackPopUpNoButton();
	  }	
	 
	 @Test(priority = 4,enabled = true, description = "VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item: Popup having validation message is displayed when distributor is not mapped with the prepack item") 
		public void Test04_116055_1122782()
	  
	  { 
		  test.landingPageActions.navigateToItemManagementFeature("Item Management");
		  test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		  test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
		  test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		  test.siteConfigurationAction.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		  test.siteConfigurationAction.checkPrePackFlag();
		  test.siteConfigurationAction.uncheckPrePackPopUpNoButton();
	  }	
}
