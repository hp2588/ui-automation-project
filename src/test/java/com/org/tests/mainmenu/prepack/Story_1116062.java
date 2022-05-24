package com.org.tests.mainmenu.prepack;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1116062 extends BaseTest{

	
	@Test(priority = 1,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking : [UI]  Relink option is available  on the details page for linked item .")
	public void Test01_1116062_1124863() 
	
	{
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
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
		
	}
	
	@Test(priority = 2,enabled = true, description = "VPLX: Barcode Management: Linking/Unlinking: [UI] User get a message for confirmation when Relinking the item.")
	public void Test02_1116062_1125534() 
	
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
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
		test.purchaseDashboardActions.verifyPrintOrderSheetButtonIsPresent("printOrder");
	}
	}
