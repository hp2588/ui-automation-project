package com.org.tests.managefacility;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;
import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;
import com.org.extentmanagers.ExtentTestManager;

public class Story_975743 extends BaseTest {

	String facilityCode, facilityName;
	String rxLicenseId, deaLicenseId, faxNumber;
	String[] preferredContactMethodList = { "Select", "Fax", "Phone", "Email" };
	String externalSystem;
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

	
	// TODO - Yugal - Refactoring of TC
	@Test(priority = 1, description = "VPLX:Manage Facilities:[UI] -To verify that Listing view for facilities "
			+ "is having facilityname ,PIS and status columns")
	public void Test01_1044347(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -Listing view for facilities is having facilityname ,PIS and status columns.");
		
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemSystemType", 0);
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				"(UTC) Coordinated Universal Time");
		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "Ext" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		
		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field facilityCode is not 20");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code" + System.currentTimeMillis());
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"Fac" + System.currentTimeMillis());
		
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", 
				TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		rxLicenseId = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		
		test.siteConfigurationAction.clickTab("Contact");
		faxNumber = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		 test.siteConfigurationAction.clickTab("Cycle Counts");
		 test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		 test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Quick Actions"),"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.verifyName_PIS_status(facilityName),
				"Name, PIS and status are not displaying.");
	}
	
	
	@Test(priority = 2, description = "VPLX:Manage Facilities:[UI] -To verify that User is not able to perfrom "
			+ "search on the facility name when search text is entered as a value which does not exist in Database")
	public void Test02_1044310(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is not able to perfrom search on the facility name when search text is entered as a value which does not exist in db");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.searhNonExistedFacility("(&&&&&&&&&&%$$"),
				"Search facility with invalid facility name is not working fine.");
	}
	
	
	@Test(priority = 3, description = "VPLX:Manage Facilities:[UI] -To verify that User is not able to perfrom search on the PIS column when search text is entered as a value which does not exist in db")
	public void Test03_1044312(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is not able to perfrom search on the PIS column when search text is entered as a value which does not exist in db");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.searhNonExistedPIS("(&&&&&&&&&&%$$"),
				"Search facility with invalid PIS is not working fine.");
	} 
	
	
	@Test(priority = 4, description = "VPLX:Manage Facilities:[UI] -To verify that User is able to view toggle and view all active facilities when toggle button is used")
	public void Test04_1044333(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is able to toggle and view all active facilities when toggle button is used.");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.verifyActiveFacilityByDefault(facilityName);
	}
	
	
	@Test(priority = 5, description = "VPLX:Manage Facilities:[UI] -To verify that User is able to view all "
			+ "active and inactive facilities when toggle button is used")
	public void Test05_1044338(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is able to view all active and inactive facilities when toggle button is used");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.ToggleFunction(),
				"User is not able to view active/inactive facility using toggle button.");
	}
	
	
	@Test(priority = 6, description = "Test Case 1044326:VPLX:Manage Facilities:[UI] -To verify that "
			+ "User is able to view all active Facilities by default")
	public void Test06_1044326(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is able to view all active Facilities by default ");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.verifyActiveFacilityByDefault(facilityName),
				"By default facility status is not showing as  Active.");
	}
	
	
	@Test(priority = 7, description = "VPLX:Manage Facilities:[UI] -To verify that User is able to perfrom a contains search on the facility column")
	public void Test07_1044321(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is able to perfrom a contains search on the facility column ");
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.searchByFacilityName(facilityName),
				"User is not able to search the dacility based on text contain.");
	}
	
	
	@Test(priority = 8, description = "VPLX:Manage Facilities:[UI] - To verify that User is able to perfrom a contains search on the PIS column")
	public void Test08_1044322(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] -User is able to perfrom a contains search on the PIS column ");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.searchByPIS(facilityName),
				"Search by PIS  is not working fine.");
	}
	
	
	@Test(priority = 9, description = "VPLX:Manage Facilities:[UI] - To verify that User is able to perform sorting on the facilityname ,PIS and status columns")
	public void Test09_1044341(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"Case VPLX:Manage Facilities:[UI] -User is able to perform sorting on the facilityname ,PIS and status columns");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.sortByFacilityName(), "User is not able to sort facility column");
        Assert.assertTrue(test.siteConfigurationAction.sortByPISName(), "User is not able to sort PIS column");
        Assert.assertTrue(test.siteConfigurationAction.sortByStatus(), "User is not able to sort status column");
	}
	
	
	@Test(priority = 10, description = "VPLX:Manage Facilities:[UI] - User is able to navigate to facility management using keyboard")
	public void Test10_1045173(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX:Manage Facilities:[UI] - User is able to navigate to facility management using keyboard");
		
		test.landingPageActions.navigateToMenu("Main Menu");
		test.landingPageActions.navigateToFeature("Facilities");
		Assert.assertTrue(test.siteConfigurationAction.keyBoardTab(), "User is not able to perform keyboard action on facility screen.");
	}
	
}