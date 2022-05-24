package com.org.tests.astarratedbugs;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class CreateDistributorTest extends BaseTest {

	String dataEnteredName, dataEnteredCode, new_data;

	/*@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new distributor")
	public void Test01_Add_Electronic_Distributor_Test(Method method) {
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "dis");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "UI");
		test.supportDataActions.clickRadioButtonOnDistributor("orderingRadioGroupValue-electronicDistributorFlag");
		test.supportDataActions.clickRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName1", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode1", dataEnteredCode);
		
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
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName1").trim(), "12346"));
		test.siteConfigurationAction.clickSaveButton();
	}*/
	
	@Test(priority = 2, description = "VPLX : System does Not allow the user set the Batch Wait Minutes = 0.")
	public void Test02_1121575(Method method) {
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
		test.supportDataActions.clickAddButtonOnDistributor("edit");
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
		test.supportDataActions.clickAddButtonOnDistributor("edit");
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
