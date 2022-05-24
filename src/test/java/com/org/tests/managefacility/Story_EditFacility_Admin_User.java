package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;
import com.org.smoketests.Map_Facility_To_User;

public class Story_EditFacility_Admin_User extends BaseTest {
	String facilityCode, facilityName;
	Map_Facility_To_User token;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String external_System;
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "edaitExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };


	
	@Test(priority = 1, description = "VPLX:[Add/Edit Facilities]:[UI]: User able to add facility "
			+ "on entering enter alphanumeric & Special value (upto 20 characters) "
			+ "in optional DEA License field.")
	public void Test01_1152040(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:[Add/Edit Facilities]:[UI]: User able to add facility on entering enter alphanumeric & Special value (upto 20 characters) in optional DEA License field.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		facilityName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");
		Assert.assertFalse(test.siteConfigurationAction.verifyFieldIsNotMandatory("deaLicenseId"),
				"[ASSERTION FAILED]: input field Dea License Id  is mandatory");
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("deaLicenseId"), 20,
				"[ASSERTION FAILED]: Max Length for input field Dea LicenseId is not 20");
		String deaLicense= test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("deaLicenseId",
					getData("Facility.DeaLicenseId")+"$%#@"+System.currentTimeMillis());
		 test.siteConfigurationAction.clickSaveButton();
		 
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");
		test.siteConfigurationAction.verifyFieldText("deaLicenseId", deaLicense);	
	
	}
	
	
	@Test(priority = 2, description = "VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility "
			+ "on deleting the entered value in the DEA License field when login with admin use")
	public void Test02_1152041(Method method) {
		ExtentTestManager.startTest(method.getName(), 
				"VPLX:[Add/Edit Facilities]:[UI]: User able to edit facility on deleting the entered value "
				+ "in the DEA License field when login with admin use");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		facilityName = test.siteConfigurationAction.getColumnFirstData("1");
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");
		test.supportDataActions.clearInputBox("deaLicenseId");
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName(facilityName, "Edit Facility");
		test.siteConfigurationAction.verifyFieldText("deaLicenseId", "");
	}
	
}
