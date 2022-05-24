package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1057897 extends BaseTest{
	String  FacilityName, DestinationName, itemName, ItemCode;
	String columnHeaders [] = {"Items","Item Id","Package Size","Daily Max","Ordered Today"};
	ArrayList<String> actualData, sortedData, order_ids, unique_order_ids;
	String OrderName, itemQuantity;
	
	
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
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-To verify Destination name "
			+ "on create new order page.")
	public void Test01_1081578(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-Destination name on create new order page.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		Assert.assertTrue(test.remoteWebOrderActions.verifyDestination(DestinationName), 
				"[ASSERTION FAILED]: Destination name " + DestinationName + " is not displayed on "
						+ "Create New Order page");
	}
	
	// TODO - Yugal - Add more items and verify they are not highlighted
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-To verify Only one item is highlighted in blue "
			+ "which is selected to add in order list.")
	public void Test02_1080527(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify Only one item is highlighted in blue "
				+ "which is selected to add in order list.");
		
		// verifying background color of row when it's highlighted, that is, after clicking on it
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.verifyColorOfOrder(itemName, getData("RemoteWebOrder.RowColor"), "item");
		
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	// TODO - Yugal - Add more items and verify they are not highlighted
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]-To verify that When user search an item "
			+ "from any alphabet and click on item to add in order list , then , "
			+ "only that particular item highlighted in blue.")
	public void Test03_1080275(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that When user search an item from any alphabet "
				+ "and click on item to add in order list , then , only that particular item highlighted in blue.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		
		test.remoteWebOrderActions.verifyColorOfOrder(itemName, getData("RemoteWebOrder.RowColor"), "item");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]-To verify Validation message is display "
			+ "if user enter any negative value(<0) in quantity to order check box")
	public void Test04_1080086(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify Validation message is display if user enter "
				+ "any negative value(<0) in quantity to order check box");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		
		test.remoteWebOrderActions.enterItemQuantityOnROCard("-1");
		Assert.assertTrue(test.remoteWebOrderActions.verifyErrorMessage("Invalid quantity value"), 
				"[ASSERTION FAILED]: Error message is not displayed");
		test.remoteWebOrderActions.enterItemQuantityOnROCard("-10");
		Assert.assertTrue(test.remoteWebOrderActions.verifyErrorMessage("Invalid quantity value"), 
				"[ASSERTION FAILED]: Error message is not displayed");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");	
	}
	
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]-To verify that By default quantity "
			+ "to order text box is not blank , it is initialized with 0 value.")
	public void Test05_1078857(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that By default quantity to order text box is not blank , it is initialized with 0 value.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		
		Assert.assertEquals(test.remoteWebOrderActions.verifyDefaultItemQuantityOnROCard(ItemCode), "0", 
				"[ASSERTION FAILED]: Default quantity for an item is not zero");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI]: To verify Ordered Today column count "
			+ "is increased in items after creating new remote order")
	public void Test06_1077769(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]: To verify Ordered Today column count is increased in "
				+ "items after creating new remote order");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		
		int orderCount = test.remoteWebOrderActions.getOrderCount(itemName);
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard("10");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		int orderCountNew = test.remoteWebOrderActions.getOrderCount(itemName);
		
		Assert.assertEquals(orderCountNew, (orderCount + 10), 
				"[ASSERTION FAILED]: Order count didn't increase after creating new order");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
	}
	
	
	@Test(priority = 7, description = "VPLX: Remote ordering:[UI]-To verify User can move all items "
			+ "to order list by click on \"ADD All\" button.")
	public void Test07_1077286(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User can move all items to order list by click on \"ADD All\" button.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		
		int countItems = (test.remoteWebOrderActions.verifyOrderList("item")).size();
		int countOrderItems = (test.remoteWebOrderActions.verifyOrderList("order")).size();
		Assert.assertEquals(countOrderItems, countItems, 
				"[ASSERTION FAILED]: Count of items mapped to destination and count of items in order list"
				+ " after clicking 'Add All' are not same");
		
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 8, description = "VPLX: Remote ordering:[UI]-To Verify that User not able to insert "
			+ "any character in quantity check box at order list.") 
	public void Test08_1077229(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To Verify that User not able to insert any character in quantity check box at order list.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		
		test.remoteWebOrderActions.enterItemQuantityOnROCard("a");
		Assert.assertTrue(test.remoteWebOrderActions.verifyErrorMessage("Invalid quantity value"), 
				"[ASSERTION FAILED]: Error message 'Invalid quantity value' not displayed on entering an alphabet "
				+ "character in item quantity in order list");
		
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 9, description = "VPLX: Remote ordering:[UI]- To verify that create new order page "
			+ "is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) "
			+ "and item display in ascending order")
	public void Test09_1076575(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- Create new order page is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) and item display in ascending order");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.remoteWebOrderActions.verifyColumnHeader(columnHeaders, 5);
		// getColumnFirstData
		actualData = test.remoteWebOrderActions.captureColumnData(2);
		
		System.out.println("Number of items: " + actualData.size());
		sortedData = new ArrayList<String>();
		for(String s : actualData) {
			sortedData.add(s);
		}
		Collections.sort(sortedData);
		
		Assert.assertEquals(actualData, sortedData, 
				"[ASSERTION FAILED]: Item list is not sorted in ascending order by item name");
	}
	
	
	@Test(priority = 10, description = "VPLX: Remote ordering:[UI]:To verify 'Quantity to order' text box "
			+ "in order list is editable.(right panel) on create new order page")
	public void Test10_1076757(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]:To verify 'Quantity to order' text box "
				+ "in order list is editable.(right panel) on create new order page");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,  itemName);
		
		Assert.assertEquals(test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity")),
				getData("RemoteWebOrder.OrderQuantity"), 
				"[ASSERTION FAILED]: Quantity value is not same as value entered");
		Assert.assertEquals(test.remoteWebOrderActions.enterItemQuantityOnROCard("30"), "30",
		
				"[ASSERTION FAILED]: Quantity value is not same as value entered");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");	
	}
	
	// TODO - wrong test case definition
	@Test(priority = 11, description = "VPLX: Remote ordering:[UI]- Create new order page is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) and item display in ascending order")
	public void Test11_(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- Create new order page is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) and item display in ascending order");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.VerifyTabIsWorking(ItemCode);
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 12, description = "VPLX: Remote ordering:[UI]- To verify Value of total count "
			+ "when user enter any character in search text box.")
	public void Test12_1080606(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- To verify Value of total count when user enter any character in search text box.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		int count = test.remoteWebOrderActions.getItemCount();
		test.supportDataActions.enterSearchTermInSearchField("$", "textValue");
		int new_count = test.remoteWebOrderActions.getItemCount();
		Assert.assertNotEquals(count, new_count, 
				"[ASSERTION FAILED]: Item count doesn't decrease on entering a random character in search term");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");		
	}
	
}

