package com.org.tests.integration;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Distributor_Integration extends BaseTest {

	String firstData, dataEnteredName, dataEnteredCode, destinationName, destinationCode, new_data;

	@Test(priority = 1, description = "VPLX: Distributors [UI] [Integration]: When a Distributor is added, "
			+ "it gets populated on the Distributors list on Distributor Accounts tab on Facility screen")
	public void Test01_1111444(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Distributors:[Integartion]: When a Distributor is added , it gets populated under Distributors list  on Distributor Account Tab on Facility screen");
		
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		test.siteConfigurationAction.checkDistributorIsAvailabelOrNot(dataEnteredName);
		test.siteConfigurationAction.clickCancelButton();
	}
	
	
	@Test(priority = 2, description = "VPLX: Distributors [UI] [Integration]: When a Distributor name "
			+ "is updated, the updated name gets populated on Distributor Account Tab on Facility screen")
	public void Test02_1111445(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Distributors [UI] [Integration]: When a Distributor name is updated, "
				+ "the updated name gets populated on Distributor Account Tab on Facility screen");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		test.siteConfigurationAction.checkDistributorIsAvailabelOrNot(dataEnteredName);
		test.siteConfigurationAction.clickCancelButton();
	}
	
	
	@Test(priority = 3, description = "VPLX: Distributors [UI] [Integration]: When a Distributor name "
			+ "is updated, the updated name gets populated on Distributor Account Tab on Destination screen")
	public void Test03_1111448(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Distributors [UI] [Integration]: When a Distributor name is updated, "
				+ "the updated name gets populated on Distributor Account Tab on Destination screen");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility("sc1@#1600250896432");
		Assert.assertTrue(test.siteConfigurationAction
				.enterOnlyIntegerInAccountNumberFieldOnDestination(dataEnteredName, "12345"));
		test.siteConfigurationAction.clickSaveButton();
	}
	
	
	@Test(priority = 4, description = "VPLX: Distributors [UI] [Integration]: When a Distributor name "
			+ "is updated, the updated name gets populated on Distributor Account Tab "
			+ "on Destination screen for the already mapped Destinations")
	public void Test04_1111449(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Distributors [UI] [Integration]: When a Distributor name is updated, the updated name gets populated on Distributor Account Tab on Destination screen for the already mapped Destinations");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit-0");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.checkDistributorIsAvailabelOrNot(dataEnteredName);
	}
	
	
	@Test(priority = 5, description = "VPLX: Distributors [UI] [Integration]: When a Distributor is inactivated,"
			+ " it is not displayed on Destination screen under distributor Account"
			+ "&"
			+ "VPLX: Distributors [UI] [Integration]: When a Distributor is inactivated, "
			+ "it is not displayed in Destination tab")
	public void Test05_1111451_1111452(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Distributors [UI] [Integration]: When a Distributor name is updated, the updated name gets populated on Distributor Account Tab on Destination screen for the already mapped Destinations");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.GLAccount"));
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.enterSearchTermInSearchField(destinationName, "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit-0");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.checkDistributorIsInactiveOnDestination(dataEnteredName);
		test.siteConfigurationAction.clickCancelButton();
	}
	
	
}
