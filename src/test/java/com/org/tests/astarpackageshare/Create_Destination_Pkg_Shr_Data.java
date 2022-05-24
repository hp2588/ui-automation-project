package com.org.tests.astarpackageshare;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_Destination_Pkg_Shr_Data extends BaseTest {

	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax, destinationName1, destinationCode1;
	
	@Test(priority = 2, description = "VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations")
	public void Test02_Add_Destination() {
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
		/*
		 * destinationCode1 = test.siteConfigurationAction.
		 * EnterRandomValueInInputFieldOnAddNewPrinterPopup( "destinationCode",
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving").trim());
		 */
		test.siteConfigurationAction.selectCheckboxPackageSharing("enablePackageSharingFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		test.siteConfigurationAction.selectReceivingFacilityForDestination(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		//Assert.assertFalse(test.siteConfigurationAction.checkCheckboxIsEnabledOrDisabledUsingJavaScript("packageShareQuantityRoundingFlag"));
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"costCenterCode", RandomStringUtils.random(6, false, true));
	
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		TestDataPropertyReaderAndWriter.setProperty("DestinationName1", TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		TestDataPropertyReaderAndWriter.setProperty("DestinationCode1", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving"));
	

	}
	
}
