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
import com.org.extentmanagers.ExtentTestManager;

public class Story_1057881 extends BaseTest{
	String orderName;
	String  facilityName, destinationName, itemName, itemCode;
	String columnHeaders [] = {"Order name", "Order Id", "Order date", "Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data, order_ids, unique_order_ids;
	String OrderName, itemQuantity;
	
	
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		  
		  facilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  destinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  itemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  //itemQuantity = getData("RemoteWebOrder.itemQuantity");	  
	}
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-To Verify that Clear button is disabled "
			+ "when their is no item available in Item detail list(Right panel)")
	public void Test01_1071160(Method method)  {
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", 
				facilityName + " - " + destinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		/*
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(
				"orderNameInput", getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		System.out.println("itemname ===> "+ itemName);
		test.supportDataActions.enterSearchTermInSearchBox(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(itemCode, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", facilityName + " - " + destinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		*/
		Assert.assertFalse(test.remoteWebOrderActions.isButtonEnabled("buildOrderDeSelectButton"), 
				"[ASSERTION FAILED]: 'buildOrderDeSelectButton' is enabled even when no items"
				+ "are selected ");
	}
	
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-To verify that A toast pop-up message "
			+ "is displayed, when user click on Add all button on create order page.")
	public void Test02_1071572(Method method) {
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		
		Assert.assertEquals(test.remoteWebOrderActions.getPopupMessage(),
				"Are you sure you want to add items to the Order List?", 
				"[ASSERTION FAILED]: Message not verified on popup ");
		
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]-To verify that A toast pop-up message "
			+ "is displayed, when user click on Clear button on create order page")
	public void Test03_1071592(Method method) {
		test.remoteWebOrderActions.clickButton("buildOrderDeSelectButton");
		
		Assert.assertEquals(test.remoteWebOrderActions.getPopupMessage(),
				"Are you sure you want to remove all items from Order List?", 
				"[ASSERTION FAILED]: Message not verified on popup ");
		
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]-To verify User can reset the search "
			+ "by click on cross button(x) on create new order page")
	public void Test04_1073056(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify User can reset the search " + 
				"by click on cross button(x) on create new order page");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.supportDataActions.resetSearch();
		Assert.assertTrue(test.supportDataActions.getValueOfInputField("textValue").isEmpty(), 
				"[ASSERTION FAILED]: Search box didn't reset after clicking cross button");
		
		/*
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", facilityName + " - " + destinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(itemCode, itemName);
		test.remoteWebOrderActions.clickCossButton();
		
		Assert.assertTrue(test.remoteWebOrderActions.verifyRightPanelIsEmpty());
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		*/
	}
	
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]- To verify that all items added "
			+ "in the order list(right panel), when user click on Add All button")
	public void Test05_1071124(Method method) {
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.clickButton("primary");
		
		Assert.assertEquals(test.remoteWebOrderActions.verifyOrderList("Order"), 
				test.remoteWebOrderActions.captureDataForParticularColumn("2"),
				"[ASSERTION FAILED]: All items are not in order list after clicking Add All button");
	}
	
	
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI]- To verify that all items is removed "
			+ "from the order list(right panel), When user click on Clear button on create new order page")
	public void Test06_1071145(Method method) {
		test.remoteWebOrderActions.clickButton("buildOrderDeSelectButton");
		test.remoteWebOrderActions.clickButton("primary");
		
		Assert.assertTrue(test.remoteWebOrderActions.verifyRightPanelIsEmpty(), 
				"[ASSERTION FAILED]: Order list panel is not empty");
	}
	
}
