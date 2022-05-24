package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1055466 extends BaseTest{

	String orderQuantity1,orderQuantity2;
	
	@Test(priority = 1, description = "VPLX: Export Single PO (Individual ISA)- [UI] : Line items should be freeze upon exporting PO")
	public void Test01_1076182() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		
//		test.purchaseDashboardActions.enterOrderQuantityValue("toOrderQuantity",getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		orderQuantity1=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		//orderQuantity2=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		test.purchaseDashboardActions.verifyLineItemFreeze("0");
		
		//test.purchaseDashboardActions.verifyLineItemFreeze(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim(),orderQuantity2);
		
		
	}
	
	@Test(priority = 2, description = "VPLX: Export Single PO (Individual ISA)- [UI] : Order status in the exported section should be Exported")
	public void Test02_1076185() {
		
		test.purchaseDashboardActions.getOrderStatus("Exported Order");
		//test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}
	
	@Test(priority = 3, description = "VPLX: Export Single PO (Individual ISA)- [UI] : Refresh button should not be displayed in the exported column")
	public void Test03_1076186() {
		
		test.purchaseDashboardActions.verifyRefreshButtonIsDisabled("#");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 4, description = "VPLX: Export Single PO (Individual ISA)- [UI] : PO card for the given distributor in the \"New\" column should be updated with the number of items and POs/ISAs ")
	public void Test04_1076194() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));
		
//		test.purchaseDashboardActions.enterOrderQuantityValue("toOrderQuantity",getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		String itemCount1=test.purchaseDashboardActions.getNewStateItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		//orderQuantity2=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		orderQuantity1=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		
		
		test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
		String itemCount2=test.purchaseDashboardActions.getNewStateItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(itemCount1 != itemCount2, "[Assertion Failed]: Dashboard count has not changed after export");
	}
	
	@Test(priority = 5, description = "VPLX: Export Single PO (Individual ISA)- [UI] : No PO/items remains on the card for the distributor after export, the distributor card should be removed ")
	public void Test05_1076195() {
	
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());
		orderQuantity1=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.verifyCardIsNotVisible(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
	}
	
	@Test(priority = 6, description = "VPLX: Export Single PO (Individual ISA)- [UI]: Existing PO card should get updated with the number of items and POs/ISAs just exported")
	public void Test06_1076196() {
			
		String itemCount1=test.purchaseDashboardActions.getExportedStateItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),getData("PurchaseOrderDetail.orderquantity"));				
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		
		//orderQuantity2=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		orderQuantity1=test.purchaseDashboardActions.getLineItemOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		
		
		String itemCount2=test.purchaseDashboardActions.getExportedStateItemCount(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		Assert.assertTrue(itemCount1!=itemCount2, "[Assertion Failed]: Dashboard count of card in exported state has not changed after export");
		
	}
	
	@Test(priority = 7, description = "VPLX: Export Single PO (Individual ISA)- [UI] : New PO card should be created in exported state to show the number of items and POs/ISAs just exported if not already ")
	public void Test07_1076198() {
		
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		
//		test.purchaseDashboardActions.enterOrderQuantityValue("toOrderQuantity",getData("PurchaseOrderDetail.orderquantity"));
		
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ElecItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.enterPONumberPerOrder("1","PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.enterPONumberPerOrder("2","PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.getConfirmExportMessageForManual();
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnExportedCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName3").trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
		
	}
}
