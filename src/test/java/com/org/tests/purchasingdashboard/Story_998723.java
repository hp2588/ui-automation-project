package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_998723 extends BaseTest {

	String po_Description,poNumber;

	@Test(priority = 1, description = "VPLX: Void Orders after Export PO: [UI]: Link of Void on Export Screen")
	public void Test01_1076602() {

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

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

		
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());
		poNumber=test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");	

		test.purchaseDashboardActions
				.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.verifyVoidButtonIsPresent("1");
		// test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}

	@Test(priority = 2, description = "VPLX: Void Orders after Export PO: [UI]: Void single PO present under distributor on export screen")
	public void Test02_1076604() {

		String po_Description = test.purchaseDashboardActions.getPODescription("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		String successmessage = "The PO " + po_Description + " for the distributor "
				+ TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim() + " successfully voided.";
		// System.out.println(successmessage);
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successmessage);
	}

	@Test(priority = 3, description = "VPLX: Void Orders after Export PO: [UI]: Void single PO present under distributor on export screen")
	public void Test03_1076631() {

		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
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
		test.purchaseDashboardActions.enterPONumberPerOrder("2", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		// test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");

		test.purchaseDashboardActions
				.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		String po_Description = test.purchaseDashboardActions.getPODescription("1");
		test.purchaseDashboardActions.verifyVoidButtonIsPresent("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");
		String successmessage = "The PO " + po_Description + " for the distributor "
				+ TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim() + " successfully voided.";
		// System.out.println(successmessage);
		
		//The PO UI1597403770395_ISA1597401771787_000101010000 for the distributor DisElecOFF1597403739794 successfully voided.
		
		
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successmessage);
	}

	@Test(priority = 4, description = "VPLX: Void Orders after Export PO: [UI]: Confirmation pop-up after clicking on Void")
	public void Test04_1076672() {

		test.purchaseDashboardActions.verifyVoidButtonIsPresent("1");
		String po_Description = test.purchaseDashboardActions.getPODescription("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		Assert.assertTrue(test.purchaseDashboardActions.getConfirmVoidPopupText(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim(), po_Description));

	}

	@Test(priority = 5, description = "VPLX: Void Orders after Export PO: [UI]: Void all PO's present under exported section")
	public void Test05_1076676() {

		test.purchaseDashboardActions.clickYesOrNoAfterVoid("secondary");
		test.purchaseDashboardActions.clickOnVoidAll("Void All");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("primary");

	}

	@Test(priority = 6, description = "VPLX: Void Orders after Export PO: [UI]: Cancellation of void functionality through confirmation pop-up.")
	public void Test06_1078851() {

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

		test.purchaseDashboardActions.enterPONumberPerOrder("1", "PO" + System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		//test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
		test.purchaseDashboardActions
				.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.verifyVoidButtonIsPresent("1");
		test.purchaseDashboardActions.clickOnVoidButton("1");
		test.purchaseDashboardActions.clickYesOrNoAfterVoid("secondary");

	}
}
