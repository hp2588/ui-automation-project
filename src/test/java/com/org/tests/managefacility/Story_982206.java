package com.org.tests.managefacility;

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

public class Story_982206 extends BaseTest {

	String facilityName;

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
	
	
	@Test(priority = 1, description = "VPLX: Specific Facility Settings: [UI]: To verify that "
			+ "the All fields available under settings tab are displayed under the Facility settings")
	public void Test01_1047763(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Others fields options are visible under the Facility settings");
		test.landingPageActions.navigateToFeature("Facilities");
		
		facilityName = TestDataPropertyReaderAndWriter.getProperty("FacilityName");
		test.supportDataActions.enterSearchTermInSearchFieldGl(facilityName, "search");
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyOtherfieldsOptionsareVisible();
	}
	
	
	@Test(priority = 2, description = "VPLX: Specific Facility Settings: [UI] To Verify the "
			+ "Others fields Options which are added via Configuration are visible under the Facility settings")
	public void Test02_1047832(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Others fields Options which are added via Configuration are visible under the Facility settings");
		
		test.siteConfigurationAction.verifyCheckboxForTQFields("enablePriceTagging",
				getData("Facilities_OtherFieldsOptions.Opt4"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("processInactiveAsExceptionFlag",
				getData("Facilities_OtherFieldsOptions.Opt5"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("admQuantityRoundingFlag",
				getData("Facilities_OtherFieldsOptions.Opt6"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("returnsOnHoldDefinitionFlag",
				getData("Facilities_OtherFieldsOptions.Opt7"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("smartOrderRoutingFlag",
				getData("Facilities_OtherFieldsOptions.Opt8"));
		test.siteConfigurationAction.verifyCheckboxForTQFields("admIgnoreCriticalLowFlag",
				getData("Facilities_OtherFieldsOptions.Opt11"));
	}
	
	
	@Test(priority = 3, description = "VPLX: Specific Facility Settings: [UI] : To verify that "
			+ "the Editing all the fields available on settings tab via clicking on check boxes."
			+ "\n&\n"
			+ "VPLX:Specific Facility Settings: [UI]: To verify that the User is able to edit "
			+ "Ignore ADC Stock out flag at facility setting tab.")
	public void Test03_1047844_1054610(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings: [UI] Editing of Other fields options via clicking on check boxes");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("enablePriceTagging", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("processInactiveAsExceptionFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("admQuantityRoundingFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("returnsOnHoldDefinitionFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("smartOrderRoutingFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("admIgnoreCriticalLowFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("admIgnoreStockOutFlag", true);
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific Facility Settings: [UI] To verify that the "
			+ "Editing of available fields under settings tab via Unchecking the check boxes.")
	public void Test04_1047846(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings: [UI] Editing of Other fields options via Unchecking the check boxes.");
		
		test.siteConfigurationAction.selectCheckboxItemsTab("enablePriceTagging", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("processInactiveAsExceptionFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("admQuantityRoundingFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("returnsOnHoldDefinitionFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("smartOrderRoutingFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("admIgnoreCriticalLowFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("admIgnoreStockOutFlag", false);
	}
	
	
}

