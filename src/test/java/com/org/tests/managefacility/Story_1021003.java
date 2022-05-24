package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_1021003 extends BaseTest {
	
	String facilityName;
	String clickedDate;
	
	
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
	
	
	@Test(priority = 1, description = "VPLX:Specific facility Settings:[UI]: To verify that "
			+ "user is able to select date from the calendar in cycle count tab")
	public void Test01_1055516(Method method) throws Throwable {

		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 1);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();

		test.siteConfigurationAction.clickTab("Cycle Counts");

		test.siteConfigurationAction.verifyPlaceHolderForCycleCount(getData("Facility.PlaceHolder"));
		test.siteConfigurationAction.clickCycleCountCalendar();
		String date = test.siteConfigurationAction.getCurrentMonth_Year();
		int day = test.siteConfigurationAction.getCurrentDate();
		test.siteConfigurationAction.ClickCurrentDate(date);
		String a = test.siteConfigurationAction.getCurrentMonth_Year();
	}
	
	
	@Test(priority = 2, description = "VPLX:Specific facility Settings:[UI]: To verify that User clicks "
			+ "on add button to add date in the list of disabled dates")
	public void Test02_1055555(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User clicks on add button so date is added in the list of disabled dates");
		
		test.siteConfigurationAction.clickAddButton();
		clickedDate = test.siteConfigurationAction.getCurrentMonth_Year();
		Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate), 
				"ASSERTION FAILED: Date " + clickedDate + " not in list of disabled dates");	
	}
	
	
	@Test(priority = 3, description = "VPLX:Specific facility Settings:[UI]: To verify that "
			+ "added date is displayed in the list of disabled dates under cycle count tab")
	public void Test03_1055530(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:Verify that added date is visible in the list of disabled dates");
		
		clickedDate = test.siteConfigurationAction.getCurrentMonth_Year();
		Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate),
				"ASSERTIOn FAILED: Date " + clickedDate + " not in list of disabled dates");
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific facility Settings:[UI]: To verify that "
			+ "remove button is displayed in front of disabled dates in cycle count tab")
	public void Test04_1055532(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:User verifies the Remove button in front of disabled dates");
		
		test.siteConfigurationAction.verifyRemoveButton();
	}
	
	
	@Test(priority = 5, description = "Test Case 1055533:VPLX:Specific facility Settings: [UI]: "
			+ "To verify that Removed date is not visible in the list of disabled dates in cycle count tab")
	public void Test05_1055533(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:Verify that removed date is not visible in the list of disabled dates");
		
		test.siteConfigurationAction.clickRemoveButton();
		Assert.assertTrue(test.siteConfigurationAction.verifyDisableDate(clickedDate));
	}
	
	
	@Test(priority = 6, description = "Test Case 1055541:VPLX:Specific facility Settings: [UI]: "
			+ "To verify that placeholder value of Disable cycle count is correct")
	public void Test06_1055541(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific facility Settings:[UI]:Verify that placeholder value of Disable cycle count on these dates textbox with VD");
		// test.siteConfigurationAction.clickCycleCountCalendar();

		test.siteConfigurationAction.verifyPlaceHolderForCycleCount(getData("Facility.PlaceHolder"));
	}

}
