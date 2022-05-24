package com.org.tests.mainmenu.receiveandprocessinvoices;

import static com.org.automation.utils.YamlReader.getData;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_998682 extends BaseTest {

	String medItem;

	@Test(priority = 1, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- View all received invoices [UI]: User is able to see the received invoices as cards in the Pending Receive and Received")
	public void Test01_1054759() {

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
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName3").trim(),
				getData("PurchaseOrderDetail.orderquantity"));

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber", "PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");

		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2").trim());
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber", "PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions
				.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

	}

	@Test(priority = 2, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- View all received invoices [UI]: The card displays Name of Active Distributor, Type of order, ISA Name , Med class ")
	public void Test02_1055476() throws InterruptedException {

		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.distributorDetailsVisibleOnHeader(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.orderTypeVisibleOnHeader("Manual Order");
		test.purchaseDashboardActions.isaDetailsAreVerified();

	}

	@Test(priority = 3, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- View all received invoices [UI]: If the order is Manual and Auto receive is on then Card directly falls in Received Column")
	public void Test03_1056181() throws InterruptedException {
		
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions
		.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}

	@Test(priority = 4, enabled = true, description = "VPLX: Receive & Process Invoices (from Distributors)- View all received invoices [UI]: Manual order with Auto receive as off  will fall in Pending Receive Column.")
	public void Test04_1056184() throws InterruptedException {

		test.supportDataActions
		.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}

}
