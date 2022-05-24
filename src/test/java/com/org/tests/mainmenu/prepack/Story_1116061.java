package com.org.tests.mainmenu.prepack;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1116061 extends BaseTest{

	String medItem_one;
	String medItem_two;
	
	@Test(priority = 1,enabled = true, description = "VPLX: [UI]:Fast Mover Prepacks item with associated Bulk item: Correct Location is displayed while searching and creating order when Prepack item do have location and bulk dont have any location")
	public void Test01_1116061_1125235() 
	
	{	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Add Another");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem2"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem2"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("PurchaseOrderData.SearchItem2"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
		test.purchaseDashboardActions.enterPONumberPerOrder("1","PO"+System.currentTimeMillis());
		test.purchaseDashboardActions.enterPONumberPerOrder("2","PO"+System.currentTimeMillis());
		
		test.purchaseDashboardActions.savePONumber("New Order");
		test.purchaseDashboardActions.clickAllISA();
		test.purchaseDashboardActions.clickButton("exportNow");
		test.purchaseDashboardActions.clickButton("primary");
		test.purchaseDashboardActions.clickOnExportedCard(getData("PurchaseOrderData.Distributor"));
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 2,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User can search on the basis of item id .")
	public void Test02_1116062_1125327() 
	
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
			}
	
	@Test(priority = 3,enabled = true, description = "VPLX:Barcode Management: Linking/Unlinking : [UI] User cannot  search on the basis of Actions.")
	public void Test01_1116061_1125333() 
	
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity",
				getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}	  
}
