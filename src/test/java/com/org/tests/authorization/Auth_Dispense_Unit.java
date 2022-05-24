package com.org.tests.authorization;

import static com.org.automation.utils.YamlReader.getData;
import static com.org.automation.utils.YamlReader.getYamlValue;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.org.automation.TestSessionInitiator;
import com.org.automation.getpageobjects.BaseTest;
import com.org.automation.utils.TestDataPropertyReaderAndWriter;

public class Auth_Dispense_Unit extends BaseTest {

	String DispenseUnitCode, descriptionForm, sortOrder;
	

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

	@Test(priority = 1, description = "VPLX: Authorization: [UI] -  Support User is able to Add/edit Dispense Units correspondng to all External Systems")
	public void Test01_1153840(Method method) {
		test.landingPageActions.navigateToFeature("Dispense Units");
		test.supportDataActions.clickOnAddButtonToAddNewRecord1("Add Dispense Unit");
		DispenseUnitCode = test.supportDataActions.EnterRandomValueInInputField("dispenseUnitCode",
				"UI_Code" + System.currentTimeMillis());
		descriptionForm = test.supportDataActions.EnterRandomValueInTextAreaField("descriptionText",
				"UI_Description" + System.currentTimeMillis());
		sortOrder = test.supportDataActions.EnterRandomValueInInputField("sortValue", "3");
		test.siteConfigurationAction.selectValueForDropDown("externalSystemKey", TestDataPropertyReaderAndWriter.getProperty("ExternalSystemName").trim());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.SuccessMessage"));
		test.supportDataActions.clickOnEditLinkCorresspondingToAddedRecord(DispenseUnitCode, "Edit Dispense Unit");
		Assert.assertTrue(test.siteConfigurationAction.verifyToggleButtonIsActiveOrNot("Active").contains("true"));
		
		DispenseUnitCode = test.siteConfigurationAction.enterRandomValueInInputField("dispenseUnitCode",
				"Code" + System.currentTimeMillis());
		test.supportDataActions.clickButton("save");
		test.supportDataActions.verifySuccessMessageOnViewPageWithLoader(getData("SuccessMessages.AddHoldReason"));
		test.supportDataActions.verifyNewlyAddedRecordNameInList(DispenseUnitCode);
	}
	
	@Test(priority = 2, description = "VPLX: Authorization: [UI] -  Support User is able to view Dispense Unit correspondng to all External Systems")
	public void Test02_1153841(Method method) {
		test.supportDataActions.verifyNewlyAddedRecordNameInList(DispenseUnitCode);
	}
	
}
