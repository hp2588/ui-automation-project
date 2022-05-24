package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;

public class Story_1058113_MultiItem extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode,Distributor;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	String OrderColumHeaders [] = {"Item Description","Item ID","Qty Requested","Status","Qty Received","Discrepancy","Verify","Verified by","Date"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity,DestinationName_MultipleItem, ItemCode1, ItemCode2;

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
	  ItemCode1 = getData("RemoteWebOrder.ItemCode1");
	  ItemCode2 = getData("RemoteWebOrder.ItemCode2");
	  }
	
	
	@Test(priority = 0, description = "VPLX: Remote ordering:[UI]: On refresh page or click on cancel button on orders detail screen displayed under \"View All Order\" section.")
	public void Test0_1135226(Method method)  {
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_MultipleItem);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.enterItemQuantityOnROCard(ItemCode1,getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.enterItemQuantityOnROCard(ItemCode2,getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
	
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_MultipleItem);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,OrderName);	
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		test.remoteWebOrderActions.verifyPage("Select a Destination");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		super.Open_Browser_Window();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		test.purchaseDashboardActions.validateDistributorCardName(Distributor);
		test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
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
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.supportDataActions.selectItemtoRecieve(itemName);
		test.siteConfigurationAction.clickButton("Received");
		Open_Browser_Window();
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
				getData("RemoteWebOrder.SentToDestinationState"));
	
	}

}
