package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1020166 extends BaseTest {

	String purchaseOrderDistributorName;
	String oldPkgSize = "5";
	String newPkgSize = "10";
	String orderQuantity="20";

	@Test(priority = 1, description = "VPLX: Buyers Dashboard: [UI]:  States present under Dashboard distributor listing")
	public void Test01_1039620() {

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

	@Test(priority = 2, description = "VPLX: Buyers Dashboard: [UI]:  Information present in states under dashboard distributor listing")
	public void Test02_1039626_1142052() {
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), "20");
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		Assert.assertTrue(
				test.purchaseDashboardActions.validateItemAndISACount(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion Failed]: Item and ISA count not present on the purchase order card");
	}

	@Test(priority = 3, description = "VPLX: Buyers Dashboard: [UI]:  Distributor listing based on Facilities present under selected Facility")
	public void Test03_1039628() {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		// purchaseOrderDistributorName =
		// test.purchaseDashboardActions.getPurchaseOrderDistributorName("New");

		// navigate to facility distributor account to verify distributor is listed in
		// the facility
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorMappedWithFacility(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion Failed]: Distributor not listed in Facility");
	}

	@Test(priority = 4, description = "VPLX: Buyers Dashboard: [UI]:  Search box present under dashboard")
	public void Test04_1039631() {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifySearchBoxOnPurchaseDashboard("searchTypeInput"),
				"[Assertion Failed]: Search Input box not present on Purchase Dashboard ");
	}

	@Test(priority = 5, description = "VPLX: Buyers Dashboard: [UI]:  Group By present under dashboard with Group by date and Group by Distributor")
	public void Test05_1039633() {
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Date");
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Distributor");
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Type");
		test.purchaseDashboardActions.selectGroupByDropdownValue("groupBy", "Group By Date");
	}

	@Test(priority = 6, description = "VPLX: Buyers Dashboard: [UI]:  Manual Orders cannot be dragged dropped from one state to another")
	public void Test06_1039726() {

		test.purchaseDashboardActions.dragAndDropOrder(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(), "Pending Receive");

	}

	@Test(priority = 7, description = "VPLX: Buyers Dashboard: [UI]:  Number of cards displayed for Manual Order under each state")
	public void Test07_1039727() {

		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Manual distributor name is not present on present on the purchase order card");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");

	}

	// need electronic distributor card in new state for the below test case
	@Test(priority = 8, description = "VPLX: Buyers Dashboard: [UI]:  Number of cards displayed for Electronic Order under each state")
	public void Test08_1039728() {

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(), "20");

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Electronic Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorCardType(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");

	}

	@Test(priority = 9, description = "Exporting electronic and Manual order")
	public void Test09_Export_Elec_Manual_Order() {

		// Move card to Received state

		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());

		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");

		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		// test.supportDataActions.selectItemtoRecieve(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.clickButton("ReceivedandSent");

		// Move electronic card to Exported state

		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());

		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");

		// Create new manual order

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), "20");
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());

		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		test.purchaseDashboardActions.enterPONumberForActivePO("PO" + System.currentTimeMillis());
		test.supportDataActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");

		// Create new electronic order

		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions
				.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(), "20");

		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim()),
				"[Assertion failed]: Electronic Distributor name is not present on present on the purchase order card");

	}

	@Test(priority = 10, description = "VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it in Existing order in New State."
			+"VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it in Existing order in Exported State."
			+"VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it in Existing order in Pending Receive State."
			+"VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it in Existing order in Received State."
			+"VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it in Existing order in New State. Electronic Distriutor and Manual Distributor")
	public void Test10_1144755_1144756_1144757_1144759_1144818() {

		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		// add logic to search and edit manual item
		test.supportDataActions.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "10");
		test.siteConfigurationAction.clickSaveButtonForISA();

		// add logic for electronic item package size change
		test.supportDataActions.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "10");
		test.siteConfigurationAction.clickSaveButtonForISA();

		// add logic for unable to order package size change
		test.supportDataActions.enterSearchTermInSearchField(
				TestDataPropertyReaderAndWriter.getProperty("UnableToOrderItem").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(
				TestDataPropertyReaderAndWriter.getProperty("UnableToOrderItem").trim());

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "10");
		test.siteConfigurationAction.clickSaveButtonForISA();

		// navigate to purchase dashboard

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

		// verify pkg size update in new state order
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizeNewOrder(newPkgSize),
				"[Assertion Failed]: Package Size is not updated");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

		// verify pkg size is not updated in Exported state order
		test.purchaseDashboardActions
				.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizeNewOrder(oldPkgSize),
				"[Assertion Failed]: Package Size is updated");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

		// verify pkg size is not updated in Pending Receive state invoice
		test.supportDataActions
				.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizePendingReceive(oldPkgSize),
				"[Assertion Failed]: Package Size is updated");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

		// verify pkg size is not updated in Received state invoice
		test.supportDataActions.clickReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizeReceive(oldPkgSize),
				"[Assertion Failed]: Package Size is updated");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}

	@Test(priority = 11, description = "VPLX: Buyer Dashboard: [UI]: Update package size for an item and verify it for New Order."
			+ "VPLX: Buyer Dashboard: [UI]: Update package size for an item and check on order new items screen."
			+"VPLX: Buyer Dashboard: [UI]: Order Quantity gets divided by Package size on Dashboard.")
	public void Test11_1144760_1144775_1150986() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
				
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name",
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());

		// verify package size on create order screen
		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizeOnCreateOrderScreen(newPkgSize),
				"[Assertion Failed] : Package size is not updated on Order new item screen");

		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions
				.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(), orderQuantity);
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");

		
		Assert.assertTrue(
				test.purchaseDashboardActions.validateDistributorCardName(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim()),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		test.purchaseDashboardActions
				.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());

		test.purchaseDashboardActions
				.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());

		Assert.assertTrue(test.purchaseDashboardActions.verifyPkgSizeNewOrder(newPkgSize),
				"[Assertion Failed]: Package Size is not updated");
		
		Assert.assertTrue(test.purchaseDashboardActions.verifyOrderQuantityIsMultipleOfPackageSize(orderQuantity,newPkgSize),
				"[Assertion Failed] : Order Quantity is not a multiple of package size");	

	}
	
	@Test(priority = 12, description = "VPLX: Buyer Dashboard: [UI]: Update package size for an item on Unable to Order items.")
	public void Test12_1144766() {
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg")));
		test.purchaseDashboardActions.clickResolveNow();
		test.purchaseDashboardActions.verifyUnableToOrderItemPkgSize(newPkgSize);
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
}
