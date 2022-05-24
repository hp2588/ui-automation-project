package com.org.tests.tq.multipartbatchpicks;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class IntFacilityTest extends BaseTest {

	String facilityCode, facilityName;
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

	@Test(priority = 1, description = "VPLX:Add Faciity")
	public void Test01_AddFacility(Method method) {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		facilityCode = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code" + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"Fac" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCode", facilityCode);
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityCode", facilityCode);
	}
}
