package com.org.tests.manualInvoiceTagging;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1078732 extends BaseTest {
	
	String purchaseOrderDistributorName;
	
	
	@Test(priority = 1, description = "VPLX : Manual Tagging [UI] : Verify user is able to add comment for the orders")
	public void Test01_1098371() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName").trim());
		
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		
		// test.supportDataActions.openPurchaseOrderManualcard();
		test.purchaseDashboardActions
		.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber","PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.clickButton("Add");
		test.siteConfigurationAction.verifyHeader("Add Comment");
		test.siteConfigurationAction.enterDataInTextAreaField("commentText", getData("PurchaseOrderDetail.Comments"));
		test.supportDataActions.clickButton("save");
		test.purchaseDashboardActions.verifyUserIsAbleToAddComment();
		
	}
	
	
	@Test(priority = 2, description = "VPLX : Manual Tagging [UI] : Verify user is able to edit the comment for the orders")
	public void Test02_1098372() {	
		test.supportDataActions.clickButton("Add");
		test.siteConfigurationAction.verifyHeader("Add Comment");
		test.siteConfigurationAction.enterDataInTextAreaField("commentText", 
				"Edited-" + " " + getData("PurchaseOrderDetail.Comments"));
		test.supportDataActions.clickButton("save");
		test.purchaseDashboardActions.verifyUserIsAbleToAddComment();
	}
	
}
