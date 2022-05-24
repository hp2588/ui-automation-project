package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975745 extends BaseTest {
	String facilityCode, facilityName, deaLicense;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "edaitExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" }; 
	
	@Override
	@BeforeClass
	public void Open_Browser_Window() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.loginPageAction.selectValueFromDropDown("Tenant", getData("IDM.tenantName"));
		test.loginPageAction.clickNextButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
	}
	
	
	@Test(priority = 1, description = "VPLX: Specific Facility Settings: [UI]: To verify that Add Facility button "
			+ "is displayed when user is redirected to Facility Management page")
	public void Test01_1044904(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings: [UI]: To verify that Add Facility button is displayed "
				+ "when user is redirected to Facility Management page");
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType"));
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));
		
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "Automation-UI-ExternalSystem" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		// test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		
	}
	
	
	@Test(priority = 2, description = "VPLX: Specific Facility Settings : [UI] To verify that Add Facility screen shows all the fields related to facility")
	public void Test02_1044921(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add screen for Facility show all the fields related to facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("rxLicenseID");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("deaLicenseID");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyPageTitleContains("Contact Information");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"), 
				"[ASSERTION FAILED]: input field EmailId  is mandatory");
		test.siteConfigurationAction.verifyInputField("vendorContactPhoneNumberText");
		test.siteConfigurationAction.verifyInputField("vendorContactFaxNumberText");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("postalCode");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("preferredContactMethodKey");	
	}
	
	
	@Test(priority = 3, description = "VPLX: Specific Facility Settings : [UI]: To verify that Facility Code field "
			+ "is displayed under general tab during add Facility")
	public void Test03_1044932(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add functionality for Facility is available under the facility setting.");
		
		test.siteConfigurationAction.clickTab("General");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("facilityCode"),
				"[ASSERTION FAILED]: Input Field Facility Code is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"),20,
				"[ASSERTION FAILED]: Max Length for input field facility Code is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode", DateUtil.getAlphaNumericString(21), 20);
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityCode"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode", "a");
		test.siteConfigurationAction.clearInputBox("facilityCode");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFacilityCode"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode", "a");	
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific Facility Settings: [UI]: To verify that Facility Name "
			+ "field is displayed as a mandatory field during add Facility")
	public void Test04_1044947(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Facility Name field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("facilityName"),
				"[ASSERTION FAILED]: Input Field Facility Name is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field facility Code is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityName", DateUtil.getAlphaNumericString(51),50);
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName", "a");
		test.siteConfigurationAction.clearInputBox("facilityName");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFacilityName"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName", "a");
	}
	
	
	@Test(priority = 5, description = "VPLX: Specific Facility Settings: [UI]: To verify that Pharmacy information "
			+ "system field is displayed as a mandatory field during add Facility")
	public void Test05_1044971(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Pharmacy field while adding a  facility.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("pharmacyInformationSystemKey"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankExternalSystemName"));
		String data =test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		Assert.assertEquals(data,external_System);	
	}
	
	
	@Test(priority = 6, description = "VPLX: Specific Facility Settings: [UI]: To verify that Pharmacy License field "
			+ "is displayed under general tab during add Facility")
	public void Test06_1045044(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Rx License field while adding a  facility.");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("rxLicenseID"),
				"[ASSERTION FAILED]: input field rx LicenseId is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field rx LicenseId is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID", DateUtil.getAlphaNumericString(21),20);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",	getData("Facility.RxLicenseId"));
	}
	
	
	@Test(priority = 7, description = "VPLX: Specific Facility Settings : [UI]:To verify that DEA License field "
			+ "is displayed under general tab during add Facility")
	public void Test07_1045045(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the DEA License field while adding a  facility.");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseId"),
				"[ASSERTION FAILED]: input field Dea License Id  is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID", DateUtil.getAlphaNumericString(21), 20);
	}
	
	
	@Test(priority = 8, description = "VPLX: Specific Facility Settings : [UI]: To verify that the Time Zone field "
			+ "is displayed under General tab during add Facility")
	public void Test08_1045878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Time Zone field while adding a  facility.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", "Select");
		test.siteConfigurationAction.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlanktimeZoneId"));
	}
	
	
	@Test(priority = 9, description = "VPLX: Specific Facility Settings: [UI]: To verify that Preferred Contact field "
			+ "is displayed as a non mandatory field during add Facility")
	public void Test09_1044988(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Preferred Contact field while adding a  facility.");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyPageTitleContains("Contact Information");
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewFacility("preferredContactMethodKey");
		Assert.assertFalse(test.siteConfigurationAction.verifydropdownsNotMandatoryOnItemscreen("preferredContactMethodKey"));
		test.siteConfigurationAction.selectValueForDropDown("preferredContactMethodKey","Phone");
		test.siteConfigurationAction.verifyFieldIsMandatory("vendorContactPhoneNumberText");
	}
	
	
	@Test(priority = 10, description = "VPLX: Specific Facility Settings: [UI]: To verify that Email field "
			+ "is displayed under contact tab during add Facility")
	public void Test10_1045142(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Email field while adding a  facility.");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"),
				"[ASSERTION FAILED]: input field EmailId  is mandatory");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue"), 50,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue", DateUtil.getAlphaNumericString(51),50);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.Invalidemail"));
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankEmail"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.Invalidemail2"));
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankEmail"));
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.email"));
	}
	

	@Test(priority = 11, description = "VPLX: Specific Facility Settings : [UI]: To verify that the Phone field "
			+ "is displayed under Contact tab during add Facility")
	public void Test11_1045883(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Phone field while adding a  facility.");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.FaxNumber2"));
		test.siteConfigurationAction
		.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankPhone"));
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber3"));
	}
	
	
	@Test(priority = 12, description = "VPLX: Specific Facility Settings : [UI] To verify that the Fax field "
			+ "is displayed under Contact tab during add Facility")
	public void Test12_1045896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Fax field while adding a  facility.");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.InvalidFaxNumber"));
		test.siteConfigurationAction.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFaxNumber"));
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber3"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
	}
	

	@Test(priority = 13, description = "VPLX: Specific Facility Settings : [UI]: To verify that the Street Address field "
			+ "is displayed under Contact tab during add Facility")
	public void Test13_1045928(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Address field while adding a  facility.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("streetAddressText");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText"), 120,
				"[ASSERTION FAILED]: Max Length for input field streetAddress is not 120");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText", DateUtil.getAlphaNumericString(121),120);

		 test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("streetAddressText",
				getData("Facility.streetAddress"));
	}
	

	@Test(priority = 14, description = "VPLX: Specific Facility Settings : [UI]: To verify that the City field "
			+ "is displayed under Contact tab during add Facility")
	public void Test14_1045937(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the City field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("cityName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("cityName"),
				"[ASSERTION FAILED]: input field cityName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field streetAddress is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName", DateUtil.getAlphaNumericString(51), 50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("cityName", getData("Facility.cityName"));
	}
	
	
	@Test(priority = 15, description = "VPLX: Specific Facility Settings : [UI: To verify that the State field "
			+ "is displayed under Contact tab while adding Facility")
	public void Test15_1045941(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the State field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("stateName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("stateName"),
				"[ASSERTION FAILED]: input field stateName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName"), 50,
				"[ASSERTION FAILED]: Max Length for input field stateName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName", DateUtil.getAlphaNumericString(51),50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("stateName", getData("Facility.stateName"));
	}
	

	@Test(priority = 16, description = "VPLX: Specific Facility Settings : [UI] To verify that the ZIP Code field "
			+ "is displayed under Contact tab while adding Facility")
	public void Test16_1045945(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the ZIP Code field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("postalCode");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("postalCode"),
				"[ASSERTION FAILED]: input field postalCode is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field postalCode is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode", DateUtil.getAlphaNumericString(21),20);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode", getData("Facility.postalCode"));	
	}
	

	@Test(priority = 17, description = "VPLX: Specific Facility Settings : [UI]: To verify that the Country field "
			+ "is displayed under Contact tab while adding Facility")
	public void Test17_1045949(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Country field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("countryName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("countryName"),
				"[ASSERTION FAILED]: input field countryName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName"), 50,
				"[ASSERTION FAILED]: Max Length for input field countryName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName", DateUtil.getAlphaNumericString(51),50);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("countryName", getData("Facility.countryName"));
	}
	
	
	@Test(priority = 18, description = "VPLX:Specific Facility Settings: [UI]: To verify that Facility code "
			+ "accepts space while creating new facility when login from support user")
	public void Test18_1151504(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings: [UI]: Facility code accepts space while creating new facility when login from support user");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Facility"+ " "+ "Code"+ System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
	}
	
	
	@Test(priority = 19, description = "VPLX:Specific Facility Settings: [UI]: To verify that Facility code "
			+ "accepts without space while creating new facility when login from support user")
	public void Test19_1151505(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings: [UI]: Facility code accepts without space while creating new facility when login from support user");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Facility"+ System.currentTimeMillis());
		facilityName= test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
	}
	
	
	@Test(priority = 20, description = "VPLX:[Add/Edit Facilities]:[UI]: To verify that user is able to add facility "
			+ "on entering alphanumeric & Special value (upto 20 characters) in optional DEA License field")
	public void Test20_1151710(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Add/Edit Facilities]:[UI]: User able to add facility on entering enter alphanumeric "
				+ "& Special value (upto 20 characters) in optional DEA License field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseID"),
				"[ASSERTION FAILED]: input field Dea License Id  is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 20");
		String deaLicense= test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("deaLicenseID",
		"DEAL$%#@1234");
		
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Facility"+ System.currentTimeMillis());
		facilityName =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 1);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.siteConfigurationAction.verifyFieldText("deaLicenseID", deaLicense);	
	}
	
	
	@Test(priority = 21, description = "VPLX:[Add/Edit Facilities]:[UI]: To verify that User is able to edit facility "
			+ "on modifying the entered value in the DEA License field.")
	public void Test21_1151713(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility on modifying the entered value in the DEA License field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		String deaLicense=test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("deaLicenseID",
					getData("Facility.DeaLicenseId") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.siteConfigurationAction.verifyFieldText("deaLicenseID", deaLicense);	
	}
	
	
	@Test(priority = 22, description = "VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility on deleting the entered value in the DEA License field.")
	public void Test22_1151715(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility on deleting the entered value in the DEA License field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.supportDataActions.clearInputBox("deaLicenseID");
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.siteConfigurationAction.verifyFieldText("deaLicenseID", "");	
	}
	
	
	@Test(priority = 23, description = "VPLX:[Add/Edit Facilities]:[UI]: User able to create facility "
			+ "without entering value in DEA licence field")
	public void Test23_1151716(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility on deleting the entered value in the DEA License field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		//test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Facility"+ System.currentTimeMillis());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
	}
	
	
	@Test(priority = 24, description = "VPLX:Facilities:[UI]: To verify that user is able to enter "
			+ "Alphanumeric characters in facility code field")
	public void Test24_1151444(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Facilities:[UI]:User able to enter special characters in facility code field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		//test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code"+System.currentTimeMillis() + "@#");
		test.siteConfigurationAction
			.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFacilityCode"));
		test.siteConfigurationAction.clearInputBox("facilityCode");
		
		facilityCode =test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code"+System.currentTimeMillis());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		
	}
	
	
}

