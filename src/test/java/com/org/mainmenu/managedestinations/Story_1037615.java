package com.org.mainmenu.managedestinations;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1037615 extends BaseTest {

	 String destinationName, destinationCode, facilityName, streetName, city, zipCode, country, state, emailID, phone,
			fax;
	
	 @Test(priority = 1, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Active toggle on Edit destination screen.")
	public void Test01_1040882(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Active toggle on Edit destination screen.");
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnAddButtonToAddDestination();
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.General")));
		Assert.assertTrue(test.siteConfigurationAction.toggleIsActiveOrNot("activeFlag"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Add destination screen");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));
		Assert.assertFalse(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		test.siteConfigurationAction.selectFacilityForDestinationDropDown("facilityKey", TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
		destinationName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"descriptionText", "Destination" + System.currentTimeMillis());
		destinationCode = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"destinationCode", "Code" + System.currentTimeMillis());
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Contact")));

		test.siteConfigurationAction.clickOnSaveButtonForAddPrinterPopup();

		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Distributor_Accounts")));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Items")));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyTabOnAddDestinationPageIsEnableOrNot(getData("DestinationTab.Users")));

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Destinations");

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
		test.siteConfigurationAction.clickActiveToggle("Active");
		Assert.assertFalse(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is active in General Tab on Edit destination screen");
		test.siteConfigurationAction.clickActiveToggle("Active");
	}

	@Test(priority = 2, description = "VPLX:Manage Destinations-General:[UI]:User verifies Indicate required fields message on Edit destination screen.")
	public void Test02_1040885(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies Indicate required fields message on Edit destination screen.");
		test.siteConfigurationAction.verifyRequiredFieldIndicator(getData("DestinationTab.Label"));
	}

	@Test(priority = 3, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Facility dropdown in general tab on Edit destination screen.")
	public void Test03_1040899(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Facility dropdown in general tab on Edit destination screen.");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewDestination("facilityKey",
				TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());
	}

	@Test(priority = 4, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Name field in general tab on Edit destination screen.")
	public void Test04_1040907(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Name field in general tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("descriptionText");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("descriptionText"),
				"[ASSERTION FAILED]: Input Field serverPrinterName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("descriptionText"), 50,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("descriptionText"),
				"[ASSERTION FAILED]: input field Name is not mandatory");
	}

	@Test(priority = 5, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Destination Code field in general tab on Edit destination screen.")
	public void Test05_1040908(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Destination Code field in general tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("destinationCode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("destinationCode"),
				"[ASSERTION FAILED]: Input Field serverPrinterName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("destinationCode"), 40,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 40");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("destinationCode"),
				"[ASSERTION FAILED]: input field Destination Code is not mandatory");
	}

	@Test(priority = 6, description = "VPLX: Manage Destinations-General:[UI]: User verifies the Pharmacy License field in general tab on Edit destination screen.")
	public void Test06_1040931(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Destinations-General:[UI]: User verifies the Rx License field in general tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("rxLicenseID");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("rxLicenseID"),
				"[ASSERTION FAILED]: Input Field serverPrinterName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 20");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("rxLicenseID"),
				"[ASSERTION FAILED]: input field Pharmacy License is mandatory");
	}

	@Test(priority = 7, description = "VPLX:Manage Destinations-General:[UI]:User verifies the DEA License field in general tab on Edit destination screen.")
	public void Test07_1040939(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the DEA License field in general tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("deaLicenseID");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("deaLicenseID"),
				"[ASSERTION FAILED]: Input Field serverPrinterName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field serverPrinterName is not 20");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseID"),
				"[ASSERTION FAILED]: input field Pharmacy License is mandatory");
	}

	@Test(priority = 8, description = "VPLX:Manage Destinations-General:[UI]:User clicks on 'Destination Participates in Package Sharing' so package sharing facility dropdown is enabled on Edit destination screen.")
	public void Test08_1040949(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User clicks on 'Destination Participates in Package Sharing' so package sharing facility dropdown is enabled on Edit destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
	}

	@Test(priority = 9, description = "VPLX:Manage Destinations-General:[UI]:User clicks on 'Destination Participates in Package Sharing' so Enable Quantity Rounding checkbox is enabled on Edit destination screen.")
	public void Test09_1040952(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User clicks on 'Destination Participates in Package Sharing' so Enable Quantity Rounding checkbox is enabled on Edit destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		Assert.assertFalse(
				test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("admQuantiyRoundingFlag"));
	}

	@Test(priority = 10, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Package sharing facility field in general tab on Edit destination screen.")
	public void Test10_1040953(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Package sharing facility field in general tab on Edit destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		List<String> Facilities = test.siteConfigurationAction
				.verifyOptionsForDropdownOnAddPrinterPopup("packageSharingFacilityKey");
		test.siteConfigurationAction.verifyRecordNotPresentInDropDownOptions(Facilities,TestDataPropertyReaderAndWriter.getProperty("FacilityName").trim());

	}

	@Test(priority = 11, description = "VPLX:Manage Destinations-General:[UI]: User verifies when 'Destination Participates in Package Sharing' is not selected so package sharing facility dropdown is disabled on Edit destination screen.")
	public void Test11_1040957(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]: User verifies when 'Destination Participates in Package Sharing' is not selected so package sharing facility dropdown is disabled on Edit destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));

	}

	@Test(priority = 12, description = "VPLX:Manage Destinations-General:[UI]:User verifies when 'Destination Participates in Package Sharing' is not selected so enable quantity rounding checkbox is disabled on Edit destination screen.")
	public void Test12_1040962(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies when 'Destination Participates in Package Sharing' is not selected so enable quantity rounding checkbox is disabled on Edit destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabled("packageSharingFacilityKey"));
		Assert.assertFalse(
				test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("admQuantiyRoundingFlag"));
	}

	@Test(priority = 13, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Verify Remote Order field in general tab on Edit destination screen.")
	public void Test13_1040969(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Verify Remote Order field in general tab on Edit destination screen.");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("invoiceEnabledFlag"));
	}

	@Test(priority = 14, description = "VPLX:Manage Destinations-General:[UI]:User clicks on Invoice enabled checkbox so cost center field is displayed on Edit destination screen.")
	public void Test14_1040975(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User clicks on Invoice enabled checkbox so cost center field is displayed on Edit destination screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("costCenterCode"));
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", true);
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("costCenterCode"));
	}

	@Test(priority = 15, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Mark up Type field in general tab on Edit destination screen.")
	public void Test15_1040986(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Mark up Type field in general tab on Edit destination screen.");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("markupAmount"));
	}

	@Test(priority = 16, description = "VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type None is selected on Edit destination screen.")
	public void Test16_1040988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type None is selected on Edit destination screen.");
		test.siteConfigurationAction.selectRadioOption("noneMarkupEnabledFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("markupAmount"));
	}

	@Test(priority = 17, description = "VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type Fixed is selected on Edit destination screen.")
	public void Test17_1040989(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type Fixed is selected on Edit destination screen.");
		test.siteConfigurationAction.selectRadioOption("fixedMarkupEnabledFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("markupAmount"));
	}

	@Test(priority = 18, description = "VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type Percent is selected on Edit destination screen.")
	public void Test18_1040990(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the markup textfield when MarkUp Type Percent is selected on Edit destination screen.");
		test.siteConfigurationAction.selectRadioOption("percentMarkupEnabledFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("markupAmount"));
		
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("invoiceEnabledFlag", false);
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsEnabledOrDisabled("costCenterCode"));
	}
	
	@Test(priority = 19, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Ignore ADC Critical Low field in general tab on Add destination screen.")
	public void Test19_1040229(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Ignore ADC Critical Low field in general tab on Add destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("admIgnoreCriticalLowFlag"));
	}

	@Test(priority = 20, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Ignore ADC Stock Out field in general tab on Add destination screen.")
	public void Test20_1040307(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Ignore ADC Stock Out field in general tab on Add destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("admIgnoreStockOutFlag"));
	}

	@Test(priority = 21, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Enable ADC Quantity Rounding field in general tab on Add destination screen.")
	public void Test21_1040308(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Enable ADC Quantity Rounding field in general tab on Add destination screen.");
		test.siteConfigurationAction.selectCheckboxCorresspondingToField("enablePackageSharingFlag", false);
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsEnabledOrDisabled("admQuantiyRoundingFlag"));
		

		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	
		test.landingPageActions.navigateToFeature("Destinations");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToDestinationName(destinationName);
		
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"),
				"[ASSERTION FAILED]: Toggle is inactive in General Tab on Edit destination screen");
	}


	@Test(priority = 22, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Street Address field in contact tab on Edit destination screen.")
	public void Test22_1040994(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Street Address field in contact tab on Edit destination screen.");
		
		test.siteConfigurationAction.clickTab("Contact");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("streetAddressText");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("streetAddressText"),
				"[ASSERTION FAILED]: Input Field streetAddressText is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText"), 120,
				"[ASSERTION FAILED]: Max Length for input field streetAddressText is not 120");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("streetAddressText"),
				"[ASSERTION FAILED]: input field Street Address is mandatory");

	}

	@Test(priority = 23, description = "VPLX:Manage Destinations-General:[UI]:User verifies the City field in contact tab on Edit destination screen.")
	public void Test23_1040995(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the City field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("cityName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("cityName"),
				"[ASSERTION FAILED]: Input Field cityName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field cityName is not 50");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("cityName"),
				"[ASSERTION FAILED]: input field City Name is mandatory");
	}

	@Test(priority = 24, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Zip Code field in contact tab on Edit destination screen.")
	public void Test24_1040996(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Zip Code field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("postalCode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("postalCode"),
				"[ASSERTION FAILED]: Input Field  zip Code is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field zip Code is not 20");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("postalCode"),
				"[ASSERTION FAILED]: input field Zip Code is mandatory");
	}

	@Test(priority = 25, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Country field in contact tab on Edit destination screen.")
	public void Test25_1040997(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Country field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("countryName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("countryName"),
				"[ASSERTION FAILED]: Input Field countryName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName"), 50,
				"[ASSERTION FAILED]: Max Length for input field countryName is not 50");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("countryName"),
				"[ASSERTION FAILED]: input field Country Name is mandatory");
	}

	@Test(priority = 26, description = "VPLX:Manage Destinations-General:[UI]:User verifies the State field in contact tab on Edit destination screen.")
	public void Test26_1040999(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the State field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("stateName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("stateName"),
				"[ASSERTION FAILED]: Input Field stateName is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName"), 50,
				"[ASSERTION FAILED]: Max Length for input field stateName is not 50");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("stateName"),
				"[ASSERTION FAILED]: input field State Name is mandatory");
	}

	@Test(priority = 27, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Email field in contact tab on Edit destination screen.")
	public void Test27_1041000(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Email field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactEmailAddressValue"),
				"[ASSERTION FAILED]: Input Field Email is not blank by default");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue"), 50,
				"[ASSERTION FAILED]: Max Length for input field Email is not 50");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"),
				"[ASSERTION FAILED]: input field Email is mandatory");
	}

	@Test(priority = 28, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Phone Number field in contact tab on Edit destination screen.")
	public void Test28_1041002(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Phone Number field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactPhoneNumberText");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactPhoneNumberText"),
				"[ASSERTION FAILED]: Input Field Phone Number is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactPhoneNumberText"),
				15, "[ASSERTION FAILED]: Max Length for input field Phone Number is not 10");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactPhoneNumberText"),
				"[ASSERTION FAILED]: input field Phone Number is mandatory");
	}

	@Test(priority = 29, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Fax field in contact tab on Edit destination screen.")
	public void Test29_1041003(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Fax field in contact tab on Edit destination screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("vendorContactFaxNumberText");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("vendorContactFaxNumberText"),
				"[ASSERTION FAILED]: Input Field Fax is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactFaxNumberText"),
				15, "[ASSERTION FAILED]: Max Length for input field Fax is not 10");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactFaxNumberText"),
				"[ASSERTION FAILED]: input field Fax Number is mandatory");
	}
	
	@Test(priority = 30, description = "VPLX:Manage Destinations-General:[UI]:User verifies the placeholder value of textboxes in contact tab on Add destination screen.")
	public void Test30_1040968(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the placeholder value of textboxes in contact tab on Add destination screen.");
		Assert.assertEquals(test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("streetAddressText"),
				"Street Address", "[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("cityName"), "City",
				"[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("postalCode"), "Zip Code",
				"[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("countryName"),
				"Country", "[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("stateName"), "State",
				"[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("vendorContactEmailAddressValue"),
				"Email", "[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("vendorContactPhoneNumberText"),
				"Phone Number", "[ASSERTION FAILED]: input field Fax Number Format is not supported");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyPlaceHolderValueForAnInputField("vendorContactFaxNumberText"),
				"Fax Number", "[ASSERTION FAILED]: input field Fax Number Format is not supported");
	}

	@Test(priority = 31, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Email field in contact tab on Add destination screen.")
	public void Test31_1040974(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Email field in contact tab on Add destination screen.");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("InvalidEmail.invalid1"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyEmailFormatValidationMessageAppearOrNot(getData("ErrorMessage.Email")));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("InvalidEmail.invalid2"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyEmailFormatValidationMessageAppearOrNot(getData("ErrorMessage.Email")));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("InvalidEmail.invalid3"));
		Assert.assertTrue(test.siteConfigurationAction
				.verifyEmailFormatValidationMessageAppearOrNot(getData("ErrorMessage.Email")));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("vendorContactEmailAddressValue",
				getData("InvalidEmail.valid"));

	}

	@Test(priority = 32, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Phone field in contact tab on Add destination screen.")
	public void Test32_1045922(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Phone field in contact tab on Add destination screen.");
		test.siteConfigurationAction.verifyErrorMessageForPhoneNumberAndFaxFieldIsDisplayedTillThe10thNumberIsEntered(
				"vendorContactPhoneNumberText", "phone number");
	}

	@Test(priority = 33, description = "VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Fax field in contact tab on Add destination screen.")
	public void Test33_1045814(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Destinations-General:[UI]:User verifies the Error message for Fax field in contact tab on Add destination screen.");
		test.siteConfigurationAction.verifyErrorMessageForPhoneNumberAndFaxFieldIsDisplayedTillThe10thNumberIsEntered(
				"vendorContactFaxNumberText", "fax number");
	}

}
