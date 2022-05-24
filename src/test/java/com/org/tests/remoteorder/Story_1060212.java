package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1060212 extends BaseTest{

	String orderName;
	String FacilityName, DestinationName, itemName, ItemCode, Distributor, itemName2;
	String columnHeaders[] = { "Order name", "Order Id", "Order date", "Items", "Ordered by", "Order from", "Status" };
	ArrayList<String> actual_data, sorted_data, order_ids, unique_order_ids;
	String OrderName, itemQuantity, ExternalSystemName, barcode, productID, ISAName, ItemCode2, DosageFormCode,
			DispenseUnitCode, MedClassCode;

	
	@Test(priority = 1, description = "VPLX: Remote ordering: [UI]-When user submit remote web order,it is display in Purchase order in case R&S true.")
	public void Test01_1085851(Method method)  {
		
		

		/*
		 * FacilityName = getData("RemoteWebOrder.FacilityName"); DestinationName =
		 * getData("RemoteWebOrder.DestinationName"); itemName =
		 * getData("RemoteWebOrder.itemName"); ItemCode =
		 * getData("RemoteWebOrder.ItemCode"); itemQuantity =
		 * getData("RemoteWebOrder.itemQuantity"); Distributor =
		 * getData("RemoteWebOrder.DistributorName"); ExternalSystemName =
		 * getData("RemoteWebOrder.ExternalSystemName"); ISAName =
		 * getData("RemoteWebOrder.ISAName"); MedClassCode =
		 * getData("RemoteWebOrder.MedClassCode"); DispenseUnitCode =
		 * getData("RemoteWebOrder.DispenseUnitCode"); DosageFormCode =
		 * getData("RemoteWebOrder.DosageFormCode");
		 */
		
		 FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  itemQuantity = getData("RemoteWebOrder.itemQuantity");
		  Distributor = TestDataPropertyReaderAndWriter.getProperty("DistributorName");
		  ExternalSystemName = TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName");
		  ISAName = TestDataPropertyReaderAndWriter.getProperty("ISAName");
		  MedClassCode = TestDataPropertyReaderAndWriter.getProperty("MedClassCode");
		  DispenseUnitCode = TestDataPropertyReaderAndWriter.getProperty("DispenseUnitCode");
		  DosageFormCode = TestDataPropertyReaderAndWriter.getProperty("DosageFormCode");
		  
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(ExternalSystemName).trim();
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add Item");
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingFormKey", DosageFormCode.trim());
		test.siteConfigurationAction.selectValueDosageDropDown("dispensingUnitKey", DispenseUnitCode.trim());

		itemName2 = test.siteConfigurationAction.enterDataInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		ItemCode2 = test.siteConfigurationAction.enterDataInInputField("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueDosageDropDown("medicationClassKey", MedClassCode.trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(FacilityName);
		test.siteConfigurationAction.clickButton("save");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		test.siteConfigurationAction.hardWaitForChromeBrowser(5);
		barcode = test.siteConfigurationAction.enterRandomValueInInputField("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		test.siteConfigurationAction.clickButton("search");

		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "2");
		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		// test.siteConfigurationAction.clickDistributorInfo("preferredDistributor",
		// "1");
		test.siteConfigurationAction.clickOnDistributorInfo(Distributor);
		// test.siteConfigurationAction.enterDistributorInfo("distributorItemCode", "1",
		// "" + System.currentTimeMillis());
		test.siteConfigurationAction.enterDistributorItemCode(Distributor, "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemName2);

		test.supportDataActions.enterSearchTermInSearchField(itemName2, "search");
		test.siteConfigurationAction.clickRecordNameToEdit(itemName2);
		
		// TODO
		test.siteConfigurationAction.verifyAndClickItemFacility(FacilityName);
		test.siteConfigurationAction.clickOnADMRoundingQuantityFlag("admQuantityRoundingFlag");
		test.siteConfigurationAction.enterCostValue("averageWholesalePriceAmount", "10");
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.enterCostValue("replacementCost", "10");
		test.siteConfigurationAction.clickButton("save");
		
		test.landingPageActions.navigateToFeature("Item Locations");	
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(itemName2,"search");
		
		//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
		test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName2);
		test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
		test.siteConfigurationAction.selectValueForDropDown("facility", FacilityName.trim());
		test.siteConfigurationAction.selectValueForDropDown("isa", TestDataPropertyReaderAndWriter.getProperty("ISAName"));
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickAssignLocationButton();
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "40");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "400");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("inventoryQuantity", "200");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("cycleCountInterval", "2");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickRecordNameToEdit(DestinationName);
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName2);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName2);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName2);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
				getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableSplitOrders");
		test.siteConfigurationAction.clickButton("save");
		test.loginPageAction._logoutApplication(getData("Auth.user"), "Logout", "Confirm");
		
		test.launchApplication(getData("weborder_app_url"));
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser"),
				getData("Auth.passwordWebOrderUser"), "");
		test.siteConfigurationAction.hardWaitForChromeBrowser(20);
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		String OrderName2 = test.remoteWebOrderActions.EnterRandomValueInInputFieldOnAddNewRemoteOrder("orderNameInput",
				getData("RemoteWebOrder.OrderName") + System.currentTimeMillis());
		test.supportDataActions.enterSearchTermInSearchField(itemName2, "textValue");
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode2, itemName);
		test.remoteWebOrderActions.enterItemQuantityOnROCard(getData("1"));
		test.remoteWebOrderActions.clickButton("buildOrderSubmitButton");
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination", FacilityName + " - " + DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		test.remoteWebOrderActions.navigateToTab("View All Orders");
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName2);
		test.remoteWebOrderActions.verifyNewlyAddedOrderInTheList(OrderName2, getData("RemoteWebOrder.PendingState"));

		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.siteConfigurationAction.hardWaitForChromeBrowser(30);

		test.launchApplication(getData("app_url"));
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser"),
				getData("Auth.passwordAdminUser"), "");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(FacilityName);

		Assert.assertTrue(test.purchaseDashboardActions.validateDistributorCardName(Distributor),
				"[Assertion failed]: Distributor name is not present on present on the purchase order card");
		Assert.assertTrue(test.purchaseDashboardActions.verifyDistributorCardType(Distributor),
				"[Assertion failed]: Distributor type(Manual/Electronic) is not present on the purchase order card");
		test.purchaseDashboardActions.openPurchaseOrderCard(Distributor);
		
	}

}
