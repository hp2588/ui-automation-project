package com.org.tests.remoteorder;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.smoketests.UI_Sanity_Opt;

public class Story_1150155 extends BaseTest{
	String orderName;
	String itemName_without_location;String itemID_without_location, ItemCode1, itemName1;
	String  FacilityName, DestinationName,itemName, ItemCode;
	String columnHeaders [] = {"Order name","Order Id","Order date","Items","Ordered by", "Order from", "Status"};
	ArrayList<String> actual_data, sorted_data,order_ids, unique_order_ids;
	String OrderName,itemQuantity;
	private String barcode;
	private String productID;
	private String itemName_without_location_Or_NDC;
	private String itemID_without_location_Or_NDC;
	private String destinationName_loc_NDC;
	private String destinationCode_loc_NDC;
	 
	
	
	@Test(priority = 1, description = "VPLX: Remote ordering:[UI]: A modal message is display while clicking on Add All button where one or more items doesn't have assigned location and rest of items are without any error.")
	public void Test01_1135625(Method method)  {
		
		 FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
	

		itemName_without_location = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		 itemID_without_location = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForTest("barcodeValue",
				"01003" + RandomStringUtils.random(10, false, true) + "0171005032328717621abcd123456789");
		
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);

		test.supportDataActions.clickButtonWithOutAnyWait("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		// TestDataPropertyReaderAndWriter.setProperty("ItemCode",itemID);
		// TestDataPropertyReaderAndWriter.setProperty("ItemName",itemName);

		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();

		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID_without_location);
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(DestinationName);
		test.siteConfigurationAction.clickEditLink(DestinationName);
		test.siteConfigurationAction.clickCheckbox("verifyRemoteOrderFlag");
		test.siteConfigurationAction.clickTabWithoutWait("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName_without_location);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_without_location);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_without_location);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
				getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
		test.siteConfigurationAction.clickTabWithoutWait("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+DestinationName);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		
		test.remoteWebOrderActions.verifyPopupMessage("One or more items did not have assigned location or assigned Product ID, and were not added to the order.  Indicator icon is displayed for all items without assigned location or Product ID");
		Assert.assertNotEquals(test.remoteWebOrderActions.verifyOrderList("Order"),test.remoteWebOrderActions.captureDataForParticularColumn("2"));
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode, itemName);
		test.remoteWebOrderActions.clickAvailableItemOnRO(ItemCode1, itemName1);
		
	}
	
	@Test(priority = 2, description = "VPLX: Remote ordering:[UI]: A modal message is display while clicking on Add All button where all items doesn't have assigned location or NDC.")
	public void Test02_1135644(Method method)  {
		
		 FacilityName= TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		  DestinationName = TestDataPropertyReaderAndWriter.getProperty("DestinationName");
		  itemName = TestDataPropertyReaderAndWriter.getProperty("ItemName");
		  ItemCode = TestDataPropertyReaderAndWriter.getProperty("ItemCode");
		  
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("genericName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("itemId"),
				"[ASSERTION FAILED]: input field is not mandatory");
		Assert.assertTrue(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("medicationClassKey"),
				"[ASSERTION FAILED]: dropdown is not mandatory");

		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("dispensingFormKey",
				TestDataPropertyReaderAndWriter.getProperty("DosageFormCode").trim());
	

		itemName_without_location_Or_NDC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("genericName",
				"ItemName" + System.currentTimeMillis());
		itemID_without_location_Or_NDC = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("itemId", "ItemID" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("medicationClassKey",
				TestDataPropertyReaderAndWriter.getProperty("MedClassCode").trim());
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel_sanity(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeaderWithoutWait("Barcodes");
		
		
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		// TestDataPropertyReaderAndWriter.setProperty("ItemCode",itemID);
		// TestDataPropertyReaderAndWriter.setProperty("ItemName",itemName);

		test.siteConfigurationAction.clickLink("Add Preferred Distributor");
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");
		test.siteConfigurationAction
				.clickOnDistributorInfo(TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		test.siteConfigurationAction.enterDistributorItemCode(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"), "" + System.currentTimeMillis());
		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickSaveButtonForISA();

		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(itemID_without_location_Or_NDC);
		
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.siteConfigurationAction.selectValueFromDropDownForManufacturer("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		destinationName_loc_NDC = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode_loc_NDC = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.landingPageActions.pageRefresh();
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName_loc_NDC);
		test.siteConfigurationAction.clickEditLink(destinationName_loc_NDC);
		test.siteConfigurationAction.clickCheckbox("verifyRemoteOrderFlag");
		test.siteConfigurationAction.clickTabWithoutWait("Items");
		test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.enterItemNameForDestinationItem(itemName_without_location_Or_NDC);
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_without_location_Or_NDC);
		test.siteConfigurationAction.clickCheckbox("activeFlag-0");
		test.siteConfigurationAction.clickActionbutton("Save & Close");
		test.siteConfigurationAction.verifyNewlyAddedRecordNameInList(itemName_without_location_Or_NDC);
		test.siteConfigurationAction.clickCheckbox("limitedOrderQuantity-0");
		test.siteConfigurationAction.enterRandomValueInInputField("maximumOrderQuantity-0",
				getData("RemoteWebOrder.MaxDailyQuantity"));
		test.siteConfigurationAction.clickCheckbox("enableReceiveNSend");
		test.siteConfigurationAction.clickTabWithoutWait("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyUserAvailableInList();
		test.siteConfigurationAction.selectUserForRemoteOrder(getData("RemoteWebOrder.UserName"));
		test.siteConfigurationAction.clickButton("save");

		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("weborder_app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameWebOrderUser").trim(), getData("Auth.passwordWebOrderUser").trim(),
				getData("Auth.ip").trim());
		
		test.remoteWebOrderActions.selectDropdownForRO("selectDestination",FacilityName+" - "+destinationName_loc_NDC);
		test.remoteWebOrderActions.clickButton("buildNewOrder");
		
		test.remoteWebOrderActions.clickButton("buildOrderSelectButton");
		test.remoteWebOrderActions.verifyPopupMessage("Are you sure you want to add items to the Order List?");
		test.remoteWebOrderActions.clickButton("primary");
		
		test.remoteWebOrderActions.verifyPopupMessage("No item was added to the order list since none of the items have assigned location and assigned Product ID.  Indicator icon is displayed for all items without assigned location or Product ID");
		Assert.assertNotEquals(test.remoteWebOrderActions.verifyOrderList("Order"),test.remoteWebOrderActions.captureDataForParticularColumn("2"));
		
	}
	
	

}
