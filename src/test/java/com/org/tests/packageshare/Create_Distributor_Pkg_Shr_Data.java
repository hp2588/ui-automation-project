package com.org.tests.packageshare;

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

public class Create_Distributor_Pkg_Shr_Data extends BaseTest {

	String dataEnteredName, dataEnteredCode, new_data;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new distributor")
	public void Test01_Add_Distributor_Test_1159392_1159393(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.GLAccount"));
		test.supportDataActions.clickAddButtonOnDistributor("save");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.GLAccount"));
		test.supportDataActions.clickAddButtonOnDistributor("save");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		String Fac1 = ("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim();
		String Fac2 = ("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim();
		test.landingPageActions.navigateToFeature("Facilities");
		

		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"));
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(Fac1);
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(Fac1, RandomStringUtils.random(6, false, true)));
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveNonControlledFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveControlledTwoFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveControlledThreeToFiveFlag");
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(Fac2);
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(Fac2, RandomStringUtils.random(6, false, true)));
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveNonControlledFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveControlledTwoFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveControlledThreeToFiveFlag");
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"), "search");
		test.supportDataActions.clickAddButtonOnDistributor(TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"));
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(Fac2);
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(Fac2, RandomStringUtils.random(6, false, true)));
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveNonControlledFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveControlledTwoFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving"),
				"enableAutoReceiveControlledThreeToFiveFlag");
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(Fac1);
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(Fac1, RandomStringUtils.random(6, false, true)));
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveNonControlledFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveControlledTwoFlag");
		test.siteConfigurationAction.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding"),
				"enableAutoReceiveControlledThreeToFiveFlag");
		test.siteConfigurationAction.clickSaveButton();
	}

}
