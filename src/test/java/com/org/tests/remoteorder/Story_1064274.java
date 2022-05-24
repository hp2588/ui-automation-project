package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1064274 extends BaseTest{
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
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-Item list Scroll is moved to that particular item in which user enter quantity in order list panel")
	public void Test01_1079438(Method method)  {
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		itemName = test.remoteWebOrderActions.getColumnFirstData("1");
		
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.verifyColorOfOrder(itemName,getData("RemoteWebOrder.RowColor"),"item","Pending");
		

	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-User is not able to enter value more than package size based on Daily max and order today in quantity to order")
	public void Test02_1079494(Method method)  {
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.clickButton("primary");
		itemName = test.remoteWebOrderActions.getColumnFirstData("1");
		int package_size = Integer.parseInt(test.remoteWebOrderActions.getColumnFirstData("4"));
		int daily_limit =  Integer.parseInt(test.remoteWebOrderActions.getColumnFirstData("5"));
		test.remoteWebOrderActions.enterItemQuantityOnROCard(String.valueOf(package_size+1));
		test.remoteWebOrderActions.verifyErrorMessage("Quantity must be multiple of package size");
		test.remoteWebOrderActions.enterItemQuantityOnROCard(String.valueOf(daily_limit+1));
		test.remoteWebOrderActions.verifyErrorMessage("Quantity over maximum allowed");

		
	}
	

}
