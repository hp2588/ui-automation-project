package com.org.tests.mainmenu.prepack;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1116056 extends BaseTest{

	String itemID, itemName, barcode, productID,itemName1,itemID1,medItem_one;
	
	@Test(priority = 1,enabled = true, description = "Bulk order quantity is calculated correctly as per the given conversion factor")
	public void Test01_1116056_1124846() 
	
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
	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
	Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
			"[Assertion Failed]: New state is not present on the Dashboard");
	Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
			"[Assertion Failed]: Exported state is not present on the Dashboard");
	Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
			"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
	Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
			"[Assertion Failed]: Received state is not present on the Dashboard");
	test.purchaseDashboardActions.clickPOActionbutton("Actions");
	test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
	Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
	test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
	test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
	medItem_one=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),getData("PurchaseOrderData.SearchItem1"));
	test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
	test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
	}
	
	@Test(priority = 2,enabled = true, description = "VPLX: [UI]:Fast Mover Prepacks item with associated Bulk item: Correct Location is displayed while searching and creating order when Prepack item do have location and bulk dont have any location")
	public void Test02_1116056_1132235() 
	
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
				"[Assertion Failed]: Exported state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
				"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
				"[Assertion Failed]: Received state is not present on the Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		medItem_one=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
	}
	}
