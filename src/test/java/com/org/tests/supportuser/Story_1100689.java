package com.org.tests.supportuser;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Story_1100689 extends BaseTest {

	
	String[] external_sys_checkboxes_id = { "pisProvidesMedClassFlag", "pisProvidesTherapeuticClassFlag",
			"allowPISItemEditFlag", "editExternalScanCodeLinksFlag", "ignorePISItemDeleteFlag",
			"ignorePISItemUpdateFlag" };
	String externalSystem,external_System,facilityName;
	String app_url;
	
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
	
	@Test(priority = 1, description = "VPLX: [IDM]: SupportUser able to Add External System")
	public void Test01_1111693(Method method) throws IOException {
		test.landingPageActions.navigateToFeature("External Systems");
		test.siteConfigurationAction.clickOnAddButtonToAddExternalSystem();
		// test.siteConfigurationAction.clickOnAddButtonToAddParticular("Add
		// External
		// System");
		test.supportDataActions.selectValueFromDropdownByIndex("externalSystemSystemType", 2);
		// test.siteConfigurationAction.selectAllCheckboxesOfExternalSystems();
		// test.siteConfigurationAction.verifyAllCheckboxesOfExternalSystemsDisabled(external_sys_checkboxes_id);
		test.siteConfigurationAction.verifyDropDownOnAddNewExternalSystem("externalSystemTimeZone");
		test.siteConfigurationAction.verifyDefaultValueInDropDownOnAddNewExternalSystem("externalSystemTimeZone",
				"(UTC) Coordinated Universal Time");
		externalSystem = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", "Ext" + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();

		TestDataPropertyReaderAndWriter.setProperty("ExternalSystemName", externalSystem);

	}
	
	@Test(priority = 2, description = "VPLX: Manage Heathcare System [UI]: External Systems-Edit: user is able to edit External System successfully")
	public void Test2_1049497() throws InterruptedException {
		test.siteConfigurationAction.clickOnEditLinkCorresspondingToExternalSystemName(external_System);
		external_System = test.siteConfigurationAction.EnterRandomValueInInputFieldOnAddNewPrinterPopup(
				"externalSystemName", getData("ExternalSystem.Name") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.verifySuccessMessageOnAddPrinter(getData("SuccessMessages.AddPrinter"));
		test.siteConfigurationAction.verifyNewlyAddedPrinterNameInPrinterList(external_System);

	}
	
	@Test(priority = 3, description = "VPLX:Add Faciity")
	public void Test03_AddFacility_1121628_1121626(Method method) {
		test.landingPageActions.navigateToFeature("Facilities");
		test.siteConfigurationAction.clickOnAddButtonToAddFacility();
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityCode",
				"Code" + System.currentTimeMillis());
		

		Assert.assertEquals(test.siteConfigurationAction.verifyMaxLengthOfAnInputField("facilityCode"), 20,
				"[ASSERTION FAILED]: Max Length for input field facilityCode is not 20");
		
		
		facilityName = test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("facilityName",
				"Fac" + System.currentTimeMillis());
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", "Select");
		test.siteConfigurationAction.selectValueForDropDown("pharmacyInformationSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.siteConfigurationAction.selectValueForDropDown("timeZoneID", getData("Facility.timeZoneId"));
		 test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("rxLicenseID",
				getData("Facility.FacilityCode") + System.currentTimeMillis());
		test.siteConfigurationAction.clickSaveButton();
		test.siteConfigurationAction.clickTab("Contact");
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("vendorContactFaxNumberText",
				getData("Facility.FaxNumber1"));
		test.siteConfigurationAction.EnterValueInInputFieldOnAddNewFacility("postalCode",
				getData("Facility.postalCode"));
		 test.siteConfigurationAction.clickTab("Cycle Counts");
		 test.siteConfigurationAction.selectCheckbox("autoGenCycleCountFlag", true);
		 test.siteConfigurationAction.clickSaveButton();
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
		//test.siteConfigurationAction.verifyNewlyAddedFacilityNameInFacilityMgtList(facilityName);
		TestDataPropertyReaderAndWriter.setProperty("FacilityName", facilityName);
	}

}
