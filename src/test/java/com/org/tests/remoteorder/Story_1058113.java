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

public class Story_1058113 extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode,Distributor;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	String OrderColumHeaders [] = {"Item Description","Item ID","Qty Requested","Status","Qty Received","Discrepancy","Verify","Verified by","Date"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity,DestinationName_MultipleItem;

	  @Override
	  @BeforeClass
	  public void Open_Browser_Window() { 
	  test = new  TestSessionInitiator(this.getClass().getSimpleName()); 
	  String app_url =  getYamlValue("weborder_app_url"); 
	  test.launchApplication(app_url);
	  test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
	  FacilityName = getData("RemoteWebOrder.FacilityName");
	  DestinationName = getData("RemoteWebOrder.DestinationName");
	  itemName = getData("RemoteWebOrder.itemName");
	  ItemCode = getData("RemoteWebOrder.ItemCode");
	  Distributor = getData("RemoteWebOrder.DistributorName");
	  itemQuantity = getData("RemoteWebOrder.itemQuantity");
	  }
	

	@Test(priority = 1, description = "VPLX: Remote ordering:[UI] - OrderDetail Page is displayed following columns Item Description, Item ID, Qty Requested, Qty Processed, Status, Qty Received, Discrepancy, Verify, Verify By and Date)")
	public void Test01_1105960(Method method)  {
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		String orderDate = test.remoteWebOrderActions.getItemDetails(OrderName, "2");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		Assert.assertTrue(test.remoteWebOrderActions.verifyColumnHeader(OrderColumHeaders, 10));
		test.remoteWebOrderActions.verifyOrderDetails("Item Description",itemName);
		test.remoteWebOrderActions.verifyOrderDetails(ItemCode,itemName,"1");
		test.remoteWebOrderActions.verifyOrderDetails(itemQuantity,itemName,"2");
		test.remoteWebOrderActions.verifyOrderDetails(itemQuantity,itemName,"3");
		test.remoteWebOrderActions.verifyOrderDetails(getData("RemoteWebOrder.VerifiedState"),itemName,"4");
		test.remoteWebOrderActions.verifyOrderDetails(String.valueOf(Integer.parseInt(itemQuantity)-1),itemName,"5");
		test.remoteWebOrderActions.verifyOrderDetails(String.valueOf(Integer.parseInt(itemQuantity)-(Integer.parseInt(itemQuantity)-1)),itemName,"6");
		test.remoteWebOrderActions.verifyOrderDetails(getData("RemoteWebOrder.UserName"),itemName,"8");
		test.remoteWebOrderActions.verifyOrderDetails(orderDate,itemName,"9");
		test.remoteWebOrderActions.verifyOrderDetails("orderCheck_0",itemName,"7");	
		Assert.assertTrue(test.remoteWebOrderActions.checkboxIsSelectedUsingJavascript("orderCheck_0"));
	
	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI] - User confirm to cancel then, success popup(toast) message is display on orderdetail page.")
	public void Test02_1105963(Method method)  {
		test.remoteWebOrderActions.navigateToTab("Create New Order");
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
		test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);		
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(Distributor);
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(itemName);

		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
				getData("RemoteWebOrder.SentToDestinationState"));
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName, getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		test.remoteWebOrderActions.verifySuccessMessage("Are you sure you want to discard your changes?");
	
	}
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI] - Qty received and Verify field is enable and after that  saved, If order status is 'Sent To Destination' and Partially Verified on OrderDetailPage")
	public void Test03_1105972(Method method)  {
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
	
	}
	
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI] - Qty received and Verify field is disable, If order status is other than 'Sent To Destination' on OrderDetailPage")
	public void Test04_1105994(Method method)  {

		test.remoteWebOrderActions.navigateToTab("Create New Order");
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
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		Assert.assertFalse(test.remoteWebOrderActions.checkCheckboxIsEnabledOrDisabled("orderCheck_0"));
		test.remoteWebOrderActions.verifyButtonIsDisabled("save");
		test.remoteWebOrderActions.verifyButtonIsDisabled("cancel");
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("ItemCode"));
		
	}
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI] -  When user clicks on Cancel button, a confirmation message should be displayed with options - Yes and No")
	public void Test05_1105996(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
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
		test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);		
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(Distributor);
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(itemName);

		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);

		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
				getData("RemoteWebOrder.SentToDestinationState"));
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName, getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		test.remoteWebOrderActions.verifySuccessMessage("Are you sure you want to discard your changes?");
		test.remoteWebOrderActions.verifyButtonIsEnabled("primary");
		test.remoteWebOrderActions.verifyButtonIsEnabled("secondary");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.verifyPage("Select a Destination");
		
	
	}
	
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI] - User confirm to Save then, success message is display on order detail page.")
	public void Test06_1129701(Method method)  {
		test.remoteWebOrderActions.navigateToTab("Create New Order");
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
		test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);		
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(Distributor);
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.selectItemtoRecieve(itemName);

		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);

		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
				getData("RemoteWebOrder.SentToDestinationState"));
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName, getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
				getData("RemoteWebOrder.VerifiedState"));

		test.remoteWebOrderActions.verifySuccessMessage("Changes were saved successfully");
	
	}
	
	
	@Test(priority = 0, description = "VPLX: Remote ordering:[UI]: On refresh page or click on cancel button on orders detail screen displayed under \"View All Order\" section.")
	public void Test0_1135062(Method method)  {
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
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,OrderName);	
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		test.remoteWebOrderActions.verifyPage("Select a Destination");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));

	
	}
	
	
	
}
