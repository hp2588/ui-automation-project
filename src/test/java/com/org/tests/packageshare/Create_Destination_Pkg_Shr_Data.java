package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_Destination_Pkg_Shr_Data extends BaseTest {

	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax, destinationName1, destinationCode1;
	
	@Test(priority = 1, description = "VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations")
	public void Test01_Add_Destination_1113883_1144794_1144790_1144792_1144762_1152261_1152262_1159053_1159054_1159057_1159075() throws InterruptedException {
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
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		destinationName1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving").trim());
		destinationCode1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving").trim());
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "123456789");
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyPageTitleContains("Items");
		test.siteConfigurationAction.selectCheckboxPackageSharing("enableReceiveNSend");
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyPageTitleContains("Users");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.clickCheckboxForUsers();
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName1, "search");
		test.supportDataActions.clickEditButtonOnDistributor(destinationName1);
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		test.siteConfigurationAction.verifyPackageShareAlert("Cannot enable Destination Participates in Package sharing option until all settings in Distributor Accounts, Items, and Users tabs are cleared.");
		test.siteConfigurationAction.navigationButtonOnItemManagement("primary");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.clearRandomValueInAccountNumberField("vendorAccountNumberText-0");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.clickTab("Items");
		test.siteConfigurationAction.verifyPageTitleContains("Items");
		test.siteConfigurationAction.selectCheckboxPackageSharing("enableReceiveNSend");
		test.siteConfigurationAction.clickTab("Users");
		test.siteConfigurationAction.verifyPageTitleContains("Users");
		test.siteConfigurationAction.clickCheckboxForUsers();
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName1, "search");		
		test.supportDataActions.clickEditButtonOnDistributor(destinationName1);
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		//Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		test.siteConfigurationAction.selectReceivingFacilityForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		//Assert.assertFalse(test.siteConfigurationAction.checkCheckboxIsEnabledOrDisabledUsingJavaScript("packageShareQuantityRoundingFlag"));
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"costCenterCode", RandomStringUtils.random(6, false, true));
		test.siteConfigurationAction.verifyTextOnFacilityDropdown();
		test.siteConfigurationAction.verifyTextOnPackageShareFacilityDropdown();
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("verifyRemoteOrderFlag"));
		Assert.assertFalse(test.siteConfigurationAction.verifyADMRoundingQuantityFlag("Enable Quantity Rounding"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot("Distributor Accounts"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot("Users"));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot("Items"));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		TestDataPropertyReaderAndWriter.setProperty("DestinationName1", TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		TestDataPropertyReaderAndWriter.setProperty("DestinationCode1", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving"));
	}
	
}
