package com.org.tests.unableToOrderItems;


import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;


public class Add_Facility extends BaseTest  {
	
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
				Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");

			}

			@Test(priority = 1, description = "VPLX:Add Faciity")
			public void Test01_AddFacility_1121628_1121626(Method method) {
				test.landingPageActions.navigateToFeature("Facilities");
				test.siteConfigurationAction.clickOnAddButtonToAddFacility();
				test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
						"Code" + System.currentTimeMillis());
				

				Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"), 20,
						"[ASSERTION FAILED]: Max Length for input field facilityCode is not 20");
				
				
				facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
						"Fac" + System.currentTimeMillis());
				test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
				test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName_UnableToOrder").trim());
				test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
				test.siteConfigurationAction.clickSaveButton();
				TestDataPropertyReaderAndWriter.setProperty("FacilityName_UnableToOrder", facilityName);
			}
			
	}

			
		

		

