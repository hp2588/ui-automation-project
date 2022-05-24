package com.org.tests.mainmenu.receiveandprocessinvoices;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.Random;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

@Listeners(com.org.listeners.TestListener.class)

public class Story_1021785 extends BaseTest {

	String medItem;
	String medItem_two;
	SoftAssert soft = new SoftAssert();

	@Test(priority = 1, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: On clicking invoice card from Dashboard, user navigates to Invoice details screen")
	public void Test01_1021785_1056707() throws InterruptedException {

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

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber", "PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

	}

	@Test(priority = 2, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  User is able to see the Distributor Details on the header")

	public void Test02_1021785_1056718() {

		
		
	}

	@Test(priority = 3, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  user is able to see Total number of Items and Order status adject to the Distributor Details on the header")

	public void Test03_1021785_1057793() {

		

	}

	@Test(priority = 4, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Columns Items, VIC, NDC, Status, Received, Shipped, Ordered, Cost, Package Size, Comments should be visible on Invoices screen")
	public void Test04_1021785_1057802() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor"));
		Assert.assertTrue(test.purchaseDashboardActions.checkColumnItems());
		Assert.assertTrue(test.purchaseDashboardActions.checkVIC());
		Assert.assertTrue(test.purchaseDashboardActions.checkNDC());
		Assert.assertTrue(test.purchaseDashboardActions.checkStatus());
		Assert.assertTrue(test.purchaseDashboardActions.checkRecieved());
		Assert.assertTrue(test.purchaseDashboardActions.checkOrdered());
		Assert.assertTrue(test.purchaseDashboardActions.checkPacketSize());
		Assert.assertTrue(test.purchaseDashboardActions.checkComments());
		Assert.assertTrue(test.purchaseDashboardActions.clickPrintOrderSheet());

	}

	@Test(priority = 5, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  User is able to check/uncheck checkboxes for each record of Invoices.")
	public void Test05_1021785_1057805() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxIsSelected());

	}

	@Test(priority = 6, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  User is able to check/uncheck all checkboxes by checking /unchecking select all checkbox in the column header")
	public void Test06_1021785_1057807() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());

	}

	@Test(priority = 7, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Receive column data is editable")
	public void Test07_1021785_1057826() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		test.purchaseDashboardActions.enterPONumberPerOrder("1", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.recievedItemsColumn("151");
		test.purchaseDashboardActions.recievedItemsCost("10");

	}

	@Test(priority = 8, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Receive column data allows data upto 10 characters")
	public void Test08_1021785_1057841() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.recievedItemsColumn("10000000001");
		test.purchaseDashboardActions.recievedItemsCost("10");
	}

	@Test(priority = 9, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Cost column data allows data upto 10 characters and accepts decimal upto 2 places")
	public void Test09_1021785_1057854() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.recievedItemsColumn("30");
		test.purchaseDashboardActions.recievedItemsCost("1000000000123");
		test.purchaseDashboardActions.recievedItemsCost("100.231");
		test.purchaseDashboardActions.recievedItemsCost("10.11");
	}

	@Test(priority = 10, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Package size column data allows data upto 10 characters")
	public void Test01_1021785_105857() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.packageSizeColumn("30000000001234");
	}

	@Test(priority = 11, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Special characters & alphabets should not be allowed while editting receive,cost & package size.")
	public void Test011_1021785_105859() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		Thread.sleep(6000);
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.verifyInvalidErrorPackageSize("10!@#$%^&*()"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyInvalidErrorCost("10!@#$%^&*()"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyInvalidErrorItems("10!@#$%^&*()"));

	}

	@Test(priority = 12, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Comments column allows data upto 500 characters")
	public void Test012_1021785_1057861() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		test.purchaseDashboardActions.clickOnComment();
		Assert.assertTrue(test.purchaseDashboardActions.addComment());
	}

	@Test(priority = 13, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: user can see the drug class , PO Number, PO Description, and Invoice Number in Invoice Info Section on left hand side")
	public void Test013_1021785_1057864() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.containsPODescription());
		Assert.assertTrue(test.purchaseDashboardActions.containsPONumber());
		Assert.assertTrue(test.purchaseDashboardActions.containsInvoiceNumber());
	}

	@Test(priority = 14, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:  Invoice texbox is editable alphanumeric data only")
	public void Test014_1021785_1057893() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC123"));
	}

	@Test(priority = 15, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: On clicking invoice card from Dashboard, user navigates to Invoice details screen")
	public void Test015_1021785_1057903() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveButton();
	}

	@Test(priority = 16, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Card moves under Received state on Dashboard on clicking Receive All button on Invoice Screen")
	public void Test016_1021785_1057905() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAllButton();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 17, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Card moves under Received state on Dashboard on clicking Receive All and Send To Queue button on Invoice Screen")
	public void Test017_1021785_1057919() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAllAndSendToQueueButton();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 18, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Card moves under Received state on Dashboard on clicking Receive and Sent to Queue button on Invoice Screen")
	public void Test018_1021785_1057949() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAndSendToQueue();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 19, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Popup appears on clicking Receive All button")
	public void Test019_1021785_1058008() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAllButton();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 20, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Popup appears on clicking Receive All & Sent to Queue")
	public void Test020_1021785_1058011() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAllAndSendToQueueButton();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 21, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Card moves under Received state on Dashboard on clicking Bypass button on Invoice Screen")
	public void Test021_1021785_1129459() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnBypass();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 22, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Status of the item changes to “Bypassed”, and no Restock transaction gets created for the item location.")
	public void Test022_1021785_1058048() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnBypass();

		// Go to TQ restock tab and check Receiving transaction
		test.landingPageActions.navigateToMenu("Transaction Queue");
		/*
		 * test.transactionQueueActions.verifyTQPageAndAppendIP(
		 * TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		 * Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage()
		 * , "\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		 * test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.
		 * getProperty("ISAName"), 0);
		 * test.storageAreaAction.selectPrinterForSelectedISA(
		 * TestDataPropertyReaderAndWriter.getProperty("PrinterName"));
		 * test.storageAreaAction.verifyStartWorkButtonAndClick();
		 * test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		 * test.transactionQueueActions.verifyTabOnTQAndClick("Restocks");
		 * 
		 * 
		 * test.transactionQueueActions.selectRestockTransaction(getData(
		 * "ProcessInvoices.SearchItem3")); boolean
		 * status=test.transactionQueueActions.verifyReceivingTransactionAfterRelease(
		 * getData("ProcessInvoices.SearchItem3")); Assert.assertTrue(
		 * status,"[Assertion Failed]: Receiving Transaction is not present in Restock tab"
		 * );
		 */
	}

	@Test(priority = 23, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Status of the item changes to “Received”, and no Restock transaction gets created for the item location.")
	public void Test023_1021785_1129460() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnBypass();

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");

	}

	@Test(priority = 24, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Status of the item changes to “Sent to Queue”, and a Restock transaction is created for the item location with the Received Quantity and “Receiving” transaction priority.")
	public void Test024_1021785_1129461() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAndSendToQueue();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	}

	@Test(priority = 25, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Invoice is moved to the Received column and the status for all items change to “Received”, and no transaction is created for any of the item locations.")
	public void Test025_1021785_1129463() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAndSendToQueue();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");

	}

	@Test(priority = 26, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors: [UI]: Invoice is moved to the Received column and the status for all items change to “Sent to Queue”, and Restock transactions are created for all item locations with the Receiving transaction Priority.")
	public void Test026_1021785_1129464() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAndSendToQueue();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");

	}

	@Test(priority = 27, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Invoice is moved to the Received column and the status for all items change to “Received”, and no transaction is created for any of the item locations.")
	public void Test027_1021785_1129475() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveButton();

	}

	@Test(priority = 28, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice: [UI]: Invoice is moved to the Received column and the status for all items change to “Received”, and no transaction is created for any of the item locations.")
	public void Test028_1021785_1129476() throws InterruptedException {

		Random random = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickOnPendingReceiveCard(getData("ProcessInvoices.Distributor2"));
		// Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + random.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickOnRecieveAndSendToQueue();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");

	}

	@Test(priority = 29, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors: [UI]: Ordered Quantity is greater Package size for an item in a single PO is created restock transaction in TQ")
	public void Test029_1021785_1130413() throws InterruptedException {

		Random rand = new Random();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("ProcessInvoices.FacilityName2"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("ProcessInvoices.SearchItem3"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity", "10000");

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysPurchaseOrder("ABC" + rand.nextInt(123456)));
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickExportNowButton();
		test.purchaseDashboardActions.clickExportNowButtonPopUp();
		test.purchaseDashboardActions.openPendingRecievedPOCard(getData("ProcessInvoices.Distributor2"));
		Assert.assertTrue(test.purchaseDashboardActions.sendKeysInvoiceNumber("ABC" + rand.nextInt(123456)));
		test.purchaseDashboardActions.recievedItemsCost("10.11");
		Assert.assertTrue(test.purchaseDashboardActions.invoiceCheckboxAllIsSelected());
		test.purchaseDashboardActions.clickRefreshOrderDetailsPage("#");
		test.purchaseDashboardActions.clickOnRecieveButton();
	}

	@Test(priority = 30, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]: Inactive Facility is not available at facility dropdown option on dashboard page")
	public void Test030_1021785_1130516() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		Assert.assertFalse(test.purchaseDashboardActions.selectDropDownValueInvalid("InvalidValue"));
	}

	@Test(priority = 31, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- Edit Invoice [UI]:Transaction Not available for Inactive Facility")
	public void Test031_1021785_1130516() throws InterruptedException {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		Assert.assertFalse(test.purchaseDashboardActions.selectDropDownValueInvalid("InvalidValue"));
	}

}
