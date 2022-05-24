package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Collections;
import java.util.regex.Pattern; 

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

import java.time.LocalDate;


public class Story_1057906 extends BaseTest{

	String  FacilityName, DestinationName, itemName, ItemCode;
	String columnHeaders [] = {"Items", "Item Id", "Package Size", "Daily Max", "Ordered Today"};
	ArrayList<String> actual_data, sorted_data;
	String OrderName, itemQuantity;
	String noDataMessage = "No Data Available.";
	
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), 
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());
		
		FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		itemQuantity = getData("RemoteWebOrder.itemQuantity");  
	}
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-To verify that By default "
			+ "Select Facility - destination name is displaying correspond to destination dropdown.")
	public void Test01_1070943(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that By default Select Facility - destination name is display correspond to destination fielde");
		
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", 
				"Select Facility - Destination");			
	}
	
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]-To verify Build a new order button "
			+ "is enabled, when user select any destination from select a destination")
	public void Test02_1069359(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify Build a new order button is enabled, when user select any destination from select a destination");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		Assert.assertTrue(test.remoteWebOrderActions.verifyButtonIsEnabled("buildNewOrder"), 
				"[ASSERTION FAILED]: Button buildNewOrder is  not enabled");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: 'Build a new order' button "
				+ "is enabled on selecting an option");
	}
	
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]- To verify that user redirected to "
			+ "\"create new order page\" when clicked on Build new order button.")
	public void Test03_1069360(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- To verify that user redirected to "
				+ "\"create new order page\" when clicked on Build new order button.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		Assert.assertTrue(test.remoteWebOrderActions.verifyPage("Available Items"), 
				"[ASSERTION FAILED]: 'Available Items' heading is not displayed, that is, "
				+ " Create New Order screen is not opened");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: User is redirected to Create New "
				+ "Order page on clicking 'Build a new order' button");
	}
	

	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]-To verify Order name text box "
			+ "contain default name of the order on create new order page")
	public void Test04_1069369(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify Order name text box contain "
				+ "default name of the order on create new order page");
		
		// default order name format: <destination name> for <month> <day of month>, <year>
		String defaultOrderName = test.remoteWebOrderActions.getOrderName("orderNameInput");
		
		Assert.assertFalse(defaultOrderName.isEmpty(), 
				"[ASSERTION FAILED]: Default remote order name on Create New Order page is empty");
		
		String[] strArr = defaultOrderName.split(" ");
		Assert.assertEquals(strArr[0], DestinationName, 
				"[ASSERTION FAILED]: Defaule RO name on Create new order page doesn't match expected format"
				+ "\nExpected format: '<destination name> for <current month> <current day>, <current year>'"
				+ "\nActual: '" + defaultOrderName + "'");
		Assert.assertEquals(strArr[1], "for", 
				"[ASSERTION FAILED]: Defaule RO name on Create new order page doesn't match expected format"
				+ "\nExpected format: '<destination name> for <current month> <current day>, <current year>'"
				+ "\nActual: '" + defaultOrderName + "'");
		
		LocalDate currentDate = LocalDate.now();
		String currentDay = String.valueOf(currentDate.getDayOfMonth());
		String currentMonth = currentDate.getMonth().toString();
		String currentYear = String.valueOf(currentDate.getYear());
		
		Assert.assertTrue(currentMonth.toLowerCase().contains(strArr[2].toLowerCase()), 
				"[ASSERTION FAILED]: Defaule RO name on Create new order page doesn't match expected format"
				+ "\nExpected format: '<destination name> for <current month> <current day>, <current year>'"
				+ "\nActual: '" + defaultOrderName + "'");
		Assert.assertEquals(strArr[3].replace(",", ""), currentDay, 
				"[ASSERTION FAILED]: Defaule RO name on Create new order page doesn't match expected format"
				+ "\nExpected format: '<destination name> for <current month> <current day>, <current year>'"
				+ "\nActual: '" + defaultOrderName + "'");
		Assert.assertEquals(strArr[4], currentYear, 
				"[ASSERTION FAILED]: Defaule RO name on Create new order page doesn't match expected format"
				+ "\nExpected format: '<destination name> for <current month> <current day>, <current year>'"
				+ "\nActual: '" + defaultOrderName + "'");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Verified default remote web order name"
				+ " in order name field on Create New Order screen");
	}
	

	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]- To Verify that List of items associated "
			+ "with the destination is displayed after user select any destination on create new order page")
	public void Test05_1070942(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- To Verify that List of items associated with the "
				+ "destination is displayed after user select any destination on create new order page");
		
		Assert.assertTrue(test.remoteWebOrderActions.verifyItemListIsAvailable(), 
				"[ASSERTION FAILED]: Item list is not displayed on Create New Order page");
		Assert.assertTrue(test.remoteWebOrderActions.verifyItem(itemName, ItemCode), 
				"[ASSERTION FAILED]: Item "+ itemName +" is not displayed in item list on Create New Order page");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: List of items is displayed on "
				+ "Create New Order page on RWO");
		//test.remoteWebOrderActions.clickButton("primary");
	}
	
	
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI]- To verify that user can search "
			+ "the item by initial letter in search text box at create new order page")
	public void Test06_1071029(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- To verify that user can search the item "
				+ "by initial letter in search text box at create new order page");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		Assert.assertTrue(test.remoteWebOrderActions.verifyItem(itemName, ItemCode), 
				"[ASSERTION FAILED]: Item "+ itemName +" is not displayed in item list on Create New Order page");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Successfully "
				+ "searched item on Create New Order Page");
	}
	
	
	@Test(priority = 7, description = "VPLX: Remote ordering:[UI]-To verify that Item column "
			+ "is displaying value consisting of alphanumeric characters on create new order page.")
	public void Test07_1070962(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that Item column " + 
				"is displaying value consisting of alphanumeric characters on create new order page.");
		
		test.supportDataActions.resetSearch();
		Assert.assertTrue(test.remoteWebOrderActions.verifyItem(itemName), 
				"[ASSERTION FAILED]: Item name " + itemName + "not displayed");
		
		List<String> itemNameColumn = test.remoteWebOrderActions.captureColumnData(2);
		
		for(String itemNameData : itemNameColumn) {
			Assert.assertTrue(Pattern.matches("^[a-zA-Z0-9]+\\s+[a-zA-Z0-9]+$", itemNameData), 
					"[ASSERTION FAILED]: Item name column data - '" + itemNameData + "' "
							+ "contains characters other than alphanumeric and white space");
		}
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Item column contains "
				+ "alphanumeric and whitespace characters ONLY");
	}
	
	
	@Test(priority = 8, description = "VPLX: Remote ordering:[UI]-To verify that Item in "
			+ "create new order page is display in ascending order on Items column only.")
	public void Test08_1070969(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that Item in create new order page "
				+ "is display in ascending order on Items column only.");
		
		List<String> actualData = test.remoteWebOrderActions.captureColumnData(2);
		
		System.out.println("Number of items: " + actualData.size());
		List<String> sortedData = new ArrayList<String>();
		for(String s : actualData) {
			sortedData.add(s);
		}
		Collections.sort(sortedData);
		Assert.assertEquals(actualData, sortedData, 
				"[ASSERTION FAILED]: Item list is not sorted in ascending order by item name");	
		
		test.siteConfigurationAction.logMessageLocal("[ASSERION PASSED]: Item list on 'Create New Order' "
				+ "page is sorted by item name");
	}
	
	
	@Test(priority = 9, description = "VPLX: Remote ordering:[UI]-To verify that A validation message is "
			+ "display, if the Item is not found by it's name while searching.")
	public void Test09_1071038(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that A validation message is display, "
				+ "if the Item is not found by it's name while searching.");
		
		test.supportDataActions.enterSearchTermInSearchField("$%%%", "textValue");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), noDataMessage, 
				"[ASSERTION FAILED]: No data available message is not displayed on screen on searching "
				+ "an item that doesn't exist");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Verified message '" + noDataMessage 
				+ "', message is displayed when searching an item that doesn't exist on 'Create New Order' page ");
	}
	
	
	@Test(priority = 10, description = "VPLX: Remote ordering:[UI]-To verify that User can add Item "
			+ "in item detail list by click on Item in left panel.")
	public void Test10_1071049(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that User can add Item "
				+ "in item detail list by click on Item in left panel.");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		// verified that initially right panel is empty 
		Assert.assertTrue(test.remoteWebOrderActions.verifyRightPanelIsEmpty());
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		// now since we can enter quantity in the item quantity field,
		// so, it's verified that item is added in right panel
		Assert.assertEquals(test.remoteWebOrderActions.enterItemQuantityOnROCard(
				getData("RemoteWebOrder.OrderQuantity")), getData("RemoteWebOrder.OrderQuantity"));
		
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.clickButton("primary");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Verified that user can item in "
				+ "order list by clicking on item row in left panel");
	}
	
	
	@Test(priority = 11, description = "VPLX: Remote ordering:[UI]-To verify that Submit order button "
			+ "is disabled until one item is added in Item detail list")
	public void Test11_1071054(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that Submit order button is disabled "
				+ "until one item is added in Item detail list");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyButtonIsDisabled("buildOrderSubmitButton"), 
				"[ASSERTION FAILED]: 'Submit Order' is not disabled when order list is empty");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: 'Submit Order' button is disabled"
				+ " when there is no item in order list");
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		//test.remoteWebOrderActions.clickButton("primary");	
	}
	
	
	@Test(priority = 12, description = "VPLX: Remote ordering:[UI]-To verify that Validation message "
			+ "is not display,if user click on cancel button without selecting any item from Item list.")
	public void Test12_1071057(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that Validation message is not display, if user "
				+ "click on cancel button without selecting any item from Item list.");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.siteConfigurationAction.clickButton("buildOrderCancelButton");
		
		Assert.assertTrue(test.remoteWebOrderActions.verifyValidationMessageIsNotDisplayed(
				"Are you sure you want to cancel your Order?"), 
				"[ASSERTION FAILED]: Validation popup in displayed with message "
				+ "'Are you sure you want to cancel your Order?' even when there is not any item in order list");
		
		// making sure that user is not on create new order page, but on the destination dropdown
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Verified that validation popup is "
				+ "not displayed on clicking Cancel button on 'Create New Order' page when there is "
				+ "no item in order list" );
	}
	
	
	@Test(priority = 13, description = "VPLX: Remote ordering: [UI]-To Verify that Unique order Id "
			+ "is generated after successfully submitting the order.")
	public void Test13_1071093(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering: [UI]-To Verify that Unique order Id is generated "
				+ "after successfully submitting the order.");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		
		// generate 5  orders
		for(int i = 0; i < 5; i++) {
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination", 
					FacilityName + " - " + DestinationName);
			test.remoteWebOrderActions.clickButton("buildNewOrder");
			
			test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(
					"orderNameInput", getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
			test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
			test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
			test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
			test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		}
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", 
				FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		
		List<String> orderIDs = test.remoteWebOrderActions.captureDataForParticularColumnOrderPage("2");
		Set<String> uniqueOrderIDs = new HashSet<String>(orderIDs);
		Assert.assertEquals(orderIDs.size(), uniqueOrderIDs.size(), 
				"[ASSERTION FAILED]: Order IDs generated are not unique"
				+ "\nList of order IDs: "+ orderIDs);
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: All order IDs are unique");
	}
	
	
	@Test(priority = 14, description = "VPLX: Remote ordering:[UI]-To verify User is able to remove the "
			+ "selected item from enter quantities panel by click on cross button using mouse pointer.")
	public void Test14_1072981(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify User is able to remove the selected item "
				+ "from enter quantities panel by click on cross button using mouse pointer.");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		
		// click cross button
		test.remoteWebOrderActions.clickCossButton();
		Assert.assertFalse(test.remoteWebOrderActions.verifyOrderListIsEmpty(), 
				"[ASSERTION FAILED]: Order list is not empty");
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: User is able to remove the selected "
				+ "item from the Order List by clicking on the cross button");
	}
	
	
	@Test(priority = 15, description = "VPLX: Remote ordering:[UI]-To verify that Items are highlighted "
			+ "in grey color when user hover mouse on it.")
	public void Test15_1073409(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-To verify that Items are highlighted "
				+ "in grey color when user hover mouse on it.");
		
		Assert.assertEquals(
				test.remoteWebOrderActions.hoverAndGetBgColorOfRow(itemName, "item"), "rgba(204, 204, 204, 1)",
				"[ASSERTION FAILED]: Background color of row doesn't match");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Item row is highlighted in "
				+ "grey color when user hovers over the row");
	}
	
	
	@Test(priority = 16, description = "VPLX: Remote ordering:[UI]: To verify that All item "
			+ "are displayed when user remove any searched item from search text box.")
	public void Test16_1076508(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]: To verify that All item are displayed "
				+ "when user remove any searched item from search text box.");
		
		test.supportDataActions.enterSearchTermInSearchField("%$", "textValue");
		Assert.assertEquals(test.supportDataActions.getNoDataText(), noDataMessage, 
				"[ASSERTION FAILED]: No data available message is not displayed on screen on searching "
				+ "an item that doesn't exist");
		
		test.supportDataActions.resetSearch();
		Assert.assertTrue(test.remoteWebOrderActions.verifyItemListIsAvailable(), 
				"[ASSERTION FAILED]: Item list is not visible even after resetting search box");
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Item list is displayed on "
				+ "resetting the search box");
	}
	
	// TODO - Yugal - Only Item with initial I is available, add multiple items with different initials and check
	@Test(priority = 17, description = "VPLX: Remote ordering:[UI]-To Verify that User can jump to the "
			+ "item list by click on Alphabet (A to Z) given in the footer of order page item list")
	public void Test17_1071016(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User can jump to the item list by click on Alphabet (A to Z) given in the footer of order page item list");
		
		test.remoteWebOrderActions.clickPageFooter(getData("RemoteWebOrder.alphabet"));
		Assert.assertTrue(test.remoteWebOrderActions.verifyItem(itemName, ItemCode));
		
		test.siteConfigurationAction.logMessageLocal("[ASSERTION PASSED]: Verified that user navigate in "
				+ "the item list by clicking the Alphabet (A to Z) in the footer");
	}
	
	
	@Test(priority = 18, description = "VPLX: Remote ordering:[UI]- To verify that if user confirm for "
			+ "cancel item order, confirmation popup(toast) message is display on create new order page.")
	public void Test18_1071078(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- To verify that if user confirm for cancel item order, "
				+ "confirmation popup(toast) message is display on create new order page.");
		
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);	
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to cancel your Order?");
		test.remoteWebOrderActions.clickButton("primary");
		
		
		
	}
	
	
	@Test(priority = 19, description = "VPLX: Remote orderingr:[UI]-User is able to update the default order name on create new order page")
	public void Test19_1069386(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote orderingr:[UI]-User is able to update the default order name on create new order page");
		test.supportDataActions.refreshPage();
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		
	}
	
	
	@Test(priority = 20, description = "VPLX: Remote ordering:[UI]-Selected destination are persist on CreateNewOrder page and ViewAllorder page,")
	public void Test20_1143497(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-Selected destination are persist on CreateNewOrder page and ViewAllorder page,");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.supportDataActions.refreshPage();
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", 
				FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", 
				FacilityName + " - " + DestinationName);	
	}
	
}
