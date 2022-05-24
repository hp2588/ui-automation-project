package com.org.tests.Astar_RO;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RO_1121489 extends BaseTest {
	
	String OrderName_1,OrderName_2,itemName,FacilityName,Destination_Split,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName,barcode,productID,OrderName1;
	ArrayList<String> previous_data, sorted_data;
	
	
	@Test(priority = 5, description = "VPLX : Remote Orders - When a split order is created without creating a destination order the split order should get verified.")
	public void Test05_1121489_RO_1121555(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : Remote Orders - When a split order is created without creating a destination order the split order should get verified.");
		
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DistributorName= TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim();
		ExternalSystemName= TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
		IPAddress= TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim();
		ISAName= TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();
		
		
		test.landingPageActions.navigateToMenu("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(ExternalSystemName).trim();
		
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		
		
		itemName = test.siteConfigurationAction.enterDataInInputField("genericName", "ItemName" + System.currentTimeMillis());
		ItemCode = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		
		barcode = "01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789";
		Assert.assertTrue(test.siteConfigurationAction.sendKeysBarcode(barcode));
		Assert.assertTrue(test.siteConfigurationAction.clickOnSearchButton());
		//test.siteConfigurationAction.clickButton("search");
		
		productID = test.siteConfigurationAction.getParsedProductID();
		
		System.out.println("productID is " + productID);
		
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction.clickOnDistributorInfo(DistributorName);
		test.siteConfigurationAction.enterDistributorItemCode(DistributorName,"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.supportDataActions.verifyNewlyAddedRecordNameInList(itemName);
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,  "search");
		
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", FacilityName);
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.remoteWebOrderActions.selectDropdownForRO("FacilityDropdown", FacilityName);
		String Destination_Split = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.clickCheckbox("verifyRemoteOrderFlag");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName);
		test.siteConfigurationAction.isRecordVisible(itemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.isRecordVisible(itemName);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", 
				getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
		
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(10);
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName1 = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput", 
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		System.out.println("Order Name is: "+ OrderName1);
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard("1");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName1,getData("RemoteWebOrder.PendingState"));
		
		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserNameForLogout"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.launchApplication(getData("app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(10);
		
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(IPAddress);
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		
		if(test.transactionQueueActions.verifyActiveTransactionItem(itemName) &&
				test.transactionQueueActions.verifyDestinationInCurrentPickWindow(Destination_Split)) {
			
			Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
			test.transactionQueueActions.clickScanOverrideOnce();
			
		} else {
			test.transactionQueueActions.makeROTransactionActive();
			if (test.transactionQueueActions.verifyActiveTransactionItem(itemName) &&
					test.transactionQueueActions.verifyDestinationInCurrentPickWindow(Destination_Split)) {
				
				Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
				test.transactionQueueActions.clickScanOverrideOnce();
				
			}
		}
		
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(10);
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName +  " - " + Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName1, getData("RemoteWebOrder.SentToDestinationState"));	
		
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, OrderName1);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(ItemCode, "1");
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
		test.remoteWebOrderActions.clickButton("invoiceOrderCancelButton");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName1,getData("RemoteWebOrder.VerifiedState"));
		
	}
	
	
}
