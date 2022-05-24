package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1004058 extends BaseTest{

	String poNumber;
	
	@Test(priority = 1, description = "VPLX: Add Items Manually to distributor POs: [UI]: System displays item when user enter blank spaces multiple times")
	public void Test01_1064816_1064825_1078906(Method method) {
		
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
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		//test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.verifyAddItemSearchLabel();
		test.purchaseDashboardActions.SearchPOItem("Search Item", "      ");
		test.purchaseDashboardActions.verifyNoRecordFoundOnItemSearch();
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
	@Test(priority = 2, description = "VPLX: Add Items Manually to distributor POs: [UI]: Search criteria applicable on 'item name/ item id/ brand name/ NDC'  on  Add Item to order screen")
	public void Test02_1064817(Method method) {
		
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
	@Test(priority = 3, description = "VPLX: Add Items Manually to distributor POs: [UI]: Search criteria on preferred distributor that matches the distributor in the Export screen")
	public void Test03_1064823(Method method) {
		
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.verifyAddItemLabelIsBold();
		test.purchaseDashboardActions.verifyAddItemSearchLabel();
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantityAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());
		poNumber=test.purchaseDashboardActions.enterPONumberForActivePO("PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.verifyPOCardIsPresent(poNumber.trim());
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		
	}
	
	@Test(priority = 4, description = "VPLX: Add Items Manually to distributor POs: [UI]: Search changes when search text characters are updated")
	public void Test04_1064831(Method method) {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
	//	test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.verifyAddItemSearchLabel();
		
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		
	}
	
	@Test(priority = 5, description = "VPLX: Add Items Manually to distributor POs: [UI]: Special character validation on search box")
	public void Test05_1066725(Method method) {
	
		test.purchaseDashboardActions.SearchPOItem("Search Item","$$$$$$");
		test.purchaseDashboardActions.verifyNoRecordFoundOnItemSearch();
		
	}
	
	@Test(priority = 6, description = "VPLX: Add Items Manually to distributor POs: [UI]: Focus moves to search box")
	public void Test06_1064828(Method method) {
		
		test.purchaseDashboardActions.clickOnTabAddItemScreen("Search Item");
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
		
	}
	
	
}
