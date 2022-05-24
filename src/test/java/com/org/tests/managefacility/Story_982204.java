package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Story_982204 extends BaseTest {
	String facilityName;
	String external_System;

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
	
	
	@Test(priority = 1, description = "Test Case 1046302:VPLX: Specific Facility Settings : [UI]: "
			+ "To verify that the Transaction Queue options such as Verify Pick/Restock are visible "
			+ "under the Facility settings")
	public void Test01_1046302(Method method) throws Throwable {

		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings : [UI] Verify the Transaction Queue options are visible under the Facility settings");
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
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey",
				external_System);
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		test.siteConfigurationAction.clickSaveButton();
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		test.siteConfigurationAction.clickRecordNameToEdit(facilityName);
		
		test.siteConfigurationAction.clickTab("Settings");
		test.siteConfigurationAction.verifyCheckboxForTQFields("verifyQuantityFlag",
				getData("Facilities_TransactionQueueOptions.Option1"));
	}
	
	
	@Test(priority = 2, description = "Test Case 1046303:VPLX: Specific Facility Settings : [UI]: To verify "
			+ "the options visible under the Facility settings")
	public void Test02_1046303(Method method) throws Throwable {
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
	
	
	@Test(priority = 3, description = "VPLX: Specific Facility Settings: [UI]: To verify that "
			+ "the Flags on settings Tab are selected by clicking on check boxes.")
	public void Test03_1046304(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings: [UI] Editing of transactions Queue options via clicking on check boxes");
		test.siteConfigurationAction.selectCheckboxItemsTab("verifyQuantityFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("prepositionISAFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("requestRestockDestinationFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("requireHoldReasonFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("requestRestockLotInfoFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("printLabelOnQuantityChangeFlag", true);
		test.siteConfigurationAction.selectCheckboxItemsTab("admIgnoreStockOutFlag", true);
	}
	
	
	@Test(priority = 4, description = "VPLX: Specific Facility Settings: [UI]: To verify that the Flags on settings tab are edited by deselecting the check boxes.")
	public void Test04_1046305(Method method) throws Throwable {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Specific Facility Settings: [UI] Editing of transactions Queue options via Unchecking the check boxes");
		test.siteConfigurationAction.selectCheckboxItemsTab("requireHoldReasonFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("requestRestockLotInfoFlag", false);
		test.siteConfigurationAction.selectCheckboxItemsTab("printLabelOnQuantityChangeFlag", false);
		test.siteConfigurationAction.checkboxIsSelectedUsingJavascript("requestRestockDestinationFlag");

	}
	
}
