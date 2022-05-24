package com.org.tests.manualInvoiceTagging;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1078729 extends BaseTest {

	String purchaseOrderDistributorName;
	
	
	@Test(priority = 1, description = "VPLX : Manual Tagging [UI] : Verify Enable Price tagging tickbar is available in the Settings corresponding to Facility")
	public void Test01_1098360() {
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifycheckboxispresent("enablePriceTagging");
		test.siteConfigurationAction.selectCheckboxItemsTab("enablePriceTagging", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("checkbox0", true);
		
		test.siteConfigurationAction.clickSaveButton();
	}
	
	@Test(priority = 2, description = "VPLX : Manual Tagging [UI] : Verify even if the price tagging is enabled the pending receive order will not have the drop down option")
	public void Test02_Test03_1098368_1098361() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", "40");
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		
		test.purchaseDashboardActions
			.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		
		
		test.purchaseDashboardActions.verifyTypeDropdownIsUnavailableFoManualOrder("priceTagName");
		
	}

}
