package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1043660 extends BaseTest {

	String medItem_two;
	String poNumber,poNumber2;

	@Test(priority = 1, description = "VPLX: Buyers Dashboard: [UI]:  States present under Dashboard distributor listing")
	public void Test01_1046604() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
				"[Assertion Failed]: Exported state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
				"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
				"[Assertion Failed]: Received state is not present on the Dashboard");

	}

	@Test(priority = 2, description = "VPLX: Buyers Dashboard: [UI]:  Colour coding of Manual and Electronic Order")
	public void Test02_1046605() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		// test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		medItem_two = test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(test.purchaseDashboardActions.verifyOrderColorCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(), "magenta"));
		Assert.assertTrue(test.purchaseDashboardActions
				.verifyOrderColorCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), "aqua"));
	}

	@Test(priority = 3, description = "VPLX: Buyers Dashboard: [UI]: Missing Items counts, unable to item order")
	public void Test03_1046816() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now",
				getData("PurchaseOrderData.ResolveNowMsg")));
	}

	@Test(priority = 4, description = "VPLX: Buyers Dashboard: [UI]: Distributor list contains completed and pending items list")
	public void Test04_1046860() {
		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
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
		medItem_two = test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
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

	//	test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));

	//	test.purchaseDashboardActions.clickDashboardRefresh("dashboardRefreshBtn");

		String itemCountBeforeInvoiceReceive = test.purchaseDashboardActions
				.getPendingReceiveItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		System.out.println(itemCountBeforeInvoiceReceive);

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.siteConfigurationAction.clickButton("Received");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		String itemCountAfterInvoiceReceive = test.purchaseDashboardActions
				.getPendingReceiveItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		System.out.println(itemCountAfterInvoiceReceive);
		Assert.assertTrue(test.purchaseDashboardActions.verifyCountAfterInvoiceReceive(itemCountBeforeInvoiceReceive,
				itemCountAfterInvoiceReceive), "[Assertion Failed]: Count is same even after Item is received");
	}

	@Test(priority = 5, description = "VPLX: Buyers Dashboard: [UI]:  Kebab Menus present under Dashboard")
	public void Test05_1046921() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("New");
		Assert.assertTrue(test.purchaseDashboardActions.verifyKebabMenuLinkCountNewOrder("New"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("New");

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
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.enterPONumberPerOrder("1", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

		test.purchaseDashboardActions.clickOnKebabMenuPerState("Exported");
		Assert.assertTrue(test.purchaseDashboardActions.verifyKebabMenuLinkCount("Exported"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("Exported");

		test.purchaseDashboardActions.clickOnKebabMenuPerState("Pending Receive");
		Assert.assertTrue(test.purchaseDashboardActions.verifyKebabMenuLinkCount("Pending Receive"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("Pending Receive");

		test.purchaseDashboardActions.clickOnKebabMenuPerState("Received");
		Assert.assertTrue(test.purchaseDashboardActions.verifyKebabMenuLinkCount("Received"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("Received");
	}

	@Test(priority = 6, description = "VPLX: Buyers Dashboard: [UI]:  Action Menus present under Dashboard")
	public void Test06_1046922() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.verifyCreateNewOrderLink("Create New Order");
	}

	@Test(priority = 7, description = "VPLX: Buyers Dashboard: [UI]:  When no PO is present for any distributor, type or Date")
	public void Test07_1046929() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Type");
		Assert.assertEquals(test.purchaseDashboardActions.getDayCount("Today"), 0);
		Assert.assertEquals(test.purchaseDashboardActions.getDayCount("Yesterday"), 0);
		Assert.assertEquals(test.purchaseDashboardActions.getDayCount("Older"), 0);
	}

	@Test(priority = 8, description = "VPLX: Buyers Dashboard: [UI]:  Electronic and Manual order during Group by Type.")
	public void Test08_1046931() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Type");
		Assert.assertTrue(test.purchaseDashboardActions.checkGroupByDistributorType());
	}

	@Test(priority = 9, description = "VPLX: Buyers Dashboard: [UI]:  Last updated time of dashboard.", enabled = false)
	public void Test09_1046939() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		System.out.println(test.purchaseDashboardActions.getDashboardTime());
	}

	@Test(priority = 10, description = "VPLX: Buyers Dashboard: [UI]:  “Collapse All”  options in Kebab Menu.")
	public void Test10_1046957() {

		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Date");
		test.purchaseDashboardActions.clickOnKebabMenuPerState("Exported");
		Assert.assertTrue(test.purchaseDashboardActions.clickOnKebabMenuOptions("Collapse All"));
		test.purchaseDashboardActions.clickOnKebabMenuPerState("Exported");
		Assert.assertTrue(test.purchaseDashboardActions.clickOnKebabMenuOptions("Expand All"));
	}

	@Test(priority = 11, description = "VPLX: Buyers Dashboard: [UI]:  “Expand All”  options in Kebab Menu",enabled=false)
	public void Test11_1046958() {

		// test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By
		// Date");
		test.purchaseDashboardActions.clickOnKebabMenuPerState("New");
		Assert.assertTrue(test.purchaseDashboardActions.clickOnKebabMenuOptions("Expand All"));
	}

	@Test(priority = 12, description = "VPLX: Buyers Dashboard: [UI]: Refresh button refresh the screen and reflect back with updated state", enabled = false)
	public void Test12_1046968() {
		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.enterValueInSearchField("searchTypeInput",
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickDashboardRefresh("dashboardRefreshBtn");
		test.purchaseDashboardActions.clearValueInSearchField("searchTypeInput");
	}

	@Test(priority = 13, description = "VPLX: Buyers Dashboard: [UI]: TAB moves to all editable fields",enabled = false)
	public void Test13_1046969() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.clickOnTabEditableField("Actions");
	}

	@Test(priority = 14, description = "VPLX: Buyers Dashboard: [UI]:  Search criteria on the dashboard screen")
	public void Test14_1046975() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		// test.purchaseDashboardActions.clickOnPurchasingDahboardLabel();
	//	test.siteConfigurationAction.refreshPage();
		test.purchaseDashboardActions.enterValueInSearchField("searchTypeInput",
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
	}

	@Test(priority = 15, description = "VPLX: Buyers Dashboard: [UI]:  Scroll bar is available for the vertical lane")
	public void Test15_1046978() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.dashboardScroll();
	}

	@Test(priority = 16, description = "VPLX: Buyers Dashboard: [UI]:  Search results displayed  open in expand mode")
	public void Test16_1046996() {

		// test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		// test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		test.purchaseDashboardActions.clearValueInSearchField("searchTypeInput");
		test.purchaseDashboardActions.enterValueInSearchField("searchTypeInput",
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
	}
}
