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

public class Story_1064271 extends BaseTest {
	
	String orderName;

	String  FacilityName, DestinationName,itemName, ItemCode,Distributor, medItem, ISAName;
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
		  
		  FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  
	}
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-Validation message is display "
			+ "if user try to submit order in which order name field is empty.")
	public void Test01_1077780(Method method)  {
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.clearInputField("orderNameInput");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.verifyErrorMessage("Invalid order name");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
		
	}
	
	@Test(priority = 2, description = "VPLX:Remote ordering:[UI]-User can enter duplicate name in order name field.")
	public void Test02_1078710(Method method)  {
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",OrderName);
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.PendingState"));
		
	
	}
	
	
	@Test(priority = 3, description = "VPLX:Remote ordering:[UI]-User can enter duplicate name in order name field.")
	public void Test03_1117808_Story_1108132(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput", OrderName);
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		
		Assert.assertTrue(test.remoteWebOrderActions.getCountOfOrderName(OrderName) > 1);
		
	}
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]-Duplicate data is not added in order "
			+ "list when user click on Add All button")
	public void Test04_1077944(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput", 
				getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		Assert.assertEquals(test.remoteWebOrderActions.verifyOrderList("Order"),
				test.remoteWebOrderActions.captureDataForParticularColumn("2"));
		
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		Assert.assertEquals(test.remoteWebOrderActions.verifyOrderList("Order"),
				test.remoteWebOrderActions.captureDataForParticularColumn("2"));
		
	}
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]-Maximum length of order "
			+ "name field is 50 on create new order page.")
	public void Test05_1078920(Method method)  {
		
		Assert.assertEquals(test.remoteWebOrderActions.verifyMaxLengthOfAnInputField("orderNameInput"),50);
	
	}
}

