package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class TestCase_1121506 extends BaseTest {

	@Test(priority = 1, description = "VPLX: Manage ISAs: [UI]: Add and View ISA")
	public void Test01_1121497(Method method) throws InterruptedException {

		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();

		test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToItemManagementFeature("Item Management");
		test.siteConfigurationAction.verifyPageHeader("fs-24", "Item Management");
		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID1").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(
				TestDataPropertyReaderAndWriter.getProperty("ItemID1").trim(), "Edit");
		test.siteConfigurationAction
				.verifyAndClickItemFacility(TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.siteConfigurationAction.enterCostValue("cycleCountIntervalDayAmount", "30");
		test.siteConfigurationAction.clickButton("save");

		test.siteConfigurationAction.enterExternalSystemValueDropdownField(
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("ItemID1").trim(), "search");
		test.supportDataActions.clickOnEditLinkCorresspondingToItem(
				TestDataPropertyReaderAndWriter.getProperty("ItemID1").trim(), "Edit");
		test.siteConfigurationAction
				.verifyAndClickItemFacility1(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.enterCostValue("cycleCountIntervalDayAmount", "30");
		test.siteConfigurationAction.clickButton("save");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress3").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName2"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName1"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));

		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());

		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress4").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName3"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

		// Make Cycle count transaction active
		Assert.assertEquals(true, test.transactionQueueActions.verifyActiveTransactionBox());
		Assert.assertEquals(true, test.transactionQueueActions.verifyWorkWithoutScannerOption());
		Assert.assertEquals(true, test.transactionQueueActions.verifyAuthorizationPopup());
		Assert.assertTrue(test.transactionQueueActions.confirmPopup());
		Assert.assertTrue(test.transactionQueueActions.verifyBinLabelScanning("Waiting for Pick Label Scan"));

		// test.landingPageActions.navigateToFeature("Main Menu");
		test.landingPageActions.navigateToFeature("Item Locations");
		test.siteConfigurationAction.selectValueFromDropDown("FacilityDropdown",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.supportDataActions.enterSearchTermInSearchFieldGl(TestDataPropertyReaderAndWriter.getProperty("ItemName1"),
				"search");
		test.supportDataActions
				.clickOnEditLinkCorresspondingToItem(TestDataPropertyReaderAndWriter.getProperty("ItemName1"));
		test.siteConfigurationAction.verifyAndClickEditButtonOnItemLocation();
		test.siteConfigurationAction.clickButtonOnEditLocation("save_button_edit_location");
		test.siteConfigurationAction.clickLocationRackButton1("rack-option d-flex mb-8");
		test.siteConfigurationAction.clickLocationRackcyclecountdateButton1("menu-children h-24 w-14 ml-12");
		test.siteConfigurationAction.clickLocationcalenderbutton("d-flex date-button");
		String date = test.siteConfigurationAction.getCurrentMonth_Year();
		test.siteConfigurationAction.ClicklocationCurrentDate1(date);
		String a = test.siteConfigurationAction.getCurrentMonth_Year();
		test.siteConfigurationAction.clickSaveButton();

		test.loginPageAction._logoutApplication(getData("RemoteWebOrder.UserName"), "Logout", "Confirm");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

		test.landingPageActions.navigateToMenu("Transaction Queue");
		test.transactionQueueActions
				.verifyTQPageAndAppendIP(TestDataPropertyReaderAndWriter.getProperty("IPAddress4").trim());
		Assert.assertTrue(test.storageAreaAction.verifyUserIsOnStorageAreaPopupPage(),
				"\"[ASSERTION FAILED]: User is not able to Navigate to ISA Page\"");
		test.storageAreaAction.selectCheckboxForISA(TestDataPropertyReaderAndWriter.getProperty("ISAName3"), 0);
		test.storageAreaAction.selectPrinterForSelectedISA(TestDataPropertyReaderAndWriter.getProperty("PrinterName2"));
		test.storageAreaAction.verifyStartWorkButtonAndClick();
		test.transactionQueueActions.verifyUserIsOnTransactionQueuePage();

	}

}
