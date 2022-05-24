package com.org.tests.packageshare;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Create_Facility_PkgShr_Data extends BaseTest {

	String facilityCode, facilityName, facilityNameReceiving, facilityNameProviding, facilityCodeReceiving, facilityCodeProviding;
	String rxLicenseId, deaLicenseId, faxNumber;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };

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

	@Test(priority = 2, description = "VPLX:Add Faciity")
	public void Test02_AddProvidingFacility_1113464(Method method) {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCodeProviding = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"PCode" + System.currentTimeMillis());
		facilityNameProviding = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"FacProv" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		/*test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		test.siteConfigurationAction.clickSaveButton();*/
		
		TestDataPropertyReaderAndWriter.setProperty("FacilityNameProviding", facilityNameProviding);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCodeProviding", facilityCodeProviding);
	}
	
	@Test(priority = 3, description = "VPLX:Add Faciity")
	public void Test03_AddReceivingFacility_1060239(Method method) {
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCodeReceiving = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"RCode" + System.currentTimeMillis());
		facilityNameReceiving = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"FacRec" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		/*test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		test.siteConfigurationAction.clickSaveButton();*/
		
		TestDataPropertyReaderAndWriter.setProperty("FacilityNameReceiving", facilityNameReceiving);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCodeReceiving", facilityCodeReceiving);
	}

}
