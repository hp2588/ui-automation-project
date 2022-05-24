package com.org.tests.transactionqueue;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982265 extends BaseTest{
 String facilityName;
 
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
 
	@Test(priority=1, description= "VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level")
	public void Test01_1067791(Method method)
	
	{
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Forced Cycle Count:[UI]: Cycle count interval is set at facility level");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");

		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		 test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey",1);
	     test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));

		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		test.siteConfigurationAction.clickSaveButton();


 
	} 
	
}
