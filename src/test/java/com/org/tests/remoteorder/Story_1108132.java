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

public class Story_1108132 extends BaseTest {
	
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
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());


		  FacilityName = getData("RemoteWebOrder.FacilityName");
		  DestinationName = getData("RemoteWebOrder.DestinationName");
		  itemName = getData("RemoteWebOrder.itemName");
		  ItemCode = getData("RemoteWebOrder.ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  Distributor = getData("RemoteWebOrder.DistributorName");
		  ISAName = getData("RemoteWebOrder.DistributorName");

			
		  test = new TestSessionInitiator(this.getClass().getSimpleName());
		  test.landingPageActions.navigateToFeature("Destinations");
			test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
			test.siteConfigurationAction.clickEditLink(DestinationName);
			test.siteConfigurationAction.selectCheckboxCorresspondingToField("verifyRemoteOrderFlag", true);
			test.siteConfigurationAction.clickTab("Items");
			test.siteConfigurationAction.clickButton("add");
			test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
			test.siteConfigurationAction.clickCheckbox("activeFlag-0");
			test.siteConfigurationAction.clickActionbutton("Save & Close");
			test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
			test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
			test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxDailyQuantity"));
			test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
			
			
			/**********************Link user with Destination**************************/
			test.siteConfigurationAction.clickTab("Users");
			test.siteConfigurationAction.verifyAndClickInactiveToggle();
			test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
			test.siteConfigurationAction.clickButton("save");
			test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		  

	}
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]: (\"Destination - <destination name>\") name is displayed on PO card under Distributor card for remote order.")
	public void Test01_1110122(Method method)  {

		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());

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

	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]- The destination name under PO is displayed from remote order after submitting the order from RO.")
	public void Test02_1110123(Method method)  {
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		
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

	}
	
	@Test(priority = 5, description = "VPLX:Remote Ordering: [UI [Integration]: A validation message is display while selecting inactive Destination from destination dropdown on home page of RO.")
	public void Test03_1130592(Method method) throws Throwable  {
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName, "search");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(DestinationName);
		test.siteConfigurationAction.clickActiveToggle("Active");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
	
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.verifyPopupMessage("Please select an active destination");	

	}

	@Test(priority = 4, description = "VPLX:Remote Ordering:[Integration]: [UI]: Distributor code is not displayed in PO for Remote Order on Buyer dashboard in case Distributor Code check box is unchecked.")
	public void Test04_1117371(Method method) throws Throwable  {
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Distributors");
		test.supportDataActions.enterSearchTermInSearchField(Distributor, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(Distributor, "Edit Distributor");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.clickAddButtonOnDistributor("save");

	
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("RemoteWebOrder.OrderQuantity"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		
		Assert.assertFalse(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");	

	}
	

	@Test(priority = 3, description = "VPLX:Remote Ordering: [Integration]: [UI]: Distributor code is displayed in PO for Remote Order on Buyer dashboard in case Distributor Code check box is checked.")
	public void Test05_1117805(Method method) throws Throwable  {
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		
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
	
		

	}

	
	@Test(priority = 6, description = "VPLX:Remote Ordering: [UI]: When a Destination name is updated, it gets updated in Facility-Destinations dropdown, Pick and PO created for Remote Order application.")
	public void Test06_1130502(Method method) throws Throwable  {
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(DestinationName);
		String destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", DestinationName+"Updated");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		test.remoteWebOrderActions.verifyDestinationNameFromPOCard(destinationName);
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP("IPAddress");
		test.storageAreaAction.selectCheckboxForISA(ISAName, 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		ArrayList<String> activeTransactionName = new ArrayList<String>();
		activeTransactionName.add(itemName+ItemCode);
		activeTransactionName.add(destinationName);
		activeTransactionName.add("");
		if(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName))
		{
			Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		}
		else
		{	test.transactionQueueActions.makeROTransactionActive();
			test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName);
				
		}
			
	}
		
	@Test(priority = 7, description = "VPLX: Remote ordering:[Integration][UI]: Newly added Destination is displayed in dropdown, if User create a new destination in VPLX and mapped with Remote order User.")
	public void Test07_1129667(Method method) throws Throwable  {
		
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
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		
			
	}
	

	@Test(priority = 8, description = "VPLX: Remote Ordering: User is able to verify the order in RO, if Verify Remote order checkbox is checked in destination")
	public void Test08_1137907(Method method) throws Throwable  {
		
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

		
	}

	@Test(priority = 9, description = "VPLX: Remote Ordering: User is not able to verify the order in RO, if Verify Remote order checkbox is not checked in destination")
	public void Test09_1137903(Method method) throws Throwable  {
		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickEditLink(DestinationName);
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("verifyRemoteOrderFlag", true);
		test.siteConfigurationAction.clickButton("save");
		  test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		  String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(),
				getData("Auth.passwordWebOrderUser").trim(), getData("Auth.ip").trim());	
		
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
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName, "1");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("orderCheck_0"));

		
	}

	@Test(priority = 10, description = "VPLX: Remote ordering:[UI]- No changes is required to create name on PO, if data is created from VPLX.")
	public void Test10_1110125(Method method) throws Throwable  {
		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		test.supportDataActions.clickCreateNewOrder("Create New Order");
		Assert.assertEquals(test.supportDataActions.verifyPOLabelIsPresent("Order New Items"), true);
		test.supportDataActions.SearchPOItem("Item Name",itemName);
		test.supportDataActions.verifyPOItemSearchResult(itemName);
		medItem = test.supportDataActions.clickSearchedPOItem(getData("PurchaseOrderDetail.itemIndex"),
				itemName);
		test.supportDataActions.enterOrderQuantity("toOrderQuantity", getData("PurchaseOrderDetail.orderquantity"));
		test.supportDataActions.clickSaveAndClose("Save & Close");
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		test.supportDataActions.openPurchaseOrderManualcard();
	
		
	}

}
