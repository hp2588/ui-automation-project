package com.org.tests.managedestinations.distributoraccounts;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1039578 extends BaseTest {
	String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;

	
	@Test(priority = 1, description = "VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor.")
	public void Test01_1047883(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor.");
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
		test.siteConfigurationAction.verifyAndClickInactiveToggle();
	//	Assert.assertFalse(test.siteConfigurationAction.verifyDistributorAccountListIsNonEmpty().isEmpty());
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "123456789");

	}

	
	@Test(priority = 2, description = "VPLX:Manage Destinations - Distributor Accounts : [UI] Editing of Account Number without checking the Check box for the Distributor.")
	public void Test02_1047889(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations - Distributor Accounts : [UI] Editing of Account Number without checking the Check box for the Distributor.");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.verifyAccountNumberFieldIsDisabled();

	}


	@Test(priority = 6, description = "VPLX: Manage Destinations - Distributor Accounts : [UI] Show only Active Distributor when Show Inactive toggle button is off")
	public void Test06_1048143(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts : [UI] Show only Active Distributor when Show Inactive toggle button is off");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "123456789");
		test.siteConfigurationAction.clickToggleButton("false", "toggle");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-0");

	}


	
	@Test(priority = 7, description = "VPLX: Manage Destinations - Distributor Accounts: [UI] Show  All Distributor when Show Inactive toggle button is on.")
	public void Test07_1048147(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts: [UI] Show  All Distributor when Show Inactive toggle button is on.");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		Assert.assertFalse(test.siteConfigurationAction.verifyDistributorAccountListIsEmpty());
		//test.siteConfigurationAction.verifyAccountNumberFieldIsDisabled();

	}


	@Test(priority = 8, description = "VPLX: Manage Destinations - Distributor Accounts : [UI] Preserving the last edited account number for the distributor.")
	public void Test08_1048151(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts : [UI] Preserving the last edited account number for the distributor.");
		test.siteConfigurationAction.clickToggleButton("true", "toggle");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-1", "+");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1");
		test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1");
		test.siteConfigurationAction.verifyAccountNumberFieldIsEnabled("vendorAccountNumberText-1");


	}

	
	@Test(priority = 9, description = "VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor with 20 alphanumeric char")
	public void Test09_1048162(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor with 20 alphanumeric char");
		//test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-1");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-1", "123");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthForAccountNumberField("vendorAccountNumberText-1"), 20,
				"[ASSERTION FAILED]: Max Length for input field  is not 20");
	}

	@Test(priority = 10, description = "VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor beyond 20 alphanumeric char")
	public void Test10_1048165(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations - Distributor Accounts : [UI] Editing of Account Number related to respective distributor beyond 20 alphanumeric char");
		//test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "12345678909865544411");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthForAccountNumberField("vendorAccountNumberText-0"), 20,
				"[ASSERTION FAILED]: Max Length for input field  is not 20");
	}

	
	@Test(priority = 11, description = "VPLX:Manage Destinations - Distributor Accounts: [UI] Cancel the changes after perform the editing in Account number.")
	public void Test11_1048169(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations - Distributor Accounts: [UI] Cancel the changes after perform the editing in Account number.");
		//test.siteConfigurationAction.clickCheckboxForDistributorAccount("activeFlag-0");
		test.siteConfigurationAction.EnterRandomValueInAccountNumberField("vendorAccountNumberText-0", "12345678909865544411");
		test.siteConfigurationAction.clickButton("cancel");
		test.siteConfigurationAction.clickButton("primary");


	}
}
