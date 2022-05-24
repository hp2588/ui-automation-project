package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RemoteWebOrderFeature extends BaseTest {
	
	String  FacilityName, DestinationName,itemName, ItemCode,Distributor,itemQuantity;
	String columnHeaders [] = {"Items","Item Id","Package Size","Daily Max","Ordered Today"};
	String OrderColumHeaders [] = {"Item Description","Item ID","Qty Requested","Status","Qty Received","Discrepancy","Verify","Verified by","Date"};
	ArrayList<String> actual_data, sorted_data;
	String OrderName;
	
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
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  Distributor = getData("RemoteWebOrder.DistributorName");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
	}
	 
	@Test(priority = 0, description = "VPLX: Remote Ordering: [UI]:The Destination dropdown is displayed "
			+ "value in dropdown \"<facility name>-<destination name>\" on home page of remote web application")
	public void Test0_1117914(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote Ordering: [UI]:The Destination dropdown is displayed value in dropdown \"<facility name>-<destination name>\" on home page of remote web application");

		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", "Select Facility - Destination");
	}
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]-To verify Build a new order button "
			+ "is enabled, when user select any destination from select a destination")
	public void Test01_1069359(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-Build a new order button is enabled, when user select any destination from select a destination");
		
		test.remoteWebOrderActions.selectDropdownForROWithIndex("selectDestination", 1);
		test.remoteWebOrderActions.verifyButtonIsEnabled("buildNewOrder");
	}
	
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]- To verify that user redirected to "
			+ "\"create new order page\" when clicked on Build new order button.")
	public void Test02_1069360(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User redirected to create new order page, when clicked on Build new order button.");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		Assert.assertFalse(test.remoteWebOrderActions.getOrderName("orderNameInput").isEmpty());
	}
	

	@Test(priority = 3, description = "VPLX: Remote ordering:[UI]- To verify that user can search the item "
			+ "by initial letter in search text box at create new order page")
	public void Test03_1071029(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User can search the item by initial letter in search text box at create new order page");
			
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
	}
	
	
	@Test(priority = 4, description = "VPLX: Remote ordering:[UI]- To verify that if user confirm "
			+ "for cancel item ordem, confirmation popup(toast) message is display on create new order page.")
	public void Test04_1071078(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User confirm to cancel then, success popup(toast) message is display on create new order page.");
			
		test.remoteWebOrderActions.clickButton("buildOrderCancelButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to cancel your Order?");
		test.remoteWebOrderActions.clickButton("primary");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.verifyButtonIsEnabled("buildNewOrder");
	}
	
	
	@Test(priority = 5, description = "VPLX: Remote ordering:[UI]- To verify that all items added "
			+ "in the order list(right panel), when user click on Add All button")
	public void Test05_1071124(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-All items added in the order list(right panel), when user click on Add All button");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.verifyButtonIsEnabled("buildNewOrder");
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		Assert.assertFalse(test.remoteWebOrderActions.verifyOrderList("order").isEmpty());
	}
	
	
	@Test(priority = 6, description = "VPLX: Remote ordering:[UI]- To verify that all items is removed "
			+ "from the order list(right panel), When user click on Clear button on create new order page")
	public void Test06_1071145(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- All items is removed from the order list(right panel), When user click on Clear button on create new order page");
		
		test.remoteWebOrderActions.clickButton("buildOrderdeDeSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to remove all items from Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		Assert.assertTrue(test.remoteWebOrderActions.verifyRightPanelIsEmpty());
	}
	
	
	@Test(priority = 7, description = "VPLX: Remote ordering:[UI]- To verify that create new order page "
			+ "is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) "
			+ "and item display in ascending order")
	public void Test07_1076575(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]- Create new order page is displayed following columns (Item,Item Id, Daily max, Package size,Ordered today) and item display in ascending order");
		
		test.remoteWebOrderActions.verifyColumnHeader(columnHeaders, 5);
		actual_data=test.remoteWebOrderActions.captureDataForParticularColumn();
		sorted_data=test.remoteWebOrderActions.sortDataForParticularColumnInAscendingOrder();
		Assert.assertEquals(actual_data,sorted_data,"ASSERT FAIL: Item list not sorted in ascending order");
	}
	
	
	@Test(priority = 8, description = "VPLX: Remote Ordering: [UI]:The Destination dropdown on "
			+ "top left corner is displayed the \"<facility name>-<destination name>\" on "
			+ "ViewAllOrder page of remote web application")
	public void Test08_1117918(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote Ordering: [UI]:The Destination dropdown on top left corner is displayed the \"<facility name>-<destination name>\" on ViewAllOrder page of remote web application");
	
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyDropDownFieldOnWebOrderPage("selectDestination");
		test.remoteWebOrderActions.verifyDefaultValueInRODropDown("selectDestination", FacilityName+" - "+DestinationName);
	}
	
	
	@Test(priority = 9, description = "VPLX: Remote orderingr:[UI]- To verify that "
			+ "user is able to update the default order name on create new order page")
	public void Test09_1069386(Method method) {
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
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
	}
	
	
	@Test(priority = 10, description = "VPLX: Remote ordering:[UI]-User can jump to the item list "
			+ "by click on Alphabet (A to Z) given in the footer of order page item list")
	public void Test10_1071016(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Remote ordering:[UI]-User can jump to the item list by click on Alphabet (A to Z) given in the footer of order page item list");
		
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.clickPageFooter(getData("RemoteWebOrder.alphabet"));
		Assert.assertTrue(test.remoteWebOrderActions.verifyItem(itemName, itemQuantity));	
	}
	
	
	@Test(priority = 11, description = "VPLX: Remote ordering:[Integration][UI]: Newly added Destination "
			+ "is displayed in dropdown, if User create a new destination in VPLX and "
			+ "mapped with Remote order User")
	public void Test11_1129667(Method method) throws Throwable  {
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(50);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",FacilityName);
		String destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		String destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+destinationName);
		
	}
	
	
	@Test(priority = 12, description = "VPLX: Remote ordering:[UI]- To verify that search working "
			+ "on following columns Order Name , Date , Order ID , Ordered By , Ordered From , Status "
			+ "on View all Orders Page")
	public void Test12_1084391(Method method)  {
		
		String OrderName=test.remoteWebOrderActions.getColumnFirstData("1");
		test.remoteWebOrderActions.enterSearchTermInSearchField(OrderName, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName);
		String Order_Id=test.remoteWebOrderActions.getColumnFirstData("2");
		test.remoteWebOrderActions.enterSearchTermInSearchField(Order_Id, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(Order_Id);
		String Order_date=test.remoteWebOrderActions.getColumnFirstData("3");
		test.remoteWebOrderActions.enterSearchTermInSearchField(Order_date, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(Order_date);
		String items=test.remoteWebOrderActions.getColumnFirstData("4");
		test.remoteWebOrderActions.enterSearchTermInSearchField(items, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(items);
		String Ordered_by=test.remoteWebOrderActions.getColumnFirstData("5");
		test.remoteWebOrderActions.enterSearchTermInSearchField(Ordered_by, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(Ordered_by);
		String Order_from=test.remoteWebOrderActions.getColumnFirstData("6");
		test.remoteWebOrderActions.enterSearchTermInSearchField(Order_from, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(Order_from);
		String status=test.remoteWebOrderActions.getColumnFirstData("7");
		test.remoteWebOrderActions.enterSearchTermInSearchField(status, "textValue");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(status);
	}
	
	
	@Test(priority = 13, description = "VPLX: Remote Ordering:[UI]- To verify that sorting icon "
			+ "must be display against all columns except items column on ViewAllOrder page "
			+ "and Default sorted on OrderId as Desc")
	public void Test13_1101574(Method method)  {	
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
		actual_data=test.remoteWebOrderActions.captureDataForParticularColumn("Order id");
		sorted_data= test.remoteWebOrderActions.sortDataForParticularColumnInDescendingOrder("Order id");
		Assert.assertEquals(actual_data, sorted_data);
	}
	
	
	//pending
	@Test(priority = 14, description = "VPLX: Remote ordering:[UI] - User confirm to Save then, success message is display on order detail page.")
	public void Test14_1129701(Method method)  {
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
	
	
	@Test(priority = 15, description = "VPLX: Remote ordering:[UI] - User confirm to cancel then, "
			+ "success popup(toast) message is display on orderdetail page")
	public void Test15_1105963(Method method)  {

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
	
	
	@Test(priority = 16, description = "VPLX: Remote ordering:[UI] - Qty received and Verify field is enable and after that  saved, If order status is 'Sent To Destination' and Partially Verified on OrderDetailPage")
	public void Test16_1105972(Method method)  {
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
	

	@Test(priority = 17, description = "VPLX: Remote ordering:[UI] - OrderDetail Page is displayed following columns Item Description, Item ID, Qty Requested, Qty Processed, Status, Qty Received, Discrepancy, Verify, Verify By and Date)")
	public void Test17_1105960(Method method)  {
			test.remoteWebOrderActions.navigateToTab("Create New Order");
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
	
}
