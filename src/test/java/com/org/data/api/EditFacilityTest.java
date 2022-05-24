package com.org.data.api;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class EditFacilityTest extends BaseTest {

	String dataEnteredName, dataEnteredCode, new_data;

	@Test(priority = 1, description = "VPLX:Edit Facility:[UI]:Verify User is able to add new distributor")
	public void Test01_Edit_Facility_Test(Method method) {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction
				.enterSearchTermInSearchField(TestDataPropertyReaderAndWriter.getProperty("FacilityName"), "search");

		test.supportDataActions.clickAddButtonOnDistributor("edit");

		test.siteConfigurationAction.clickTab("Transaction Priority Options");
		test.siteConfigurationAction
				.clickCheckboxBatchTransactionPriorities(TestDataPropertyReaderAndWriter.getProperty("PriorityName"));

		/*
		 * test.siteConfigurationAction.clickTab("Distributor Accounts");
		 * Assert.assertTrue(
		 * test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive")
		 * .contains("false")); test.siteConfigurationAction.
		 * verifyUserIsAbleToSelectToggleButton("Show Inactive", true);
		 * Assert.assertTrue(
		 * test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive")
		 * .contains("true"));
		 * test.siteConfigurationAction.clickOnCheckboxForDistributorToMapWithFacility(
		 * TestDataPropertyReaderAndWriter.getProperty("DistributorName"));
		 * Assert.assertTrue(test.siteConfigurationAction.
		 * enterOnlyIntegerInAccountNumberFieldForSanity(
		 * TestDataPropertyReaderAndWriter.getProperty("DistributorName").trim(),
		 * "12345"));
		 */
		test.siteConfigurationAction.clickSaveButton();
	}
}
