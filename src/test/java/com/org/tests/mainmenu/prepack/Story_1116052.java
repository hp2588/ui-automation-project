package com.org.tests.mainmenu.prepack;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

@Listeners(com.org.listeners.TestListener.class)

public class Story_1116052 extends BaseTest {
	String itemID, itemName, barcode, productID;

	@Test(priority = 1, enabled = true, description = "VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item:  Popup having validation message is displayed when user uncheck Prepack  flag")
	public void Test01_116052_1122627()

	{
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(getData("ProcessInvoices.ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",getData("ProcessInvoices.DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",getData("ProcessInvoices.DispenseUnitCode").trim());
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		itemID = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",getData("ProcessInvoices.MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(getData("ProcessInvoices.FacilityName").trim());
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
		//test.siteConfigurationAction.clickOnEditButtonItemManagement();
		
	//	test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		test.siteConfigurationAction.verifyAndClickItemFacility(getData("ProcessInvoices.FacilityName").trim()); 
		test.siteConfigurationAction.checkPrePackFlag();
		test.siteConfigurationAction.clickSetBulkItem();
		test.siteConfigurationAction.clickSelectBulkItemAndSave();
		test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickButton("save");
	    test.supportDataActions.enterSearchTermInSearchField(itemID, "search");
	   // test.siteConfigurationAction.clickOnEditButtonItemManagement();
	    
		// test.supportDataActions.clickOnEditLinkCorresspondingToItem(itemID, "Edit");
		test.siteConfigurationAction.verifyAndClickItemFacility(getData("ProcessInvoices.FacilityName").trim()); 
		test.siteConfigurationAction.checkPrePackFlag();
		test.siteConfigurationAction.uncheckPrePackPopUpNoButton();
		}
	
	 @Test(priority = 2,enabled = true, description = "VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item : validation message is displayed for Prepack conversion factor text field when user enters invalid value") 
		public void Test02_116052_1122628()
	  
	  { 
	  test.siteConfigurationAction.sendKeysPrePackConversionFactorTextBox("ADC"); 
	  Assert.assertTrue(test.siteConfigurationAction.invalidMessageDisplayed());
	  }
	 
	 @Test(priority = 3,enabled = true, description ="VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item: User is able to select bulk item for prepack item when user click on any item present under popup") 
		public void Test03_116052_1122631()
  
	 { 
	  test.siteConfigurationAction.sendKeysPrePackConversionFactorTextBox("ADC"); 
	  Assert.assertTrue(test.siteConfigurationAction.invalidMessageDisplayed());
	  test.siteConfigurationAction.sendKeysPrePackConversionFactorTextBox("1");
	 }
	
	 @Test(priority = 4,enabled = true, description ="VPLX: [UI]: Fast Mover Prepacks item with associated Bulk item:  fastmover with bulk flag is selected by default when Prepack flag set to true(checked)") 
		public void Test04_116052_1122633()
		
	 { 
		test.siteConfigurationAction.checkPrePackFlag();
		test.siteConfigurationAction.uncheckPrePackPopUpYesButton();
		test.siteConfigurationAction.checkPrePackFlag();
		test.siteConfigurationAction.clickButton("save");
	 }
	}
