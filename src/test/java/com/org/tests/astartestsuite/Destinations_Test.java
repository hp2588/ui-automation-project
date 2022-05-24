package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Destinations_Test extends BaseTest {

	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;

	@Test(priority = 1, description = "To verify the system provides the ability for CF Support to add destinations")
	public void Test01_1114516() {
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
	}
	
	@Test(priority = 2, description = "VPLX : Destinations : verify all added destinations are listed and in alphabetical order (A-Z)")
	public void Test01_1114454() {
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		TestDataPropertyReaderAndWriter.setProperty("DestinationName", destinationName);
		TestDataPropertyReaderAndWriter.setProperty("DestinationCode", destinationCode);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(destinationName);

	}

}
