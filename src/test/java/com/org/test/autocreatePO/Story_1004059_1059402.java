package com.org.test.autocreatePO;

import static com.org.automation.utils.YamlReader.getData;

import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.smoketests.Story_110;

public class Story_1004059_1059402 extends BaseTest {

	String distributorNew, facilityOnWFAScreen = TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim(),
			barcode, itemName, ISAName = TestDataPropertyReaderAndWriter.getProperty("ISAName").trim(), itemID,
			productID, FaciltiyName2,dataEnteredCode,new_data;
	
	String dataEnteredName;
	
	

	@Test(priority = 1, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: Auto-create is created if the user navigates to buyers dashboard and QOH less than min qty.")
	public void Test01_1086205() {

		/*
		 * ====================================CREATE
		 * DISTRIBUTOR==================================================
		 */
		
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"dis" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		//test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag", true);
		//test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode", dataEnteredCode);
        System.out.println("DistributorName "+ dataEnteredName);

		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		/*
		 * test.siteConfigurationAction.
		 * clickOnEditLinkCorresspondingToFacilityName_Sanity(
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		 */

		test.siteConfigurationAction.clickTab("Distributor Accounts");

		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		/*
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * verifyMessageWhenToggelIsOnOrOff(
		 * "No distributors have been selected for this facility. Select Show Inactive to see all available distributors."
		 * ));
		 */

		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		/*
		 * Assert.assertFalse(test.siteConfigurationAction.
		 * verifyMessageWhenToggelIsOnOrOff(
		 * "No distributors have been selected for this facility. Select Show Inactive to see all available distributors."
		 * ));
		 */

		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"));

		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim(), "12345"));

		test.siteConfigurationAction.clickSaveButton();
		// test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"),50,500);
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		/*
		 * test.siteConfigurationAction.
		 * clickOnEditLinkCorresspondingToFacilityName_Sanity(
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		 */

		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.purchaseDashboardActions.verifyDistributorMappedWithFacility(
						TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim()),
				"[Assertion Failed]: Distributor not listed in Facility");



		/*
		 * =================================CREATE ITEM AND ASSIGN THE ABOVE
		 * CREATED DISTRIBUTOR==================================================
		 */

		test.landingPageActions.navigateToFeature("Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());

		test.siteConfigurationAction.clickActionbutton("Actions");
		test.siteConfigurationAction.clickActionbutton("Add New Item");
		test.supportDataActions.verifyAddNewItemLabelIsPresent("Add New Item");
		test.siteConfigurationAction.clickCheckboxfacilityitemlevel(facilityOnWFAScreen);

		itemName=test.siteConfigurationAction.enterRandomValueInInputField("genericName",
				"ItemName" + System.currentTimeMillis());
		System.out.println("itemName" +itemName);
		itemID = test.siteConfigurationAction.enterRandomValueInInputField("itemId",
				"ItemID" + System.currentTimeMillis());
		System.out.println("itemID" +itemID);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("medicationClassKey", 1);

		test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingFormKey", 1);
		//test.siteConfigurationAction.selectValueFromDropDownByIndex("dispensingUnitKey", 1);

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.siteConfigurationAction.verifyAndClickProductID("Product ID");
		test.siteConfigurationAction.verifyAndClickAddProductID("Add Product ID");
		test.siteConfigurationAction.verifyHeader("Barcodes");
		barcode = test.remoteWebOrderActions.enterRandomValueInInputFieldForRO("barcodeValue",
				"01003" + System.currentTimeMillis() + "0171005032328717621abcd123456789");

		test.siteConfigurationAction.clickButton("search");
		productID = test.siteConfigurationAction.getParsedProductID();
		System.out.println("productID=" + productID);
		test.siteConfigurationAction.clickButton("link");
		test.siteConfigurationAction.verifyAddedProductID(productID);

		// test.siteConfigurationAction.selectValueFromDropDown("manufacturerKey",manufacturer);
		test.siteConfigurationAction.enterRandomValueInInputField("packageSize", "5");
		// test.siteConfigurationAction.clickButton("add");
		test.siteConfigurationAction.clickToAddPreferredDistributor("Add Preferred Distributor");
		// test.siteConfigurationAction.verifyAddedProductID(productID);
		test.siteConfigurationAction.verifyPageHeader("ml-24", "Manage Distributors");

		test.siteConfigurationAction.clickOnDistributorInfo(dataEnteredName);
		test.siteConfigurationAction.enterDistributorItemCode(dataEnteredName, "" + System.currentTimeMillis());

		test.siteConfigurationAction.clickButton("primary");
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));

		/*
		 * =================================ASSIGN LOCATION TO THE ABOVE CREATED
		 * ITEM such that QOH < MIN
		 * Quantity==================================================
		 */
		
		 test.landingPageActions.navigateToFeature("Main Menu");
		 
			test.landingPageActions.navigateToFeature("Item Locations");	
			test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown", facilityOnWFAScreen);
			test.supportDataActions.enterSearchTermInSearchFieldGl(itemName,"search");

			//test.siteConfigurationAction.verifyUSerIsOnLocationManagementPage();
			test.supportDataActions.clickOnEditLinkCorresspondingToItemNew(itemName);
			test.siteConfigurationAction.verifyButtonOnEditLocation("assign_button");
			test.siteConfigurationAction.clickButtonOnEditLocation("assign_button");
			test.siteConfigurationAction.selectValueForDropDown("facility", facilityOnWFAScreen);
			test.siteConfigurationAction.selectValueForDropDown("isa", ISAName);
			test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
			test.siteConfigurationAction.clickAssignLocationButton();

		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("refillPointQuantity", "50");
		test.siteConfigurationAction.enterValueInQuantityFieldOnLocationScreen("parQuantity", "200");
		test.siteConfigurationAction.enterAndReturnValueInQuantityFieldOnLocationScreen("inventoryQuantity", "10");
		test.siteConfigurationAction.clickSaveButton();

		/*
		 * ======================NAVIGATE TO DASHBOARD TO VERIFY AUTO-CREATED PO========================
		 */

		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.supportDataActions.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		//test.purchaseDashboardActions.clickDashboardRefresh("dashboardRefreshBtn");
		test.purchaseDashboardActions.clickRefreshOrder("Refresh Order");
		//test.purchaseDashboardActions.selectDropDownValue(facilityOnWFAScreen);
		
		Assert.assertEquals(test.supportDataActions.verifyPurchaseOrderManualCardisPresent(), true);
		// test.supportDataActions.openPurchaseOrderManualcard();
//		test.purchaseDashboardActions
	//			.openPurchaseOrderCard(TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim());
		
		//Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
			//	"[Assertion Failed]: New state is not present on the Dashboard");
		//Assert.assertTrue(test.purchaseDashboardActions.verifyAutoCreatedPO(dataEnteredName),
			//	"[ASSERTION FAILED]: Auto-Create PO is not visible in New State on Dashboard.");

	}

	@Test(priority = 2, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: Auto-create is created if the user navigates to buyers dashboard and QOH less than min qty.")
	public void Test02_1086204() {
		FaciltiyName2 = test.siteConfigurationAction.selectAndReturnValueFromDropDownByIndex("FacilityDropdown", 1);

		Assert.assertFalse(test.purchaseDashboardActions.verifyAutoCreatedPODoesNotExist(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is visible in New State on Dashboard for other facility.");

	}

	@Test(priority = 3, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: System is created some of the items after coming back to Dashboard screen")
	public void Test03_1086199() {
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);

		Assert.assertTrue(test.purchaseDashboardActions.verifyAutoCreatedPO(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is not visible in New State on Dashboard.");

	}

	@Test(priority = 4, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: System created items on click of refresh button")
	public void Test04_1086198() {

		test.purchaseDashboardActions.clickAutoCreatedPOCard(dataEnteredName);
		test.purchaseDashboardActions.distributorDetailsVisibleOnHeader(dataEnteredName);
		test.purchaseDashboardActions.clickButton("void0");
		test.purchaseDashboardActions.clickButton("primary");
		Assert.assertFalse(test.purchaseDashboardActions.verifyAutoCreatedPODoesNotExist(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is visible in New State on Dashboard for other facility.");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		//test.purchaseDashboardActions.clickDashboardRefresh("dashboardRefreshBtn");
		test.purchaseDashboardActions.clickRefreshOrder("Refresh Order");
		Assert.assertTrue(test.purchaseDashboardActions.verifyAutoCreatedPO(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is not visible in New State on Dashboard.");

	}
	
	@Test(priority = 5, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: While moving to transaction queue from purchase dashboard and get back to the same facility")
	public void Test05_1086201() {
		test.landingPageActions.navigateToFeature("Transaction Queue");
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.transactionQueueActions.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		
		Assert.assertNotNull(
				test.siteConfigurationAction.getFacilityFromISAScreen().isEmpty());
		facilityOnWFAScreen=test.siteConfigurationAction.getFacilityFromISAScreen();
		
	
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyAutoCreatedPO(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is not visible in New State on Dashboard.");
		
		
	}
	@Test(priority = 6, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: Hitting F5 button on keyboard while on dashboard screen")
	public void Test06_1086202() {
		test.purchaseDashboardActions.clickAutoCreatedPOCard(dataEnteredName);
		test.purchaseDashboardActions.distributorDetailsVisibleOnHeader(dataEnteredName);
		test.purchaseDashboardActions.clickButton("void0");
		test.purchaseDashboardActions.clickButton("primary");
		Assert.assertFalse(test.purchaseDashboardActions.verifyAutoCreatedPODoesNotExist(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is visible in New State on Dashboard for other facility.");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		test.transactionQueueActions.pressKeyUsingAction(Keys.F5);
		test.supportDataActions.pageRefresh();
		Assert.assertFalse(test.purchaseDashboardActions.verifyAutoCreatedPODoesNotExist(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is visible in New State on Dashboard for other facility.");
		test.siteConfigurationAction.selectValueForDropDown("FacilityDropdown", facilityOnWFAScreen);
		
	}
	
	@Test(priority = 7, description = "VPLX: Auto Create Purchase Orders (POs) : [UI]: Navigating to buyers dashboard from main menu")
	public void Test07_1086203() {
		
		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToMenu("Purchasing Dashboard");
		test.purchaseDashboardActions
				.selectDropDownValue(facilityOnWFAScreen);
		Assert.assertTrue(test.purchaseDashboardActions.verifyDashboardState("New"),
				"[Assertion Failed]: New state is not present on the Dashboard");
		Assert.assertTrue(test.purchaseDashboardActions.verifyAutoCreatedPO(dataEnteredName),
				"[ASSERTION FAILED]: Auto-Create PO is not visible in New State on Dashboard.");


	}

}
