package com.org.tests.BDSanityFeatureChecklist;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class FacilityFeature extends BaseTest { 
	String facilityCode, facilityName;

	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "edaitExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String printerName,serverPrinterName;


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
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

	}

	@Test(priority = 1, description = "VPLX: Specific Facility Settings : [UI] Verify the Add functionality for Facility is available under the facility setting.")
	public void Test01_1044904(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add functionality for Facility is available under the facility setting.");
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
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
	}
	
	
	@Test(priority = 2, description = "VPLX: Specific Facility Settings : [UI] Verify the Add screen for Facility show all the fields related to facility.")
	public void Test02_1044921(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add screen for Facility show all the fields related to facility.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("rxLicenseID");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("deaLicenseId");
		Assert.assertTrue(test.siteConfigurationAction
				.verifyDropDownIsEnabledOrDisabledForFacility("pharmacyInformationSystemKey"));
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Specific Facility Settings : [UI] Verify the Facility Code field while adding a  facility.")
	public void Test03_1044932(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Add functionality for Facility is available under the facility setting.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityCode");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("facilityCode"),
				"[ASSERTION FAILED]: Input Field Facility Code is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field facility Code is not 20");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityCode"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode", "a");
		test.siteConfigurationAction.clearInputBox("facilityCode");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFacilityCode"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode", "a");
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific Facility Settings : [UI] Verify the Facility Name field while adding a  facility.")
	public void Test04_1044947(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Facility Name field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("facilityName");
		Assert.assertTrue(test.siteConfigurationAction.verifyInputFieldIsBlank("facilityName"),
				"[ASSERTION FAILED]: Input Field Facility Name is not blank by default");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field facility Code is not 50");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("facilityName"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName", "a");
		test.siteConfigurationAction.clearInputBox("facilityName");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFacilityName"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName", "a");
	}
	
	
	@Test(priority = 5, description = "VPLX: Specific Facility Settings : [UI] Verify the Pharmacy field while adding a  facility.")
	public void Test05_1044971(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Pharmacy field while adding a  facility.");
		Assert.assertTrue(test.siteConfigurationAction.verifyFieldIsMandatory("pharmacyInformationSystemKey"),
				"[ASSERTION FAILED]: input field is not mandatory");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction
				.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankExternalSystemName"));
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", external_System);
	}
	
	
	@Test(priority = 6, description = "VPLX: Specific Facility Settings : [UI] Verify the Rx License field while adding a  facility.")
	public void Test06_1045044(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Rx License field while adding a  facility.");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("rxLicenseID"),
				"[ASSERTION FAILED]: input field rx LicenseId is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("rxLicenseID"), 20,
				"[ASSERTION FAILED]: Max Length for input field rx LicenseId is not 20");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.rxLicenseId"));

	}
	
	@Test(priority = 7, description = "VPLX: Specific Facility Settings : [UI] Verify the DEA License field while adding a  facility.")
	public void Test07_1045045(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the DEA License field while adding a  facility.");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseId"),
				"[ASSERTION FAILED]: input field Dea License Id  is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseId"), 20,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 20");		
	}
	
	
	@Test(priority = 8, description = "VPLX: Specific Facility Settings : [UI] Verify the Time Zone field while adding a  facility.")
	public void Test08_1045878(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Time Zone field while adding a  facility.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyDropDownIsEnabledOrDisabledForFacility("timeZoneID"));
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", "Select");
		test.siteConfigurationAction.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlanktimeZoneId"));
	}
	
	
	@Test(priority = 9, description = "VPLX: Specific Facility Settings : [UI] Verify the Preferred Contact field while adding a  facility.")
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
	}
	
	
	@Test(priority = 10, description = "VPLX: Specific Facility Settings : [UI] Verify the Email field while adding a  facility.")
	public void Test10_1045142(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Email field while adding a  facility.");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("vendorContactEmailAddressValue"),
				"[ASSERTION FAILED]: input field EmailId  is mandatory");
		Assert.assertEquals(
				test.siteConfigurationAction.verifyMaxLengthOfAnInputField("vendorContactEmailAddressValue"), 50,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 50");
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
	

	@Test(priority = 11, description = "VPLX: Specific Facility Settings : [UI] Verify the Fax field while adding a  facility.")
	public void Test11_1045896(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Fax field while adding a  facility.");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.InvalidFaxNumber"));
		test.siteConfigurationAction.verifyErrorMessageForBlankFieldForFacility(getData("Facility.ErrorMsg_BlankFaxNumber"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber3"));
	}
	
	
	@Test(priority = 12, description = "VPLX: Specific Facility Settings : [UI] Verify the State field while adding a  facility.")
	public void Test12_1045941(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the State field while adding a  facility.");

		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("stateName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("stateName"),
				"[ASSERTION FAILED]: input field stateName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("stateName"), 50,
				"[ASSERTION FAILED]: Max Length for input field stateName is not 50");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("stateName", getData("Facility.stateName"));

	}
	
	
	@Test(priority = 13, description = "VPLX: Specific Facility Settings : [UI] Verify the ZIP Code field while adding a  facility.")
	public void Test13_1045945(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the ZIP Code field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("postalCode");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("postalCode"),
				"[ASSERTION FAILED]: input field postalCode is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("postalCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field postalCode is not 20");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
	}
	
	
	@Test(priority = 14, description = "VPLX: Specific Facility Settings : [UI] Verify the Country field while adding a  facility.")
	public void Test14_1045949(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Country field while adding a  facility.");
		
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("countryName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("countryName"),
				"[ASSERTION FAILED]: input field countryName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("countryName"), 50,
				"[ASSERTION FAILED]: Max Length for input field countryName is not 50");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("countryName",
				getData("Facility.countryName"));
	}
	
	
	@Test(priority = 15, description = "VPLX: Specific Facility Settings : [UI] Verify the City field while adding a  facility.")
	public void Test15_1045937(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the City field while adding a  facility.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("cityName");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("cityName"),
				"[ASSERTION FAILED]: input field cityName is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("cityName"), 50,
				"[ASSERTION FAILED]: Max Length for input field streetAddress is not 50");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("cityName", getData("Facility.cityName"));
	}
	
	
	@Test(priority = 16, description = "VPLX: Specific Facility Settings : [UI] Verify the Address field while adding a  facility.")
	public void Test16_1045928(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Address field while adding a  facility.");
		test.siteConfigurationAction.verifyInputFieldOnAddNewFacility("streetAddressText");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("streetAddressText"), 120,
				"[ASSERTION FAILED]: Max Length for input field streetAddress is not 120");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("streetAddressText",
				getData("Facility.streetAddress"));
	}
	
	
	@Test(priority = 17, description = "VPLX: Specific Facility Settings : [UI] Verify the Phone field while adding a  facility.")
	public void Test17_1045883(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Phone field while adding a  facility.");

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber2"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactPhoneNumberText",
				getData("Facility.PhoneNumber3"));
	}
	
	
	@Test(priority = 18, description = "VPLX:Specific facility Settings: [UI]: A new Facility is created when user enters the required fields in tabs and clicks on Save button")
	public void Test18_1129687(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings: [UI]: A new Facility is created when user enters the required fields in tabs and clicks on Save button");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey",	external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName,"Edit Facility");
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.verifyTabOnAddDestinationPageIsEnableOrNot("Contact");
		test.siteConfigurationAction.verifyPageTitleContains("Contact Information");
	}
	
	
	@Test(priority = 19, description = "VPLX: Specific Facility Settings : [UI] Verify the Transaction Queue options are visible under the Facility settings")
	public void Test19_1046302(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Transaction Queue options are visible under the Facility settings");
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyCheckboxForTQFields("verifyQuantityFlag",
				getData("Facilities_TransactionQueueOptions.Option1"));
	}
	
	
	@Test(priority = 20, description = "VPLX: Specific Facility Settings : [UI] Verify the Transaction options which are added via Configuration are visible under the Facility settings")
	public void Test20_1046303(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Transaction options which are added via Configuration are visible under the Facility settings");
		
		test.siteConfigurationAction.verifyCheckboxForTQFields("verifyQuantityFlag",
				getData("Facilities_TransactionQueueOptions.Option1"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("prepositionISAFlag",
				getData("Facilities_TransactionQueueOptions.Option2"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("requestRestockDestinationFlag",
				getData("Facilities_TransactionQueueOptions.Option3"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("requireHoldReasonFlag",
				getData("Facilities_TransactionQueueOptions.Option4"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("requestRestockLotInfoFlag",
				getData("Facilities_TransactionQueueOptions.Option5"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("printLabelOnQuantityChangeFlag",
				getData("Facilities_TransactionQueueOptions.Option6"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("admIgnoreStockOutFlag",
				getData("Facilities_TransactionQueueOptions.Option7"));
	}
	
	
	@Test(priority = 21, description = "VPLX:Specific Facility Settings:[UI]:Validate that Distributor Name is available while adding/editing a facility.")
	public void Test21_1054937(Method method) {
		ExtentTestManager.startTest(method.getName(),"VPLX:Specific Facility Settings:[UI]:Validate that Distributor Name is available while adding/editing a facility.");
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Distributor Name"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
	}
	
	
	@Test(priority = 22, description = "VPLX:Specific Facility Settings:[UI]:Validate that Account numbers are available while adding/editing a facility.")
	public void Test22_1054955(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Account numbers are available while adding/editing a facility.");
		Assert.assertTrue(test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Account Number"));
	}
	
	
	@Test(priority = 23, description = "VPLX:Specific facility Settings:[UI]:User clicks on add button so date is added in the list of disabled dates")
	public void Test23_1055555(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User clicks on add button so date is added in the list of disabled dates");
		
		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.clickCycleCountCalendar();
		String date = test.siteConfigurationAction.getCurrentMonth_Year();
		int day = test.siteConfigurationAction.getCurrentDate();
		test.siteConfigurationAction.ClickCurrentDate(date);
		test.siteConfigurationAction.clickAddButton();
		String clickedDate = test.siteConfigurationAction.getCurrentMonth_Year();
		Assert.assertFalse(test.siteConfigurationAction.verifyDisableDate(clickedDate));
	}
	
	
	@Test(priority = 24, description = "VPLX: Manage Printers: [UI]:[Integration]: When a printer is added/updated, the Printer name gets populated in Settings tab on Facilities Page")
	public void Test24_1106887(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Manage Printers: [UI]:[Integration]: When a printer is added/updated, the Printer name gets populated in Settings tab on Facilities Page");

		test.landingPageActions.navigateToMenu("Manin Menu");
		test.landingPageActions.navigateToFeature("Printers");
		test.siteConfigurationAction.clickOnAddButtonToAddPrinter();
		test.siteConfigurationAction.hardWaitForChromeBrowser(6);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("facilityModelKey",1);
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("serverPrinterName");
		serverPrinterName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"serverPrinterName", "Automation-UI-ServerPrinter" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyInputFieldOnAddNewPrinterPopup("printerName");
		printerName = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup("printerName",
				"Automation-UI-Printer" + System.currentTimeMillis());
		test.siteConfigurationAction.verifyDropDownFieldOnAddNewPrinterPopup("printerModelKey");
		test.siteConfigurationAction.selectValueFromDropDownByIndex("printerModelKey", 1);
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipAddress",
				DateUtil.getRandomIPAddress());
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("ipPort",
				getData("PrinterDetails.PortNumber"));

		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaWidth",
				getData("PrinterDetails.PaperWidth"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewPrinterPopup("printableAreaHeight",
				getData("PrinterDetails.PaperHeight"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Manin Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName,"Edit Facility");
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.selectValueForDropDown("manualRestockPrinterKey",printerName);
		test.siteConfigurationAction.selectValueForDropDown("receivingPrinterKey",printerName);
		test.siteConfigurationAction.selectValueForDropDown("exceptionPrinterKey",printerName);
		test.siteConfigurationAction.selectValueForDropDown("binLabelPrinterKey",printerName);
		test.siteConfigurationAction.clickSaveButton();
		
	}
	

}
