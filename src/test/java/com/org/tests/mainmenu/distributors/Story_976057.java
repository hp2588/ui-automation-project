package com.org.tests.mainmenu.distributors;

import static com.org.automation.utils.YamlReader.getData;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_976057 extends BaseTest {
	String firstData, dataEnteredName, dataEnteredCode, placeHolder;

	@Test(priority = 1, description = "VPLX:Manage Distributors:[UI]:User verifies the Inline error message for mandatory fields.")
	public void Test01_1032902(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Inline error message for mandatory fields.");
		test.landingPageActions.navigateToFeature("Distributors");
		test.supportDataActions.verifyLabelIsPresent("Distributors");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.supportDataActions.verifyRadioButtonIsEnabledOrDisabled("save");
	}

	@Test(priority = 2, description = "VPLX:Manage Distributors:[UI]:User clicks on cancel button so pop up is closed on Add Distributor page")
	public void Test02_1032950_1034163(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on cancel button so pop up is closed on Add Distributor page");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
	}

	@Test(priority = 3, description = "VPLX:Manage Distributors:[UI]:User verifies the Add button on Add Distributor page.")
	public void Test03_1034137(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Add button on Add Distributor page.");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		dataEnteredName = test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		dataEnteredCode = test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		test.supportDataActions.clickAddButtonOnDistributor("save");
		Assert.assertTrue(test.supportDataActions.verifyLabelIsPresent("Distributors"));
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"));
		firstData = test.supportDataActions.firstDataOnDistributor("1");
		Assert.assertEquals(dataEnteredName, firstData);
	}

	@Test(priority = 4, description = "VPLX:Manage Distributors:[UI]:User verifies the Active toggle on Add Distributor page.")
	public void Test04_1032940(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Active toggle on Add Distributor page.");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		Assert.assertTrue(test.supportDataActions.verifyToggleButtonIsPresent(getData("ToggleValue.GLAccount")),
				"[ASSERTION FAILED]: Toggle Button is Not Present on Add Distributor Pop-up");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
	}

	@Test(priority = 5, description = "VPLX:Manage Distributors:[UI]:User verifies the Indicate required fields message on Add Distributor page.")
	public void Test05_1032947(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Indicate required fields message on Add Distributor page.");
		test.supportDataActions.verifyRequiredField("* Indicates required fields");
	}

	@Test(priority = 6, description = "VPLX:Manage Distributors:[UI]:User verifies the Name field on Add Distributor page.")
	public void Test06_1032947(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Name field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", "sc1@#");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("descriptionText");
		Assert.assertEquals(value, 40);
	}

	@Test(priority = 7, description = "VPLX:Manage Distributors:[UI]:User verifies the Distributor Code field on Add Distributor page.")
	public void Test07_1032964(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Distributor Code field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("shortCode", "sc1@#");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("shortCode");
		Assert.assertEquals(value, 40);
	}

	@Test(priority = 8, description = "VPLX:Manage Distributors:[UI]:User verifies the Distributor website link field on Add Distributor page.")
	public void Test08_1032970(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Distributor website link field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactWebsiteAddressValue",
				"https://www.samplewebsite.com");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("vendorContactWebsiteAddressValue");
		Assert.assertEquals(value, 50);
	}

	@Test(priority = 9, description = "VPLX:Manage Distributors:[UI]:User clicks on Ordering tab so fields is displayed on Add Distributor page.")
	public void Test09_1032976(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on Ordering tab so fields is displayed on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderMedClassUsedFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-manualDistributorFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-electronicDistributorFlag");
		test.supportDataActions.verifyRadioButtonOnDistributor("isActive");
	}

	@Test(priority = 10, description = "VPLX:Manage Distributors:[UI]:User clicks on Contact tab so fields is displayed on Add Distributor page..")
	public void Test10_1032980(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User clicks on Contact tab so fields is displayed on Add Distributor page.");
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

	@Test(priority = 11, description = "VPLX:Manage Distributors:[UI]:User verifies the Street Address field Under Contact tab on Add Distributor page.")
	public void Test11_1032985(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Street Address field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("streetAddressText", "Noida");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("streetAddressText");
		Assert.assertEquals(value, 120);
	}

	@Test(priority = 12, description = "VPLX:Manage Distributors:[UI]:User verifies the City field Under Contact tab on Add Distributor page.")
	public void Test12_1032986(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the City field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("cityName", "NoidaCity");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("cityName");
		Assert.assertEquals(value, 50);
	}

	@Test(priority = 13, description = "VPLX:Manage Distributors:[UI]:User verifies the Email field Under Contact tab on Add Distributor page.")
	public void Test13_1032996(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Email field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactEmailAddressValue", "bd@globallogic.com");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("vendorContactEmailAddressValue");
		Assert.assertEquals(value, 50);
	}

	@Test(priority = 14, description = "VPLX:Manage Distributors:[UI]:User verifies the State field Under Contact tab on Add Distributor page.")
	public void Test14_1032990(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the State field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("stateName", "UP");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("stateName");
		Assert.assertEquals(value, 50);
	}

	@Test(priority = 15, description = "VPLX:Manage Distributors:[UI]:User verifies the ZipCode field Under Contact tab on Add Distributor page.")
	public void Test15_1032991(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the ZipCode field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("postalCode", "201301");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("postalCode");
		Assert.assertEquals(value, 20);
	}

	@Test(priority = 16, description = "VPLX:Manage Distributors:[UI]:User verifies the Fax field Under Contact tab on Add Distributor page.")
	public void Test16_1032995(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Fax field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactFaxNumberText", "1234567890");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("vendorContactFaxNumberText");
		Assert.assertEquals(value, 15);
	}

	@Test(priority = 17, description = "VPLX:Manage Distributors:[UI]:User verifies the Phone Number field Under Contact tab on Add Distributor page.")
	public void Test17_1032993(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Phone Number field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactPhoneNumberText", "1234567890");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("vendorContactPhoneNumberText");
		Assert.assertEquals(value, 15);
	}

	@Test(priority = 18, description = "VPLX:Manage Distributors:[UI]:User verifies the Country field Under Contact tab on Add Distributor page.")
	public void Test18_1041321(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Country field Under Contact tab on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("countryName", "India");
		int value = test.supportDataActions.verifyMaxLengthOfAnSearchField("countryName");
		Assert.assertEquals(value, 50);
	}

	@Test(priority = 19, description = "VPLX:Manage Distributors:[UI]:User verifies the Error message for Fax field on Add Distributor page.")
	public void Test19_1033360(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Error message for Fax field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactFaxNumberText", "1230");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Please enter a valid fax number");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactFaxNumberText", "1234567890");
	}

	@Test(priority = 20, description = "VPLX:Manage Distributors:[UI]:User verifies the Error message for Email field on Add Distributor page.")
	public void Test20_1033363(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Error message for Email field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactEmailAddressValue", "https462376");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Please enter a valid email");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactEmailAddressValue", "bd@globallogic.com");
	}

	@Test(priority = 21, description = "VPLX:Manage Distributors:[UI]:User verifies the Error message for Phone Number field on Add Distributor page.")
	public void Test21_1033357(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Error message for Phone Number field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("vendorContactPhoneNumberText", "1234");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName("Please enter a valid phone number");
	}

	@Test(priority = 22, description = "VPLX:Manage Distributors:[UI]:User verifies the Include distributor Code checkbox under ordering tab on Add Distributor page.")
	public void Test22_1033449_1033386(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Include distributor Code checkbox under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyAndClickContactTab("Ordering");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderShortCodeUsedFlag"));
	}

	@Test(priority = 23, description = "VPLX:Manage Distributors:[UI]:User verifies the Include Current date checkbox under ordering tab on Add Distributor page.")
	public void Test23_1033457(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Include Current date checkbox under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderDateUsedFlag");
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderDateUsedFlag"));
	}

	@Test(priority = 24, description = "VPLX:Manage Distributors:[UI]:User verifies the Include ISA Name checkbox under ordering tab on Add Distributor page.")
	public void Test24_1033467(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Include ISA Name checkbox under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderISANameUsedFlag");
		Assert.assertTrue(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderISANameUsedFlag"));
	}

	@Test(priority = 25, description = "VPLX:Manage Distributors:[UI]:User verifies the Include Med Class checkbox under ordering tab on Add Distributor page.")
	public void Test25_1033480(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Include Med Class checkbox under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("purchaseOrderShortCodeUsedFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("purchaseOrderMedClassUsedFlag"));
	}

	//TC needs to be updated
	
	/*@Test(priority = 26, description = "VPLX:Manage Distributors:[UI]:User verifies the Internal Facility field under ordering tab on Add Distributor page.")
	public void Test26_1033508(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Internal Facility field under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("internalFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("internalFlag"));
		test.supportDataActions.clickRadioButtonOnDistributor("internalFlag");
	}*/

	@Test(priority = 27, description = "VPLX:Manage Distributors:[UI]:User verifies the Package Sharing Provider facility field under ordering tab on Add Distributor page.")
	public void Test27_1033496(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Package Sharing Provider facility field under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("facilityCode");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("facilityCode"));
	}

	@Test(priority = 28, description = "VPLX:Manage Distributors:[UI]:User verifies the cost per eaches field under ordering tab on Add Distributor page.")
	public void Test28_1034104(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the cost per eaches field under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("costEachesFlag");
		Assert.assertFalse(test.supportDataActions.verifyCheckboxIsChecked("costEachesFlag"));
	}

	@Test(priority = 29, description = "VPLX:Manage Distributors:[UI]:User verifies the Electronic Ordering radiobutton under ordering tab on Add Distributor page.")
	public void Test29_1034117(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Electronic Ordering radiobutton under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-electronicDistributorFlag");
		Assert.assertFalse(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-electronicDistributorFlag"));
	}

	@Test(priority = 30, description = "VPLX:Manage Distributors:[UI]:User verifies the Include Med Class checkbox under ordering tab on Add Distributor page.")
	public void Test30_1033480(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Include Med Class checkbox under ordering tab on Add Distributor page.");
		test.supportDataActions.verifyRadioButtonOnDistributor("orderingRadioGroupValue-manualDistributorFlag");
		Assert.assertTrue(
				test.supportDataActions.verifyCheckboxIsChecked("orderingRadioGroupValue-manualDistributorFlag"));
	}

	@Test(priority = 31, description = "VPLX:Manage Distributors:[UI]:User verifies the Add Distributor Pop up.")
	public void Test31_1034131(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Add Distributor Pop up.");
		test.supportDataActions.verifyPopUpTextOnDistributor("Add Distributor");
		test.supportDataActions.clickAddButtonOnDistributor("cancel");
	}

	@Test(priority = 32, description = "VPLX:Manage Distributors:[UI]: Add Distributors - Place Holder Text is appearing properly when add distributor form is opened")
	public void Test32_1048007(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]: Add Distributors - Place Holder Text is appearing properly when add distributor form is opened");
		test.supportDataActions.clickAddButtonOnDistributor("add");
		test.supportDataActions.verifyAndClickContactTab("Contact");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("descriptionText");
		Assert.assertEquals(placeHolder, "Name");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("shortCode");
		Assert.assertEquals(placeHolder, "Distributor Code");
		placeHolder = test.siteConfigurationAction
				.verifyPlaceHolderValueForAnInputField("vendorContactWebsiteAddressValue");
		Assert.assertEquals(placeHolder, "https://www.samplewebsite.com/");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("streetAddressText");
		Assert.assertEquals(placeHolder, "Street Address");
		placeHolder = test.siteConfigurationAction
				.verifyPlaceHolderValueForAnInputField("vendorContactEmailAddressValue");
		Assert.assertEquals(placeHolder, "Email");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("cityName");
		Assert.assertEquals(placeHolder, "City");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("postalCode");
		Assert.assertEquals(placeHolder, "Zip Code");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("stateName");
		Assert.assertEquals(placeHolder, "State");
		placeHolder = test.siteConfigurationAction
				.verifyPlaceHolderValueForAnInputField("vendorContactPhoneNumberText");
		Assert.assertEquals(placeHolder, "Phone Number");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("vendorContactFaxNumberText");
		Assert.assertEquals(placeHolder, "Fax Number");
		placeHolder = test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("countryName");
		Assert.assertEquals(placeHolder, "Country");
	}

	@Test(priority = 33, description = "VPLX:Manage Distributors:[UI]:User verifies the Error message for Distributor Code field on Add Distributor page.")
	public void Test33_1032930_1032911_1110855(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Distributors:[UI]:User verifies the Error message for Distributor Code field on Add Distributor page.");
		test.supportDataActions.enterValueOnAddDistributorPage("descriptionText", dataEnteredName);
		test.supportDataActions.enterDuplicateValueOnAddDistributorPage("shortCode", dataEnteredCode);
		test.supportDataActions.clickAddButtonOnDistributor("save");
		test.supportDataActions.verifyErrorMessageForAlreadyExistingName(
				"This distributor code already exists. Please provide a unique distributor code .");
	}
}
