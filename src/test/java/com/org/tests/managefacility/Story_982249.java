package com.org.tests.managefacility;

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

public class Story_982249 extends BaseTest {

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
	
	
	@Test(priority = 1, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the Show Inactive toggle is displayed under Distributor Account tab."
			+ "\n&\n"
			+ "VPLX:Specific Facility Settings:[UI] To verify that the Distributor Account headline "
			+ "is displayed under Distributor account tab.")
	public void Test01_1054818_1054893(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:User verifies the Show Inactive toggle under Distributor Account tab.");
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 2);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("timeZoneID", 2);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
		test.siteConfigurationAction.verifyToggleOption("Distributor Accounts");
		test.siteConfigurationAction.verifyToggleOption("Show Inactive");
		Assert.assertTrue(
				test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Show Inactive").contains("false"));
	}
	
	
	@Test(priority = 2, description = "VPLX:Specific Facility Settings:[UI]:To verify that "
			+ "the List of active distributors is displayed under Distributor Accounts tab by default.")
	public void Test02_1054891(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:User verifies the list of combined active & Inactive records under Distributor Accounts tab when Show Inactive toggle is ON.");
		
		test.siteConfigurationAction.clickToggleButton("true", getData("ToggleValue.Carousel"));
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		
		test.siteConfigurationAction.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickToggleButton("true", getData("ToggleValue.Carousel"));
		
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");	
	}
	
	
	@Test(priority = 3, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "Distributor Name is available while adding/editing a facility.")
	public void Test03_1054937(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Distributor Name is available while adding/editing a facility.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Distributor Name"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
	}
	
	
	@Test(priority = 4, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "Enable Auto Receive Non-Controlled column is available while adding/editing a facility.")
	public void Test04_1054943(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Enable Auto Receive Non-Controlled column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Enable Auto Receive Non-Controlled"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveNonControlledFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveNonControlledFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveNonControlledFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveNonControlledFlag"));
	}
	
	
	@Test(priority = 5, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "Enable Auto Receive C-II column is available while adding/editing a facility.")
	public void Test05_1054945(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Enable Auto Receive C-II column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Enable Auto Receive CII"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveControlledTwoFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveControlledTwoFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveControlledTwoFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveControlledTwoFlag"));
	}
	
	
	@Test(priority = 6, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "Enable Auto Receive C-III-V column is available while adding/editing a facility.")
	public void Test06_1054946(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Enable Auto Receive C-III-IV column is available while adding/editing a facility.");
		
		Assert.assertTrue(test.siteConfigurationAction
				.verifyColumnHeaderOnFacilityTransactionPriorities("Enable Auto Receive CIII-V"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveControlledThreeToFiveFlag");
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveControlledThreeToFiveFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("enableAutoReceiveControlledThreeToFiveFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("enableAutoReceiveControlledThreeToFiveFlag"));
	}
	
	
	@Test(priority = 7, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "Account numbers are available while adding/editing a facility.")
	public void Test07_1054955(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Account numbers are available while adding/editing a facility.");
		
		Assert.assertTrue(
				test.siteConfigurationAction.verifyColumnHeaderOnFacilityTransactionPriorities("Account Number"));
	}
	
	
	@Test(priority = 8, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "the List of Distributor accounts is displayed under Distributor Account tab.")
	public void Test08_1054895(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that list of the Distributor accounts are visible under Distributor Account tab.");
		
		test.siteConfigurationAction.checkTransactionPriorityList();
	}
	
	
	@Test(priority = 9, description = "VPLX:Specific Facility Settings: [UI]: To verify that the Active/Inactive"
			+ " check-boxes operation is performed by clicking on check-boxes under Distributor Accounts tab.")
	public void Test09_1054919(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Check that user can Active/Inactive checkboxes by click on checkboxes under Distributor Accounts tab.");
		
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag"); 
		// test.siteConfigurationAction.clickToggleButton("true",getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertFalse(test.siteConfigurationAction.verifyCheckboxIsCheckedTransactionPriorities("activeFlag"));
	}
	
	
	@Test(priority = 10, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "account Number field accepts only numeric digits.")
	public void Test10_1054952(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate that Account Number field accepts only numeric digits.");
		
		Assert.assertTrue(test.siteConfigurationAction.verifyMaxLengthOfAnInputFieldAddFacility());
		test.siteConfigurationAction.clickCheckboxTransactionPriorities("activeFlag");
		Assert.assertTrue(test.siteConfigurationAction.enterOnlyIntegerInAccountNumberFieldAndVerifyLength("vendorAccountCode", 
				DateUtil.generateRandomDigits(21), 20));
	}
	
	
	@Test(priority = 11, description = "VPLX:Specific Facility Settings: [UI]: To verify that "
			+ "POPUP message is displayed for Save button when distributor account is added.")
	public void Test11_1054957(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]:Validate POPUP message for Save button when distributor account is added.");
		test.siteConfigurationAction.clickSaveButton();
		
		//test.siteConfigurationAction.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.EditCarousel"),50,500);
		test.siteConfigurationAction.verifyPageTitleContains("Facility Management");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				getData("Facility.FacilityName") + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueFromDropDownByIndex("pharmacyInformationSystemKey", 2);
		test.siteConfigurationAction.selectValueFromDropDownByIndex("timeZoneID", 2);
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Distributor Accounts");
	}
	
	
	@Test(priority = 12, description = "VPLX:Specific Facility Settings:[UI]: To verify that "
			+ "the List of distributor accounts is displayed correctly after distributor name is searched"
			+ "\n&\n"
			+ "VPLX:Specific Facility Settings: [UI]: To verify that the Message displayed correctly "
			+ "when no distributor account is active & Show Inactive is off.")
	public void Test12_1054914_1054910(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Specific Facility Settings:[UI]: List of distributor accounts displays correctly on searching distributor name.");
		
		test.siteConfigurationAction.clickToggleButton("false", getData("ToggleValue.Carousel"));
		Assert.assertTrue(test.siteConfigurationAction.verifyMessageWhenToggelIsOnOrOff("No distributors have been selected for this facility. Select Show Inactive to see all available distributors."));
		
		test.siteConfigurationAction.clickToggleButton("true", getData("ToggleValue.Carousel"));
		String value = test.siteConfigurationAction.returnTransactionPriorityName();
		test.siteConfigurationAction.enterSearchTermInSearchField(value, "search");
		String value1 = test.siteConfigurationAction.returnTransactionPriorityName();
		Assert.assertEquals(value, value1);
	}
	
	
}
