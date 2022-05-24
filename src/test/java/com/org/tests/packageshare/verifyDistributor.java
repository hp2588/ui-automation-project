package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class verifyDistributor extends BaseTest {

	String  dataEnteredName1, dataEnteredName2, new_data;
	
	@Test(priority = 1, description = "VPLX: Package Sharing:[UI]:User creates a facility then distributor is created automatically")
	public void Test01_1108862_1109036_1110723_1113465(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Package Sharing:[UI]:User creates a facility then distributor is created automatically");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		dataEnteredName1="Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding").trim();
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName1, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName1, new_data);
		dataEnteredName2="Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving").trim();
		test.supportDataActions.enterSearchTermInSearchField(dataEnteredName1, "search");
		new_data = test.supportDataActions.getColumnFirstData("1");
		Assert.assertEquals(dataEnteredName1, new_data);
		
	}

	@Test(priority = 2, description = "VPLX: Package Sharing:[UI]:Distributor is created on creation of new facility in defined way")
	public void Test02_1108909_1108914_1109035_1109109_1125074(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Package Sharing:[UI]:User creates a facility then distributor is created automatically");
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		Assert.assertFalse(test.supportDataActions.verifyTextboxIsEnabledOrDisabled("shortCode"));
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderShortCodeUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderDateUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderMedClassUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("internalFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("costEachesFlag"));
		Assert.assertTrue(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-manualDistributorFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderISANameUsedFlag"));
		test.supportDataActions.verifyDistributorDisabledField();
		test.supportDataActions.clickAddButtonOnDistributor("cancel");


		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		Assert.assertFalse(test.supportDataActions.verifyTextboxIsEnabledOrDisabled("shortCode"));
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderShortCodeUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderDateUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderMedClassUsedFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("internalFlag"));
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("costEachesFlag"));
		Assert.assertTrue(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-manualDistributorFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderISANameUsedFlag"));
		test.supportDataActions.verifyDistributorDisabledField();
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 3, description = "VPLX: Package Sharing:[UI]:User is able to enable and disable the fields")
	public void Test03_1109030(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Package Sharing:[UI]:User is able to enable and disable the fields");
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderShortCodeUsedFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderDateUsedFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderMedClassUsedFlag"));
		test.supportDataActions.clickAddButtonOnDistributor("cancel");

		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.uncheckRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderShortCodeUsedFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderDateUsedFlag"));
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderMedClassUsedFlag"));
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 4, description = "VPLX: Package Sharing:[UI]:User is able to manually capture the Distributor website link on edit")
	public void Test04_1109034(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Package Sharing:[UI]:User is able to manually capture the Distributor website link on edit");
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactWebsiteAddressValue",
				"https://www.samplewebsite.com");
		test.supportDataActions.clickAddButtonOnDistributor("save");

		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactWebsiteAddressValue",
				"https://www.samplewebsite.com");
		test.supportDataActions.clickAddButtonOnDistributor("save");
	}

	@Test(priority = 5, description = "VPLX: Package Sharing:[UI]:User verifies Contact tab details are fetching from facility screen")
	public void Test05_1109064(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Package Sharing:[UI]:User verifies Contact tab details are fetching from facility screen");
		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameProviding")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");

		test.supportDataActions.verifyAndClickContactTab("Contact");
		test.supportDataActions.verifyTextboxOnDistributor("streetAddressText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactEmailAddressValue");
		test.supportDataActions.verifyTextboxOnDistributor("cityName");
		test.supportDataActions.verifyTextboxOnDistributor("postalCode");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactPhoneNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactFaxNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("countryName");
		test.supportDataActions.verifyTextboxOnDistributor("stateName");
		test.supportDataActions.enterValueOnAddDistributorPage("streetAddressText", "Noida");
		test.supportDataActions.enterValueOnAddDistributorPage("cityName", "NoidaCity");
		test.supportDataActions.clickAddButtonOnDistributor("save");

		test.siteConfigurationAction.enterSearchTermInSearchField(("Pkg-Sharing_" + TestDataPropertyReaderAndWriter.getProperty("FacilityNameReceiving")).trim(), "search");
		test.supportDataActions.clickAddButtonOnDistributor("edit");
		test.supportDataActions.verifyAndClickContactTab("Contact");
		test.supportDataActions.verifyTextboxOnDistributor("streetAddressText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactEmailAddressValue");
		test.supportDataActions.verifyTextboxOnDistributor("cityName");
		test.supportDataActions.verifyTextboxOnDistributor("postalCode");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactPhoneNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactFaxNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("countryName");
		test.supportDataActions.verifyTextboxOnDistributor("stateName");
		test.supportDataActions.enterValueOnAddDistributorPage("streetAddressText", "Noida");
		test.supportDataActions.enterValueOnAddDistributorPage("cityName", "NoidaCity");
		test.supportDataActions.clickAddButtonOnDistributor("save");
	}

}
