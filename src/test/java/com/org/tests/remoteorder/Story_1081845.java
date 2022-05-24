package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1081845 extends BaseTest{
	String orderName;
	String app_url ;
	String  FacilityName, DestinationName,itemName, ItemCode,Distributor,DestinationName_BothFalse;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;
	ArrayList<String> Orderstatus = new ArrayList<String>(Arrays.asList("Pending", "Sent To Destination","Verfied","Voided"));


	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		app_url= getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
			  
		  FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  Distributor = TestDataPropertyReaderAndWriter.getProperty("DistributorName");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
	}
	
	
	
	/*
	 * @Test(priority = 3, description =
	 * "VPLX: Remote ordering:[UI]- Display of all valid Orders status in View All Orders' tab"
	 * ) public void Test03_1097611(Method method) {
	 * test.remoteWebOrderActions.navigateToTab("View All Orders");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName+" - "+DestinationName);
	 * Assert.assertTrue(Orderstatus.contains(test.remoteWebOrderActions.
	 * captureDataForParticularColumnOrderPage("7").get(0)));
	 * 
	 * }
	 */
	
	/*
	 * @Test(priority = 5, description =
	 * "VPLX: Remote ordering:[UI]-Order status is  Sent To Destination when all the items status are sent to the destination"
	 * ) public void Test05_1095344(Method method) {
	 * test.remoteWebOrderActions.navigateToTab("Create New Order");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName+" - "+DestinationName);
	 * test.remoteWebOrderActions.clickButton("buildNewOrder"); String OrderName =
	 * test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(
	 * "orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis
	 * ()); test.supportDataActions.enterSearchTermInSearchField(itemName,
	 * "textValue");
	 * test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
	 * test.remoteWebOrderActions.enterItemQuantityOnROCard(getData(
	 * "RemoteWebOrder.OrderQuantity"));
	 * test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
	 * 
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName+" - "+DestinationName);
	 * test.remoteWebOrderActions.clickButton("buildNewOrder");
	 * test.remoteWebOrderActions.navigateToTab("View All Orders");
	 * test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData(
	 * "RemoteWebOrder.PendingState"));
	 * test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"),
	 * "Logout", "Confirm");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	 * 
	 * test.launchApplication(getYamlValue("app_url"));
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	 * test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"
	 * ), getData("Auth.passwordAdminUser"), "");
	 * test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	 * test.supportDataActions.selectDropDownValue(FacilityName);
	 * test.purchaseDashboardActions.validateDistributorCardName(Distributor);
	 * test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
	 * test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
	 * test.remoteWebOrderActions.verifyDestinationNameFromPOCard(DestinationName);
	 * test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",test
	 * .supportDataActions.generatingRandomStringForPO(5));
	 * test.supportDataActions.savePONumber("New Order");
	 * test.siteConfigurationAction.clickButton("exportNow");
	 * test.siteConfigurationAction.clickButton("primary");
	 * test.supportDataActions.clickPendingReceiveCard(Distributor);
	 * test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",test
	 * .supportDataActions.generatingRandomStringForInvoice(5));
	 * test.supportDataActions.savePONumber("PendingReceive");
	 * test.supportDataActions.enterItemCostForInvoice("cost", "10");
	 * test.supportDataActions.savePONumber("PendingReceive");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(20);
	 * test.supportDataActions.selectItemtoRecieve(itemName);
	 * test.siteConfigurationAction.clickButton("Received");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(20);
	 * test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout",
	 * "Confirm"); test.launchApplication(getData("weborder_app_url"));
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	 * test.loginPageAction.LoginToTheBDApplication(getData(
	 * "Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(20);
	 * test.remoteWebOrderActions.navigateToTab("View All Orders");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName + " - " + DestinationName);
	 * test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData(
	 * "RemoteWebOrder.SentToDestinationState"));
	 * 
	 * }
	 */
 
	/*
	 * @Test(priority = 4, description =
	 * "VPLX: Remote ordering:[UI]- Remote order created  when 'Receive and Send' flag is true for selected Destination"
	 * +
	 * "VPLX: Remote ordering:[UI]: PO order status is updated from Pending to Voided in RO after Void the PO in Buyer dashboard"
	 * ) public void Test04_1097585_1130498(Method method) {
	 * 
	 * test.remoteWebOrderActions.navigateToTab("Create New Order");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName + " - " + DestinationName);
	 * test.remoteWebOrderActions.clickButton("buildNewOrder"); String OrderName =
	 * test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(
	 * "orderNameInput", getData("RemoteWebOrder.OrderName") +
	 * System.currentTimeMillis());
	 * test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
	 * test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName); String
	 * package_size = test.remoteWebOrderActions.getItemDetails(itemName, "2"); int
	 * order_quantity =
	 * test.remoteWebOrderActions.enterItemQuantityInRnSOrder(package_size);
	 * Assert.assertTrue(order_quantity % Integer.parseInt(package_size) == 0);
	 * test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName + " - " + DestinationName);
	 * test.remoteWebOrderActions.clickButton("buildNewOrder");
	 * test.remoteWebOrderActions.navigateToTab("View All Orders");
	 * test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,
	 * getData("RemoteWebOrder.PendingState"));
	 * 
	 * test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"),
	 * "Logout", "Confirm");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	 * 
	 * test.launchApplication(getYamlValue("app_url"));
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(20);
	 * test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"
	 * ), getData("Auth.passwordAdminUser"), "");
	 * test.landingPageActions.navigateToMenu("Purchasing Dashboard");
	 * test.supportDataActions.selectDropDownValue(FacilityName);
	 * test.purchaseDashboardActions.validateDistributorCardName(Distributor);
	 * test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
	 * test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
	 * test.remoteWebOrderActions.clickButton("void0");
	 * test.remoteWebOrderActions.clickButton("primary");
	 * test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout",
	 * "Confirm"); test.launchApplication(getData("weborder_app_url"));
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	 * test.loginPageAction.LoginToTheBDApplication(getData(
	 * "Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
	 * test.siteConfigurationAction.hardWaitForChromeBrowser(20);
	 * test.remoteWebOrderActions.navigateToTab("View All Orders");
	 * test.remoteWebOrderActions.selectDropdownForRO("selectDestination",
	 * FacilityName + " - " + DestinationName);
	 * test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData(
	 * "RemoteWebOrder.VoidedState"));
	 * 
	 * }
	 */
	
	@Test(priority = 0, description = "VPLX: Remote ordering:[UI]- Remote order created when 'Split Order' flag is true for selected Destination")
	public void Test07_1097591(Method method)  {

			
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);

		test.launchApplication(getData("app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);

		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
				getData("Auth.passwordAdminUser"), "");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		//test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		// test.supportDataActions.verifyLabelIsPresent("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey",TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		String itemID = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");
	
		

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		String barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");

		//test.siteConfigurationAction.clickButton("search");

		String productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "3");
	
		

		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(TestDataPropertyReaderAndWriter.getProperty("DistributorName"),"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID);
		
		test.landingPageActions.navigateToFeature("Main Menu");
		 
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName"),"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		String DestinationName_Split = TestDataPropertyReaderAndWriter.getProperty("DestinationName_Split").trim();
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName_Split);
		test.siteConfigurationAction.clickEditLink(DestinationName_Split);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.remoteWebOrderActions.clickCheckboxAgainstItem(itemName,"limitedOrderQuantity");
		test.remoteWebOrderActions.enterRandomValueInInputFieldAgainstItem(itemName,"maximumOrderQuantity", getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard("10");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_Split);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));

		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		test.purchaseDashboardActions.validateDistributorCardName(Distributor);
		test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		int OrderquantityPkg = test.remoteWebOrderActions.verifyOrderQuantityOnPO(itemID);
		Assert.assertEquals((10/3), OrderquantityPkg);
		test.landingPageActions.navigateToFeature("Transaction Queue");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.transactionQueueActions.verifyTQPageAndAppendIP("IPAddress");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		ArrayList<String> activeTransactionName = new ArrayList<String>();
		activeTransactionName.add(itemID+ItemCode);
		activeTransactionName.add("1");
		activeTransactionName.add(DestinationName);
		activeTransactionName.add("Destination Orders");
		if(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName))
		{
			test.transactionQueueActions.verifyTransactionWithPriority("Destination Orders",itemID);
			test.transactionQueueActions.verifyTransactionWithPriority("Destination Orders",itemID+ItemCode);
		}
		else
		{	test.transactionQueueActions.makeROTransactionActive();
			if (test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName)) {
				test.transactionQueueActions.verifyTransactionWithPriority("Destination Orders",itemID);

				
			}
			
		}
		
	}
	
	
	
}
