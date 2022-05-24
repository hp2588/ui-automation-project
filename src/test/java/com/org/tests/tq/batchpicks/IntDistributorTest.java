package com.org.tests.tq.batchpicks;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class IntDistributorTest extends BaseTest {

	String dataEnteredName, dataEnteredCode, new_data;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:Verify User is able to add new distributor")
	public void Test01_Add_Distributor_Test(Method method) {
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnMedClassCode_Sanity("descriptionText",
				"dis" + System.currentTimeMillis());
		dataEnteredCode = test.supportDataActions.enterValueOnMedClassCode_Sanity("shortCode",
				"UI" + System.currentTimeMillis());
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		//test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName, new_data);
		TestDataPropertyReaderAndWriter.setProperty("DistributorName", dataEnteredName);
		TestDataPropertyReaderAndWriter.setProperty("DistributorCode", dataEnteredCode);

		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");

		test.supportDataActions.clickAddButtonOnDistributor("edit");

		test.siteConfigurationAction.clickTab("Transaction Priority Options");
		test.siteConfigurationAction
				.clickCheckboxBatchTransactionPriorities(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));

		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
		test.siteConfigurationAction.verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("true"));
		test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldForSanity(
				TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim(), "12345"));
		test.siteConfigurationAction.clickSaveButton();
	}
}
