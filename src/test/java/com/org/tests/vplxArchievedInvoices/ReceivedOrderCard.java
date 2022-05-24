package com.org.tests.vplxArchievedInvoices;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class ReceivedOrderCard extends BaseTest {

	@Test(priority = 1, description = "VPLX: Export All POs (All ISAs): [UI]: Export button confirmation pop-up message.")
	public void Test01_1053383() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		 test.purchaseDashboardActions.clickPOActionbutton("Actions");
		 test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		 test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		 test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		 test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
		 TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		 test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
		 getData("PurchaseOrderDetail.orderquantity"));
		 test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		 test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		 test.purchaseDashboardActions.enterPONumberPerOrder("1","PO"+System.currentTimeMillis());
		 test.purchaseDashboardActions.savePONumber("New Order");
		 test.purchaseDashboardActions.clickButton("exportNow");
		 test.purchaseDashboardActions.clickButton("primary");
		 test.purchaseDashboardActions.clickOnReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		 test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	
		
}
	
