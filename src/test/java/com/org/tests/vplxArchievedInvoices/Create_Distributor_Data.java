package com.org.tests.vplxArchievedInvoices;

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

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new Manual distributor auto receive ON")
	public void Test02_Add_Distributor_Test(Method method) {
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"DisArchieve" + System.currentTimeMillis());
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
		TestDataPropertyReaderAndWriter.setProperty("DistributorName1", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode1", dataEnteredCode);

	}

	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:Map distributors to facility")
	public void Test01_Map_Distributors_To_Facility(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(
				TestDataPropertyReaderAndWriter.getProperty("FacilityName"));
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));

		// Manual Distributor Auto Receive ON
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),
				RandomStringUtils.random(6, false, true)));
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"), "enableAutoReceiveNonControlledFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"), "enableAutoReceiveControlledTwoFlag");
		test.purchaseDashboardActions.clickCheckboxDistributorOptions(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"),
				"enableAutoReceiveControlledThreeToFiveFlag");

		test.siteConfigurationAction.clickSaveButton();
	}

}
