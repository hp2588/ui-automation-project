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

public class Story_975746 extends BaseTest {
	String facilityName, rxLicenseId, deaLicenseId, faxNumber;
	List<String> preferredContactMethod = new ArrayList<String>(Arrays.asList("Select", "Fax", "Phone", "E-mail"));
	String external_System;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
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
	
	
	@Test(priority = 1, description = "VPLX:Specific facility Settings:[UI]: To verify that Edit facility screen "
			+ "is displayed when user clicks on facility name button on Facility Management page")
	public void Test01_1044910(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User clicks on Edit button on Facility Management page so Edit facility screen is displayed.");
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemSystemType",
				getData("ExternalSystem.SystemType"));
		test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				getData("ExternalSystem.TimeZone"));
		
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("streetAddressText",
				getData("Facility.streetAddress"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("cityName",
				getData("Facility.cityName"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("stateName", 
				getData("Facility.stateName"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("countryName",
				getData("Facility.countryName"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.email"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		
		Assert.assertEquals(test.siteConfigurationAction.clickFacilityEditLink(facilityName), facilityName);
	}
	
	
	@Test(priority = 2, description = "VPLX:Specific facility Settings:[UI]: To verify that User is able to see "
			+ "UI Components under General tab for the Edit Facility screen")
	public void Test02_1044920(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the fields under General tab for the Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("rxLicenseID");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("deaLicenseID");
		
		Assert.assertFalse(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
	}
	
	
	@Test(priority = 3, description = "VPLX:Specific facility Settings: [UI]: To verify that User able to see "
			+ "Facility code field under General tab while editing on Edit Facility screen.")
	public void Test03_1044989(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Facility code under General tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityCode"),
				"[ASSERTION FAILED]: Field facilityCode is not mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("facilityCode").isEmpty(),
				"[ASSERTION FAILED]: input field facilityCode is not auto populated");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field facility Code is not 20");
	}
	
	
	@Test(priority = 4, description = "VPLX:Specific facility Settings: [UI]: To verify that Name filed "
			+ "is displayed under General tab while editing Facility")
	public void Test04_1045361(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add functionality for Facility is available under the facility setting.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityName"),
				"[ASSERTION FAILED]: Field facilityName is not mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("facilityName").isEmpty(),
				"[ASSERTION FAILED]: input field facilityName is not auto populated");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field facilityName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityName", DateUtil.getAlphaNumericString(51),50);
	}
	
	
	@Test(priority = 5, description = "VPLX:Specific facility Settings: [UI]: To verify that External System "
			+ "is displayed under General tab while editing Facility")
	public void Test05_1045365(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field External System under General tab while editing on Edit Facility screen.");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey")); 
		// test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewDestination("pharmacyInformationSystemKey", getData("Facility.ExternalSystemName"));
		
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("pharmacyInformationSystemKey"),
				"[ASSERTION FAILED]: input field is not mandatory");																			
		Assert.assertFalse(
				test.siteConfigurationAction.getAllDataFromDropDown("pharmacyInformationSystemKey").isEmpty(),
				"[ASSERTION FAILED]: input field pharmacyInformationSystemKey is not empty");
	}
	

	@Test(priority = 6, description = "VPLX:Specific facility Settings: [UI]: To verify that the Timezone field "
			+ "is displayed under General tab while editing Facility")
	public void Test06_1045373(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Timezone under General tab while editing on Edit Facility screen.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewFacility("timeZoneID",
				getData("Facility.timeZoneId"));
		
		Assert.assertTrue(!test.siteConfigurationAction.getAllDataFromDropDown("timeZoneID").isEmpty(),
				"[ASSERTION FAILED]: input field timeZoneId is not empty");
	}

	
	@Test(priority = 7, description = "Test Case 1045374:VPLX:Specific facility Settings: [UI]: To verify "
			+ "that the Pharmacy License field is displayed under General tab while editing Facility")
	public void Test07_1045374(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Rx License under General tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("rxLicenseID");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("rxLicenseID").isEmpty(),
				"[ASSERTION FAILED]: input field rxLicenseId is not auto populated");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				("Facility.RxLicenseId") + System.currentTimeMillis());

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID", "@");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("rxLicenseID"),
				"[ASSERTION FAILED]: Field rxLicenseId is  mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field rxLicenseId is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID", DateUtil.getAlphaNumericString(21),20);
	}
	
	
	@Test(priority = 8, description = "VPLX:Specific facility Settings:[UI]:User verifies the field DEA License under General tab while editing on Edit Facility screen.")
	public void Test08_1045375(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field DEA License under General tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("deaLicenseID");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseID"),
				"[ASSERTION FAILED]: Field deaLicenseId is  mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field deaLicenseId is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseID", DateUtil.getAlphaNumericString(21),20);
		
	}
	
	
	@Test(priority = 9, description = "VPLX:Specific facility Settings:[UI]:To verify that the User verifies the "
			+ "field Street Address under Contact tab while editing on Edit Facility screen.")
	public void Test09_1045376(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Street Address under Contact tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("streetAddressText");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("streetAddressText").isEmpty(),
				"[ASSERTION FAILED]: input field streetAddressText is not auto populated");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("streetAddressText"),
				"[ASSERTION FAILED]: Field streetAddressText is  mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText"), 120,
				"[ASSERTION FAILED]: Max Length for input field streetAddress is not 120");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText", DateUtil.getAlphaNumericString(121),120);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("streetAddressText", getData("Facility.streetAddress"));
		
	}
	
	
	@Test(priority = 10, description = "VPLX:Specific facility Settings:[UI]: To verify that the "
			+ "City field is displayed under Contact tab while editing Facility")
	public void Test10_1046909(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field City under Contact tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("cityName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("cityName"),
				"[ASSERTION FAILED]: Field cityName is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("cityName").isEmpty(),
				"[ASSERTION FAILED]: input field cityName is not auto populated");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field cityName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName", DateUtil.getAlphaNumericString(51),50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("cityName", getData("Facility.cityName"));	
	}
	
	
	@Test(priority = 11, description = "VPLX:Specific facility Settings:[UI]: To verify that the "
			+ "State field is displayed under Contact tab while editing Facility")
	public void Test11_1046910(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field State under Contact tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("stateName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("stateName"),
				"[ASSERTION FAILED]: Field stateName is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("stateName").isEmpty(),
				"[ASSERTION FAILED]: input field stateName is not auto populated");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName"), 50,
				"[ASSERTION FAILED]: Max Length for input field stateName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName", DateUtil.getAlphaNumericString(51),50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("stateName", getData("Facility.stateName"));
	}
	
	
	@Test(priority = 12, description = "VPLX: Specific facility Settings:[UI]: To verify that the "
			+ "Country field is displayed under Contact tab while editing Facility.")
	public void Test12_1046913(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Country under Contact tab while editing on Edit Facility screen.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("countryName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("countryName"),
				"[ASSERTION FAILED]: Field countryName is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("countryName").isEmpty(),
				"[ASSERTION FAILED]: input field countryName is not auto populated");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName"), 50,
				"[ASSERTION FAILED]: Max Length for input field countryName is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName", DateUtil.getAlphaNumericString(51),50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("countryName",
				getData("Facility.countryName"));
	}
	
	
	@Test(priority = 13, description = "VPLX:Specific facility Settings:[UI]: To verify that the Email field "
			+ "is displayed under Contact tab while editing Facility.")
	public void Test13_1046919(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]: Email field is displayed under Contact tab while editing Facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("vendorContactEmailAddressValue");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"),
				"[ASSERTION FAILED]: Field vendorContactEmailAddressValue is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("vendorContactEmailAddressValue").isEmpty(),
				"[ASSERTION FAILED]: input field vendorContactEmailAddressValue is not auto populated");
		
		Assert.assertEquals(
				test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue"), 50,
				"[ASSERTION FAILED]: Max Length for input field vendorContactEmailAddressValue is not 50");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue", 
				DateUtil.getAlphaNumericString(51),50);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.email"));
	}
	
	
	@Test(priority = 14, description = "VPLX:Specific facility Settings:[UI]: To verify that the Phone Number field"
			+ " is displayed under Contact tab while editing on Facility.")
	public void Test14_1047686(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Phone Number under Contact tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("vendorContactPhoneNumberText");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactPhoneNumberText"),
				"[ASSERTION FAILED]: Field vendorContactPhoneNumberText is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("vendorContactPhoneNumberText").isEmpty(),
				"[ASSERTION FAILED]: input field vendorContactPhoneNumberText is not auto populated");
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactPhoneNumberText"),
				15, "[ASSERTION FAILED]: Max Length for input field vendorContactPhoneNumberText is not 15");
		//test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactPhoneNumberText", DateUtil.generateRandomDigits(16),15);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber3"));
	}
	
	
	@Test(priority = 15, description = "VPLX:Specific facility Settings:[UI]: To verify that the Fax field"
			+ " is displayed under Contact tab while editing Facility.")
	public void Test15_1047702(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field Fax under Contact tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("vendorContactFaxNumberText");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("vendorContactFaxNumberText").isEmpty(),
				"[ASSERTION FAILED]: input field vendorContactFaxNumberText is not auto populated");
		
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactFaxNumberText"),
				"[ASSERTION FAILED]: Field vendorContactFaxNumberText is  mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactFaxNumberText"),
				15, "[ASSERTION FAILED]: Max Length for input field vendorContactFaxNumberText is not 15");
		//test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactFaxNumberText", DateUtil.generateRandomDigits(16),15);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.InvalidFaxNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber3"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
	}
	

	@Test(priority = 16, description = "VPLX:Specific facility Settings:[UI]: To verify that the ZipCode "
			+ "is displayed under Contact tab while editing Facility")
	public void Test16_1047715(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the field ZipCode under Contact tab while editing on Edit Facility screen.");
		
		// test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("postalCode");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("postalCode"),
				"[ASSERTION FAILED]: Field postalCode is  mandatory");
		Assert.assertTrue(!test.siteConfigurationAction.verifyInputFieldIsAutopopulated("postalCode").isEmpty(),
				"[ASSERTION FAILED]: input field postalCode is not auto populated");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode") + System.currentTimeMillis());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode", "@");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankpostalCode"));
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field postalCode is not 20");
		test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode", DateUtil.getAlphaNumericString(21),20);
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
	}
	
	
	@Test(priority = 17, description = "VPLX: Specific facility Settings: [UI]: To verify that the "
			+ "Preferred Contact Method field is displayed under Contact tab while editing Facility.")
	public void Test17_1047732(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific facility Settings:[UI]:User verifies the Preferred Contact Method field under Contact tab while editing on Edit Facility screen.");
		// test.siteConfigurationAction.clickTab("Contact");
		System.out.println("EXPECTED LIST" + preferredContactMethod);
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("preferredContactMethodKey"),
				"[ASSERTION FAILED]: Field postalCode is  mandatory");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewFacility("preferredContactMethodKey", "Select");
		
		test.siteConfigurationAction.selectValueForDropDown("preferredContactMethodKey", "E-mail");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue", getData("Facility.email"));	
	}
	

	@Test(priority = 18, description = "VPLX: Specific facility Settings:[UI]:To verify that the User "
			+ "is able to see Contact tab is enabled after entering mandatory fileds "
			+ "under General tab while editing on Edit Facility screen.")
	public void Test18_1047736(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific facility Settings:[UI]:User is able to see Contact tab is enabled after "
				+ "entering mandatory fileds under General tab while editing on Edit Facility screen.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey",external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		
		Assert.assertTrue(test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("Contact"));
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyPageTitleContains("Contact Information");
	}
	

	@Test(priority = 19, description = "VPLX:Specific facility Settings:[UI]: To verify that the Error message "
			+ "is displayed when user enters incorrect format for Fax under Contact tab while editing Facility")
	public void Test19_1047709(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Error message is displayed when user enters incorrect format for Fax under Contact tab while editing Facility");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.InvalidFaxNumber"));
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFaxNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
	}
	
	
	@Test(priority = 20, description = "VPLX:Specific facility Settings:[UI]:  Error message is displayed for Email field under Contact tab while editing Facility.")
	public void Test20_1047709(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:  Error message is displayed for Email field under Contact tab while editing Facility.");
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue", 
				getData("Facility.email") + "1");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankEmail"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactEmailAddressValue",
				getData("Facility.email"));
	}
	

	@Test(priority = 21, description = "VPLX:Specific facility Settings: [UI]: To verify that a new Facility "
			+ "is created when user enters the required fields in tabs and clicks on Save button")
	public void Test21_1129687(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings: [UI]: A new Facility is created when user enters the required fields in tabs and clicks on Save button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		
		test.siteConfigurationAction.clickTab("Contact");
		Assert.assertTrue(test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("Contact"));
		test.siteConfigurationAction.verifyPageTitleContains("Contact Information");	
	}
	
	
	@Test(priority = 21, description = "VPLX:Specific facility Settings: [UI]: To verify that the User "
			+ "verifies the error message for PIS System under General tab while editing on Edit Facility screen.")
	public void Test22_1045368(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings: [UI]: User verifies the error message for PIS System under General tab while editing on Edit Facility screen.");
		
		test.siteConfigurationAction.clickTab("General");
		Assert.assertFalse(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey"));
	}
	
	
	@Test(priority = 23, description = "VPLX:Specific facility Settings:[UI]: To verify that "
			+ "Visual design for the Edit Facility screen is correct")
	public void Test23_1044915(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]: Visual design for the Edit Facility screen is correct");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("rxLicenseID");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("deaLicenseID");
		
		Assert.assertFalse(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
	}
	
	
}
