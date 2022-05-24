package com.org.tests.mainmenu.prepack;


import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;


@Listeners(com.org.listeners.TestListener.class)

public class Story_1116060 extends BaseTest{

	String medItem_one;
	String medItem_two;
	
	@Test(priority = 1,enabled = true, description = "Adjusted on hand Quantity is calculated correctly")
	public void Test01_1116060_1122616() 
	
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
		
	}	  
}
