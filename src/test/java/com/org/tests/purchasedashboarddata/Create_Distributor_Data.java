package com.org.tests.purchasedashboarddata;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_Distributor_Data extends BaseTest {

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

	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive ON")
	public void Test02_Add_Distributor_Test(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisManON" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField(
		// "internalFlag", true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName2", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode2", dataEnteredCode);

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

	@Test(priority = 4, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Electronic distributor auto receive ON")
	public void Test04_Add_Distributor_Test(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisElecON" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());
		test.purchaseDashboardActions.enterDistributorWebsite("vendorContactWebsiteAddressValue",
				"https://www.sunpharma.com/");

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName4", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode4", dataEnteredCode);

	}

	@Test(priority = 5, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive OFF")
	public void Test05_Add_Distributor_Test(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"Dis2ManOFF" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField(
		// "internalFlag", true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName5", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode5", dataEnteredCode);

	}

	@Test(priority = 6, description = "VPLX:Manage Distributors:[UI]:Add AutoCreate distributor")
	public void Test06_Add_Distributor_Test(Method method) {

		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.purchaseDashboardActions.selectRadioButtonElectronicDistributor("electronicDistributorFlag");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DistAutoCr" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());

		// test.siteConfigurationAction.selectCheckboxCorresspondingToField("internalFlag",
		// true);
		// test.supportDataActions.enterValueOnMedClassCode_Sanity("facilityCode",
		// TestDataPropertyReaderAndWriter.getProperty("FacilityName"));

		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("AutoCreateDistributor", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("AutoCreateID", dataEnteredCode);

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

		// Manual Distributor 2 Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName5"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName5"),
				RandomStringUtils.random(6, false, true)));

		// Manual Distributor Auto Receive ON
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"), "enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"), "enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName2"),
				"enableAutoReceiveControlledThreeToFiveFlag");

		// Electronic Distributor Auto Receive OFF
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName3"),
				RandomStringUtils.random(6, false, true)));

		// Electronic Distributor Auto Receive ON
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"), "enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"), "enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName4"),
				"enableAutoReceiveControlledThreeToFiveFlag");
		// Auto Create distributor Auto Receive ON

		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("AutoCreateDistributor"),
				"enableAutoReceiveControlledThreeToFiveFlag");

		test.siteConfigurationAction.clickSaveButton();
	}

}
