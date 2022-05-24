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
import com.org.extentmanagers.ExtentTestManager;

public class Story_1066876 extends BaseTest{
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
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:{UI]-A toast pop-up message is displayed, when user click on cancel button  on create order page")
	public void Test01_1077918(Method method) {
		
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", "Select Facility - Destination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to cancel your Order?");
		test.remoteWebOrderActions.clickButton("primary");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-Validation message is display if order name field is blank and user click on submit order")
	public void Test02_1079447(Method method)  {
		
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				"Dummy");
		test.remoteWebOrderActions.clearInputField("orderNameInput");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.verifyErrorMessage("Invalid order name");
		
	} 
	
	
}
