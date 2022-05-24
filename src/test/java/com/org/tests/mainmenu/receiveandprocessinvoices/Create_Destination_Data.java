package com.org.tests.mainmenu.receiveandprocessinvoices;

import static com.org.automation.utils.YamlReader.getData;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_Destination_Data extends BaseTest {

	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax, destinationName1, destinationCode1;
	
	@Test(priority = 2, description = "VPLX:Manage Destinations-General:[UI]:Verify User Is able to add destinations")
	public void Test02_Add_Destination() {
		test.landingPageActions.navigateToFeature("Main Menu");
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
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode1 = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", TestDataPropertyReaderAndWriter.getProperty("FacilityCodeReceiving").trim());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", true);
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		test.siteConfigurationAction.selectValueFromDropDownByIndex("packageSharingFacilityKey",1);
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"costCenterCode", RandomStringUtils.random(6, false, true));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		TestDataPropertyReaderAndWriter.setProperty("DestinationName1", destinationName1);
		TestDataPropertyReaderAndWriter.setProperty("DestinationCode1", destinationCode1);

	}
	
}
