package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1066387 extends BaseTest {

	String invoice1, invoice2, ponumber1, ponumber2;

	@Test(priority = 1, description = "VPLX: Buyer Dashboard Updates: [UI]: Multiple Invoices for Same distributor", enabled = false)
	public void Test01_1070885_1070886(Method method) {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName4").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName4").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName4").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName4").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.enterPONumberPerOrder("1", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.enterPONumberPerOrder("2", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.verifyReceivedStateAll();
		test.purchaseDashboardActions
				.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

	}

	@Test(priority = 2, description = "VPLX: Buyer Dashboard Updates: [UI]: Display of all invoices  and different distributors under received column"
			+ "VPLX: Buyer Dashboard Updates: [UI]: Display of distributor and invoices under received  screen")
	public void Test02_1070886_1070887(Method method) {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		ponumber1 = test.purchaseDashboardActions.enterPONumberPerOrder("1", "PO" + System.currentTimeMillis());
		ponumber2 = test.purchaseDashboardActions.enterPONumberPerOrder("2", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		
		//Received and send item 1
		test.purchaseDashboardActions.clickOnPOCardInPendingReceived(ponumber1);
		invoice1 = test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.siteConfigurationAction.clickButton("ReceivedandSent");
		
		//Received and send item 2		
		test.purchaseDashboardActions.clickOnPOCardInPendingReceived(ponumber2);
		invoice2 = test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.siteConfigurationAction.clickButton("ReceivedandSent");
		
		
		test.purchaseDashboardActions.verifyDistributorCardInPendingReceivedIsOpen(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"), "Dashboard");

		test.purchaseDashboardActions.verifyReceivedStateAll();
		test.purchaseDashboardActions
				.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions
				.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.verfiyInvoiceIsPresentInReceivedState(ponumber1, invoice1);
		test.purchaseDashboardActions.verfiyInvoiceIsPresentInReceivedState(ponumber2, invoice2);
	}

}
