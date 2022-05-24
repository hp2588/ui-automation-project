package com.org.tests.mainmenu.distributors;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1000183 extends BaseTest {

	String firstData, dataEnteredName, dataEnteredCode;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:User verifies the Edit Distributor Screen.")
	public void Test01_1034254(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Edit Distributor Screen.");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.Carousel"));
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickEditButtonOnDistributor(dataEnteredName);
		//test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(dataEnteredName, "Edit Distributor");
	}

	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:User clicks on cancel button so pop up is closed on Edit Distributor screen.")
	public void Test02_1034259(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on cancel button so pop up is closed on Edit Distributor screen.");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
	}

	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:User verifies the Save button on Edit Distributor screen.")
	public void Test03_1034284(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Save button on Edit Distributor screen.");
		
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickEditButtonOnDistributor(dataEnteredName);
		test.supportDataActions.verifyAndClickContactTab("Contact");
		test.supportDataActions.enterValueOnAddDistributorPage("cityName", "NoidaCity");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
	}

	@Test(priority = 4, description = "VPLX:Manage Distributors:[UI]:User verifies the Active toggle on Edit Distributor screen.")
	public void Test04_1034301(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Active toggle on Edit Distributor screen.");
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickEditButtonOnDistributor(dataEnteredName);
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Edit Distributor Pop-up");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 5, description = "VPLX:Manage Distributors:[UI]:User verifies the functionality of Active toggle on Edit Distributor screen.")
	public void Test05_1034307(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the functionality of Active toggle on Edit Distributor screen.");
		test.siteConfigurationAction.enterSearchTermInSearchField(dataEnteredName, "search");
		test.supportDataActions.clickEditButtonOnDistributor(dataEnteredName);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		test.supportDataActions.clickToggleButton("false", getData("ToggleValue.GLAccount"));
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("false"));
		test.supportDataActions.clickToggleButton("true", getData("ToggleValue.GLAccount"));
	}

	@Test(priority = 6, description = "VPLX:Manage Distributors:[UI]:User verifies the Indicate required fields message on Edit Distributor page.")
	public void Test06_1034333(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Indicate required fields message on Edit Distributor page.");
		test.supportDataActions.verifyRequiredField("* Indicates required fields");
	}

	@Test(priority = 7, description = "VPLX:Manage Distributors:[UI]:User verifies the Name Field on Edit Distributor page.")
	public void Test07_1034340(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Name Field on Edit Distributor page.");
		test.supportDataActions.autoPopulatedFieldOnDistributor("descriptionText");
	}

	@Test(priority = 8, description = "VPLX:Manage Distributors:[UI]:User verifies the Distributor Code Field on Edit Distributor page.")
	public void Test08_1034343(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Distributor Code Field on Edit Distributor page.");
		test.supportDataActions.autoPopulatedFieldOnDistributor("shortCode");
	}

	@Test(priority = 9, description = "VPLX:Manage Distributors:[UI]:User verifies the Distributor website link Field on Edit Distributor page.")
	public void Test09_1034345(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Distributor website link Field on Edit Distributor page.");
		test.supportDataActions.autoPopulatedFieldOnDistributor("vendorContactWebsiteAddressValue");
	}

	@Test(priority = 10, description = "VPLX:Manage Distributors:[UI]:User clicks on Ordering tab so fields is displayed on Edit Distributor page.")
	public void Test10_1034934_1035022(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on Ordering tab so fields is displayed on Edit Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-manualDistributorFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-electronicDistributorFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.verifytextOnEditDistributor("Default Purchase Order Description");
	}

	@Test(priority = 11, description = "VPLX:Manage Distributors:[UI]:User clicks on Contact tab so fields is displayed on Edit Distributor page.")
	public void Test11_1034936_1034938_1034941_1034943_1034947_1034949_1034956_1034958(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on Contact tab so fields is displayed on Edit Distributor page.");
		test.supportDataActions.verifyAndClickContactTab("Contact");
		test.supportDataActions.verifyTextboxOnDistributor("streetAddressText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactEmailAddressValue");
		test.supportDataActions.verifyTextboxOnDistributor("cityName");
		test.supportDataActions.verifyTextboxOnDistributor("postalCode");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactPhoneNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("vendorContactFaxNumberText");
		test.supportDataActions.verifyTextboxOnDistributor("countryName");
		test.supportDataActions.verifyTextboxOnDistributor("stateName");
	}
	
	//TC needs to be updated

	/*@Test(priority = 12, description = "VPLX:Manage Distributors:[UI]:User verifies the Internal Facility field under ordering tab on Edit Distributor page.")
	public void Test12_1034976(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Internal Facility field under ordering tab on Edit Distributor page.");
		test.supportDataActions.verifyAndClickContactTab("Ordering");
		test.supportDataActions.verifyRadioButtonOnDistributor("internalFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("internalFlag"));
		test.supportDataActions.clickRadioButtonOnDistributor("internalFlag");
	}

	@Test(priority = 13, description = "VPLX:Manage Distributors:[UI]:User verifies the Package Sharing Provider facility field under ordering tab on Edit Distributor page.")
	public void Test13_1034974(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Package Sharing Provider facility field under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("facilityCode");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("facilityCode"));
	}*/

	@Test(priority = 14, description = "VPLX:Manage Distributors:[UI]:User verifies the cost per eaches field under ordering tab on Edit Distributor page.")
	public void Test14_1034984(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the cost per eaches field under ordering tab on Edit Distributor page.");
		test.supportDataActions.verifyAndClickContactTab("Ordering");
		test.supportDataActions.verifyRadioButtonOnDistributor("costEachesFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("costEachesFlag"));
	}

	@Test(priority = 15, description = "VPLX:Manage Distributors:[UI]:User verifies the Electronic Ordering radiobutton under ordering tab on Edit Distributor page.")
	public void Test15_1034980(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Electronic Ordering radiobutton under ordering tab on Edit Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-electronicDistributorFlag");
		Assert.assertFalse(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-electronicDistributorFlag"));
	}

	@Test(priority = 16, description = "VPLX:Manage Distributors:[UI]:User verifies the Manual Ordering radiobutton under ordering tab on Edit Distributor page.")
	public void Test16_1034983(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Manual Ordering radiobutton under ordering tab on Edit Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-manualDistributorFlag");
		Assert.assertTrue(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-manualDistributorFlag"));
	}
}
