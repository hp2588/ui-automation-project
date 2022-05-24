package com.org.tests.siteconfiguration;

import static com.org.automation.utils.YamlReader.getData;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.DateUtil;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975909 extends BaseTest {

	String facilityName;

	@Test(priority = 1, description = "VPLX: Specific Facility settings : [UI] Verify that all schedule days are selected by default")
	public void Test01_1055502(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify that all schedule days are selected by default");
		test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations");
		test.landingPageActions.navigateToFeature("Facilities Setup");

		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey",
				getData("Facility.ExternalSystemName"));
		test.siteConfigurationAction.selectValueForDropDown("timeZoneId", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities Setup");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);

		test.siteConfigurationAction.clickOnEditLinkCorresspondingToFacilityName("abc");

		test.siteConfigurationAction.clickTab("Cycle Counts");
		test.siteConfigurationAction.verifyAllButtonIsSelected();

	}

	@Test(priority = 2, description = "VPLX: Specific Facility settings : [UI] Verify Cycle Count schedule can be set  from 12 am to 12 pm")
	public void Test02_1055640(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify Cycle Count schedule can be set  from 12 am to 12 pm");
		test.siteConfigurationAction.verifyStartTime(getData("Facilities_CycleCount.StartTime"));
		test.siteConfigurationAction.verifyEndTime(getData("Facilities_CycleCount.EndTime"));

	}

	@Test(priority = 3, description = "VPLX: Specific Facility settings : [UI] Verify user can deselect  Cycle Count schedule via clicking on the particular Hour for respective day")
	public void Test03_1055677(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify user can deselect  Cycle Count schedule via clicking on the particular Hour for respective day");
		test.siteConfigurationAction.verifyAllButtonIsSelected();
		test.siteConfigurationAction.getButtonColor(getData("Facilities_CycleCount.Color_enabled"), "Wednesday19_pm");
		test.siteConfigurationAction.clickScheduleButton("Wednesday19_pm");
		test.siteConfigurationAction.getButtonColor(getData("Facilities_CycleCount.Color_disabled"), "Wednesday19_pm");
		test.siteConfigurationAction.verifybuttonisDisabled("Wednesday19_pm");

	}

	@Test(priority = 4, description = "VPLX: Specific Facility settings : [UI] Verify a message for Shift + mouse click is visible under the cycle count tab")
	public void Test04_1055682(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify a message for Shift + mouse click is visible under the cycle count tab");
		test.siteConfigurationAction.verifyCycleCountMessage(getData("Facilities_CycleCount.Header_message"));

	}

	@Test(priority = 5, description = "VPLX: Specific Facility settings : [UI] Verify check box for capture earliest expiration date when counting default state is unchecked")
	public void Test05_1055743(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify check box for capture earliest expiration date when counting default state is unchecked");
		test.siteConfigurationAction.verifyCheckboxFieldOnCycleCount("enableOldestExpirationDateFlag");
	}

	@Test(priority = 6, description = "VPLX: Specific Facility settings : [UI] Verify check box for 'Create cycle count transactions for items with assigned interval' default state is unchecked.")
	public void Test06_1055745(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify check box for 'Create cycle count transactions for items with assigned interval' default state is unchecked.");
		test.siteConfigurationAction.verifyCheckboxFieldOnCycleCount("autoGenCycleCountFlag");
	}

	@Test(priority = 7, description = "VPLX: Specific Facility settings : [UI] Verify user can check the check box for Create cycle count transactions for items with assigned interval")
	public void Test07_1055749(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify user can check the check box for Create cycle count transactions for items with assigned interval");
		test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);

	}

	@Test(priority = 8, description = "VPLX: Specific Facility settings : [UI] Verify user can check the check box for Capture earliest expiration date under the cycle count tab")
	public void Test8_1055750(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify user can check the check box for Capture earliest expiration date under the cycle count tab");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
	}

	@Test(priority = 9, description = "VPLX: Specific Facility settings : [UI] Verify user can check the check box for Capture earliest expiration date under the cycle count tab")
	public void Test9_1055750(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility settings : [UI] Verify user can check the check box for Capture earliest expiration date under the cycle count tab");
		test.siteConfigurationAction.selectCheckboxItemsTab("enableOldestExpirationDateFlag", true);
	}
}