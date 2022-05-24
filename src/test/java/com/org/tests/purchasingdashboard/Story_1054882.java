package com.org.tests.purchasingdashboard;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1054882 extends BaseTest {

	String successMessage,itemName,isaCount;
	
	@Test(priority = 1, description = "VPLX: Add Items Manually to distributor POs: [UI]: Default Order Quantity for an Item")
	public void Test01_1064951() {
		
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
	//	test.purchaseDashboardActions.clickItemInPO(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyDefaultOrderQuantityOnAddScreen(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}

	@Test(priority = 2, description = "VPLX: Add Items Manually to distributor POs: [UI]: User is able to update the order quantity")
	public void Test02_1064953() {
		
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
		test.purchaseDashboardActions.clearrOrderQuantityItemLevel("toOrderQuantity");
		test.purchaseDashboardActions.enterOrderQuantityItemLevel("toOrderQuantity", "15");
	}
	
	@Test(priority = 3, description = "VPLX: Add Items Manually to distributor POs: [UI]: Save and add another functionality with toast messages")
	public void Test03_1064960() {
		
		test.purchaseDashboardActions.clickButtonOnDashboard("add");
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		itemName=test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantityAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		
		successMessage="Order quantity for " + itemName + " increased in the existing PO for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim();
		
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
		test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.clickOnCancel("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
		
	}
	
	@Test(priority = 4, description = "VPLX: Add Items Manually to distributor POs: [UI]: Toast message validation on adding a quantity to an item existing for a Purchase Order")
	public void Test04_1064965() {
		
	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	test.purchaseDashboardActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	test.purchaseDashboardActions.clickPOActionbutton("Actions");
	test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
	test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	itemName=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
			TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
			getData("PurchaseOrderDetail.orderquantity"));
	test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
	
	successMessage=	itemName + " has been added to existing order for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim();
	test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
	
	}
	
	@Test(priority = 5, description = "VPLX: Create PO: [UI]: Item does not have NDC and distributor linked and No location\"")
	public void Test05_1064971_1064980() {
		
		test.purchaseDashboardActions.verifyResolveNowIsPresent("Resolve Now", getData("PurchaseOrderData.ResolveNowMsg"));
		test.purchaseDashboardActions.clickOnResolveNowLink("Resolve Now");
		test.purchaseDashboardActions.verifycountOnListPage();
	}
	
	@Test(priority = 6, description = "VPLX: Add Items Manually to distributor POs: [UI]: Save and Close  functionality with toast messages")
	public void Test06_1064975() {
		
	test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
	test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName1").trim());
	test.purchaseDashboardActions.clickButtonOnDashboard("add");
	test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	itemName=test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.enterOrderQuantityAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
			getData("PurchaseOrderDetail.orderquantity"));
	test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
	successMessage=	itemName + " was added to existing PO for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim();
	
	test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
	}
	
	@Test(priority = 7, description = "VPLX: Add Items Manually to distributor POs: [UI]: Cancel button  functionality with toast messages")
	public void Test07_1064976() {
		
	test.purchaseDashboardActions.clickButtonOnDashboard("add");
	test.purchaseDashboardActions.SearchPOItem("Search Item",TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.verifyPOItemSearchResultAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.clickSearchedPOItemAfterAdd(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
	test.purchaseDashboardActions.clickOnCancel("Cancel");
	test.purchaseDashboardActions.clickOnCancel("Yes");
	test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 8, description = "VPLX: Add Items Manually to distributor POs: [UI]:  Item already exists in an existing new PO with ISA/class that matches the item's location/class.")
	public void Test08_1054883_1064978() {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		itemName=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName1").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		successMessage=	itemName + " has been added to existing order for " + TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim();
		test.purchaseDashboardActions.verifySuccessMessageOnViewPageWithLoader(successMessage);
		
	}
	

	@Test(priority = 9, description = "VPLX: Add Items Manually to distributor POs: [UI]:  Item does not exist in any existing new PO, and the item's location/class does not match an ISA/class of an existing new PO")
	public void Test09_1064981_1066717() {
		
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.verifyPOItemSearchResult(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		itemName=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim());
		test.purchaseDashboardActions.enterOrderQuantity(TestDataPropertyReaderAndWriter.getProperty("ManualItemName2").trim(),
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim());
		test.purchaseDashboardActions.clickPOBasedOnISAName(TestDataPropertyReaderAndWriter.getProperty("ShortName2").trim());
		isaCount=test.purchaseDashboardActions.getAllISACount();
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
		test.purchaseDashboardActions.validateItemCountInPO(TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(),isaCount);
	}
	
}
