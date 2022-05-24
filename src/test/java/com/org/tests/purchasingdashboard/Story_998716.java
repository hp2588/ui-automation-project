package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.awt.AWTException;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_998716 extends BaseTest{

	@Test(priority = 1, description = "VPLX: Export All POs (All ISAs): [UI]: Printing of PO items through Print Order Sheet button.")
	public void Test01_1053978() throws AWTException {
	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		//test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		//test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem2"));
		//test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem2"));
		//test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
		//		getData("PurchaseOrderData.SearchItem2"));
		//test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
		//		getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickPrintOrderSheet("printOrder");
		test.purchaseDashboardActions.clickEscape();
	}
	
	@Test(priority = 2, description = "VPLX: Export All POs (All ISAs): [UI]:  Print Order Sheet button for all states items.")
	public void Test02_1053984() throws AWTException {
		
		//add other states as well
		test.purchaseDashboardActions.verifyPrintOrderSheetButtonIsPresent("printOrder");
		//test.purchaseDashboardActions.clickEscape();
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}
	
	@Test(priority = 3, description = "VPLX: Export All POs (All ISAs): [UI]: Printing of PO items through Print Order Sheet button for electronic orders.")
	public void Test03_1053986() throws AWTException {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickPrintOrderSheet("printOrder");
		//test.purchaseDashboardActions.clickEscape();
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}
	
	@Test(priority = 4, description = "VPLX: Export All POs (All ISAs): [UI]: Printing of PO items through Print Order Sheet button will open printing option as per browser")
	public void Test04_1053988_1053991() throws AWTException {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickPrintOrderSheet("printOrder");
		//test.purchaseDashboardActions.clickEscape();
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}
	
	@Test(priority = 5, description = "VPLX: Export All POs (All ISAs): [UI]: Fields displayed after Printing.")
	public void Test05_1053993() throws AWTException {
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim() );
		test.purchaseDashboardActions.clickPrintOrderSheet("printOrder");
		//test.purchaseDashboardActions.clickEscape();
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
		// steps to validate to printer pdf to be added
	}
}
