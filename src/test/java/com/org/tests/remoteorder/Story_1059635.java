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

public class Story_1059635 extends BaseTest{
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
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]- A drop down list for "
			+ "'Select A Destination' must be coming on View all Order page")
	public void Test01_1084359(Method method)  {
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination","Select Facility - Destination");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyDropDownFieldOnViewAllOrderPage("selectDestination");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", "Select Facility - Destination");
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]- On selecting any row "
			+ "from the view all order page , the row gets highlighted in blue")
	public void Test02_1084389(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		String orderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderName, "Pending");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, orderName);
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		
		test.remoteWebOrderActions.verifyColorOfRow(orderName, "rgba(234, 239, 247, 1)", "order", "Pending");
		
	}
	
	
	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]- Search working on following columns "
			+ "Item description , Date , Order ID , Ordered By , Ordered From , Status on View all Orders Page")
	public void Test03_1084391(Method method)  {
		
		String orderName = test.remoteWebOrderActions.getColumnFirstData("1");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderName, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderName);
		
		String orderId = test.remoteWebOrderActions.getColumnFirstData("2");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderId, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderId);
		
		String orderDate = test.remoteWebOrderActions.getColumnFirstData("3");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderDate, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderDate);	
		
		String orderedBy=test.remoteWebOrderActions.getColumnFirstData("5");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderedBy, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderedBy);
		
		String orderedFrom = test.remoteWebOrderActions.getColumnFirstData("6");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderedFrom, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderedFrom);
		
		String orderStatus = test.remoteWebOrderActions.getColumnFirstData("7");
		test.remoteWebOrderActions.enterSearchTermInSearchField(orderStatus, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(orderStatus);
		
		test.supportDataActions.resetSearch();
		
	}
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]-A vertical scroll bar is available for user to browse through all remote orders on ViewAllOrder page")
	public void Test04_1084422(Method method)  {
		if(test.remoteWebOrderActions.verifyOrderList("order").size()>=15) {
			test.remoteWebOrderActions.verifyVerticalScroll();
		}
		else {
			int orders=test.remoteWebOrderActions.verifyOrderList("order").size();
			test.remoteWebOrderActions.navigateToTab("Create New Order");
			for(int i = orders; i <= 15; i++) {
				test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
				test.remoteWebOrderActions.clickButton("buildNewOrder");
				String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
				test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
				test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
				test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
				test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
			}
			
			test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
			test.remoteWebOrderActions.navigateToTab("View All Orders");
			test.remoteWebOrderActions.verifyVerticalScroll();
		}
		
	}
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]- A message must be display if user doesn't select the destination on VIewAllOrder page")
	public void Test05_1084445(Method method)  {
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.supportDataActions.refreshPage();
		test.remoteWebOrderActions.selectDropdownForRO_New("selectDestination", "Select Facility - Destination");
		test.remoteWebOrderActions.selectDropdownForRO_New("selectDestination", "Select Facility - Destination");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyMessageWhenNoDestinationIsSelected();
	}
	
	
	// TODO - Yugal - Refactoring
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI]-All columns are sorted except 'Items' on ViewAllOrder page")
	public void Test06_1084417(Method method)  {
		
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
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
		
	}
	
	
	/*
	 * @Test(priority = 7, description =
	 * "VPLX: VPLX: Remote ordering:[UI]-ViewAllOrder page is displayed following columns Order Name,Order ID,Order Date,Items,Ordered By,Ordered from,Status"
	 * ) public void Test07_1084414(Method method) {
	 * 
	 * test.remoteWebOrderActions.verifyColumnHeader(columnHeaders, 7);
	 * ArrayList<String> order_ids=
	 * test.remoteWebOrderActions.captureDataForParticularColumn("Order ID");
	 * test.remoteWebOrderActions.verifyOrderIdContainsNumericData(order_ids);
	 * ArrayList<String> order_dates=
	 * test.remoteWebOrderActions.captureDataForParticularColumn("Order date");
	 * test.remoteWebOrderActions.verifyDateFormat(order_dates); ArrayList<String>
	 * number_of_items=
	 * test.remoteWebOrderActions.captureDataForParticularColumn("Items");
	 * test.remoteWebOrderActions.verifyOrderIdContainsNumericData(number_of_items);
	 * 
	 * 
	 * 
	 * test.remoteWebOrderActions.navigateToTab("Create New Order");
	 * Assert.assertEquals(test.siteConfigurationAction.
	 * verifyMaxLengthOfAnInputField("orderNameInput"), 50,
	 * "[ASSERTION FAILED]: Max Length for input field facility Code is not 20");
	 * 
	 * }
	 */
	
}
