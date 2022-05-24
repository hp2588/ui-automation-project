package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_101845_Split extends BaseTest{
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
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]- Remote order created when 'Split Order' flag is true for selected Destination")
	public void Test01_1129664_1135506(Method method)  {
		
		  FacilityName = getData("RemoteWebOrder.FacilityName");
		  DestinationName = getData("RemoteWebOrder.DestinationName");
		  itemName = getData("RemoteWebOrder.itemName");
		  ItemCode = getData("RemoteWebOrder.ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  Distributor = getData("RemoteWebOrder.DistributorName");
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
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
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "4");
	
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
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
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
		test.remoteWebOrderActions.enterItemQuantityOnROCard("15");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		/**********************Validate a Remote Web Order**************************/
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.PendingState"));
		Assert.assertEquals(test.remoteWebOrderActions.getItemDetails(OrderName,"3"),"1");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		Assert.assertEquals(test.remoteWebOrderActions.getItemDetails(ItemCode+itemName,"2"),"15");
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
	
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		test.purchaseDashboardActions.validateDistributorCardName(Distributor);
		test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		int OrderquantityPkg = test.remoteWebOrderActions.verifyOrderQuantityOnPO(itemID);
		Assert.assertEquals((15/4), OrderquantityPkg);
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		 test.supportDataActions.selectDropDownValue(FacilityName);
		 test.purchaseDashboardActions.validateDistributorCardName(Distributor);
		 test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
		 test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		 test.remoteWebOrderActions.clickButton("void0");
		 test.remoteWebOrderActions.clickButton("primary");
		test.landingPageActions.navigateToFeature("Transaction Queue");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.transactionQueueActions.verifyTQPageAndAppendIP("IPAddress");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		ArrayList<String> activeTransactionName = new ArrayList<String>();
		activeTransactionName.add(itemID+ItemCode);
		activeTransactionName.add("3");
		activeTransactionName.add(DestinationName);
		activeTransactionName.add("Destination Orders");
		if(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName))
		{
			Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
			//Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
			//Assert.assertTrue(test.transactionQueueActions.confirmPopup());
			Assert.assertTrue(test.transactionQueueActions.verifyOverrideWithoutScanner(
					"Waiting for Pick Label Scan... (F2) to override.", "Transaction Completed"));
		}
		else
		{	test.transactionQueueActions.makeROTransactionActive();
			if (test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName)) {
			   	test.transactionQueueActions.clickScanOverride();

			}
			
		}
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.SentToDestinationState"));
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName,"1");
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName,getData("RemoteWebOrder.VerifiedState"));	
			
		}		
		
	
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]: PO order status is updated from Pending to Sent To destination in RO after Bypass the PO in Buyer dashboard")
	public void Test02_1130497(Method method) {
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		test.remoteWebOrderActions.navigateToTab("Create New Order");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		int order_quantity = test.remoteWebOrderActions.enterItemQuantityInRnSOrder("10");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.PendingState"));

		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);

		test.launchApplication(getYamlValue("app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
				getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);
		test.purchaseDashboardActions.validateDistributorCardName(Distributor);
		test.purchaseDashboardActions.verifyDistributorCardType(Distributor);
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		test.siteConfigurationAction.enterDataInInputField("purchaseOrderNumber",
				test.supportDataActions.generatingRandomStringForPO(5));
		test.supportDataActions.savePONumber("New Order");
		test.siteConfigurationAction.clickButton("exportNow");
		test.siteConfigurationAction.clickButton("primary");
		test.supportDataActions.clickPendingReceiveCard(TestDataPropertyReaderAndWriter.getProperty("Distributor_Name"));
		test.siteConfigurationAction.enterDataInInputField("receiveOrderInvoice",
				test.supportDataActions.generatingRandomStringForInvoice(5));
		test.supportDataActions.savePONumber("PendingReceive");
		test.supportDataActions.enterItemCostForInvoice("cost", "10");
		test.remoteWebOrderActions.clickButton("ByPassed");
		test.remoteWebOrderActions.clickButton("primary");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName, getData("RemoteWebOrder.SentToDestinationState"));

	}

}
