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

public class Story_1075825 extends BaseTest{
	String orderName;
	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;

	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		/*
		 * FacilityName = getData("RemoteWebOrder.FacilityName"); DestinationName =
		 * getData("RemoteWebOrder.DestinationName"); itemName =
		 * getData("RemoteWebOrder.itemName"); ItemCode =
		 * getData("RemoteWebOrder.ItemCode"); itemQuantity =
		 * getData("RemoteWebOrder.itemQuantity");
		 */
		  
		  
		  FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
	}
	

	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]- 'Status' column is displayed on ViewAllOrder page")
	public void Test01_1087264(Method method)  {
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput", 
				getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote Ordering:[UI]- Order must be displayed on ViewAllOrder tab after creating the order")
	public void Test02_1101569(Method method)  {
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput", 
				getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		
	}
	
	
	@Test(priority = 4, description = "VPLX: Remote Ordering:[UI]- Sorting icon must be display against all columns  except items column on ViewAllOrder page and Default sorted on OrderId as Desc")
	public void Test04_1101574(Method method)  {
		
		test.remoteWebOrderActions.verifyAndClickSortIcon("Order name");
		test.remoteWebOrderActions.verifySortIcon("Order name");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Order ID");
		test.remoteWebOrderActions.verifySortIcon("Order ID");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Order date");
		test.remoteWebOrderActions.verifySortIcon("Order date");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Ordered by");
		test.remoteWebOrderActions.verifySortIcon("Ordered by");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Ordered from");
		test.remoteWebOrderActions.verifySortIcon("Ordered from");;
		test.remoteWebOrderActions.verifyAndClickSortIcon("Status");
		test.remoteWebOrderActions.verifySortIcon("Status");
		
		test.remoteWebOrderActions.verifyAndClickSortIcon("Order ID");
		test.remoteWebOrderActions.verifyAndClickSortIcon("Order ID");
		actual_data=test.remoteWebOrderActions.captureDataForParticularColumnOrderPage("2");
		sorted_data= test.remoteWebOrderActions.sortDataForParticularColumnInDescendingOrderOnOrders("2");
		
		Assert.assertEquals(actual_data, sorted_data);
		
	}
}

