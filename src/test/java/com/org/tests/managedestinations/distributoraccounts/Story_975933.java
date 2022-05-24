package com.org.tests.managedestinations.distributoraccounts;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.lang.reflect.Method;

import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975933 extends BaseTest {

	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;
	String[] columnHeaders = { "Distributor Name", "Account Number" };

	@Test(priority = 1, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] Set of distributor accounts with their account Numbers are visible for Respective destination under the Distributor Accounts tab on Add destination screen.")
	public void Test01_1048597(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] Set of distributor accounts with their account Numbers are visible for Respective destination under the Distributor Accounts tab on Add destination screen.");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		test.supportDataActions.selectValueFromDropdownByIndex("FacilityDropdown", 1);
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag",true);
		test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"costCenterCode", "CC" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts"));
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		// test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
		test.siteConfigurationAction.verifyAccountNumberFieldIsBlank();
		test.siteConfigurationAction.verifyAccountNumberFieldIsDisabled();

	}

	@Test(priority = 2, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User clicks on Distributor Accounts tab so listed Account numbers are disabled by default.")
	public void Test02_1048659(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User clicks on Distributor Accounts tab so listed Account numbers are disabled by default.");
		test.siteConfigurationAction.verifyAccountNumberFieldIsDisabled();

	}

	@Test(priority = 3, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User clicks on checkbox so Account number textbox is enabled while aading distributor accounts information.")
	public void Test03_1048665(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User clicks on checkbox so Account number textbox is enabled while aading distributor accounts information.");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");

	}

	@Test(priority = 5, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the column Distributor Name under Distributor Accounts tab.")
	public void Test05_1048691(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the column Distributor Name under Distributor Accounts tab.");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeader("Distributor Name"),
				"[ASSERTION FAILED]: column Distributor Name and Account Number are  not available");
	}

	@Test(priority = 6, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI]: User verifies the checkbox in front of Distributor name & Account Number column under Distributor Accounts tab.")
	public void Test06_1048697(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI]: User verifies the checkbox in front of Distributor name & Account Number column under Distributor Accounts tab");

		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");

	}

	@Test(priority = 7, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies error message for Account number textbox on Add Destination screen.")
	public void Test07_1048701(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies error message for Account number textbox on Add Destination screen.");
		test.siteConfigurationAction.verifySetOfDistributorAccordingToDestination(
				getData("DistributorAccountsColumnNumber.DistributorName"));
		//test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");

		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "+");
		//test.siteConfigurationAction.verifyErrorMessageForBlankDistributorField("Please enter a valid");


	}

	@Test(priority = 8, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the combined list of distributors when Show All toggle is on.")
	public void Test08_1048706(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the combined list of distributors when Show All toggle is on.");
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		int active_count = test.siteConfigurationAction.verifyDistributorAccountListIsNonEmpty();
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		int total_count = test.siteConfigurationAction.verifyDistributorAccountListIsNonEmpty();
		Assert.assertTrue(total_count >= active_count);
	}

	@Test(priority = 9, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies Cancel the changes after searching is performed on Add destination screen.")
	public void Test09_1048714(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies Cancel the changes after searching is performed on Add destination screen.");

		test.siteConfigurationAction.verifyToggleIsInActive("toggle");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-1",
				getData("DistributorDetails.AccountNumber"));
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickPopup("Yes");

	}

	@Test(priority = 11, description = "VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the list of all distributor accounts when Show Inactive toggle button is on.")
	public void Test11_1048728(Method method) throws InterruptedException {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts  : [UI] User verifies the list of all distributor accounts when Show Inactive toggle button is on.");
			
		test.siteConfigurationAction.hardWaitForChromeBrowser(20); //due to slow load time
		test.siteConfigurationAction.clickButton("edit-0");
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyPageTitleContains("Distributor Accounts");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.verifyDistributorAccountListIsNonEmpty();
	}

	@Test(priority = 12, description = "VPLX: Manage Destinations -Distributor Accounts:[UI]: User verifies the column Account Number column under Distributor Accounts tab.")
	public void Test12_1048733(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations -Distributor Accounts:[UI]: User verifies the column Account Number column under Distributor Accounts tab.");

		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeader("Distributor Name"),
				"[ASSERTION FAILED]: column Distributor Name and Account Number are  not available");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeader("Account Number"),
				"[ASSERTION FAILED]: column Distributor Name and Account Number are  not available");

	}

}
