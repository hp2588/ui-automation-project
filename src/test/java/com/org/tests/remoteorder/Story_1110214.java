package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1110214 extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity,Distributor;

	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		 FacilityName = getData("RemoteWebOrder.FacilityName");
		  DestinationName = getData("RemoteWebOrder.DestinationName");
		  itemName = getData("RemoteWebOrder.itemName");
		  ItemCode = getData("RemoteWebOrder.ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  Distributor = getData("RemoteWebOrder.DistributorName");
	}
	

	@Test(priority = 1, description = "VPLX: Remote Ordering: [UI] - \"Add\" button for the purchase order is disabled If user has selected any Remote purchase order on Buyer Dashboard")
	public void Test01_1121818(Method method)  {
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		
		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		test.remoteWebOrderActions.verifyButtonIsDisabled("add");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote Ordering: [UI] - Buyers have the ability to change the quantity of the items in the Remote Orders")
	public void Test02_1121897(Method method)  {
		
		test.remoteWebOrderActions.clickCheckboxPO(itemName);
		test.remoteWebOrderActions.updateOrderQuantity(itemName,"11");
	}
	@Test(priority = 3, description = "VPLX: Remote Ordering: [UI] - Selected item is added in  existing PO as non-remote order, if user \"Create New Order\" function in Action menu from buyer dashboard.")
	public void Test03_1121895(Method method)  {
		
		test.supportDataActions.clickPOActionbutton("Actions");
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name", itemName);
		test.supportDataActions.verifyPOItemSearchResult(itemName);
		String medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
		test.remoteWebOrderActions.verifySelectedItemInPO(itemName);
		
	}

	@Test(priority = 4, description = "VPLX: Remote Ordering: [UI] - Buyers have the ability to remove items from POs for Remote Orders.")
	public void Test04_1121896(Method method)  {
		test.remoteWebOrderActions.clickCheckboxPO(itemName);
		test.remoteWebOrderActions.clickButton("ignore");
		test.remoteWebOrderActions.verifyErrorMessage("Are you sure that you want to remove item from the order?");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.verifySuccessMessage("Item successfully removed from the purchase order");
	}
	
	
	

}
