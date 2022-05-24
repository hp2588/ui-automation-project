package com.org.tests.astartestsuite;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;
import java.util.ArrayList;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.extentmanagers.ExtentTestManager;

public class Add_Dispense_Unit_Test extends BaseTest {

	String codeValue, descriptionForm, sortOrder, external_System;

	@BeforeClass
	public void Open_Browser_Window() {

	}

	@BeforeTest
	public void Login_Browser() {
		test = new TestSessionInitiator(this.getClass().getSimpleName());
		String app_url = getYamlValue("app_url");
		test.launchApplication(app_url);
		

	}
	
	@Test(priority = 1, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new External system")
	public void Test01_Add_External_System_Test(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View:Verify User is able to add new External system");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameSupportUser").trim(),
				getData("Auth.passwordSupportUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
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
		test.landingPageActions.navigateToMenu("Main Menu");
		test.loginPageAction._logoutApplication(getData("Auth.user").trim(), getData("Auth.button1").trim(), getData("Auth.button2").trim());

	}

	@Test(priority = 2, description = "VPLX: Dispense Unit [UI]:Verify User is able to add new dispense unit")
	public void Test02_Add_DispenseUnit_Test(Method method) {
		ExtentTestManager.startTest(method.getName(),
				"VPLX: Dispense Unit [UI]: Dispense Unit-View:Verify User is able to add new dispense units");
		test.loginPageAction.LoginToTheBDApplication(getData("Auth.userNameAdminUser").trim(),
				getData("Auth.passwordAdminUser").trim(), getData("Auth.ip").trim());
		test.landingPageActions.navigateToMenu("Main Menu");
		Assert.assertTrue(test.landingPageActions.verifyUserIsOnLandingPage("Key Destinations"),
				"[ASSERTION FAILED]: User is not able to Navigate to Landing Page");
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.siteConfigurationAction.selectDropdownDispenseExternal(external_System, "externalSystems");
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		codeValue = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"UI_Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPage(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(codeValue);
	}

}
