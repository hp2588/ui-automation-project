package com.org.tests.purchasedashboarddata;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Create_2_Distributor extends BaseTest {

	String dataEnteredName, dataEnteredCode, new_data;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive OFF")
	public void Test01_Add_Distributor_Test(Method method) {
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisManOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName1", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode1", dataEnteredCode);

	}
	
	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Electronic distributor auto receive OFF")
	public void Test03_Add_Distributor_Test(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisElecOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());
		test.purchaseDashboardActions.enterDistributorWebsite("vendorContactWebsiteAddressValue",
				"https://www.cardinalhealth.com/");

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName3", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode3", dataEnteredCode);

	}
	
	@Test(priority = 7, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test07_Map_Distributors_To_Facility(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		/*
		 * test.siteConfigurationAction.
		 * clickOnEditLinkCorresspondingToFacilityName_Sanity(
		 * TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		 */

		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));

		// Manual Distributor Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),
				RandomStringUtils.random(6, false, true)));

	
		// Electronic Distributor Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"),
				RandomStringUtils.random(6, false, true)));

		

		test.siteConfigurationAction.clickSaveButton();
	}

}
