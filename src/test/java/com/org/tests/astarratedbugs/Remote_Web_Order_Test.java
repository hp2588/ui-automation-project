package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Remote_Web_Order_Test extends BaseTest {
	
	String OrderName_1,OrderName_2,itemName,FacilityName,DestinationName,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName;
	ArrayList<String> previous_data, sorted_data;

	

	 
		

	@Test(priority = 5, description = "VPLX : Remote Orders - When a split order is created without creating a destination order the split order should get verified.")
	public void Test05_1121489(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX : Remote Orders - When a split order is created without creating a destination order the split order should get verified.");
		
		itemName= TestDataPropertyReaderAndWriter.getProperty("ItemName").trim();
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName").trim();
		ItemCode= TestDataPropertyReaderAndWriter.getProperty("ItemCode").trim();
		DistributorName= TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim();
		ExternalSystemName= TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
		IPAddress= TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim();
		ISAName= TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();
		
		//test.launchApplication(getYamlValue("app_url"));
		//test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		//test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.enterSearchTermInSearchField(itemName, "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName, "Edit");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickEditLink(DestinationName);
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
		test.siteConfigurationAction.clickButton("save");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName1 = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		System.out.println("Order Name is $$$ "+ OrderName1);
		test.supportDataActions.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemName"), "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard("1");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_1,getData("RemoteWebOrder.PendingState"));
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		
		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.transactionQueueActions.verifyTQPageAndAppendIP("IPAddress");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		
	   	Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		
		
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName1,getData("RemoteWebOrder.VerfiedState"));	
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
	}
	
	
}
