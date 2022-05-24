package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

@Listeners(com.org.listeners.TestListener.class)

public class Story_1021006 extends BaseTest{

	String medItem_one, medItem_two, poNumber, poNumber2, invoiceNumber;
	
	@Test(priority = 1, description = "VPLX: Create POs : [UI] User is able to cancel the order Place process on pressing cancel button")
	public void Test01_1051213_1054954() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
		//Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(),false);
	}
	
	@Test(priority = 2, description = "VPLX: Create POs : [UI] Order new item page get opened on clicking Order New Items option")
	public void Test02_1051186_1054922() {
		

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
	}
	
	@Test(priority = 3, description = "VPLX: Create POs : [UI] User is able to search items and get details of the item")
	public void Test03_1051200_1054939() {
		
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	}
	
	@Test(priority = 4, description = "VPLX: Create POs : [UI] Order is being placed by clicking Save&Close button with order quantity given")
	public void Test04_1051207() {
		
		medItem_one=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPurchaseOrderManualCardisPresent(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),true);
	}
	
	@Test(priority = 5, description = "VPLX: Create POs: [UI]: New Purchase order is created under New State")
	public void Test05_1111558() {
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.getOrderStatus("New Order");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 6, description = "VPLX: Create POs : [UI] Multiple items Order can be placed by clicking Save&Add button with order quantity given")
	public void Test06_1051212_1111564() {
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		medItem_two=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(), getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 7, description = "VPLX: Create PO: [UI] : Dynamic width of NDC and VIC field for orders under New state.")
	public void Test07_1145096() {

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		// add code for NDC and vic validation

		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemVICOrder(TestDataPropertyReaderAndWriter.getProperty("VICItem6").trim()));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemNDCOrder(TestDataPropertyReaderAndWriter.getProperty("NDCItem6").trim()));

		poNumber = test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		// test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

	}

	@Test(priority = 8, description = "VPLX: Create PO: [UI] : Dynamic width of NDC and VIC field for orders under Exported state.")
	public void Test08_1145097() {

		test.purchaseDashboardActions
				.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemVICOrder(TestDataPropertyReaderAndWriter.getProperty("VICItem6").trim()));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemNDCOrder(TestDataPropertyReaderAndWriter.getProperty("NDCItem6").trim()));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}

	@Test(priority = 9, description = "VPLX: Create PO: [UI] : Dynamic width of NDC and VIC field for orders under pending receive state."
			+ "VPLX: Create PO: [UI] : Dynamic width of NDC and VIC field for orders when two items under two isa's are present.")
	public void Test09_1145098_1145100() {

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
		

		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());

		poNumber2 = test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		
		test.purchaseDashboardActions
		.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		poNumber = test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));

		test.purchaseDashboardActions.clickOnPOCardInPendingReceived(poNumber);

		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemVICInvoice(TestDataPropertyReaderAndWriter.getProperty("VICItem1").trim()));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemNDCInvoice(TestDataPropertyReaderAndWriter.getProperty("NDCItem1").trim()));

		test.purchaseDashboardActions.clickOnPOCardInPendingReceived(poNumber2);

		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemVICInvoice(TestDataPropertyReaderAndWriter.getProperty("VICItem2").trim()));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemNDCInvoice(TestDataPropertyReaderAndWriter.getProperty("NDCItem2").trim()));

		invoiceNumber = test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		// test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");

	}

	@Test(priority = 10, description = "VPLX: Create PO: [UI] : Dynamic width of NDC and VIC field for orders under received state.")
	public void Test10_1145099() {

		test.supportDataActions.clickReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.clickOnPOCardInPendingReceived(poNumber2);
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemVICOrder(TestDataPropertyReaderAndWriter.getProperty("VICItem1").trim()));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyItemNDCOrder(TestDataPropertyReaderAndWriter.getProperty("NDCItem1").trim()));

	}
	
	
}
