package com.org.tests.Astar_RO;
import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class RO_121555 extends BaseTest {

	String OrderName_1,OrderName_2,itemName,FacilityName,DestinationName,ItemCode,DistributorName,ExternalSystemName,IPAddress,ISAName,barcode,productID,OrderName1;
	ArrayList<String> previous_data, sorted_data;
	
	
	@Test(priority = 6, description = "VPLX [Remote Order]: System allows to verify the order when a split order is created without a creating a destination order.")
	public void Test06_1121555(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX [Remote Order]: System allows to verify the order when a split order is created without a creating a destination order.");
		
		FacilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim();
		DistributorName= TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim();
		ExternalSystemName= TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim();
		IPAddress= TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim();
		ISAName= TestDataPropertyReaderAndWriter.getProperty("ISAName").trim();
		
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(ExternalSystemName).trim();
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey", TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode").trim());

		itemName = test.siteConfigurationAction.enterDataInInputField("genericName","ItemName" + System.currentTimeMillis());
		ItemCode = test.siteConfigurationAction.enterDataInInputField("itemId","ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
		test.siteConfigurationAction.clickButton("save");
		
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();	
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
				//test.siteConfigurationAction.clickDistributorInfo("preferredDistributor", "1");
		test.siteConfigurationAction.clickOnDistributorInfo(DistributorName);
				//test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1", "" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDistributorItemCode(DistributorName,"" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(ItemCode);
				
				
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", FacilityName);
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

				//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(TestDataPropertyReaderAndWriter.getProperty("ItemName"));
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", FacilityName);
		test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
				//test.siteConfigurationAction.clickAssignLocationButton();
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
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0", getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
		
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");

		
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName1 = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",getData("RemoteWebOrder.OrderName")+System.currentTimeMillis());
		System.out.println("Order Name is $$$ "+ OrderName1);
		test.supportDataActions.enterSearchTermInSearchField(itemName, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode,itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard("1");
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
				
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName1,getData("RemoteWebOrder.PendingState"));
		
		
		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserNameForLogout"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.launchApplication(getData("app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(15);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"), getData("Auth.passwordAdminUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(10);
		
		test.landingPageActions.navigateToFeature("Transaction Queue");
		test.transactionQueueActions.verifyTQPageAndAppendIP(IPAddress);
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ShortName"), 0);
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		ArrayList<String> activeTransactionName = new ArrayList<String>();
		activeTransactionName.add(itemName + ItemCode);
		activeTransactionName.add(Destination_Split);
		activeTransactionName.add("");
		if(test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName))
		{
			Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
			//Assert.assertTrue(test.transactionQueueActions.verifyAuthorizationPopupDetails());
			//Assert.assertTrue(test.transactionQueueActions.confirmPopup());
			test.transactionQueueActions.clickScanOverrideOnce();

		}
		else
		{	test.transactionQueueActions.makeROTransactionActive();
			if (test.transactionQueueActions.verifyValidItemNameInCurrentPick(activeTransactionName)) {
				Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
				test.transactionQueueActions.clickScanOverrideOnce();
			}
			
		}
		
		
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"), getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + Destination_Split);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_1, getData("RemoteWebOrder.SentToDestinationState"));	
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder(itemName, "1");
		test.remoteWebOrderActions.clickCheckbox("orderCheck_0");
		test.remoteWebOrderActions.clickButton("invoiceOrderSubmitButton");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName_1, getData("RemoteWebOrder.VerifiedState"));	
		
	}

}
