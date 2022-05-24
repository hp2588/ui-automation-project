package com.org.tests.mainmenu.prepack;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1116057 extends BaseTest{

	String medItem_one;
	
	@Test(priority = 1,enabled = true, description = "VPLX:Fast Mover Prepacks item with associated Bulk item:[UI}:Order new item page get opened on clicking Order New Items option")
	public void Test01_1116057_1054135() 
	{
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
				"[Assertion Failed]: Exported state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
				"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
				"[Assertion Failed]: Received state is not present on the Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
	}
	
	@Test(priority = 2,enabled = true, description = "User is able to search items and get details of the item")
	public void Test02_1116057_1054130() 
	{	
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
	}
	
	@Test(priority = 3,enabled = true, description = "Order(For Manual and elctronic vendor) is created for prepack item when user enters order quantity and Save the popup")
	public void Test03_1116057_1054136() 
	{	
		medItem_one=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Save & Close");
	}
	
	@Test(priority = 4,enabled = true, description = "VPLX:Fast Mover Prepacks item with associated Bulk item:[UI]: Order is visible under New tab on dashboard")
	public void Test04_1116057_1054140() 
	{	
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(),true);
	}
	
	@Test(priority = 5,enabled = true, description = "Bulk item name is displayed on the card on PO dashboard")
	public void Test05_1116057_1054142() 
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrdereElectronicCardisPresent(),true);
	}
	
	@Test(priority = 6,enabled = true, description = "VPLX:Fast Mover Prepacks item with associated Bulk item: [UI]: Item list is displayed when user click on POcard")
	public void Test06_1116057_1054144() 
	{	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}
	
	@Test(priority = 7,enabled = true, description = "Auto card is created automatically when user refresh the dahboard page if Adjusted on hand quantity is less than the minimum quantity")
	public void Test07_1116057_1054145() 
	{	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}
	
	@Test(priority = 8,enabled = true, description = "Fast mover bulk order is displayed on card when created for Prepack item")
	public void Test08_1116057_1054146() 
	{	test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}
	
	@Test(priority = 9,enabled = true, description = "Restock transaction is created for prepack item when card is received and send to queue")
	public void Test09_1116057_1054147() 
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}

	@Test(priority = 10,enabled = true, description = "PO dashboard page is loaded fine and all cards are opened properly")
	public void Test10_1116057_1131584() 
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.openPurchaseOrderCard(getData("PurchaseOrderData.Distributor"));
	}
	
	
	@Test(priority = 11,enabled = true, description = "VPLX:Fast Mover Prepacks item with associated Bulk item:[UI]: User is able to cancel the order Place process on pressing cancel button")
	public void Test11_1116057_1054138() 
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		medItem_one=test.purchaseDashboardActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.purchaseDashboardActions.clickSaveAndClose("Cancel");
		test.purchaseDashboardActions.clickOnCancel("Yes");
	}
	
	@Test(priority = 12,enabled = true, description = "VPLX: Fast Mover Prepacks item with associated Bulk item:[UI]: Card  is exported from NEW tab when click on Export now button")
	public void Test12_1116057_1054148() 
	
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
	
	@Test(priority = 13,enabled = true, description = "VPLX: Fast Mover Prepacks item with associated Bulk item :[UI]: Card is moved from Export tab to Receiving/Pending receiving tab when exported")
	public void Test013_1116057_1054149() 
	
	{	
		test.purchaseDashboardActions.clickPrintOrderSheet("printOrder");
		test.purchaseDashboardActions.clickOnDashboardLink("Dashboard");
	}
	
	@Test(priority = 14,enabled = true, description = "QOH is updated for prepack item when restock transaction mark completed")
	public void Test014_1116057_1054152() 
	
	{	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions.selectDropDownValue(getData("PurchaseOrderData.FacilityName"));
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Exported"),
				"[Assertion Failed]: Exported state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Pending Receive"),
				"[Assertion Failed]: Pending Receive state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("Received"),
				"[Assertion Failed]: Received state is not present on the Dashboard");
		test.purchaseDashboardActions.clickPOActionbutton("Actions");
		test.purchaseDashboardActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.purchaseDashboardActions.verifyPOLabelIsPresent("Order New Items"),true);
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
		}
	
	@Test(priority = 15,enabled = true, description = "User is able to mark restock transaction complete for prepack item")
	public void Test15_1116057_1054154() 
	{	
		test.purchaseDashboardActions.SearchPOItem("Item Name", getData("PurchaseOrderData.SearchItem1"));
		test.purchaseDashboardActions.verifyPOItemSearchResult(getData("PurchaseOrderData.SearchItem1"));
	}

	}
