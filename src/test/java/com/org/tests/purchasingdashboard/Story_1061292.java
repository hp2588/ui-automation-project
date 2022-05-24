package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1061292 extends BaseTest{

	String poNumber;
	
	@Test(priority = 1, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of Received accordion after date removal")
	public void Test01_1070513_1070514(Method method) {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		
		test.purchaseDashboardActions.verifyReceivedStateAll();
	}
	
	@Test(priority = 2, description = "VPLX: Buyer Dashboard Updates: [UI]: Status of the item changes to “Received”, and no Restock transaction gets created for the item .")
	public void Test02_1129462(Method method) {
		
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"));
		test.supportDataActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"));
		test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", "100");
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		//test.supportDataActions.openPurchaseOrderManualcard();
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ISAName1").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		
		test.purchaseDashboardActions.verifyDistributorCardInNewStateIsOpen(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(),"Dashboard");
		
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		//test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("Received");
		
		// Go to TQ WFA screen and check Receiving transaction is created
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName1"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		//Assert.assertTrue(test.storageAreaAction.verifyNoReceivingTransactionForAvailableISA(TestDataPropertyReaderAndWriter.getProperty("ShortName1")));
		
	}
	
	@Test(priority = 3, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of Invoices under Received Column")
	public void Test03_1070515_1070887(Method method) {
		
		test.purchaseDashboardActions.clickWFACancelButton("Cancel");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(),getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ISAName1").trim());
		poNumber=test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.verifyPOCardIsPresent(poNumber);
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	

	@Test(priority = 4, description = "VPLX: Buyer Dashboard Updates: [UI]:  Expand and Collapse of Received column")
	public void Test04_1070516(Method method) {
		
		test.purchaseDashboardActions.minimizeMaximizeNewStateAccordian("Received");	
	}
	
	@Test(priority = 5, description = "VPLX: Buyer Dashboard Updates: [UI]:  Type item name in search box to create a PO")
	public void Test05_1118183(Method method) {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSaveAndClose("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
}
